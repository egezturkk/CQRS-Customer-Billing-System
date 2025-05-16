package com.turkcell.customer.query.service.impl;

import com.turkcell.customer.query.dto.CustomerInvoiceDTO;
import com.turkcell.customer.query.service.CustomerInvoiceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerInvoiceQueryServiceImpl implements CustomerInvoiceQueryService {

    private final RedisTemplate<String, CustomerInvoiceDTO> redisTemplateInvoice;

    @Override
    public List<CustomerInvoiceDTO> getInvoicesByCustomerId(Long customerId) {
        String key = "Invoice::" + customerId;
        List<CustomerInvoiceDTO> invoices = new ArrayList<>();
        Map<Object, Object> invoiceEntries = redisTemplateInvoice.opsForHash().entries(key);
        for (Map.Entry<Object, Object> entry : invoiceEntries.entrySet()) {
            invoices.add((CustomerInvoiceDTO) entry.getValue());
        }
        return invoices;
    }

    @Override
    public List<CustomerInvoiceDTO> getAllInvoices() {
        List<CustomerInvoiceDTO> allInvoices = new ArrayList<>();
        Set<String> keys = redisTemplateInvoice.keys("Invoice::*");

        if (keys != null) {
            for (String key : keys) {
                Map<Object, Object> invoiceEntries = redisTemplateInvoice.opsForHash().entries(key);
                for (Map.Entry<Object, Object> entry : invoiceEntries.entrySet()) {
                    allInvoices.add((CustomerInvoiceDTO) entry.getValue());
                }
            }
        }
        return allInvoices;
    }
}

