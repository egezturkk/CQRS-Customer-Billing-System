package com.turkcell.customer.query.controller;

import com.turkcell.customer.query.dto.CustomerInvoiceDTO;
import com.turkcell.customer.query.service.CustomerInvoiceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class CustomerInvoiceQueryController {

    private final CustomerInvoiceQueryService customerInvoiceQueryService;

    @GetMapping("/{customerId}")
    public List<CustomerInvoiceDTO> getInvoicesByCustomerId(@PathVariable Long customerId) {
        return customerInvoiceQueryService.getInvoicesByCustomerId(customerId);
    }

    @GetMapping
    public List<CustomerInvoiceDTO> getAllInvoices() {
        return customerInvoiceQueryService.getAllInvoices();
    }
}
