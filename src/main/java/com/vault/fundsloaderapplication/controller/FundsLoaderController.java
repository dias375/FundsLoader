package com.vault.fundsloaderapplication.controller;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.service.FundsLoaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fundsloader")
public class FundsLoaderController {

    private final FundsLoaderService fundsLoaderService;

    public FundsLoaderController(FundsLoaderService fundsLoaderService) {
        this.fundsLoaderService = fundsLoaderService;
    }

    @GetMapping
    public List<LoadRequest> getLoadRequests(){
        return fundsLoaderService.getLoadRequests();
    }

    @GetMapping("/customer")
    public List<LoadRequest> getAllLoadRequestsByCustomerId(@RequestBody Customer customer){
        return fundsLoaderService.getAllLoadRequestsByCustomerId(customer);
    }

    @PostMapping
    public void postLoadRequest(@RequestBody LoadRequest loadRequest){
        fundsLoaderService.saveLoadRequest(loadRequest);
    }
}