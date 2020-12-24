package com.bank.coroutineapp.mapper

import com.bank.coroutineapp.domain.Transaction
import com.bank.coroutineapp.dto.TransactionDTO
import com.bank.coroutineapp.repository.AccountTypeRepository
import com.bank.coroutineapp.repository.CustomerRepository
import org.springframework.stereotype.Component

@Component
class TransactionMapper(private val customerRepository: CustomerRepository, private val accountTypeRepository: AccountTypeRepository) {
    suspend fun toTransactionDTO(transaction: Transaction): TransactionDTO {
        val customer = customerRepository.findById(transaction.customerId)
        return TransactionDTO(
                id = transaction.id,
                amount = transaction.amount,
                accountTypeName = accountTypeRepository.findById(transaction.accountTypeId)!!.name,
                customerFirstName = customer!!.firstName,
                customerLastName = customer.lastName,
                transactionDate = transaction.transactionDate
        )
    }
}
