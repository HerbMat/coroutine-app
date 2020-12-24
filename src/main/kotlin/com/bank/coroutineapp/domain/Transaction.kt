package com.bank.coroutineapp.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document
data class Transaction(
        @Id
        val id: String,
        val amount: BigDecimal,
        val accountTypeId: String,
        val customerId: String,
        val transactionDate: LocalDateTime
)