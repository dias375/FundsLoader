package com.vault.fundsloaderapplication.controller;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.service.LoadRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/load/request")
public class LoadRequestController {

    private final LoadRequestService loadRequestService;

    public LoadRequestController(LoadRequestService loadRequestService) {
        this.loadRequestService = loadRequestService;
    }

    @GetMapping
    public List<LoadRequest> getLoadRequests(){
        return loadRequestService.getLoadRequests();
    }

    @GetMapping("/customer")
    public List<LoadRequest> getAllLoadRequestsByCustomerId(@RequestBody Customer customer){
        return loadRequestService.getAllLoadRequestsByCustomerId(customer);
    }

    @PostMapping
    public void postLoadRequest(@RequestBody LoadRequest loadRequest){
        loadRequestService.saveLoadRequest(loadRequest);
    }
}
