import org.gradle.api.publish.maven.MavenPom
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document
import org.w3c.dom.Element

//project.extra["bintrayPublications"] = listOf("hexagon_pom", "hexagon_lean_pom")

//apply(from = "../gradle/kotlin.gradle")
apply(from = "../gradle/bintray.gradle")
//apply(from = "../gradle/dokka.gradle")

plugins {
//    signing
    `maven-publish`
}

//dependencies {
//    "implementation"(project(":http_server_jetty"))
//
//    "testImplementation"(project(":http_client_ahc"))
//}

task("processTemplate") {
    dependsOn("test")

    doLast {
        val stringProperties: Map<String, *> = project.properties.filter { it.value is String }
        val templates = projectDir.listFiles { f -> f.isDirectory && f.name.startsWith("hexagon_") }
        val templateDirs = templates?.map { it.name } ?: emptyList()
        templateDirs.forEach { dir ->
            copy {
                from("$projectDir/$dir")
                into("$buildDir/$dir")
                filter {
                    var content = it

                    stringProperties.entries.forEach { entry ->
                        val entryValue = entry.value.toString()
                        content = content.replace("\${project.${entry.key}}", entryValue)
                    }

                    content
                }
            }

            copy {
                from("$projectDir/src")
                into("$buildDir/$dir/src")
            }

            copy {
                from("$rootDir/gradle/wrapper")
                into("$buildDir/$dir/gradle/wrapper")
            }

            copy {
                from(rootDir.toString())
                into("$buildDir/$dir")
                include("gradlew", "gradlew.bat")
            }

            file("$buildDir/$dir/gradle.properties").writeText("""
                name=\${project.name}
                version=\${version}
                group=\${group}
                description=\${description}

                gradleScripts=${properties["gradleScripts"]}/${rootProject.version}/gradle

                mainClassName=${group}.ServiceKt

                hexagonVersion=$rootProject.version
                logbackVersion=${properties["logbackVersion"]}

                junitVersion=${properties["junitVersion"]}
            """.trimIndent())
        }
    }
}

//signing {
//    useInMemoryPgpKeys(
//        findProperty("signingKey")?.toString() ?: System.getenv("SIGNING_KEY"),
//        findProperty("signingPassword")?.toString() ?: System.getenv("SIGNING_PASSWORD")
//    )
//    setRequired { gradle.taskGraph.hasTask("publish") }
//    sign(publishing.publications)
//}

publishing {

//    repositories {
//        maven {
//            url = java.net.URI(
//                if (rootProject.version.toString().endsWith("SNAPSHOT"))
//                    "https://oss.sonatype.org/content/repositories/snapshots"
//                else
//                    "https://oss.sonatype.org/service/local/staging/deploy/maven2"
//            )
//
//            credentials {
//                username =
//                    findProperty("ossrhUsername")?.toString() ?: System.getenv("OSSRH_USERNAME")
//                password =
//                    findProperty("ossrhPassword")?.toString() ?: System.getenv("OSSRH_PASSWORD")
//            }
//        }
//    }

    publications {

        createPomPublication("hexagon_pom") { pomDom ->
            properties.set(mapOf(
                "project.build.sourceEncoding" to Charsets.UTF_8.name(),
                "kotlin.version" to project.properties["kotlinVersion"].toString(),
                "hexagon.version" to rootProject.version.toString()
            ))

            withXml {
                listOf("repositories", "dependencyManagement", "build").forEach {
                    asElement().importElement(pomDom.firstElement(it))
                }
            }
        }

        createPomPublication("hexagon_lean_pom") { pomDom ->
            withXml {
                val root = asElement()
                val version = rootProject.version.toString()
                listOf("parent", "build").forEach {
                    root.importElement(pomDom.firstElement(it))
                }
                root.ownerDocument.firstElement("parent").appendElement("version", version)
            }
        }
    }
}

fun File.parseDom(): Document =
    DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this)

fun Document.firstElement(name: String): Element =
    getElementsByTagName(name).item(0) as Element

fun Element.appendElement(name: String, value: Any?): Element =
    appendChild(
        ownerDocument.createElement(name).also { it.textContent = value.toString() }
    ) as Element

fun Element.importElement(element: Element): Element =
    appendChild(ownerDocument.importNode(element, true)) as Element

fun PublicationContainer.createPomPublication(
    artifact: String, block: MavenPom.(Document) -> Unit = {}) {

    create<MavenPublication>(artifact) {
        artifactId = this.name
        pom {
            packaging = "pom"

            scm {
                val vcsUrl = findProperty("vcsUrl") ?: error("'vcsUrl' property must be defined")

                connection.set("scm:git:$vcsUrl")
                developerConnection.set("scm:git:git@github.com:hexagonkt/hexagon.git")
                url.set("https://github.com/hexagonkt/hexagon")
            }

            licenses {
                license {
                    name.set("The MIT License")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }

            developers {
                developer {
                    id.set("hexagonkt")
                    name.set("Hexagon Toolkit")
                    email.set("project@hexagonkt.com")
                }
            }

            url.set(project.properties["siteHost"].toString())
            val pomDom = project.file("maven/${artifactId}.xml").parseDom()
            name.set(pomDom.firstElement("name").textContent)
            description.set(pomDom.firstElement("description").textContent)
            block(pomDom)
        }
    }
}
