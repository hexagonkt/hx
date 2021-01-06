package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.Component
import java.util.*
import javax.swing.JButton
import javax.swing.JTable
import javax.swing.event.CellEditorListener
import javax.swing.table.TableCellEditor

class ColorTableEditor : JButton(), TableCellEditor {
    override fun addCellEditorListener(cellEditorListener: CellEditorListener) {
        // TODO Pendiente de implementar
    }

    override fun cancelCellEditing() {
        // TODO Pendiente de implementar
    }

    override fun getCellEditorValue(): Any {
        return background
    }

    override fun getTableCellEditorComponent(
        table: JTable, value: Any, isSelected: Boolean, row: Int, column: Int
    ): Component? {
        return null
    }

    override fun isCellEditable(eventObject: EventObject): Boolean {
        return true
    }

    override fun removeCellEditorListener(cellEditorListener: CellEditorListener) {
        // TODO Pendiente de implementar
    }

    override fun shouldSelectCell(eventObject: EventObject): Boolean {
        return false
    }

    override fun stopCellEditing(): Boolean {
        return true
    }

    companion object {
        private const val serialVersionUID = 8645749362259594219L
    }

    init {
        isBorderPainted = false
        isOpaque = true
    }
}
