package com.bank.coroutineapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutineAppApplication

fun main(args: Array<String>) {
	runApplication<CoroutineAppApplication>(*args)
}
