package com.vault.fundsloaderapplication.controller;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.service.FundsLoaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/v1")
public class FundsLoaderController {

    private final FundsLoaderService fundsLoaderService;

    public FundsLoaderController(FundsLoaderService fundsLoaderService) {
        this.fundsLoaderService = fundsLoaderService;
    }
    @PostMapping("/load-funds")
    public ResponseEntity<LoadResponse> postLoadRequest(@RequestBody RawLoadRequest rawloadRequest) throws ParseException {
        if(!fundsLoaderService.validateJson(rawloadRequest)){
            return ResponseEntity.badRequest().body(null);
        }
        LoadRequest loadRequest = LoadRequest.from(rawloadRequest);
        LoadResponse loadResponse= fundsLoaderService.loadFunds(loadRequest);
        return ResponseEntity.ok(loadResponse);
    }
}