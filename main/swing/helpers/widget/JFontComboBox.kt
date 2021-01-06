package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.Component
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.io.Serializable
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer
import javax.swing.border.EmptyBorder

/**
 * Description of the Class
 *
 * PENDIENTE: al seleccionar una fuente la opcion seleccionada no se dibuja con
 * la fuente elegida (cambiar la propiedad font del combo al cambiar la
 * seleccion)
 *
 * @author jamming
 * @created   13 de mayo de 2001
 */
class JFontComboBox : JComboBox<Any?>(GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames) {
    /**
     * Description of the Class
     *
     * ERROR: al abrir el combo por primera vez el borde inferior de la lista no
     * se pinta
     *
     * @author jamming
     * @created   13 de mayo de 2001
     */
    /*
    class MetalFontComboBoxUI extends MetalComboBoxUI
    {
        public void itemStateChanged(ItemEvent event)
        {
            System.out.println(event.getItem().toString());
            //comboBox.getModel().getSelectedItem();
        }
    }
     */
    internal inner class FontComboBoxRenderer : JLabel(), ListCellRenderer<Any?>, Serializable {
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
            border = EmptyBorder(1, 1, 1, 1)
        }
    }

    /**
     * Constructor for the FontComboBox object
     *
     * @since
     */
    init {
        setRenderer(FontComboBoxRenderer())
    }
}
