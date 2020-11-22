#!/usr/bin/env kscript

@file:CompilerOpts("-jvm-target 11")
@file:DependsOn("com.hexagonkt:http_server_jetty:1.3.2")
@file:DependsOn("ch.qos.logback:logback-classic:1.2.3")

import com.hexagonkt.injection.*
import com.hexagonkt.http.server.*
import com.hexagonkt.http.server.jetty.JettyServletAdapter

InjectionManager.bind<ServerPort>(JettyServletAdapter())

println(java.io.File(".").absolutePath)

serve {
    get("/dir/*", java.io.File("hexagon_service"))
    get("/hello/{name}") { ok("Hello, ${pathParameters["name"]}!", "text/plain") }
}

println(java.io.File("hexagon_service").absolutePath)
println(java.io.File("hexagon_service").exists())
