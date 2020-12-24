package com.bank.coroutineapp.repository

import com.bank.coroutineapp.domain.Transaction
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: CoroutineCrudRepository<Transaction, String>, TransactionQueryRepository {
}