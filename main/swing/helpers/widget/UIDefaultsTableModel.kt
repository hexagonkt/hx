package com.hexagonkt.sidekick.swing.helpers.widget

import java.util.*
import javax.swing.UIManager
import javax.swing.table.AbstractTableModel

/**
 *
 * @author jamming
 */
class UIDefaultsTableModel : AbstractTableModel() {
    private var defaultsArray: Array<Array<Any?>>? = null
    private val columnNames = arrayOf("Clave", "Valor")

    /**
     * .
     * TODO Pendiente de documentar
     * @param aTable
     * @return Array
     */
    fun hashtableToArray(aTable: Hashtable<*, *>): Array<Array<Any?>> {
        val resultArray = Array(aTable.size) { arrayOfNulls<Any>(2) }
        val tableKeys = aTable.keys()
        var ii = 0
        while (tableKeys.hasMoreElements()) {
            val currentKey = tableKeys.nextElement()
            resultArray[ii][0] = currentKey
            resultArray[ii][1] = aTable[currentKey]
            ii++
        }
        return resultArray
    }

    /**
     * Returns the number of columns in the model. A
     * `JTable` uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see .getRowCount
     */
    override fun getColumnCount(): Int {
        return columnNames.size
    }

    /**
     * Returns the number of rows in the model. A
     * `JTable` uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see .getColumnCount
     */
    override fun getRowCount(): Int {
        return defaultsArray!!.size
    }

    /**
     * Returns the value for the cell at `columnIndex` and
     * `rowIndex`.
     *
     * @param   rowIndex    the row whose value is to be queried
     * @param   columnIndex     the column whose value is to be queried
     * @return  the value Object at the specified cell
     */
    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? {
        return defaultsArray!![rowIndex][columnIndex]
    }

    /**
     * @see javax.swing.table.TableModel.getColumnName
     */
    override fun getColumnName(columnIndex: Int): String {
        return columnNames[columnIndex]
    }

    /**
     * @see javax.swing.table.TableModel.getColumnClass
     */
    override fun getColumnClass(columnIndex: Int): Class<*>? {
        return getValueAt(0, columnIndex)?.javaClass
    }

    /** Creates a new instance of UIDefaultsTableModel  */
    init {
        val defaults = UIManager.getDefaults()
        defaultsArray = hashtableToArray(defaults)
    }
}
