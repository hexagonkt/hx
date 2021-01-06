package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.BorderLayout
import java.awt.Component
import java.awt.Font
import java.awt.GraphicsEnvironment
import javax.swing.*
import javax.swing.border.Border
import javax.swing.border.EmptyBorder

/**
 * Description of the Class
 *
 * @author jamming
 * @created   13 de mayo de 2001
 */
class JFontList : JList<Any?>(GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames) {
    /**
     * Description of the Class
     *
     * @author jamming
     * @created   13 de mayo de 2001
     */
    internal inner class FontListRenderer : JLabel(), ListCellRenderer<Any?> {
        protected var noFocusBorder: Border

        /**
         * Gets the ListCellRendererComponent attribute of the
         * FontComboBoxRenderer object
         *
         * @param list          Description of Parameter
         * @param value         Description of Parameter
         * @param index         Description of Parameter
         * @param isSelected    Description of Parameter
         * @param cellHasFocus  Description of Parameter
         * @return              The ListCellRendererComponent value
         * @since
         */
        override fun getListCellRendererComponent(
            list: JList<*>,
            value: Any?,
            index: Int,
            isSelected: Boolean,
            cellHasFocus: Boolean
        ): Component {
            val popupFont = list.font
            border = if (cellHasFocus) UIManager.getBorder("List.focusCellHighlightBorder") else noFocusBorder
            isEnabled = list.isEnabled
            componentOrientation = list.componentOrientation
            font = Font(value as String?, popupFont.style, popupFont.size)
            text = value
            if (isSelected) {
                background = list.selectionBackground
                foreground = list.selectionForeground
            } else {
                background = list.background
                foreground = list.foreground
            }
            return this
        }

        init {
            isOpaque = true
            noFocusBorder = EmptyBorder(1, 1, 1, 1)
        }
    }

    companion object {
        @JvmStatic
        fun main(aArgs: Array<String>) {
            val f = JFrame("Test")
            f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            f.rootPane.layout = BorderLayout()
            f.rootPane.add(JFontList(), BorderLayout.CENTER)
            f.pack()
            f.isVisible = true
        }
    }

    /**
     * Constructor for the FontComboBox object
     *
     * @since
     */
    init {
        selectionMode = ListSelectionModel.SINGLE_SELECTION
        cellRenderer = FontListRenderer()
    }
}
