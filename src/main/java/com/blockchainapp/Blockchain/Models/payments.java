package com.blockchainapp.Blockchain.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "payments_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class payments {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long payment_id;

    @Column(name = "account_id")
    private int account_id;

    @Column(name = "beneficiary")
    private String beneficiary;

    @Column(name = "beneficiary_acc_no")
    private String beneficiary_acc_no;

    @Column(name = "amount")
    private double amount;

    @Column(name = "reference_no")
    private String reference_no;

    @Column(name = "status")
    private String status;

    @Column(name = "reason_code")
    private String reason_code;

    @Column(name = "created_at")
    private String created_at;
}
