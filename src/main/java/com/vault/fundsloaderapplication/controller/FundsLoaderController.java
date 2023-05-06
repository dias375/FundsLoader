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
    @PostMapping
    public LoadResponse postLoadRequest(@RequestBody LoadRequest loadRequest){
        return fundsLoaderService.saveLoadRequest(loadRequest);
    }

    //DEBUG -> TODO Turn into private
    @GetMapping("/request")
    public List<LoadRequest> getLoadRequests(){
        return fundsLoaderService.getLoadRequests();
    }

    @GetMapping("/response")
    public List<LoadResponse> getLoadResponses(){
        return fundsLoaderService.getLoadResponses()
                ;}

    @GetMapping("/request/customer")
    public List<LoadRequest> getAllLoadRequestsByCustomerId(@RequestBody Customer customer){
        return fundsLoaderService.getAllLoadRequestsByCustomerId(customer);
    }
}