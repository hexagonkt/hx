#!/usr/bin/env kotlins

/*
 * To run Kotlin scripts you have to define the `KOTLIN_HOME` environment variable and create the
 * `kotlins` launcher with the following commands:
 *
 * echo 'kotlinc -cp $KOTLIN_HOME/lib/kotlin-main-kts.jar -script $@' >/usr/local/bin/kotlins
 * chmod +x /usr/local/bin/kotlins
 */

@file:CompilerOptions("-jvm-target", "11")
@file:DependsOn("com.hexagonkt:http_server_jetty:1.3.7")

import com.hexagonkt.injection.*
import com.hexagonkt.http.server.*
import com.hexagonkt.http.server.jetty.JettyServletAdapter

println(java.io.File(".").absolutePath)

InjectionManager.bind<ServerPort>(JettyServletAdapter())

println(java.io.File(".").absolutePath)

serve {
    get("/dir/*", java.io.File("hexagon_service"))
    get("/hello/{name}") { ok("Hello, ${pathParameters["name"]}!", "text/plain") }
}

println(java.io.File("hexagon_service").absolutePath)
println(java.io.File("hexagon_service").exists())
