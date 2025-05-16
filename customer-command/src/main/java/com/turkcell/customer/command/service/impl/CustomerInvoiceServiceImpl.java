package com.turkcell.customer.command.service.impl;

import com.turkcell.customer.command.dto.CustomerInvoiceDTO;
import com.turkcell.customer.command.entity.CustomerInvoice;
import com.turkcell.customer.command.mapper.CustomerInvoiceMapper;
import com.turkcell.customer.command.repository.CustomerInvoiceRepository;
import com.turkcell.customer.command.repository.CustomerRepository;
import com.turkcell.customer.command.service.CustomerInvoiceService;
import com.turkcell.customer.command.service.KafkaProducerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {

    private final CustomerInvoiceRepository customerInvoiceRepository;
    private final CustomerRepository customerRepository;
    private final CustomerInvoiceMapper customerInvoiceMapper;
    private final KafkaProducerService kafkaProducerService;

    public CustomerInvoiceServiceImpl(CustomerInvoiceRepository customerInvoiceRepository,
                                      CustomerRepository customerRepository,
                                      CustomerInvoiceMapper customerInvoiceMapper,
                                      KafkaProducerService kafkaProducerService) {
        this.customerInvoiceRepository = customerInvoiceRepository;
        this.customerRepository = customerRepository;
        this.customerInvoiceMapper = customerInvoiceMapper;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public CustomerInvoiceDTO saveInvoice(CustomerInvoiceDTO customerInvoiceDTO) {
        CustomerInvoice customerInvoice = customerInvoiceMapper.toEntity(customerInvoiceDTO);

        customerInvoice.setCustomer(customerRepository.findById(customerInvoiceDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID")));

        customerInvoice = customerInvoiceRepository.save(customerInvoice);

        CustomerInvoiceDTO savedInvoiceDTO = customerInvoiceMapper.toDTO(customerInvoice);
        kafkaProducerService.sendInvoiceMessage(savedInvoiceDTO);

        return savedInvoiceDTO;
    }
}
