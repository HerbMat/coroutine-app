package com.bank.coroutineapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/hello")
    suspend fun helloWorld(): String {
        return "Hello World"
    }
}