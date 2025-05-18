package com.zeta.paymentManagementSystem.model;

import com.zeta.paymentManagementSystem.constants.Category;
import com.zeta.paymentManagementSystem.constants.PaymentType;
import com.zeta.paymentManagementSystem.constants.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "_payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    private int id;
    private double amount;
    private PaymentType paymentType;
    private Category category;
    private Status status;
    private Date date;

}
