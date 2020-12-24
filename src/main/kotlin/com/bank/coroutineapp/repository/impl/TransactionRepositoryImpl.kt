package com.bank.coroutineapp.repository.impl

import com.bank.coroutineapp.domain.Transaction
import com.bank.coroutineapp.repository.TransactionQueryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class TransactionRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): TransactionQueryRepository {
    override fun findAllByQuery(query: Query): Flow<Transaction> {
        return mongoTemplate.find(query, Transaction::class.java).asFlow()
    }
}