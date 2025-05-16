package com.turkcell.customer.command.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.turkcell.customer.command.dto.CustomerDTO;
import com.turkcell.customer.command.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
