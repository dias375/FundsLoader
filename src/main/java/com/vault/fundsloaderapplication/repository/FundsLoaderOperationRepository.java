package com.vault.fundsloaderapplication.repository;

        import com.vault.fundsloaderapplication.model.FundsLoaderOperation;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.UUID;

public interface FundsLoaderOperationRepository extends JpaRepository<FundsLoaderOperation, UUID> {


}
