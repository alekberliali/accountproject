package com.project.account.dto.converter;

import com.project.account.dto.AccountDto;
import com.project.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto modelToDto(Account account) {
        return new AccountDto(account.getId(),
                account.getBalance(),
                account.getCreationDate(),
                customerDtoConverter.modelToDto(account.getCustomer()),
                account.getTransaction().stream().map(transactionDtoConverter::modelToDto).collect(Collectors.toSet()));


    }
}
