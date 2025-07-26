package com.demoCompany.payment.payment.command.service.service;

import com.demoCompany.payment.payment.command.service.dto.PaymentCommandApiRequest;
import com.demoCompany.payment.payment.command.service.dto.PaymentCommandApiResponse;

public interface PaymentCommandService {

    PaymentCommandApiResponse process(PaymentCommandApiRequest request);

}
