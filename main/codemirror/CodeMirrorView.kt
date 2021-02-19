package com.hexagonkt.sidekick.codemirror

import com.hexagonkt.helpers.logger
import javafx.scene.layout.BorderPane
import javafx.scene.web.WebView
import kotlinx.html.*
import kotlinx.html.ScriptType.textJavaScript
import kotlinx.html.stream.createHTML
import java.io.File

internal class CodeMirrorView {

    val directory = File("build/resources/main/codemirror/web")
    private val path = directory.toURI().toString().removeSuffix("/")

    val root = BorderPane().apply {
        center = WebView().apply {
            val html = createHTML().html {
                head {
                    link(rel = "stylesheet", href = "$path/codemirror.css")
                    listOf("monokai", "nord", "twilight", "vibrant-ink").forEach {
                        link(rel = "stylesheet", href = "$path/theme-${it}.css")
                    }
                    style {
                        type = "text/css"
                        media = "screen"

                        unsafe {
                            raw(
                                """
                            body { margin: 0 }
                            .CodeMirror { height: 100% }
                            """.trimIndent()
                            )
                        }
                    }
                }
                body {
                    textArea {
                        id = "editor"

                        +"""
                        email: jaguililla@azampar.co
                        name: Juanjo Aguililla
                        image: https://example.com
                        active: true
                        code: jaguililla
                        creation: 20160907111015614
                        """.trimIndent()
                    }

                    script(textJavaScript, "$path/codemirror.js") {}
                    script(textJavaScript, "$path/mode-yaml.js") {}
                    script(textJavaScript, "$path/keymap-vim.js") {}
                    script(textJavaScript, "$path/addon-active-line.js") {}

                    script {
                        unsafe {
                            raw(
                                """
                            var editorElement = document.getElementById("editor");
                            var editor = CodeMirror.fromTextArea(editorElement, {
                              theme: "nord",
                              mode: "yaml",
                              keyMap: "vim",
                              lineNumbers: true,
                              styleActiveLine: true,
                              matchBrackets: true,
                            });
                            """
                            )
                        }
                    }
                }
            }

            logger.info { html }
//            File("build/cm.html").writeText(html)
            engine.loadContent(html)
        }
    }
}
