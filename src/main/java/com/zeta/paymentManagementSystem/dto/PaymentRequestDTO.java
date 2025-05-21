package com.zeta.paymentManagementSystem.dto;


import com.zeta.paymentManagementSystem.constants.Category;
import com.zeta.paymentManagementSystem.constants.PaymentType;
import com.zeta.paymentManagementSystem.constants.Status;
import lombok.Data;

import java.sql.Date;

@Data
public class PaymentRequestDTO {
    private double amount;
    private PaymentType paymentType;
    private Category category;
    private Status status;
    private Date date;
    private int userId;
}
