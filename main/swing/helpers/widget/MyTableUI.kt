package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.Point
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JTable
import javax.swing.event.MouseInputListener
import javax.swing.plaf.basic.BasicTableUI

/**
 * Description of the Class
 *
 * @author jamming
 * @created   17 de abril de 2001
 */
class MyTableUI : BasicTableUI() {
    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     * @since
     */
    override fun createMouseInputListener(): MouseInputListener {
        return MyMouseInputHandler()
    }

    /**
     * Description of the Class
     *
     * @author jamming
     * @created   17 de abril de 2001
     */
    internal inner class MyMouseInputHandler : MouseInputHandler() {
        /**
         * Description of the Method
         *
         * @param e  Description of Parameter
         * @since
         */
        override fun mouseClicked(e: MouseEvent) {
            val theTable = e.component as JTable
            val rowIndex = theTable.rowAtPoint(Point(e.x, e.y))
            if (theTable.isRowSelected(rowIndex)) {
                theTable.removeRowSelectionInterval(rowIndex, rowIndex)
            } else {
                theTable.addRowSelectionInterval(rowIndex, rowIndex)
            }
        }

        /**
         * Este metodo es sobreescrito para que no ejecute las acciones por
         * defecto del L&F de la JTable
         *
         * @param e  Description of Parameter
         * @since
         */
        override fun mousePressed(e: MouseEvent) {
            // TODO Pendiente de implementar
        }

        /**
         * Exactamente igual que el metodo anterior
         *
         * @param e  Description of Parameter
         * @since
         */
        override fun mouseReleased(e: MouseEvent) {
            // TODO Pendiente de implementar
        }
    }

    companion object {
        /**
         * The main program for the UIDefaultsMain class
         *
         * @param args  The command line arguments
         * @since
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val datos = Array(4) { arrayOfNulls<Any>(3) }
            datos[0][0] = "Aguililla"
            datos[0][1] = "Martinez"
            datos[0][2] = "Alicia"
            datos[1][0] = "Martinez"
            datos[1][1] = "Perez"
            datos[1][2] = "Luis"
            datos[2][0] = "Gonzalez"
            datos[2][1] = "Rodrigez"
            datos[2][2] = "Manolo"
            datos[3][0] = "Nito"
            datos[3][1] = "Del Bosque"
            datos[3][2] = "Helena"
            val columnas = arrayOfNulls<String>(3)
            columnas[0] = "Primer Apellido"
            columnas[1] = "Segundo Apellido"
            columnas[2] = "Nombre"
            val tabla = JTable(datos, columnas)
            tabla.setUI(MyTableUI())
            val ventana = JFrame("Prueba Tabla")
            ventana.contentPane.add(tabla)
            ventana.pack()
            ventana.isVisible = true
        }
    }
}
