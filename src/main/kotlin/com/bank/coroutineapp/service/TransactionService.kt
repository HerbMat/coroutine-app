package com.bank.coroutineapp.service

import com.bank.coroutineapp.dto.TransactionDTO
import com.bank.coroutineapp.dto.TransactionDetailsRequest
import com.bank.coroutineapp.mapper.TransactionMapper
import com.bank.coroutineapp.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class TransactionService(private val transactionRepository: TransactionRepository, private val transactionMapper: TransactionMapper) {

    suspend fun getAll(transactionDetailsRequest: TransactionDetailsRequest): Flow<TransactionDTO> {
        return transactionRepository.findAllByQuery(buildCriteriaQuery(transactionDetailsRequest))
                .map { transactionMapper.toTransactionDTO(it) }
    }

    private suspend fun buildCriteriaQuery(transactionDetailsRequest: TransactionDetailsRequest): Query {
        val criteria = Criteria()
        transactionDetailsRequest.accountTypes?.let {
            if (isThereCriteriaToAdd(it)) {
                criteria.and("accountType.\$id").`in`(it.split(","))
            }
        }
        transactionDetailsRequest.customerIds?.let {
            if (isThereCriteriaToAdd(it)) {
                criteria.and("customer.\$id").`in`(it.split(","))
            }
        }
        val query = Query()
        query.addCriteria(criteria)

        return query
    }

    private suspend fun isThereCriteriaToAdd(listString: String): Boolean = listString.isNotBlank() && listString != "ALL"

}