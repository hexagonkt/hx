package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.event.ActionListener
import javax.swing.JColorChooser
import javax.swing.JPanel
import javax.swing.colorchooser.AbstractColorChooserPanel
import javax.swing.colorchooser.ColorChooserComponentFactory

internal object ColorChooserDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val listener = ActionListener { System.exit(0) }
        val chooser = JColorChooser()
        val thePanels = ColorChooserComponentFactory.getDefaultChooserPanels()
        val thePanels1 = arrayOfNulls<AbstractColorChooserPanel>(2)
        System.arraycopy(thePanels, 1, thePanels1, 0, 2)
        chooser.chooserPanels = thePanels1
        chooser.previewPanel = JPanel()
        val colorChooser = JColorChooser.createDialog(null, "Color Chooser", false, chooser, listener, listener)
        for (cp in chooser.chooserPanels) {
            println(cp.javaClass.name)
            println(cp.displayName)
        }
        colorChooser.isVisible = true
    }
}
