package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.BorderLayout
import java.awt.Color
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

internal open class AppFrame() : JFrame() {
    /**
     * TODO .
     */
    fun open() {
        pack()
        isVisible = true
    }

    companion object {
        /** TODO .  */
        private val serialVersionUID = -3251170969358255886L
    }

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
    }
} //@formatOff

internal class MainFrame() : AppFrame() {
    init {
        val f: JFrame = this
        val props = arrayOf(
            "control", "info", "nimbusAlertYellow", "nimbusBase", "nimbusDisabledText", "nimbusFocus",
            "nimbusGreen", "nimbusInfoBlue", "nimbusLightBackground", "nimbusOrange", "nimbusRed",
            "nimbusSelectedText", "nimbusSelectionBackground", "text"
        )

        class ColorButton(aText: String?) : JButton(aText) {
            init {
                isOpaque = true
                background = UIManager.getColor(aText)
                if ((background == Color.BLACK)) foreground = Color.WHITE
                addActionListener(ActionListener {
                    UIManager.put(text, JColorChooser.showDialog(f, text, background))
                    background = UIManager.getColor(text)
                    if ((background == Color.BLACK)) foreground = Color.WHITE
                    SwingUtilities.updateComponentTreeUI(f)
                })
            }
        }

        val actExit: Action = object : AbstractAction("Exit") {
            override fun actionPerformed(aE: ActionEvent) {
                f.dispose()
            }
        }
        title = "Synthetizer"
        layout = BorderLayout()
        //    setUndecorated(true);
//    getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        jMenuBar = object : JMenuBar() {
            init {
                add(object : JMenu("Menu") {
                    init {
                        add(JMenuItem(actExit))
                    }
                })
            }
        }
        add(object : JToolBar() {
            init {
                name = "tbrMain"
                isFloatable = false
                add(object : JButton(actExit) {
                    init {
                        isBorderPainted = false
                        isFocusable = false
                    }
                })
            }
        }, BorderLayout.NORTH)
        val pnlColors = JPanel(FlowLayout(FlowLayout.CENTER, 3, 3))
        for (key: String? in props) pnlColors.add(ColorButton(key))
        add(object : JPanel() {
            init {
                layout = BorderLayout()
                val pnl = JScrollPane(JTree())
                add(JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnl, pnlColors), BorderLayout.CENTER)
            }
        }, BorderLayout.CENTER)
        add(JLabel("Status bar"), BorderLayout.SOUTH)
    }
} //@formatOn

/**
 * TODO .
 * @author jamming
 */
object Synthetizer {
    /**
     * TODO .
     * @throws Exception
     */
    @Throws(Exception::class)
    private fun laf() {
        try {
//            UIManager.put ("control", Color.WHITE);
//            UIManager.put ("info", Color.GRAY);
//            UIManager.put ("nimbusAlertYellow", Color.YELLOW);
//            UIManager.put ("nimbusBase", Color.GRAY);
//            UIManager.put ("nimbusDisabledText", Color.BLACK);
//            UIManager.put ("nimbusFocus", Color.WHITE);
//            UIManager.put ("nimbusGreen", Color.WHITE);
//            UIManager.put ("nimbusInfoBlue", Color.WHITE);
//            UIManager.put ("nimbusLightBackground", Color.WHITE);
//            UIManager.put ("nimbusOrange", Color.WHITE);
//            UIManager.put ("nimbusRed", Color.WHITE);
//            UIManager.put ("nimbusSelectedText", Color.BLACK);
//            UIManager.put ("nimbusSelectionBackground", Color.WHITE);
//            UIManager.put ("text", Color.WHITE);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")
            JFrame.setDefaultLookAndFeelDecorated(true)
            println(">>> " + UIManager.getLookAndFeel().supportsWindowDecorations)
        } catch (e: Exception) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            } catch (e1: Exception) {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())
            }
        }
    }

    /**
     * TODO .
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            laf()
            SwingUtilities.invokeAndWait(object : Runnable {
                override fun run() {
                    MainFrame().open()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
