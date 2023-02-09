package com.blockchainapp.Blockchain.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Users {

    @Id
    private int user_id;
    @NotEmpty(message = "The First name field cannot be empty")
    @Size(min = 3, message = "First name must be more than 3 characters")
    private String first_name;
    @NotEmpty(message = "The First name field cannot be empty")
    private String last_name;
    @Email
    @NotEmpty(message = "Please enter a valid email address")
    private String email;
    @NotNull(message = "Enter your password")
    private String password;
    @Transient
    private String confirm_password;
    private String token;
    private String code;
    private int verified;
    private LocalDate verified_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
