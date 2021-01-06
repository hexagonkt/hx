
plugins {
    kotlin("jvm") version "1.4.21"
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
    val tornadofxVersion = properties["tornadofxVersion"]
    val kotlinxHtmlVersion = properties["kotlinxHtmlVersion"]

    "implementation"("com.hexagonkt:hexagon_core:$hexagonVersion")
    "implementation"("com.hexagonkt:http_server_jetty:$hexagonVersion")
    "implementation"("com.hexagonkt:http_client_ahc:$hexagonVersion")
    "implementation"("com.formdev:flatlaf:$flatlafVersion")
    "implementation"("no.tornado:tornadofx:$tornadofxVersion")
    "implementation"("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
}
