package com.tcs.service.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.tcs.service.constant.ExceptionMessage
import com.tcs.service.error.customexception.DataNotFoundException
import com.tcs.service.model.Model
import com.tcs.service.repository.Repository
import io.dapr.client.DaprClient
import io.dapr.client.DaprClientBuilder
import org.apache.logging.log4j.kotlin.logger
import org.springframework.stereotype.Service


@Service
class Service(private val repository: Repository) {
    val logger = logger()
    fun getById(id: String): Model {
        logger.info("Before Cast")
        return Model(repository.findById(id.toInt()).get() ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND))
    }


    fun get(): MutableList<Model>{
        //The below lines of code is for POC on Mongo Template
        //repository.getAllByDesc("Sample").forEach{i -> println(i.modId)}
        var models = mutableListOf<Model>()
        var result = repository.findAll() ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND)
        result.forEach { entity -> models.add(Model(data = entity)) }
        return models
    }

fun secretClient(): MutableMap<String, String>? {
    val SECRET_STORE_NAME: String = "azurekeyvault"

    val JSON_SERIALIZER = ObjectMapper()
 val secretKey = "dbconn"
 val client : DaprClient  = DaprClientBuilder().build()
    println(client)

    var secret = client.getSecret(SECRET_STORE_NAME,secretKey).block()
    return secret
}


}