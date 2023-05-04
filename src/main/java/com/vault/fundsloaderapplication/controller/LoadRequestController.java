package com.vault.fundsloaderapplication.controller;

import com.vault.fundsloaderapplication.model.LoadRequest;
import com.vault.fundsloaderapplication.service.LoadRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/load")
public class LoadRequestController {

    private final LoadRequestService loadRequestService;

    public LoadRequestController(LoadRequestService loadRequestService) {
        this.loadRequestService = loadRequestService;
    }

    @GetMapping
    public List<LoadRequest> getLoadRequests(){
        return loadRequestService.getLoadRequests();
    }

    @PostMapping
    public void postLoadRequest(@RequestBody LoadRequest loadRequest){
        loadRequestService.saveLoadRequests(loadRequest);
    }
}
