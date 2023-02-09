package com.blockchainapp.Blockchain.helpers;

import java.util.UUID;

public class Token {

    public static String generateToken(){
        String token = UUID.randomUUID().toString();
        return token;
    }

    public static String generateReference(){
        String referenceId = UUID.randomUUID().toString();
        return referenceId;
    }
}
