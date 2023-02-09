package com.blockchainapp.Blockchain.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Query {
    public int amount;
    public String from;

    @JsonProperty("to")
    public String myto;
}
