package com.hexagonkt.sidekick.swing

import com.formdev.flatlaf.FlatDarculaLaf
import java.awt.*
import java.awt.BorderLayout.*
import java.awt.SystemTray.getSystemTray
import java.awt.Toolkit.getDefaultToolkit
import java.awt.datatransfer.StringSelection
import javax.swing.*
import javax.swing.JLabel.CENTER
import javax.swing.SwingUtilities.invokeLater
import javax.swing.border.EmptyBorder
import kotlin.system.exitProcess

internal class Gui {

    private val frmNavigation: JFrame by lazy {
        JFrame().apply {
            layout = BorderLayout()
            add(JLabel("Date / Time").apply { horizontalAlignment = SwingConstants.CENTER }, CENTER)
            add(JLabel("<html>▲<br/>Applications</html>").apply { horizontalAlignment = SwingConstants.CENTER; horizontalTextPosition = CENTER }, NORTH)
            add(JLabel("▶<br/>Files").apply { horizontalAlignment = SwingConstants.CENTER }, EAST)
            add(JLabel("▼<br/>Windows").apply { horizontalAlignment = SwingConstants.CENTER }, SOUTH)
            add(JLabel("◀").apply { horizontalAlignment = SwingConstants.CENTER;  }, WEST)
            isUndecorated = true
            pack()
            setLocationRelativeTo(null)
        }
    }

    private val frame by lazy {
        JFrame("Generator").apply {
            contentPane.layout = BorderLayout(10, 10)

            add(JPanel().apply {
                border = EmptyBorder(10, 10, 10, 10)
                layout = BorderLayout(10, 10)
                add(JTabbedPane().apply {
                    addTab("JWT", JPanel().apply {
                        val txtSecret = JTextField("ePSemGeCvaTDBKWLnpJkhD8lkK1y5XhL")
                        val txtUser = JTextField("Minime")
                        val txtRoles = JTextField("USER")
                        val lblToken = JLabel()

                        add(JLabel("Secret"))
                        add(txtSecret)
                        add(JLabel("User"))
                        add(txtUser)
                        add(JLabel("Roles"))
                        add(txtRoles)
                        add(JButton("JWT Token").apply {
                            addActionListener {
                                lblToken.text = ""
                                getDefaultToolkit()
                                    .systemClipboard.setContents(StringSelection(""), null)
                            }
                        })
                        add(JLabel("Token"))
                        add(lblToken)
                    })
                })
            }, CENTER)
        }
    }

    init {
        setUpLookAndFeel()
    }

    fun display() {
//        initTray(frame)
//        initTray(frmNavigation)
//        frmNavigation.apply {
//            pack()
//        }
        frmNavigation.isVisible = true
    }

    private fun initTray(frame: JFrame) {
        if (!SystemTray.isSupported()) return

        val contextClassLoader = Thread.currentThread().contextClassLoader
        val url = contextClassLoader.getResource("img/tray.png")
        val image = getDefaultToolkit().getImage(url)

        val popupMenu = PopupMenu().apply {
            add(
                MenuItem("Exit").apply {
                    addActionListener {
                        invokeLater { exitProcess(0) }
                    }
                }
            )
        }

        val trayIcon = TrayIcon(image, "App", popupMenu).apply {
            isImageAutoSize = true

            addActionListener {
                invokeLater {
                    if (frame.isVisible) {
                        frame.isVisible = false
                    }
                    else {
                        frame.isVisible = true
                        frame.toFront()
                    }
                }
            }
        }

        getSystemTray().add(trayIcon)
    }

    /*
     * https://www.formdev.com/flatlaf/customizing
     * https://www.formdev.com/flatlaf/themes
     */
    private fun setUpLookAndFeel() {
        UIManager.put("Component.arrowType", "triangle")
        UIManager.setLookAndFeel(FlatDarculaLaf())
    }
}
