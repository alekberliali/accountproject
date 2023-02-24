package com.project.account.service;

import com.project.account.dto.CustomerDto;
import com.project.account.dto.converter.CustomerDtoConverter;
import com.project.account.exception.CustomerNotFoundException;
import com.project.account.model.Customer;
import com.project.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository, converter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Customer result = customerService.findById("id");
        assertEquals(result, customer);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findById("id"));
    }

    @Test
    public void testGetCustomerId_whenCustomerIdExists_shouldReturnCustomerDto() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id", "name", "surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.modelToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getById("id");
        assertEquals(result, customerDto);
    }

    @Test
    public void testGetCustomerId_whenCustomerIdDoesNotExists_shouldReturnCustomerDto() {

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,()->customerService.getById("id"));
        Mockito.verifyNoInteractions(converter);
    }
}