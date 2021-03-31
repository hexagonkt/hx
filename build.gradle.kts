
plugins {
    kotlin("jvm") version "1.4.31"
    id("org.openjfx.javafxplugin") version "0.0.9"
    id("application")
}

apply(from = "${properties["gradleScripts"]}/kotlin.gradle")
apply(from = "${properties["gradleScripts"]}/lean.gradle")

extensions.configure<org.openjfx.gradle.JavaFXOptions> {
    modules = listOf("javafx.controls", "javafx.web")
}

extensions.configure<JavaApplication> {
    mainClass.set("com.hexagonkt.sidekick.SidekickKt")
}

dependencies {
    val hexagonVersion = properties["hexagonVersion"]
    val flatlafVersion = properties["flatlafVersion"]
    val kotlinxHtmlVersion = properties["kotlinxHtmlVersion"]
    val undertowVersion = properties["undertowVersion"]

    "implementation"("com.hexagonkt:http_server_jetty:$hexagonVersion")
    "implementation"("com.hexagonkt:http_client_ahc:$hexagonVersion")
    "implementation"("com.hexagonkt:serialization_xml:$hexagonVersion")
    "implementation"("com.hexagonkt:serialization_yaml:$hexagonVersion")
    "implementation"("com.hexagonkt:logging_logback:$hexagonVersion")
    "implementation"("com.formdev:flatlaf:$flatlafVersion")
    "implementation"("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
    "implementation"("io.undertow:undertow-servlet:$undertowVersion")
}
