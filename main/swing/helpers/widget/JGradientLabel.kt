package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.*
import javax.swing.JLabel
import javax.swing.plaf.metal.MetalLabelUI

/**
 * Etiqueta que muestra como fondo un gradiente desde un color inicial a otro final.
 * El gradiente puede ser de un punto fijo a otro fijo, o dinámico segun se cambia
 * de tamaño la etiqueta (propiedad gradientType)
 * NOTA: para hacer que mantenga la relación, en vez de puntos, las propiedades
 * pueden ser porcentajes de donde se quiere que este el principio y el final
 * del gradiente, por ejemplo beginGradientX = 50 (50%) beginGradientY = 0 (0%)
 * @author jamming
 *
 * PENDIENTE:
 * - Se puede mejorar el rendimiento generando una imagen fija del gradiente cuando
 * se cambia algunos de sus parametros y siendo esta imagen la que se muestra
 * al pintar "paint" la etiqueta
 */
class JGradientLabel : JLabel() {
    /**
     * @author jamming
     */
    private inner class MetalGradientLabelUI : MetalLabelUI() {
        /**
         * Paint clippedText at textX, textY with background.lighter() and then
         * shifted down and to the right by one pixel with background.darker().
         */
        override fun paintDisabledText(l: JLabel, g: Graphics, s: String, textX: Int, textY: Int) {
            TextUtilities.drawOutlinedText(g, textX, textY, s, 1, Color.orange)
        }

        /**
         * Paint clippedText at textX, textY with the labels foreground color.
         */
        override fun paintEnabledText(l: JLabel, g: Graphics, s: String, textX: Int, textY: Int) {
            TextUtilities.drawOutlinedText(g, textX, textY, s, 1, Color.orange)
        }
    }

    /** Holds value of property firstGradientColor.  */
    private var firstGradientColor: Color? = null

    /** Holds value of property secondGradientColor.  */
    private var secondGradientColor: Color? = null

    /** Holds value of property gradientStartPoint.  */
    private var gradientStartPoint: Point? = null

    /** Holds value of property gradientEndPoint.  */
    private var gradientEndPoint: Point? = null
    /** Getter for property gradientType.
     * @return Value of property gradientType.
     */
    /** Setter for property gradientType.
     * @param gradientType New value of property gradientType.
     */
    /** Holds value of property gradientType.  */
    var gradientType = CUSTOM

    // Variables de uso interno
    protected var gradient: GradientPaint? = null
    protected var savedHeight = 0
    protected var savedWidth = 0

    /**
     * Getter for property firstGradientColor.
     * @return Value of property firstGradientColor.
     */
    fun getFirstGradientColor(): Color? {
        return firstGradientColor
    }

    /**
     * Getter for property gradientEndPoint.
     * @return Value of property gradientEndPoint.
     */
    fun getGradientEndPoint(): Point? {
        return gradientEndPoint
    }

    /**
     * Getter for property gradientStartPoint.
     * @return Value of property gradientStartPoint.
     */
    fun getGradientStartPoint(): Point? {
        return gradientStartPoint
    }

    /**
     * Getter for property secondGradientColor.
     * @return Value of property secondGradientColor.
     */
    fun getSecondGradientColor(): Color? {
        return secondGradientColor
    }

    /**
     * Insert the method's description here.
     * @param g java.awt.Graphics
     */
    override fun paint(g: Graphics) {
        if (isOpaque) {
            val g2d = g as Graphics2D
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            // NOTA: esto se puede poner en el evento resize para mejorar el rendimiento
            if (savedHeight != height || savedWidth != width) {
                savedHeight = height
                savedWidth = width
                updateGradient()
            }
            g2d.paint = gradient
            g2d.fillRect(0, 0, width, height)
        }
        super.paint(g)
    }

    /**
     * Setter for property firstGradientColor.
     * @param firstGradientColor New value of property firstGradientColor.
     */
    fun setFirstGradientColor(firstGradientColor: Color?) {
        requireNotNull(firstGradientColor) { "Argumento no valido para la propiedad firstGradientColor" }
        this.firstGradientColor = firstGradientColor
        updateGradient()
    }

    /**
     * Setter for property gradientEndPoint.
     * @param gradientEndPoint New value of property gradientEndPoint.
     */
    fun setGradientEndPoint(gradientEndPoint: Point?) {
        this.gradientEndPoint = gradientEndPoint
        updateGradient()
    }

    /**
     * Setter for property gradientStartPoint.
     * @param gradientStartPoint New value of property gradientStartPoint.
     */
    fun setGradientStartPoint(gradientStartPoint: Point?) {
        requireNotNull(gradientStartPoint) { "Argumento no valido para la propiedad gradientStartPoint" }
        this.gradientStartPoint = gradientStartPoint
        updateGradient()
    }

    /**
     * Setter for property secondGradientColor.
     * @param secondGradientColor New value of property secondGradientColor.
     */
    fun setSecondGradientColor(secondGradientColor: Color?) {
        requireNotNull(secondGradientColor) { "Argumento no valido para la propiedad secondGradientColor" }
        this.secondGradientColor = secondGradientColor
        updateGradient()
    }

    fun updateGradient() {
        when (gradientType) {
            CUSTOM -> {
            }
            HORIZONTAL -> {
                gradientStartPoint!!.setLocation(0, 0)
                gradientEndPoint!!.setLocation(width, 0)
            }
            VERTICAL -> {
                gradientStartPoint!!.setLocation(0, 0)
                gradientEndPoint!!.setLocation(0, height)
            }
            DIAGONAL_DOWN -> {
                gradientStartPoint!!.setLocation(0, 0)
                gradientEndPoint!!.setLocation(width, height)
            }
            DIAGONAL_UP -> {
                gradientStartPoint!!.setLocation(0, height)
                gradientEndPoint!!.setLocation(width, 0)
            }
            else -> {
            }
        }
        gradient = GradientPaint(gradientStartPoint, firstGradientColor, gradientEndPoint, secondGradientColor)
    }

    companion object {
        // Constantes que especifican el valor de los puntos de inicio y de fin
        const val RELATIVE = 0
        const val FIXED = 1
        const val CUSTOM = 0
        const val HORIZONTAL = 1
        const val VERTICAL = 2
        const val DIAGONAL_DOWN = 3
        const val DIAGONAL_UP = 4
    }

    /** Creates a new instance of JGradientLabel  */
    init {
        this.setUI(MetalGradientLabelUI())
        firstGradientColor = background
        secondGradientColor = background
        gradientStartPoint = Point(0, 0)
        gradientEndPoint = Point(0, 0)
        savedHeight = height
        savedWidth = width
        updateGradient()
    }
}
