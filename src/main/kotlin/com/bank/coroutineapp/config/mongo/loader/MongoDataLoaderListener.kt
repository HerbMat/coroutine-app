package com.bank.coroutineapp.config.mongo.loader

import com.bank.coroutineapp.repository.AccountTypeRepository
import com.bank.coroutineapp.repository.CustomerRepository
import com.bank.coroutineapp.repository.TransactionRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.reactor.mono
import org.apache.logging.log4j.LogManager
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import reactor.kotlin.core.publisher.toMono

/**
 * On startup loads initial mongo collections to database.
 */
@ConditionalOnProperty(value = ["bank-application.mongo.load-data"], havingValue = "true")
@Component
class MongoDataLoaderListener(
        private val accountTypeRepository: AccountTypeRepository,
        private val customerRepository: CustomerRepository,
        private val transactionRepository: TransactionRepository
) : ApplicationListener<ApplicationStartedEvent> {
    companion object {
        val LOG = LogManager.getLogger()
    }

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        mono {
            launch {
                accountTypeRepository.deleteAll()
                accountTypeRepository.saveAll(MongoInitialDataLoader.getAccountTypes()).singleOrNull()
            }
            launch {
                customerRepository.deleteAll()
                customerRepository.saveAll(MongoInitialDataLoader.getCustomers()).singleOrNull()
            }
            launch {
                transactionRepository.deleteAll()
                transactionRepository.saveAll(MongoInitialDataLoader.getTransactions()).singleOrNull()
            }
        }.subscribe()
    }
}