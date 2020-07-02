package com.hexagonkt.hx

import com.hexagonkt.helpers.Resource
import com.hexagonkt.helpers.logger
import com.hexagonkt.hx.ace.AceView
import com.hexagonkt.hx.codemirror.CodeMirrorView
import javafx.application.Platform.exit
import javafx.application.Platform.runLater
import javafx.event.Event
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType.CONFIRMATION
import javafx.scene.control.ButtonType.OK
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.App
import tornadofx.launch
import java.awt.MenuItem
import java.awt.PopupMenu
import java.awt.SystemTray
import java.awt.SystemTray.getSystemTray
import java.awt.Toolkit.getDefaultToolkit
import java.awt.TrayIcon
import kotlin.system.exitProcess

const val appName = "ace demo"
const val appImage = "img/tray.png"

internal class HxApp : App() {

//    override val primaryView = AceView::class
    override val primaryView = CodeMirrorView::class

    override fun start(stage: Stage) {
        super.start(stage)

        stage.icons.add(Image(appImage))
        stage.sizeToScene()
        stage.scene.stylesheets.add("dark.css")

        initTray(stage)
    }

    override fun stop() {
        exitProcess(0)
    }

    private fun initTray (primaryStage: Stage) {
        if (!SystemTray.isSupported()) return

        val image = getDefaultToolkit().getImage(Resource(appImage).url())

        val popupMenu = PopupMenu().apply {
            add(
                MenuItem("Exit").apply {
                    addActionListener {
                        runLater { exitApplication() }
                    }
                }
            )
        }

        val trayIcon = TrayIcon(image, appName, popupMenu).apply {
            isImageAutoSize = true

            addActionListener {
                runLater {
                    if (primaryStage.isShowing) {
                        primaryStage.hide()
                    }
                    else {
                        primaryStage.show()
                        primaryStage.title = appName
                        primaryStage.toFront()
                    }
                }
            }
        }

        getSystemTray().add(trayIcon)
    }

    private fun exitApplication(handler: Event? = null) {
        val alert = Alert(CONFIRMATION).apply {
            title = "Confirm $appName Exit"
            headerText = "Do you really want to close $appName?"
        }

        if (alert.showAndWait().get() == OK) exit() else handler?.consume()
    }
}

fun main() {
    logger.info { "Starting $appName..." }
    launch<HxApp>()
    logger.info { "$appName started" }
}
