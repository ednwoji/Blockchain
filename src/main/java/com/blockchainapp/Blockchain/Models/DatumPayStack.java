package com.blockchainapp.Blockchain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatumPayStack {

    public int id;
    public String name;
    public String slug;
    public String code;
    public String longcode;
    public String gateway;
    public boolean pay_with_bank;
    public boolean active;
    public String country;
    public String currency;
    public String type;
    public boolean is_deleted;
    public String createdAt;
    public String updatedAt;
}
