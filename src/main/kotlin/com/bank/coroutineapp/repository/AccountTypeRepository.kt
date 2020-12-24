package com.bank.coroutineapp.repository

import com.bank.coroutineapp.domain.AccountType
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountTypeRepository: CoroutineCrudRepository<AccountType, String> {
}