package com.vault.fundsloaderapplication.controller;

import com.vault.fundsloaderapplication.model.LoadRequest;
import com.vault.fundsloaderapplication.repository.LoadRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/load")
public class LoadRequestController {

    private final LoadRequestRepository loadRequestRepository;

    public LoadRequestController(LoadRequestRepository loadRequestRepository) {
        this.loadRequestRepository = loadRequestRepository;
    }

    @GetMapping
    public List<LoadRequest> getLoadRequests(){
        return loadRequestRepository.findAll();
    }

    @PostMapping
    public void postLoadRequests(@RequestBody LoadRequest loadRequest){
        loadRequestRepository.save(loadRequest);
    }
}
