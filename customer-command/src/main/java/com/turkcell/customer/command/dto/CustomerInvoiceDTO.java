package com.turkcell.customer.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInvoiceDTO {
    private Long id;
    private Long customerId;
    private Double amount;
    private String invoiceDate;
}
