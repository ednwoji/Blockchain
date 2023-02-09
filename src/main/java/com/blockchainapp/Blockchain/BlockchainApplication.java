package com.blockchainapp.Blockchain;

import com.blockchainapp.Blockchain.Models.EthToDollar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class BlockchainApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlockchainApplication.class, args);
	}

	@Scheduled(fixedDelay = 2000)
	public void scheduledRun(){

		System.out.println("Current Scheduled time" +new Date());
	}

}
