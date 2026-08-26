package com.mccr.rutalibre.domain.repository;

import com.mccr.rutalibre.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
