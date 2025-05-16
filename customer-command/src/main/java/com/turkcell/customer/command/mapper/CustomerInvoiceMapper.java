package com.turkcell.customer.command.mapper;

import com.turkcell.customer.command.dto.CustomerInvoiceDTO;
import com.turkcell.customer.command.entity.CustomerInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerInvoiceMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(target = "invoiceDate", source = "invoiceDate")
    CustomerInvoiceDTO toDTO(CustomerInvoice customerInvoice);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(target = "invoiceDate", source = "invoiceDate")
    CustomerInvoice toEntity(CustomerInvoiceDTO customerInvoiceDTO);
}
