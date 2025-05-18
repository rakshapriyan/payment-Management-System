package com.zeta.paymentManagementSystem.service;

import com.zeta.paymentManagementSystem.model.Payment;

import java.util.List;

public interface PaymentService {
    void addPayment(int paymentId, Payment payment);

    List<Payment> getAllPayments();

    void deletePayment(int paymentId);

    void updatePayment(int paymentId, Payment payment);

    Payment getPayment(int paymentId);
}
