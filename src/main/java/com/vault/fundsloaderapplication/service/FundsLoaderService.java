package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class FundsLoaderService {

    int DAILY_OPERATIONS_LIMIT = 3;
    BigDecimal DAILY_AMOUNT_LIMIT = BigDecimal.valueOf(5000);
    BigDecimal WEEKLY_AMOUNT_LIMIT = BigDecimal.valueOf(20000);

    private final FundsLoaderOperationRepository fundsLoaderOperationRepository;

    public FundsLoaderService(FundsLoaderOperationRepository fundsLoaderOperationRepository) {
        this.fundsLoaderOperationRepository = fundsLoaderOperationRepository;
    }

    public LoadResponse fundsLoadRequest(LoadRequest loadRequest){

        if(isOperationIdAlreadyUsed(loadRequest)){
            log.warn("OPERATION ID DUPLICATED: id=" + loadRequest.getId());
            return null;
        }

        FundsLoaderOperation fundsLoaderOperation = new FundsLoaderOperation();
        LoadResponse loadResponse = new LoadResponse(loadRequest.getId(), loadRequest.getCustomerId(), isOperationAccepted(loadRequest));
        fundsLoaderOperation.setVariables(loadRequest, loadResponse);
        fundsLoaderOperationRepository.save(fundsLoaderOperation);
        log.info("LOAD OPERATION: " + loadResponse);
        return loadResponse;
    }

    private boolean isOperationAccepted(LoadRequest loadRequest){
        if(isAboveDailyOperationsLimit(loadRequest)){return false;}
        if(isAboveWeeklyOperationsLimit(loadRequest)){return false;}
        return true;
    }

    public boolean validateAmount(RawLoadRequest rawLoadRequest){
        return rawLoadRequest.getLoadAmount().charAt(0) == '$';
    }

    private boolean isOperationIdAlreadyUsed(LoadRequest loadRequest){
        List <FundsLoaderOperation> operations = fundsLoaderOperationRepository.operationsById(loadRequest.getId());
        return !operations.isEmpty();
    }

    private boolean isAboveDailyOperationsLimit(LoadRequest loadRequest){
        List<FundsLoaderOperation> dailyOperationsFromCustomer = fundsLoaderOperationRepository.dailyOperationsFromCustomer(loadRequest.getCustomerId(), loadRequest.getTime().toLocalDate());

        if(dailyOperationsFromCustomer.size() >= DAILY_OPERATIONS_LIMIT){
            log.info("DAILY_OPERATIONS_LIMIT REACHED: id=" + loadRequest.getId());
            return true;
        }

        BigDecimal totalAmountWithFutureOperation = getTotalAmountWithFutureOperation(loadRequest, dailyOperationsFromCustomer);
        if(totalAmountWithFutureOperation.compareTo(DAILY_AMOUNT_LIMIT) > 0) {
            log.info("DAILY_AMOUNT_LIMIT REACHED: id=" + loadRequest.getId());
            return true;
        }
        return false;
    }

    private boolean isAboveWeeklyOperationsLimit(LoadRequest loadRequest){
        List<LocalDate> daysOfWeek = datesListOfCalendarWeek(loadRequest.getTime().toLocalDate());
        List<FundsLoaderOperation> weeklyOperationsFromCustomer = fundsLoaderOperationRepository.weeklyOperationsFromCustomer(loadRequest.getCustomerId(), daysOfWeek.get(0), daysOfWeek.get(6));

        BigDecimal totalAmountWithFutureOperation = getTotalAmountWithFutureOperation(loadRequest, weeklyOperationsFromCustomer);
        if(totalAmountWithFutureOperation.compareTo(WEEKLY_AMOUNT_LIMIT) > 0) {
            log.info("WEEKLY_AMOUNT_LIMIT REACHED: id=" + loadRequest.getId());
            return true;
        }

        return false;
    }

    private BigDecimal getTotalAmountWithFutureOperation(LoadRequest newOperation, List<FundsLoaderOperation> oldOperations) {
        BigDecimal totalOperationsFromCustomerAmount = newOperation.getLoadAmount();
        for(FundsLoaderOperation op : oldOperations){
            totalOperationsFromCustomerAmount = totalOperationsFromCustomerAmount.add(op.getLoadAmount());
        }
        return totalOperationsFromCustomerAmount;
    }

    private List<LocalDate> datesListOfCalendarWeek(LocalDate date) {
        LocalDate start = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return IntStream.range(0, 7).mapToObj(start::plusDays).collect(toList());
    }
}