package com.download.sample.axonbank.axonbank;

import org.axonframework.config.EventHandlingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxonbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonbankApplication.class, args);
	}

    @Autowired
    public void configure(EventHandlingConfiguration config) {
        config.registerTrackingProcessor("TransactionHistory");
    }

}
