package com.project.account.dto.converter;

import com.project.account.dto.CustomerAccountDto;
import com.project.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {
    private final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public CustomerAccountDto modelToCustomerAccountDto(Account account) {
        return new CustomerAccountDto(account.getId(),
                account.getBalance(),
                account.getTransaction().stream().map(transactionDtoConverter::modelToDto).collect(Collectors.toSet()),
                account.getCreationDate());
    }
}
