package com.hexagonkt.sidekick.swing.helpers.widget

import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.JSlider
import javax.swing.JSpinner
import javax.swing.colorchooser.AbstractColorChooserPanel
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener

/**
 *
 * @author jamming
 * @version
 */
class AlphaChooserPanel : AbstractColorChooserPanel(), ChangeListener {
    protected var lblAlphaLabel: JLabel? = null
    protected var sldAlphaSlider: JSlider? = null
    protected var spnAlphaSpinner: JSpinner? = null
    override fun buildChooser() {
        lblAlphaLabel = JLabel("Transparencia")
        sldAlphaSlider = JSlider()
    }

    override fun getDisplayName(): String {
        return ""
    }

    override fun getLargeDisplayIcon(): Icon? {
        return null
    }

    override fun getSmallDisplayIcon(): Icon? {
        return null
    }

    override fun updateChooser() {}

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e  a ChangeEvent object
     */
    override fun stateChanged(e: ChangeEvent) {}
}
