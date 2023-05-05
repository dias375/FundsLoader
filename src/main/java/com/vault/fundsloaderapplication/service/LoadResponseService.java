package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
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

    public void saveLoadResponse(LoadResponse loadResponse){
        loadResponseRepository.save(loadResponse);
    }

    public void saveLoadResponse(LoadRequest loadRequest, boolean accepted){
        LoadResponse loadResponse = new LoadResponse(loadRequest.getId(), loadRequest.getCustomer_id(), accepted);
        loadResponseRepository.save(loadResponse);
    }
}