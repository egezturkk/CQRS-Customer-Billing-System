package com.turkcell.customer.command.service;

import com.turkcell.customer.command.dto.CustomerInvoiceDTO;

public interface CustomerInvoiceService {
    CustomerInvoiceDTO saveInvoice(CustomerInvoiceDTO invoiceDTO);
}
