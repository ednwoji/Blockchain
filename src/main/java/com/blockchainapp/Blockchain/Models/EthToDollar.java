package com.blockchainapp.Blockchain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EthToDollar {

    public String USD;
    public String NGN;
    public BigDecimal BTC;
    public BigDecimal ETH;
}
