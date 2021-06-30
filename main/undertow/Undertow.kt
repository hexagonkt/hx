package com.hexagonkt.sidekick.undertow

import com.hexagonkt.logging.Logger
import io.undertow.Handlers
import java.lang.management.ManagementFactory

import io.undertow.Undertow
import io.undertow.util.Headers

fun main() {

    val logger = Logger("com.hexagonkt")

    val router = Handlers.routing()
        .get("*") { // TODO Don't work
            it.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
        }
        .get("/hello") {
//            it.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
            it.responseSender.send("Hello World")
        }
        .get("/bye") {
//            it.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
            it.responseSender.send("Cruel World")
        }

    Undertow.builder()
        .addHttpListener(8080, "localhost")
        .setHandler(router)
        .build()
        .start()

    logger.info { ManagementFactory.getRuntimeMXBean().uptime }
}
