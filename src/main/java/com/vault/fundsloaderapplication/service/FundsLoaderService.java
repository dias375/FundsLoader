package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundsLoaderService {

    int DAILY_OPERATIONS_LIMIT = 3;
    int DAILY_AMOUNT_LIMIT = 5000;
    int WEEKLY_AMOUNT_LIMIT = 20000;


    @Autowired
    private FundsLoaderOperationRepository fundsLoaderOperationRepository;

    public List<FundsLoaderOperation> getFundsLoaderOperations(){return fundsLoaderOperationRepository.findAll();}

    public List<FundsLoaderOperation> getAllLoadRequestsByCustomerId(Customer customer){
        return fundsLoaderOperationRepository.findAllLoadRequestsFromCustomerId(customer.getCustomer_id());
    }

    public LoadResponse fundsLoadRequest(LoadRequest loadRequest){

        if(isOperationIdAlreadyUsed(loadRequest)){
            System.out.println("DEBUG: OPERATION ID DUPLICATED");
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
            System.out.println("DEBUG: DAILY_OPERATIONS_LIMIT REACHED");
            return true;
        }

        int dailyOperationsFromCustomerAmount = Integer.valueOf(loadRequest.getLoad_amount());
        for(FundsLoaderOperation op : dailyOperationsFromCustomer){
            dailyOperationsFromCustomerAmount += Integer.valueOf(op.getLoad_amount());
        }

        if(dailyOperationsFromCustomerAmount > DAILY_AMOUNT_LIMIT) {
            System.out.println("DEBUG: DAILY_AMOUNT_LIMIT REACHED");
            return true;
        }

        return false;
    }

    private boolean isAboveWeeklyOperationsLimit(LoadRequest loadRequest){
        //TODO implement weekly check
        return false;
    }



}