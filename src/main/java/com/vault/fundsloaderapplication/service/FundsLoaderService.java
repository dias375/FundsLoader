package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

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

    public List<FundsLoaderOperation> getFundsLoaderOperations(){return fundsLoaderOperationRepository.findAll();}

    public List<FundsLoaderOperation> getOperationsByCustomerId(long customerId){
        return fundsLoaderOperationRepository.findAllLoadRequestsFromCustomerId(customerId);
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

    private boolean isOperationIdAlreadyUsed(LoadRequest loadRequest){
        List <FundsLoaderOperation> operations = fundsLoaderOperationRepository.operationsById(loadRequest.getId());
        return !operations.isEmpty();
    }

    private boolean isAboveDailyOperationsLimit(LoadRequest loadRequest){
        //TODO add day check
        List<FundsLoaderOperation> dailyOperationsFromCustomer = fundsLoaderOperationRepository.dailyOperationsFromCustomer(loadRequest.getCustomerId());

        if(dailyOperationsFromCustomer.size() >= DAILY_OPERATIONS_LIMIT){
            log.info("DAILY_OPERATIONS_LIMIT REACHED: id=" + loadRequest.getId());
            return true;
        }


        BigDecimal dailyOperationsFromCustomerAmount = loadRequest.getLoadAmount();
        for(FundsLoaderOperation op : dailyOperationsFromCustomer){
            dailyOperationsFromCustomerAmount = dailyOperationsFromCustomerAmount.add(op.getLoadAmount());
        }

        if(dailyOperationsFromCustomerAmount.compareTo(DAILY_AMOUNT_LIMIT) > 0) {
            log.info("DAILY_AMOUNT_LIMIT REACHED: id=" + loadRequest.getId());
            return true;
        }

        return false;
    }

    private boolean isAboveWeeklyOperationsLimit(LoadRequest loadRequest){
        //TODO implement weekly check
        return false;
    }

}