package com.project.account.service;

import com.project.account.dto.CustomerDto;
import com.project.account.dto.converter.CustomerDtoConverter;
import com.project.account.exception.CustomerNotFoundException;
import com.project.account.model.Customer;
import com.project.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    protected Customer findById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer could not find id:" + id));
    }

    public CustomerDto getById(String id) {
        return customerDtoConverter.modelToCustomerDto(findById(id));
    }
}
