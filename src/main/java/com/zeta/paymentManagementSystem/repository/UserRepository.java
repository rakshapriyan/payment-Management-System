package com.zeta.paymentManagementSystem.repository;

import com.zeta.paymentManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
