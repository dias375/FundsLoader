package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Slf4j
@Service
public class FundsLoaderService {

    int DAILY_OPERATIONS_LIMIT = 3;
    BigDecimal DAILY_AMOUNT_LIMIT = BigDecimal.valueOf(5000);;
    BigDecimal WEEKLY_AMOUNT_LIMIT = BigDecimal.valueOf(20000);


    @Autowired
    private FundsLoaderOperationRepository fundsLoaderOperationRepository;

    public List<FundsLoaderOperation> getFundsLoaderOperations(){return fundsLoaderOperationRepository.findAll();}

    public List<FundsLoaderOperation> getOperationsByCustomerId(long customerId){
        return fundsLoaderOperationRepository.findAllLoadRequestsFromCustomerId(customerId);
    }

    public LoadResponse fundsLoadRequest(LoadRequest loadRequest){

        if(isOperationIdAlreadyUsed(loadRequest)){
            log.info("DEBUG: OPERATION ID DUPLICATED");
            return null;
        }

        FundsLoaderOperation fundsLoaderOperation = new FundsLoaderOperation();
        LoadResponse loadResponse = new LoadResponse(loadRequest.getId(), loadRequest.getCustomer_id(), isOperationAccepted(loadRequest));
        fundsLoaderOperation.setVariables(loadRequest, loadResponse);
        fundsLoaderOperationRepository.save(fundsLoaderOperation);
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
        List<FundsLoaderOperation> dailyOperationsFromCustomer = fundsLoaderOperationRepository.dailyOperationsFromCustomer(loadRequest.getCustomer_id());

        if(dailyOperationsFromCustomer.size() >= DAILY_OPERATIONS_LIMIT){
            log.info("DEBUG: DAILY_OPERATIONS_LIMIT REACHED");
            return true;
        }


        BigDecimal dailyOperationsFromCustomerAmount = loadRequest.getLoad_amount();
        for(FundsLoaderOperation op : dailyOperationsFromCustomer){
            dailyOperationsFromCustomerAmount = dailyOperationsFromCustomerAmount.add(op.getLoad_amount());
        }

        if(dailyOperationsFromCustomerAmount.compareTo(DAILY_AMOUNT_LIMIT) > 0) {
            log.info("DEBUG: DAILY_AMOUNT_LIMIT REACHED");
            return true;
        }

        return false;
    }

    private boolean isAboveWeeklyOperationsLimit(LoadRequest loadRequest){
        //TODO implement weekly check
        return false;
    }

    public LoadRequest convertRawLoadRequest(RawLoadRequest rawLoadRequest) throws ParseException {
        return new LoadRequest(rawLoadRequest.getId(), rawLoadRequest.getCustomer_id(), rawLoadRequest.getLoadAmountInBigDecimal(), rawLoadRequest.getTime());
    }



}