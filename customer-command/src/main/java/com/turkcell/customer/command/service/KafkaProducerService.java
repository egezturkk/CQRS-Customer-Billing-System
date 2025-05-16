package com.turkcell.customer.command.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.customer.command.dto.CustomerInvoiceDTO;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String topic, Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendInvoiceMessage(CustomerInvoiceDTO invoiceDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(invoiceDTO);
            kafkaTemplate.send("customer-invoice", jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
