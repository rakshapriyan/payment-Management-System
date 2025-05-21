package com.zeta.paymentManagementSystem.service.impl;

import com.zeta.paymentManagementSystem.model.Payment;
import com.zeta.paymentManagementSystem.model.User;
import com.zeta.paymentManagementSystem.repository.PaymentRepository;
import com.zeta.paymentManagementSystem.repository.UserRepository;
import com.zeta.paymentManagementSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addPayment(int paymentId, Payment payment) {
        if (payment.getUser() != null) {
            User user = userRepository.findById(payment.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            payment.setUser(user);
        }
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(int paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public void updatePayment(int paymentId, Payment payment) {
        Payment existing = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setId(paymentId);
        payment.setUser(existing.getUser());
        paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(int paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }
}
