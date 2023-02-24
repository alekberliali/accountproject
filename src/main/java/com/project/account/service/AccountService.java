package com.project.account.service;

import com.project.account.dto.AccountDto;
import com.project.account.dto.CreateAccountRequest;
import com.project.account.dto.converter.AccountDtoConverter;
import com.project.account.model.Account;
import com.project.account.model.Customer;
import com.project.account.model.Transaction;
import com.project.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, TransactionService transactionService, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findById(createAccountRequest.getCustomerId());
        Account account = new Account(customer, createAccountRequest.getInitialCredit(), LocalDateTime.now());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = transactionService.initialMoney(account, createAccountRequest.getInitialCredit());
            account.getTransaction().add(transaction);
        }
        return converter.modelToDto(accountRepository.save(account));
    }
}
