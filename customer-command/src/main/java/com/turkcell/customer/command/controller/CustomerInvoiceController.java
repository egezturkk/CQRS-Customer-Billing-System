package com.turkcell.customer.command.controller;

import com.turkcell.customer.command.dto.CustomerInvoiceDTO;
import com.turkcell.customer.command.service.CustomerInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class CustomerInvoiceController {

    private final CustomerInvoiceService customerInvoiceService;

    @PostMapping
    public ResponseEntity<CustomerInvoiceDTO> createInvoice(@RequestBody CustomerInvoiceDTO invoiceDTO) {
        CustomerInvoiceDTO createdInvoice = customerInvoiceService.saveInvoice(invoiceDTO);
        return ResponseEntity.ok(createdInvoice);
    }
}
