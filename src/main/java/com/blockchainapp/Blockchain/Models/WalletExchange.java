package com.blockchainapp.Blockchain.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletExchange {

        public String adapter;
        public String from;
        public String fromNetwork;

        @JsonProperty("to")
        public String myto;
        public String toNetwork;
        public int amountFrom;
        public double amountTo;
        public double minAmount;
        public Object maxAmount;
        public String quotaId;
        public Date validUntil;

}
