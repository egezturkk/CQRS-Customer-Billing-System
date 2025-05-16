package com.turkcell.customer.query.service;

import com.turkcell.customer.query.dto.CustomerInvoiceDTO;
import java.util.List;

public interface CustomerInvoiceQueryService {
    List<CustomerInvoiceDTO> getInvoicesByCustomerId(Long customerId);
    List<CustomerInvoiceDTO> getAllInvoices();
}

