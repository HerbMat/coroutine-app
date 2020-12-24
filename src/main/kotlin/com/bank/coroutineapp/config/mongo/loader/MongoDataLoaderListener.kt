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
            accountTypeRepository.deleteAll()
            customerRepository.deleteAll()
            transactionRepository.deleteAll()
            accountTypeRepository.saveAll(MongoInitialDataLoader.getAccountTypes()).singleOrNull()
            customerRepository.saveAll(MongoInitialDataLoader.getCustomers()).singleOrNull()
            transactionRepository.saveAll(MongoInitialDataLoader.getTransactions(customerRepository, accountTypeRepository)).singleOrNull()
        }.subscribe()
    }
}