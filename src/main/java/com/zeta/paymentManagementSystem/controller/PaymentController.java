package com.zeta.paymentManagementSystem.controller;

import com.zeta.paymentManagementSystem.dto.PaymentRequestDTO;
import com.zeta.paymentManagementSystem.model.Payment;
import com.zeta.paymentManagementSystem.model.User;
import com.zeta.paymentManagementSystem.service.PaymentService;
import com.zeta.paymentManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequestDTO paymentDTO) {
        if (paymentDTO.getUserId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID is required.");
        }

        User user = userService.getUserById(paymentDTO.getUserId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setCategory(paymentDTO.getCategory());
        payment.setStatus(paymentDTO.getStatus());
        payment.setDate(paymentDTO.getDate());
        payment.setUser(user);

        paymentService.addPayment(0, payment); // ID is auto-generated
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment created successfully.");
    }


    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id) {
        Payment payment = paymentService.getPayment(id);
        if (payment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
        }
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable int id, @RequestBody PaymentRequestDTO paymentDTO) {
        Payment existing = paymentService.getPayment(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
        }

        existing.setAmount(paymentDTO.getAmount());
        existing.setPaymentType(paymentDTO.getPaymentType());
        existing.setCategory(paymentDTO.getCategory());
        existing.setStatus(paymentDTO.getStatus());
        existing.setDate(paymentDTO.getDate());

        paymentService.updatePayment(id, existing);
        return ResponseEntity.ok("Payment updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable int id) {
        Payment existing = paymentService.getPayment(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
        }
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment deleted successfully.");
    }
}
