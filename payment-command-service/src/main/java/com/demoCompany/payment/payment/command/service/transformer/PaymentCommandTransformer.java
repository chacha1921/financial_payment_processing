package com.demoCompany.payment.payment.command.service.transformer;

import com.demoCompany.payment.payment.command.service.dto.PaymentCommandApiRequest;
import com.demoCompany.payment.payment.command.service.model.PaymentStatus;
import com.demoCompany.payment.payment.command.service.model.Payments;
import com.demoCompany.payment.payment.command.service.repository.model.PaymentsJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentCommandTransformer {

    public PaymentsJpaEntity paymentToPaymentEntity(Payments payment) {
        return PaymentsJpaEntity.builder()
                .paymentId(payment.getPaymentId())
                .paymentStatus(payment.getPaymentStatus().name())
                .build();

    }

    public Payments paymentEntityToPayment(PaymentsJpaEntity payment) {
        return Payments.builder()
                .paymentId(payment.getPaymentId())
                .paymentStatus(PaymentStatus.valueOf(payment.getPaymentStatus()))
                .build();

    }

    public Payments apiRequestToPayments(PaymentCommandApiRequest request) {
        return Payments.builder()
                .paymentProviderId(request.getProviderId())
                .paymentId(request.getPaymentId())
                .paymentStatus(PaymentStatus.CREATE)
                .build();

    }
}
