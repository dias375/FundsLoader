package com.vault.fundsloaderapplication.repository;

import com.vault.fundsloaderapplication.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoadRequestRepository extends JpaRepository<LoadRequest, Long> {

    @Query(
            value = "select * from LOAD_REQUEST where LOAD_REQUEST.CUSTOMER_ID = :customerId",
            nativeQuery = true
    )
    List<LoadRequest> findAllLoadRequestsFromCustomerId(@Param("customerId") long customerId);


    @Query(
            value = "select LOAD_REQUEST.ID, LOAD_REQUEST.CUSTOMER_ID, LOAD_REQUEST.LOAD_AMOUNT, LOAD_REQUEST.TIME from LOAD_REQUEST, LOAD_RESPONSE where LOAD_REQUEST.ID = LOAD_RESPONSE.ID and LOAD_RESPONSE.ACCEPTED = TRUE and LOAD_REQUEST.CUSTOMER_ID = :customerId",
            nativeQuery = true
    )
    List<LoadRequest> dailyOperationsFromCustomer(@Param("customerId") long customerId);


}
