package com.example.gatewayservice.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Companion.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        val filter: GatewayFilter = OrderedGatewayFilter({ exchange, chain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response

            println("Logging Filter baseMessage=${config.baseMessage}")

            if (config.preLogger) {
                println("Logging PRE filter: request id=${request.id}")
            }

            return@OrderedGatewayFilter chain.filter(exchange)
                .then(Mono.fromRunnable {
                    if (config.postLogger) {
                        println("Logging POST filter: response code=${response.statusCode}")
                    }
                })
        }, Ordered.LOWEST_PRECEDENCE)

        return filter
    }

    companion object {
        data class Config(
            val baseMessage: String,
            val preLogger: Boolean,
            val postLogger: Boolean,
        )
    }

}