#!/usr/bin/env kotlin

@file:CompilerOptions("-jvm-target", "1.8")
@file:DependsOn("com.hexagonkt:http_server_jetty:1.3.20")
@file:DependsOn("com.hexagonkt:logging_slf4j_jul:1.3.20")

import com.hexagonkt.http.server.jetty.serve
import java.io.File

serve {
    get("/dir/*", File("hexagon_service"))
    get("/hello/{name}") { ok("Hello, ${pathParameters["name"]}!", "text/plain") }
}
