package com.blockchainapp.Blockchain.helpers;

public class HTML {

    public static String htmlEmailTemplate(String token, String code){

        String url = "http:127.0.0.1:8082/verify?token=" + token + "&code=" +code;

        String emailTemplate = "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <!-- <link rel='stylesheet' href='css/email.css'> -->\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "\n" +
                "*{\n" +
                "    box-sizing: border-box;\n" +
                "    font-family: Comfortaa;\n" +
                "}\n" +
                "\n" +
                "body{\n" +
                "    height: 100vh;\n" +
                "    background-color: rgb(212, 222, 230);\n" +
                "    display: flex;\n" +
                "    align-items: center;\n" +
                "    justify-content: center;\n" +
                "}\n" +
                ".wrapper {\n" +
                "    width: 550px;\n" +
                "    height: auto;\n" +
                "    padding: 15px;\n" +
                "    background-color: white;\n" +
                "    border-radius: 7px;\n" +
                "}\n" +
                ".email-msg-header{\n" +
                "    text-align: center;\n" +
                "}\n" +
                ".email-msg-header span {\n" +
                "    font-size:35px;\n" +
                "    color: blue;\n" +
                "    \n" +
                "}\n" +
                ".welcome-text{\n" +
                "    text-align: center;\n" +
                "}\n" +
                "\n" +
                ".verify-account-btn{\n" +
                "    padding: 15px;\n" +
                "    background-color: rgb(99, 99, 202);\n" +
                "    text-decoration: none;\n" +
                "    color: white;\n" +
                "    border-radius: 5px;\n" +
                "}\n" +
                "\n" +
                ".copy-right{\n" +
                "    text-align: center;\n" +
                "    padding: 15px;\n" +
                "    color: gray;\n" +
                "    font-size: 14px;\n" +
                "    margin: 15px 0px;\n" +
                "}\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div class='wrapper'>\n" +
                "        <h2 class='email-msg-header'>\n" +
                "            Welcome and thank you for choosing \n" +
                "            <br>\n" +
                "            <span>Emeka Exchange</span>\n" +
                "        </h2>\n" +
                "        <hr>\n" +
                "\n" +
                "        <p class='welcome-text'>\n" +
                "            Your Account have been created successfully, please click below link to confirm your account\n" +
                "        </p>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "\n" +
                "        <center>\n" +
                "            <a href='"+ url +"' class='verify-account-btn' role='button'>Verify Account</a>\n" +
                "        </center>\n" +
                "\n" +
                "        <div class='copy-right'>\n" +
                "            &copy; Copyright 2022. All Rights Reserved.\n" +
                "        </div>\n" +
                "\n" +
                "    </div>\n" +
                "\n" +
                "    \n" +
                "</body>\n" +
                "</html>";

        return emailTemplate;
    }
}
