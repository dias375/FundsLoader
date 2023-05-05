package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.LoadRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadRequestService {

    int DAILY_OPERATIONS_LIMIT = 3;
    int DAILY_AMOUNT_LIMIT = 5000;
    int WEEKLY_AMOUNT_LIMIT = 20000;

    @Autowired
    private LoadRequestRepository loadRequestRepository;

    public List<LoadRequest> getLoadRequests(){
        return loadRequestRepository.findAll();
    }

    public List<LoadRequest> getAllLoadRequestsByCustomerId(Customer customer){
        return loadRequestRepository.findAllLoadRequestsFromCustomerId(customer.getCustomer_id());
    }

    public void saveLoadRequests(LoadRequest loadRequest){
        if(isOperationAboveLimits(loadRequest)){return;}
        loadRequestRepository.save(loadRequest);
    }

    private boolean isOperationAboveLimits(LoadRequest loadRequest){
        if(isAboveDailyOperationsLimit(loadRequest)){return true;}
        if(isAboveDailyAmountLimit(loadRequest)){return true;}
        if(isAboveWeeklyAmountLimit(loadRequest)){return true;}
        return false;
    }

    private boolean isAboveDailyOperationsLimit(LoadRequest loadRequest){
        return false;
    }

    private boolean isAboveDailyAmountLimit(LoadRequest loadRequest){
        return false;
    }

    private boolean isAboveWeeklyAmountLimit(LoadRequest loadRequest){
        return false;
    }



}
