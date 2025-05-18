package com.zeta.paymentManagementSystem.service.impl;

import com.zeta.paymentManagementSystem.model.Payment;
import com.zeta.paymentManagementSystem.repository.PaymentRepository;
import com.zeta.paymentManagementSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public void addPayment(int paymentId, Payment payment) {
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
        Payment existingPayment = paymentRepository.findById(paymentId).orElse(null);
        if (existingPayment != null) {
            existingPayment.setAmount(payment.getAmount());
            existingPayment.setDate(payment.getDate());
            existingPayment.setStatus(payment.getStatus());
            paymentRepository.save(existingPayment);
        }

    }

    @Override
    public Payment getPayment(int paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }
}
