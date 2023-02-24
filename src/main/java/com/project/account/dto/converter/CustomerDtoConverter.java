package com.project.account.dto.converter;

import com.project.account.dto.AccountCustomerDto;
import com.project.account.dto.CustomerDto;
import com.project.account.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    private final CustomerAccountDtoConverter converter;

    public CustomerDtoConverter(CustomerAccountDtoConverter converter) {
        this.converter = converter;
    }

    public AccountCustomerDto modelToDto(Customer customer) {
        if (customer == null) {
            return new AccountCustomerDto("", "", "");
        }
        return new AccountCustomerDto((customer.getId()), customer.getName(), customer.getSurname());
    }

    public CustomerDto modelToCustomerDto(Customer customer) {
        return new CustomerDto(customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getAccounts().stream().map(converter::modelToCustomerAccountDto).collect(Collectors.toSet())
                );
    }
}
