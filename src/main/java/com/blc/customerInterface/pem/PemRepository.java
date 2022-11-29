package com.blc.customerInterface.pem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PemRepository extends JpaRepository<Pem, UUID> {
}
