package com.hexagonkt.sidekick.undertow

import com.hexagonkt.logging.Logger
import java.lang.management.ManagementFactory
import io.undertow.server.HttpServerExchange

import io.undertow.Undertow
import io.undertow.util.Headers
import java.lang.Exception

fun main() {

    val logger = Logger("com.hexagonkt")

    val server = Undertow.builder()
        .addHttpListener(8080, "localhost")
        .setHandler {
            @Throws(Exception::class) fun handleRequest(exchange: HttpServerExchange) {
                exchange.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
                exchange.responseSender.send("Hello World")
            }
        }.build()
    server.start()

    logger.info { ManagementFactory.getRuntimeMXBean().uptime }
}
