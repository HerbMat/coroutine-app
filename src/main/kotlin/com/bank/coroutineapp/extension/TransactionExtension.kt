package com.bank.coroutineapp.extension

import com.bank.coroutineapp.domain.Transaction
import com.bank.coroutineapp.dto.TransactionDTO

fun Transaction.toTransactionDTO(): TransactionDTO {
    return TransactionDTO(
            id = id,
            amount = amount,
            accountTypeName = accountType.name,
            customerFirstName = customer.firstName,
            customerLastName = customer.lastName,
            transactionDate = transactionDate
    )
}