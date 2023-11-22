package com.example.gatewayservice.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Companion.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {

        // Custom pre filter
        return GatewayFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response

            println("Custom PRE filter: request id=${request.id}")

            return@GatewayFilter chain.filter(exchange)
                .then(Mono.fromRunnable {
                    println("Custom POST filter: response code=${response.statusCode}")
                })
        }
    }

    companion object {
        class Config
    }

}