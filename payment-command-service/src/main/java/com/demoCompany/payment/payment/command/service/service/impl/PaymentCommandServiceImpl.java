package com.demoCompany.payment.payment.command.service.service.impl;

import com.demoCompany.payment.payment.command.service.dto.PaymentCommandApiRequest;
import com.demoCompany.payment.payment.command.service.dto.PaymentCommandApiResponse;
import com.demoCompany.payment.payment.command.service.model.Payments;
import com.demoCompany.payment.payment.command.service.repository.PaymentCommandServiceRepository;
import com.demoCompany.payment.payment.command.service.service.PaymentCommandService;
import com.demoCompany.payment.payment.command.service.transformer.PaymentCommandTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class PaymentCommandServiceImpl implements PaymentCommandService {


    private final PaymentCommandServiceRepository paymentCommandServiceRepository;
    private final PaymentCommandTransformer paymentCommandTransformer;

    public PaymentCommandServiceImpl(PaymentCommandServiceRepository paymentCommandServiceRepository,
                                     PaymentCommandTransformer paymentCommandTransformer) {
        this.paymentCommandServiceRepository = paymentCommandServiceRepository;
        this.paymentCommandTransformer = paymentCommandTransformer;
    }

    @Override
    public PaymentCommandApiResponse process(PaymentCommandApiRequest request) {

        var response = new PaymentCommandApiResponse();
        String paymentId = request.getPaymentId();

        log.info("Request arrived for payment with id {} and payload: {}", paymentId, request);
        boolean saved = paymentCommandServiceRepository.save(paymentCommandTransformer.apiRequestToPayments(request));
        buildResponse(response, paymentId, saved);
        return response;
    }

    private static void buildResponse(PaymentCommandApiResponse response, String paymentId, boolean saved) {
        if (saved) {
            response.buildSuccess(paymentId);
        } else {
            response.buildFailure(paymentId);
        }
    }
}
