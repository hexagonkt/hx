package com.hexagonkt.sidekick.swing

import com.formdev.flatlaf.FlatDarculaLaf
import com.hexagonkt.sidekick.swing.helpers.FontIcon
import java.awt.*
import java.awt.BorderLayout.*
import java.awt.SystemTray.getSystemTray
import java.awt.Toolkit.getDefaultToolkit
import java.awt.datatransfer.StringSelection
import javax.swing.*
import javax.swing.SwingUtilities.invokeLater
import javax.swing.border.EmptyBorder
import kotlin.system.exitProcess

internal class Gui {

    private fun jFrame(layout: LayoutManager? = null, block: JFrame.() -> Unit): JFrame =
        JFrame().apply {
            if (layout != null)
                this.layout = layout
            block()
        }

    private fun Container.jLabel(constraints: Any? = null, block: JLabel.() -> Unit): JLabel =
        JLabel().apply(block).apply { this@jLabel.add(this, constraints) }

    private fun Container.jButton(constraints: Any? = null, block: JButton.() -> Unit): JButton =
        JButton().apply(block).apply { this@jButton.add(this, constraints) }

    private fun JFrame.directionButton(
        constraints: String,
        text: String,
        horizontalTextPosition: Int,
        verticalTextPosition: Int,
        icon: Char,
    ): JButton =
        jButton(constraints) {
            this.text = text
            this.horizontalAlignment = SwingConstants.CENTER
            this.horizontalTextPosition = horizontalTextPosition
            this.verticalTextPosition = verticalTextPosition
            this.isFocusable = false
            this.icon = FontIcon("/icofont.ttf", Color.GRAY, icon, 32)
        }

    private val frmNavigation: JFrame by lazy {
        jFrame(BorderLayout()) {

            jLabel(CENTER) {
                text = "Date / Time"
                horizontalAlignment = SwingConstants.CENTER
            }

            directionButton(NORTH, "Applications", SwingConstants.CENTER, SwingConstants.BOTTOM, '\uEABD')
            directionButton(EAST, "Files", SwingConstants.CENTER, SwingConstants.BOTTOM, '\uEABC')
            directionButton(SOUTH, "Windows", SwingConstants.CENTER, SwingConstants.TOP, '\uEABA')
            directionButton(WEST, "Actions", SwingConstants.CENTER, SwingConstants.BOTTOM, '\uEABB')

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
