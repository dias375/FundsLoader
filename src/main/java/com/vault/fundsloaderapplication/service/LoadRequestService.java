package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.LoadRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadRequestService {

    @Autowired
    private LoadRequestRepository loadRequestRepository;

    public List<LoadRequest> getLoadRequests(){
        return loadRequestRepository.findAll();
    }

    public List<LoadRequest> getAllLoadRequestsByCustomerId(Customer customer){
        return loadRequestRepository.findAllLoadRequestsFromCustomerId(customer.getCustomer_id());
    }

    public void saveLoadRequests(LoadRequest loadRequest){

        //TODO
        //#1 Max amount load per day: $5000
        //#2 Max amount load per week: $20000
        //#3 Max load operations per day: 3

        loadRequestRepository.save(loadRequest);
    }



}
