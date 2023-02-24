package com.project.account.service;

import com.project.account.model.Account;
import com.project.account.model.Transaction;
import com.project.account.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    protected Transaction initialMoney(final Account account, BigDecimal amount){
        return transactionRepository.save(new Transaction(amount,account));
    }

}
