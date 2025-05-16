package com.turkcell.customer.command.controller;

import com.turkcell.customer.command.dto.CustomerDTO;
import com.turkcell.customer.command.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        var customer = service.save(customerDTO);
        return ResponseEntity.ok(customer);
    }
}
