package com.demoCompany.payment.payment.dispatcher.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.demoCompany.payment")
public class PaymentDispatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentDispatcherApplication.class, args);
    }
}
