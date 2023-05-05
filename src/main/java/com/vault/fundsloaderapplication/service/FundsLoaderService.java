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

    public void saveLoadRequest(LoadRequest loadRequest){
        loadRequestRepository.save(loadRequest);
        LoadResponse loadResponse = new LoadResponse(loadRequest.getId(), loadRequest.getCustomer_id(), isOperationAccepted(loadRequest));
        loadResponseRepository.save(loadResponse);
    }

    private boolean isOperationAccepted(LoadRequest loadRequest){
        if(isAboveDailyOperationsLimit(loadRequest)){return false;}
        if(isAboveDailyAmountLimit(loadRequest)){return false;}
        if(isAboveWeeklyAmountLimit(loadRequest)){return false;}
        return true;
    }

    private boolean isAboveDailyOperationsLimit(LoadRequest loadRequest){
        //will have to join LOAD_REQUEST from TODAY and check if they are accepted:TRUE in LOAD_RESPONSE
        return false;
    }

    private boolean isAboveDailyAmountLimit(LoadRequest loadRequest){
        return false;
    }

    private boolean isAboveWeeklyAmountLimit(LoadRequest loadRequest){
        return false;
    }



}