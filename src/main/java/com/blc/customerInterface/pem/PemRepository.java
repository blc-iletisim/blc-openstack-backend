package com.blc.customerInterface.pem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PemRepository extends JpaRepository<Pem, UUID> {
    Optional<Pem> findByName(String name);
}
