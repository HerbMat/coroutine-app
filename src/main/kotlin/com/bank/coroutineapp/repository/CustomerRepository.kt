package com.bank.coroutineapp.repository

import com.bank.coroutineapp.domain.Customer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: CoroutineCrudRepository<Customer, String> {
}