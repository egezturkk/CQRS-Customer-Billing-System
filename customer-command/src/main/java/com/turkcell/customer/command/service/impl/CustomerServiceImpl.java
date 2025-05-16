package com.turkcell.customer.command.service.impl;

import com.turkcell.customer.command.dto.CustomerDTO;
import com.turkcell.customer.command.entity.Customer;
import com.turkcell.customer.command.mapper.CustomerMapper;
import com.turkcell.customer.command.repository.CustomerRepository;
import com.turkcell.customer.command.service.CustomerService;
import com.turkcell.customer.command.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;
    private final KafkaProducerService kafkaProducerService;

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toCustomer(customerDTO);
        Customer savedCustomer = repository.save(customer);
        kafkaProducerService.sendMessage("customer-topic", customerMapper.toCustomerDTO(savedCustomer));
        return customerMapper.toCustomerDTO(savedCustomer);
    }
}
