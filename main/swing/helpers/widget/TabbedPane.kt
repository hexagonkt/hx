package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.event.ActionEvent
import java.util.*
import javax.swing.*

/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
class TabbedPane : JTabbedPane() {
    /**
     * .
     * TODO Pendiente de documentar
     * @author jamming
     */
    internal inner class ChangeTabbedPaneAction : AbstractAction() {
        /**
         * @see java.awt.event.ActionListener.actionPerformed
         */
        override fun actionPerformed(event: ActionEvent) {
            val mnemonic = event.actionCommand
            val tabIndex = (tabMnemonics[mnemonic.toUpperCase()] as Int?)!!.toInt()
            (event.source as TabbedPane).selectedIndex = tabIndex
        }
    }

    /** TODO Pendiente de documentar.  */
    var tabbedPaneInputMap: InputMap? = null

    /** TODO Pendiente de documentar.  */
    var tabbedPaneActionMap: ActionMap? = null
    // NOTA: usar array de caracteres en vez de Hashtable
    /** TODO Pendiente de documentar.  */
    var tabMnemonics: Hashtable<String, Int> = Hashtable<String, Int>()

    /**
     * .
     * TODO Pendiente de documentar
     * @param tabIndex
     * @param tabMnemonic
     */
    fun setMnemonic(tabIndex: Int, tabMnemonic: String) {
        // PENDIENTE: pasar a mayúsculas tabMnemonic (no funciona si se le pasa
        // una minúscula)
        // PENDIENTE: controlar que el indice exista
        // PENDIENTE: controlar que no se repiten teclas aceleradoras
        tabbedPaneInputMap!!.put(
            KeyStroke.getKeyStroke(tabMnemonic[0].toInt(), ActionEvent.ALT_MASK, false),
            "changeTabbedPane"
        )
        tabMnemonics[tabMnemonic] = tabIndex
    }

    companion object {
        /**
         * .
         * TODO Pendiente de documentar
         * @param args
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val frame = JFrame("")
            val tabbedPane = TabbedPane()
            tabbedPane.add("A", JLabel("Pulsa Alt + Vocal"))
            tabbedPane.add("E", JLabel("No quiero sus pestañas!!!"))
            tabbedPane.add("I", JLabel("A la mierda!!!"))
            tabbedPane.add("O", JLabel("A Carrasco le gustan \"Los Chungitos\""))
            tabbedPane.add("U", JLabel("Y a Pitican le gusta lamer culos"))
            tabbedPane.setMnemonic(0, "A")
            tabbedPane.setMnemonic(1, "E")
            tabbedPane.setMnemonic(2, "I")
            tabbedPane.setMnemonic(3, "O")
            tabbedPane.setMnemonic(4, "U")
            frame.contentPane.add(tabbedPane)
            frame.pack()
            frame.isVisible = true
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    init {
        tabbedPaneInputMap = SwingUtilities.getUIInputMap(this, WHEN_IN_FOCUSED_WINDOW)
        if (tabbedPaneInputMap == null) {
            tabbedPaneInputMap = InputMap()
            SwingUtilities.replaceUIInputMap(this, WHEN_FOCUSED, tabbedPaneInputMap)
        }
        tabbedPaneActionMap = SwingUtilities.getUIActionMap(this)
        if (tabbedPaneActionMap == null) {
            tabbedPaneActionMap = ActionMap()
        }
        tabbedPaneActionMap!!.put("changeTabbedPane", ChangeTabbedPaneAction())
        SwingUtilities.replaceUIActionMap(this, tabbedPaneActionMap)
    }
}
