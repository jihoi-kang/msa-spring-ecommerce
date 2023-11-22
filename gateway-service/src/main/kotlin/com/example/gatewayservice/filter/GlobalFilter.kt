package com.example.gatewayservice.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Companion.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {

        // Custom pre filter
        return GatewayFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response

            println("Global Filter baseMessage=${config.baseMessage}")

            if (config.preLogger) {
                println("Global PRE filter: request id=${request.id}")
            }

            return@GatewayFilter chain.filter(exchange)
                .then(Mono.fromRunnable {
                    if (config.postLogger) {
                        println("Global POST filter: response code=${response.statusCode}")
                    }
                })
        }
    }

    companion object {
        data class Config(
            val baseMessage: String,
            val preLogger: Boolean,
            val postLogger: Boolean,
        )
    }

}