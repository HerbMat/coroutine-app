package com.bank.coroutineapp.controller

import com.bank.coroutineapp.dto.TransactionDTO
import com.bank.coroutineapp.dto.TransactionDetailsRequest
import com.bank.coroutineapp.service.TransactionService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/transactions")
@RestController
class TransactionController(private val transactionService: TransactionService) {

    @GetMapping(produces = [MediaType.APPLICATION_NDJSON_VALUE])
    suspend fun geTransactions(transactionDetailsRequest: TransactionDetailsRequest): Flow<TransactionDTO> {
        return transactionService.getAll(transactionDetailsRequest)
    }
}