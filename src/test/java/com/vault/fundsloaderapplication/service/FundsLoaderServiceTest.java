package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.FundsLoaderOperationRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FundsLoaderServiceTest {

    LocalDateTime TIME = LocalDateTime.now();
    LoadRequest REQUEST_ON_DAILY_AMOUNT_LIMIT = new LoadRequest(5, 11, BigDecimal.valueOf(5000), TIME);
    LoadRequest REQUEST_ABOVE_DAILY_AMOUNT_LIMIT = new LoadRequest(6, 11, BigDecimal.valueOf(10000), TIME);

    @Mock
    private FundsLoaderOperationRepository repository;

    @InjectMocks
    private FundsLoaderService service;

    @BeforeAll
    public static void setUp(){

    }

    @Test
    void loadFunds_singleOperationShouldBeAccepted(){
        LoadResponse loadResponse = service.loadFunds(REQUEST_ON_DAILY_AMOUNT_LIMIT);
        assertTrue(loadResponse.isAccepted());
    }

    @Test
    void loadFunds_operationAboveLimitShouldNotBeAccepted(){
        LoadResponse loadResponse = service.loadFunds(REQUEST_ABOVE_DAILY_AMOUNT_LIMIT);
        assertFalse(loadResponse.isAccepted());
    }
}