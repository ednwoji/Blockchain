package com.blockchainapp.Blockchain.Repository;

import com.blockchainapp.Blockchain.Models.payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<payments, Long> {
}
