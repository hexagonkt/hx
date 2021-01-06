package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.Color
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.table.TableCellRenderer

class ColorTableRenderer : JLabel(), TableCellRenderer {
    override fun getTableCellRendererComponent(
        table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int
    ): Component {
        background = value as Color
        return this
    }

    companion object {
        private const val serialVersionUID = -6575044549440253586L
    }

    init {
        isOpaque = true
    }
}
