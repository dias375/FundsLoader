package com.vault.fundsloaderapplication.repository;

        import com.vault.fundsloaderapplication.model.FundsLoaderOperation;
        import com.vault.fundsloaderapplication.model.LoadRequest;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import java.util.List;
        import java.util.UUID;

public interface FundsLoaderOperationRepository extends JpaRepository<FundsLoaderOperation, UUID> {

        @Query(
                value = "select * from OPERATIONS where OPERATIONS.CUSTOMER_ID = :customerId",
                nativeQuery = true
        )
        List<FundsLoaderOperation> findAllLoadRequestsFromCustomerId(@Param("customerId") long customerId);

        @Query(
                value = "select * from OPERATIONS where OPERATIONS.ACCEPTED = TRUE and OPERATIONS.CUSTOMER_ID = :customerId",
                nativeQuery = true
        )
        List<FundsLoaderOperation> dailyOperationsFromCustomer(@Param("customerId") long customerId);

        /*
        @Query(
                value = "select * from OPERATIONS where OPERATIONS.ID = :newId",
                nativeQuery = true
        )
        List<FundsLoaderOperation> operationsById(@Param("new_id") long newId);
         */
}
