package com.hexagonkt.sidekick.swing.helpers

import javax.swing.JPanel
import javax.swing.border.EmptyBorder
import java.awt.Component
import javax.swing.Box
import javax.swing.JComponent
import javax.swing.BoxLayout

class BoxPanel(aAxis: Int, vararg aComponents: Component) : JPanel() {
    constructor(aAxis: Int, aPadding: Int, vararg aComponents: Component) :
        this(aAxis, *aComponents) {

        border = EmptyBorder(aPadding, aPadding, aPadding, aPadding)
    }

    override fun add(aComponent: Component): BoxPanel {
        super.add(aComponent)
        return this
    }

    fun addAlignedX(aComponent: JComponent, aAlignmentX: Float): BoxPanel {
        aComponent.alignmentX = aAlignmentX
        return add(aComponent)
    }

    fun addAlignedRight(aComponent: JComponent): BoxPanel {
        return addAlignedX(aComponent, RIGHT_ALIGNMENT)
    }

    fun addHorizontalStrut(aWidth: Int): BoxPanel {
        return add(Box.createHorizontalStrut(aWidth))
    }

    fun addGlue(): BoxPanel {
        return add(Box.createGlue())
    }

    companion object {
        private const val serialVersionUID = 1L
        fun horizontalBoxPanel(aPadding: Int, vararg aComponents: Component): BoxPanel {
            return BoxPanel(BoxLayout.LINE_AXIS, aPadding, *aComponents)
        }

        fun verticalBoxPanel(aPadding: Int, vararg aComponents: Component): BoxPanel {
            return BoxPanel(BoxLayout.PAGE_AXIS, aPadding, *aComponents)
        }

        fun horizontalBoxPanel(vararg aComponents: Component): BoxPanel {
            return BoxPanel(BoxLayout.LINE_AXIS, *aComponents)
        }

        fun verticalBoxPanel(vararg aComponents: Component): BoxPanel {
            return BoxPanel(BoxLayout.PAGE_AXIS, *aComponents)
        }
    }

    init {
        layout = BoxLayout(this, aAxis)
        for (c in aComponents) {
            add(c)
        }
    }
}
