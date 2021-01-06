package com.hexagonkt.sidekick.swing.helpers

import javax.swing.event.AncestorListener
import javax.swing.event.AncestorEvent
import javax.swing.JComponent
import javax.swing.border.EmptyBorder
import javax.swing.border.CompoundBorder
import java.awt.Dimension
import java.awt.Insets
import javax.swing.border.Border

open class AncestorAdapter : AncestorListener {
    override fun ancestorAdded(aEvent: AncestorEvent) {}
    override fun ancestorRemoved(aEvent: AncestorEvent) {}
    override fun ancestorMoved(aEvent: AncestorEvent) {}
}

fun setMargin(
    aComponent: JComponent, aTop: Int, aRight: Int, aBottom: Int, aLeft: Int
) {
    val border = aComponent.border
    val marginBorder: Border = EmptyBorder(Insets(aTop, aLeft, aBottom, aRight))
    aComponent.border = border?.let { CompoundBorder(marginBorder, it) } ?: marginBorder
}

fun setShrinkable(
    aComponent: JComponent, aHorizontal: Boolean, aVertical: Boolean
) {
    aComponent.addAncestorListener(object : AncestorAdapter() {
        override fun ancestorAdded(aEvent: AncestorEvent) {
            val preferred = aComponent.preferredSize
            aComponent.minimumSize = Dimension(
                if (aHorizontal) 0 else preferred.width,
                if (aVertical) 0 else preferred.height
            )
            aComponent.removeAncestorListener(this)
        }
    })
}

fun setExpandable(
    aComponent: JComponent, aHorizontal: Boolean, aVertical: Boolean
) {
    aComponent.addAncestorListener(object : AncestorAdapter() {
        override fun ancestorAdded(aEvent: AncestorEvent) {
            val preferred = aComponent.preferredSize
            aComponent.maximumSize = Dimension(
                if (aHorizontal) Int.MAX_VALUE else preferred.width,
                if (aVertical) Int.MAX_VALUE else preferred.height
            )
            aComponent.removeAncestorListener(this)
        }
    })
}
