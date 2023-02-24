package com.project.account.dto.converter;

import com.project.account.dto.TransactionDto;
import com.project.account.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {
    public TransactionDto modelToDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTransactionDate());
    }
}
