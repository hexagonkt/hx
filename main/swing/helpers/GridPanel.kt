package com.hexagonkt.sidekick.swing.helpers

import javax.swing.JPanel
import java.awt.GridLayout
import java.awt.Component

class GridPanel : JPanel {
    constructor(vararg aComponents: Component?) : super(GridLayout()) {
        for (c in aComponents) {
            add(c)
        }
    }

    constructor(aRows: Int, aColumns: Int, vararg aComponents: Component?) : super(GridLayout(aRows, aColumns)) {
        for (c in aComponents) {
            add(c)
        }
    }

    constructor(aRows: Int, aColumns: Int, aHGap: Int, aVGap: Int, vararg aComponents: Component?) : super(
        GridLayout(
            aRows,
            aColumns,
            aHGap,
            aVGap
        )
    ) {
        for (c in aComponents) {
            add(c)
        }
    }

    companion object {
        private const val serialVersionUID = 1L
        fun columnsGridPanel(aColumns: Int, vararg aComponents: Component?): GridPanel {
            return GridPanel(0, aColumns, *aComponents)
        }

        fun rowsGridPanel(aRows: Int, vararg aComponents: Component?): GridPanel {
            return GridPanel(aRows, 0, *aComponents)
        }

        fun gridPanel(aRows: Int, aColumns: Int, vararg aComponents: Component?): GridPanel {
            return GridPanel(aRows, aColumns, *aComponents)
        }

        fun columnsGridPanel(
            aColumns: Int, aHGap: Int, aVGap: Int, vararg aComponents: Component?
        ): GridPanel {
            return GridPanel(0, aColumns, aHGap, aVGap, *aComponents)
        }

        fun rowsGridPanel(
            aRows: Int, aHGap: Int, aVGap: Int, vararg aComponents: Component?
        ): GridPanel {
            return GridPanel(aRows, 0, aHGap, aVGap, *aComponents)
        }

        fun gridPanel(
            aRows: Int, aColumns: Int, aHGap: Int, aVGap: Int, vararg aComponents: Component?
        ): GridPanel {
            return GridPanel(aRows, aColumns, aHGap, aVGap, *aComponents)
        }
    }
}
