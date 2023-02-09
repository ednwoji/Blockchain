package com.blockchainapp.Blockchain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPayStack {
    public String account_number;
    public String account_name;
    public int bank_id;
}
