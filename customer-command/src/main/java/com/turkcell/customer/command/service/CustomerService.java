package com.turkcell.customer.command.service;

import com.turkcell.customer.command.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);
}
