package com.blockchainapp.Blockchain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootAccountValidationPayStack {
    public boolean status;
    public String message;
    public DataPayStack data;
}
