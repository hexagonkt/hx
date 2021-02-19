#!/usr/bin/env kotlins

@file:CompilerOptions("-jvm-target", "1.8")
@file:DependsOn("com.hexagonkt:http_client_ahc:1.3.9")

import com.hexagonkt.helpers.println
import com.hexagonkt.http.client.Client
import com.hexagonkt.http.client.ClientSettings
import com.hexagonkt.http.client.ahc.AhcAdapter
import com.hexagonkt.serialization.SerializationManager
import com.hexagonkt.serialization.Json

SerializationManager.formats = linkedSetOf(Json)

val http = Client(AhcAdapter(), "http://localhost:8080", ClientSettings(Json))

val loanId = 102030
val response = http.put("/loans/$loanId",
    mapOf(
        "title" to "Car Loan",
    )
)

response.body.println()
