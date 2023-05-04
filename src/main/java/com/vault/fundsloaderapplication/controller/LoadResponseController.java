package com.vault.fundsloaderapplication.controller;

import com.vault.fundsloaderapplication.model.LoadResponse;
import com.vault.fundsloaderapplication.service.LoadResponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/load/response")
public class LoadResponseController {

    private final LoadResponseService loadResponseService;

    public LoadResponseController(LoadResponseService loadResponse) {
        loadResponseService = loadResponse;
    }

    @GetMapping
    public List<LoadResponse> getLoadRequests(){
        return loadResponseService.getLoadResponses();
    }

}