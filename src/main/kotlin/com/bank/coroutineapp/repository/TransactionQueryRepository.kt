package com.bank.coroutineapp.repository

import com.bank.coroutineapp.domain.Transaction
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.core.query.Query

interface TransactionQueryRepository {
    fun findAllByQuery(query: Query): Flow<Transaction>
}