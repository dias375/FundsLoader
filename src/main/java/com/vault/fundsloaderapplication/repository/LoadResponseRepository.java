package com.vault.fundsloaderapplication.repository;

import com.vault.fundsloaderapplication.model.LoadResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoadResponseRepository extends JpaRepository<LoadResponse, Long> {


}