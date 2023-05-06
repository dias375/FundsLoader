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
    private LoadRequestRepository loadRequestRepository;
    @Autowired
    private LoadResponseRepository loadResponseRepository;

    public List<LoadRequest> getLoadRequests(){return loadRequestRepository.findAll();}

    public List<LoadResponse> getLoadResponses(){return loadResponseRepository.findAll();}

    public List<LoadRequest> getAllLoadRequestsByCustomerId(Customer customer){
        return loadRequestRepository.findAllLoadRequestsFromCustomerId(customer.getCustomer_id());
    }

    public LoadResponse saveLoadRequest(LoadRequest loadRequest){

        //TODO check if operation id already exists, if yes, ignore

        loadRequestRepository.save(loadRequest);
        LoadResponse loadResponse = new LoadResponse(loadRequest.getId(), loadRequest.getCustomer_id(), isOperationAccepted(loadRequest));
        loadResponseRepository.save(loadResponse);
        return loadResponse;
    }

    private boolean isOperationAccepted(LoadRequest loadRequest){
        if(isAboveDailyOperationsLimit(loadRequest)){
            return false;
        }
        if(isAboveWeeklyOperationsLimit(loadRequest)){
            return false;
        }
        return true;
    }

    private boolean isAboveDailyOperationsLimit(LoadRequest loadRequest){
        //will have to join LOAD_REQUEST from TODAY and check if they are accepted:TRUE in LOAD_RESPONSE
        List<LoadRequest> dailyOperationsFromCustomer = loadRequestRepository.dailyOperationsFromCustomer(loadRequest.getCustomer_id());

        if(dailyOperationsFromCustomer.size() >= DAILY_OPERATIONS_LIMIT){
            System.out.println("DEBUG: DAILY_OPERATIONS_LIMIT REACHED");
            return true;
        }

        int dailyOperationsFromCustomerAmount = Integer.valueOf(loadRequest.getLoad_amount());
        for(LoadRequest l : dailyOperationsFromCustomer){
            dailyOperationsFromCustomerAmount += Integer.valueOf(l.getLoad_amount());
        }

        if(dailyOperationsFromCustomerAmount > DAILY_AMOUNT_LIMIT) {
            System.out.println("DEBUG: DAILY_AMOUNT_LIMIT REACHED");
            return true;
        }

        return false;
    }

    private boolean isAboveWeeklyOperationsLimit(LoadRequest loadRequest){
        return false;
    }



}