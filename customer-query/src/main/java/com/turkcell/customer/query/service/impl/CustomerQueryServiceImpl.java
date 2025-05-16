package com.turkcell.customer.query.service.impl;

import com.turkcell.customer.query.dto.CustomerDTO;
import com.turkcell.customer.query.service.CustomerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final RedisTemplate<String, CustomerDTO> redisTemplate;
    private static final String REDIS_PREFIX = "Customer::";

    @Override
    public CustomerDTO getCustomerById(Long id) {
        String key = REDIS_PREFIX + id;
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        Set<String> keys = redisTemplate.keys(REDIS_PREFIX + "*");
        List<CustomerDTO> customers = new ArrayList<>();
        if (keys != null) {
            for (String key : keys) {
                CustomerDTO customer = redisTemplate.opsForValue().get(key);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        }
        return customers;
    }
}

