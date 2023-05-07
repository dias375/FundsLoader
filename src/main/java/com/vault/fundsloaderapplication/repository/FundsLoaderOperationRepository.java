package com.vault.fundsloaderapplication.repository;

        import com.vault.fundsloaderapplication.model.Operation;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import java.time.LocalDate;
        import java.util.List;
        import java.util.UUID;

public interface FundsLoaderOperationRepository extends JpaRepository<Operation, UUID> {

        @Query(
                value = "select * from OPERATIONS where OPERATIONS.ACCEPTED = TRUE and OPERATIONS.CUSTOMER_ID = :customerId and OPERATIONS.TIME = :date",
                nativeQuery = true
        )
        List<Operation> dailyOperationsFromCustomer(@Param("customerId") long customerId, @Param("date") LocalDate date);

        @Query(
                value = "select * from OPERATIONS where OPERATIONS.ACCEPTED and OPERATIONS.TIME between :start and :end = TRUE and OPERATIONS.CUSTOMER_ID = :customerId",
                nativeQuery = true
        )
        List<Operation> weeklyOperationsFromCustomer(@Param("customerId") long customerId, @Param("start") LocalDate startDate, @Param("end") LocalDate endDate);


        @Query(
                value = "select * from OPERATIONS where OPERATIONS.ID = :id",
                nativeQuery = true
        )
        List<Operation> operationsById(@Param("id") long id);

}
