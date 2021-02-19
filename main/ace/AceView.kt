package com.hexagonkt.sidekick.ace

import javafx.geometry.Pos.BASELINE_LEFT
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.web.WebView
import kotlinx.html.*
import kotlinx.html.ScriptType.textJavaScript
import kotlinx.html.stream.createHTML
import java.io.File

internal class AceView {
    val root = BorderPane().apply {
        center = WebView().apply {
            val html = createHTML().html {
                head {
                    style {
                        type = "text/css"
                        media = "screen"

                        unsafe {
                            raw("""
                            #editor {
                                position: absolute;
                                top: 0;
                                right: 0;
                                bottom: 0;
                                left: 0;
                            }
                            .normal-mode .ace_cursor {
                                border: 1px solid white !important;
                                background-color: white !important;
                                opacity: 1;
                            }
                            """.trimIndent())
                        }
                    }
                }
                body {
                    div {
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

                    val resources = File("build/resources/main/ace/web").toURI()
                    script(textJavaScript, "$resources/ace.js") {}
                    script(textJavaScript, "$resources/theme-chrome.js") {}
                    script(textJavaScript, "$resources/theme-monokai.js") {}
                    script(textJavaScript, "$resources/theme-nord_dark.js") {}
                    script(textJavaScript, "$resources/theme-twilight.js") {}
                    script(textJavaScript, "$resources/theme-vibrant_ink.js") {}
                    script(textJavaScript, "$resources/mode-yaml.js") {}
                    script(textJavaScript, "$resources/keybinding-vim.js") {}

                    script {
                        unsafe {
                            raw("""
                            var editor = ace.edit("editor");
                            editor.setFontSize(14);
                            editor.setTheme("ace/theme/nord_dark");
                            editor.getSession().setMode("ace/mode/yaml");
                            editor.setKeyboardHandler("ace/keyboard/vim");
                            """)
                        }
                    }
                }
            }

            engine.loadContent(html)
        }

        bottom = HBox().apply {
            spacing = 10.0
            alignment = BASELINE_LEFT

            children.addAll(
                Label("HOUR").apply { id = "lblHour" },
                Label("CODE").apply { id = "lblCode" },
                Label("METHOD").apply { id = "lblMethod" },
                Label("URL").apply { id = "lblUrl" },
                Label("CONTENT_TYPE").apply { id = "lblContentType" },
                Label("TIME").apply { id = "lblTime" },
            )
        }
    }
}
