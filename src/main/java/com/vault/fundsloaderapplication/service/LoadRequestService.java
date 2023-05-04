package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.LoadRequest;
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

    public void saveLoadRequests(LoadRequest loadRequest){
        loadRequestRepository.save(loadRequest);
    }

}
