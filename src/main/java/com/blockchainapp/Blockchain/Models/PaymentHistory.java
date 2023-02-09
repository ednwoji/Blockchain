package com.blockchainapp.Blockchain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "v_payments_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory {

    @Id
    @Column(name = "payment_id")
    private int payment_id;

    @Column(name = "account_id")
    private int account_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "beneficiary")
    private String beneficiary;

    @Column(name = "beneficiary_acc_no")
    private String beneficiary_acc_no;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private String status;

    @Column(name = "reference_no")
    private String reference_no;


    @Column(name = "reason_code")
    private String reason_code;

    @Column(name = "created_at")
    private String created_at;

}
