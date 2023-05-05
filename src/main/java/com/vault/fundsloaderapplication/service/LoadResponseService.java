package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.LoadResponse;
import com.vault.fundsloaderapplication.repository.LoadResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadResponseService {

    @Autowired
    private LoadResponseRepository loadResponseRepository;

    public List<LoadResponse> getLoadResponses(){
        return loadResponseRepository.findAll();
    }

    public void saveLoadResponses(LoadResponse loadResponse){
        loadResponseRepository.save(loadResponse);
    }
/*
    public long checkAmmountByCustomer(long customerId){
        List <LoadResponse> responses = loadResponseRepository.findByCustomerId(customerId);
        return responses.get(0).getCustomer_id();
    }
*/
}