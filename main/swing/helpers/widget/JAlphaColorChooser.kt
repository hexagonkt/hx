package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JColorChooser
import javax.swing.JFrame
import javax.swing.colorchooser.ColorSelectionModel

/**
 * Description of the Class
 *
 * @author jamming
 * @created   8 de mayo de 2001
 */
internal class JAlphaColorChooser : JColorChooser, ActionListener {
    /**
     *
     */
    constructor() : super() {
        // @todo Auto-generated constructor stub
    }

    /**
     * @param initialColor
     */
    constructor(initialColor: Color?) : super(initialColor) {        // @todo Auto-generated constructor stub
    }

    /**
     * @param model
     */
    constructor(model: ColorSelectionModel?) : super(model) {        // @todo Auto-generated constructor stub
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     * @since
     */
    override fun actionPerformed(event: ActionEvent) {
        System.exit(0)
    }

    companion object {
        /**
         * The main program for the JAlphaColorChooser class
         *
         * @param args  The command line arguments
         * @since
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val listener = JAlphaColorChooser()
            val chooser = JColorChooser()
            val frame = JFrame("Color Chooser")
            frame.show()
            /*
        AbstractColorChooserPanel[] thePanels
                 = ColorChooserComponentFactory.getDefaultChooserPanels();
        AbstractColorChooserPanel[] thePanels1 = new AbstractColorChooserPanel[2];
        System.arraycopy(thePanels, 1, thePanels1, 0, 2);
        chooser.setChooserPanels(thePanels1);
        chooser.setPreviewPanel(new JPanel());
        */
            val colorChooser = createDialog(
                frame, "Color Chooser", false, chooser, listener, listener
            )
            val panels = chooser.chooserPanels
            for (ii in panels.indices) {
                println(panels[ii].javaClass.name)
                println(panels[ii].displayName)
            }
            colorChooser.show()
        }
    }
}
