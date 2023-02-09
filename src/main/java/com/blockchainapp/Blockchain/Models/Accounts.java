package com.blockchainapp.Blockchain.Models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Accounts {

    @Id
    private int account_id;
    private int user_id;
    @Column(unique = true)
    private String account_number;
    private String account_name;
    private BigDecimal balance;
    private String account_type;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
