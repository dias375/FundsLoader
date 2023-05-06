package com.vault.fundsloaderapplication.controller;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.service.FundsLoaderService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/fundsloader")
public class FundsLoaderController {

    private final FundsLoaderService fundsLoaderService;

    public FundsLoaderController(FundsLoaderService fundsLoaderService) {
        this.fundsLoaderService = fundsLoaderService;
    }
    @PostMapping
    public LoadResponse postLoadRequest(@RequestBody RawLoadRequest rawloadRequest) throws ParseException {
        LoadRequest loadRequest = fundsLoaderService.convertRawLoadRequest(rawloadRequest);
        return fundsLoaderService.fundsLoadRequest(loadRequest);
    }

    //DEBUG -> TODO Turn into private
    @GetMapping("/operations")
    public List<FundsLoaderOperation> getLoadRequests(){
        return fundsLoaderService.getFundsLoaderOperations();
    }

    @GetMapping("/customer/{customerId}")
    public List<FundsLoaderOperation> getOperationsByCustomerId(@PathVariable long customerId){
        return fundsLoaderService.getOperationsByCustomerId(customerId);
    }
}