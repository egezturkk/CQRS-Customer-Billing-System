package com.turkcell.customer.command.repository;

import com.turkcell.customer.command.entity.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice, Long> {
}
