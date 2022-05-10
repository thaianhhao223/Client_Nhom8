package com.iuh.clientnhom8.request.bill;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SolvePaymentBillRequest {
    private String billId;
    private String paymentType;
    private Boolean isSolvePayment;
}
