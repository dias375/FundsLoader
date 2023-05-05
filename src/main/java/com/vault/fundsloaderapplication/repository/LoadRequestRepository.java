package com.vault.fundsloaderapplication.repository;

import com.vault.fundsloaderapplication.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoadRequestRepository extends JpaRepository<LoadRequest, Long> {

    @Query(value = "select * from LOAD_REQUEST lr where lr.CUSTOMER_ID = :customerId", nativeQuery = true)
    List<LoadRequest> findAllLoadRequestsFromCustomerId(@Param("customerId") long customerId);
}
