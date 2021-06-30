package com.hexagonkt.sidekick

import com.hexagonkt.logging.logger
import javafx.application.Application
import javafx.application.Application.launch
import javafx.application.Platform.exit
import javafx.application.Platform.runLater
import javafx.event.Event
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType.CONFIRMATION
import javafx.scene.control.ButtonType.OK
import javafx.scene.image.Image
import javafx.stage.Stage
import java.awt.MenuItem
import java.awt.PopupMenu
import java.awt.SystemTray
import java.awt.SystemTray.getSystemTray
import java.awt.Toolkit.getDefaultToolkit
import java.awt.TrayIcon
import java.net.URL
import kotlin.system.exitProcess

const val appName = "ace demo"
const val appImage = "classpath:img/tray.png"

internal class SidekickApp : Application() {

    override fun start(stage: Stage) {

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

        val image = getDefaultToolkit().getImage(URL(appImage))

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
    launch(SidekickApp::class.java)
    logger.info { "$appName started" }
}
