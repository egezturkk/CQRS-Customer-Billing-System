package com.turkcell.customer.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInvoice {

    private Long id;
    private Double amount;
    private String invoiceDate;
    private Customer customer;
}
