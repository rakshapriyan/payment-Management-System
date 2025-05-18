package com.zeta.paymentManagementSystem.repository;

import com.zeta.paymentManagementSystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
