package com.vault.fundsloaderapplication.repository;

import com.vault.fundsloaderapplication.model.LoadRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadRequestRepository extends JpaRepository<LoadRequest, Long> {
}
