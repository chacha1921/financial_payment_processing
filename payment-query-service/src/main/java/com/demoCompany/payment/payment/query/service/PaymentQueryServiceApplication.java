package com.demoCompany.payment.payment.query.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.demoCompany.payment")
public class PaymentQueryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentQueryServiceApplication.class, args);
    }
}
