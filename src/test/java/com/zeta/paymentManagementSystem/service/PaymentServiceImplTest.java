package com.zeta.paymentManagementSystem.service;

import com.zeta.paymentManagementSystem.constants.Status;
import com.zeta.paymentManagementSystem.model.Payment;
import com.zeta.paymentManagementSystem.repository.PaymentRepository;
import com.zeta.paymentManagementSystem.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Payment samplePayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        samplePayment = new Payment();
        samplePayment.setId(1);
        samplePayment.setAmount(100.0);
        samplePayment.setDate(Date.valueOf("2024-05-01"));
        samplePayment.setStatus(Status.PENDING);
    }

    @Test
    void testAddPayment() {
        when(paymentRepository.save(samplePayment)).thenReturn(samplePayment);
        paymentService.addPayment(1, samplePayment);
        verify(paymentRepository, times(1)).save(samplePayment);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> paymentList = Collections.singletonList(samplePayment);
        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(1, result.size());
        assertEquals(samplePayment, result.get(0));
    }

    @Test
    void testDeletePayment() {
        int paymentId = 1;
        doNothing().when(paymentRepository).deleteById(paymentId);
        paymentService.deletePayment(paymentId);
        verify(paymentRepository, times(1)).deleteById(paymentId);
    }

    @Test
    void testGetPayment_Exists() {
        when(paymentRepository.findById(1)).thenReturn(Optional.of(samplePayment));

        Payment result = paymentService.getPayment(1);

        assertNotNull(result);
        assertEquals(samplePayment, result);
    }

    @Test
    void testGetPayment_NotExists() {
        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        Payment result = paymentService.getPayment(1);

        assertNull(result);
    }
}