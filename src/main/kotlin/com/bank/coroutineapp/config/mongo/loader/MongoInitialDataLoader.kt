package com.bank.coroutineapp.config.mongo.loader

import com.bank.coroutineapp.domain.AccountType
import com.bank.coroutineapp.domain.Customer
import com.bank.coroutineapp.domain.Transaction
import com.bank.coroutineapp.repository.AccountTypeRepository
import com.bank.coroutineapp.repository.CustomerRepository
import com.bank.coroutineapp.utlis.CSVCollectionFileReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * It loads initial entity collection from file.
 */
object MongoInitialDataLoader {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun getAccountTypes(): List<AccountType> {
        return CSVCollectionFileReader
                .extractCollectionDataFromFile("accountypes.csv")
                .map {
                    AccountType(it["account_type"]!!, it["name"].orEmpty())
                }
    }

    fun getCustomers(): List<Customer> {
        return CSVCollectionFileReader
                .extractCollectionDataFromFile("customers.csv")
                .map {
                    Customer(
                            it["id"]!!,
                            it["first_name"].orEmpty(),
                            it["last_name"].orEmpty(),
                            (it["last_login_balance"] ?: "0").toBigDecimal()
                    )
                }
    }

    suspend fun getTransactions(customerRepository: CustomerRepository, accountTypeRepository: AccountTypeRepository): List<Transaction> {
        return CSVCollectionFileReader
                .extractCollectionDataFromFile("transactions.csv")
                .map { Transaction(
                        it["transaction_id"]!!,
                        (it["transaction_amount"] ?: "0").toBigDecimal(),
                        accountTypeRepository.findById(it["account_type"]!!)!!,
                        customerRepository.findById(it["customer_id"]!!)!!,
                        LocalDateTime.parse(it["transaction_date"], formatter)
                ) }
    }
}