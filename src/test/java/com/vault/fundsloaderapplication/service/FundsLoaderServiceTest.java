package com.vault.fundsloaderapplication.service;

import com.vault.fundsloaderapplication.repository.FundsLoaderOperationRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FundsLoaderServiceTest {

    @Mock
    private FundsLoaderOperationRepository repository;

    @InjectMocks
    private FundsLoaderService service;



}