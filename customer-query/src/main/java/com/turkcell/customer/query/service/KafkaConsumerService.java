package com.turkcell.customer.query.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.customer.query.dto.CustomerDTO;
import com.turkcell.customer.query.dto.CustomerInvoiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final RedisTemplate<String, CustomerDTO> redisTemplateCustomer;
    private final RedisTemplate<String, CustomerInvoiceDTO> invoiceRedisTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "customer-topic", groupId = "customer-query-group")
    public void consumeCustomerMessage(String message) {
        try {
            CustomerDTO customerDTO = objectMapper.readValue(message, CustomerDTO.class);
            String key = "Customer::" + customerDTO.getId();
            redisTemplateCustomer.opsForValue().set(key, customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "customer-invoice", groupId = "customer-query-group")
    public void consumeInvoiceMessage(String message) {
        try {
            CustomerInvoiceDTO customerInvoiceDTO = objectMapper.readValue(message, CustomerInvoiceDTO.class);

            System.out.println("Received invoice with date: " + customerInvoiceDTO.getInvoiceDate());

            if (customerInvoiceDTO.getInvoiceDate() != null) {
                String key = "Invoice::" + customerInvoiceDTO.getCustomerId();
                invoiceRedisTemplate.opsForHash().put(key, customerInvoiceDTO.getId().toString(), customerInvoiceDTO);
            } else {
                System.err.println("InvoiceDate is null for invoice ID: " + customerInvoiceDTO.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

