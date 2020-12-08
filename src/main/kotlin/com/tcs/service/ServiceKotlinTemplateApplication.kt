package com.tcs.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.dapr.client.DaprClient
import io.dapr.client.DaprClientBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceKotlinTemplateApplication

fun main(args: Array<String>) {

    runApplication<ServiceKotlinTemplateApplication>(*args)
    val SECRET_STORE_NAME: String = "azurekeyvault"

    val JSON_SERIALIZER = ObjectMapper()
    val secretKey = "deliverymomentdbapi"
    val client : DaprClient = DaprClientBuilder().build()
    println(client)

    var secret = client.getSecret(SECRET_STORE_NAME,secretKey).block()

}
