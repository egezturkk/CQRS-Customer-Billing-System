package com.turkcell.customer.query.service;

import com.turkcell.customer.query.dto.CustomerDTO;
import java.util.List;

public interface CustomerQueryService {
    CustomerDTO getCustomerById(Long id);
    List<CustomerDTO> getAllCustomers();
}
