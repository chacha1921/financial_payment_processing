package com.demoCompany.payment.payment.command.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.demoCompany.payment")
public class PaymentCommandServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentCommandServiceApplication.class, args);
    }
}
