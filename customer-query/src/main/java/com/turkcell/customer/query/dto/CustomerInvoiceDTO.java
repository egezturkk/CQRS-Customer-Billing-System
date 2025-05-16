package com.turkcell.customer.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInvoiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long customerId;
    private Double amount;
    private String invoiceDate;
}
