package com.zeta.paymentManagementSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeta.paymentManagementSystem.constants.Category;
import com.zeta.paymentManagementSystem.constants.PaymentType;
import com.zeta.paymentManagementSystem.constants.Status;
import com.zeta.paymentManagementSystem.model.Payment;
import com.zeta.paymentManagementSystem.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

@WebMvcTest(PaymentController.class)
@ContextConfiguration(classes = {
        PaymentController.class,
        PaymentControllerTest.TestConfig.class
})
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    static class TestConfig {
        @Bean
        public PaymentService paymentService() {
            return mock(PaymentService.class);
        }
    }

    @BeforeEach
    void setup() {
        reset(paymentService);
    }

    private Payment createSamplePayment(int id) {
        return new Payment(
                id,
                2500.00,
                PaymentType.INCOMING,
                Category.SALARY,
                Status.PENDING,
                Date.valueOf(LocalDate.now())
        );
    }

    @Test
    void testCreatePayment() throws Exception {
        Payment payment = createSamplePayment(1);
        doNothing().when(paymentService).addPayment(payment.getId(), payment);

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Payment created successfully."));
    }

    @Test
    void testGetAllPayments() throws Exception {
        List<Payment> payments = Arrays.asList(createSamplePayment(1), createSamplePayment(2));
        when(paymentService.getAllPayments()).thenReturn(payments);

        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].paymentType", is("INCOMING")))
                .andExpect(jsonPath("$[0].category", is("SALARY")));
    }

    @Test
    void testGetPaymentById_Found() throws Exception {
        Payment payment = createSamplePayment(1);
        when(paymentService.getPayment(1)).thenReturn(payment);

        mockMvc.perform(get("/payments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentType", is("INCOMING")))
                .andExpect(jsonPath("$.status", is("PENDING")));
    }

    @Test
    void testGetPaymentById_NotFound() throws Exception {
        when(paymentService.getPayment(999)).thenReturn(null);

        mockMvc.perform(get("/payments/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Payment not found with ID: 999"));
    }

    @Test
    void testUpdatePayment_Found() throws Exception {
        Payment updated = createSamplePayment(1);
        when(paymentService.getPayment(1)).thenReturn(updated);
        doNothing().when(paymentService).updatePayment(1, updated);

        mockMvc.perform(put("/payments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment updated successfully."));
    }

    @Test
    void testDeletePayment_Found() throws Exception {
        Payment existing = createSamplePayment(1);
        when(paymentService.getPayment(1)).thenReturn(existing);
        doNothing().when(paymentService).deletePayment(1);

        mockMvc.perform(delete("/payments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment deleted successfully."));
    }

    @Test
    void testDeletePayment_NotFound() throws Exception {
        when(paymentService.getPayment(404)).thenReturn(null);

        mockMvc.perform(delete("/payments/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Payment not found with ID: 404"));
    }
}
