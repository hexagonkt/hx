package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.*
import javax.swing.Icon
import javax.swing.JPanel

/**
 *
 * @author jamming
 */
class JImagePanel(thePaintMethod: Int, theImage: Icon?) : JPanel() {
    private val imageX = 0
    private val imageY = 0

    /** Holds value of property image.  */
    private var image: Icon? = null
    /**
     * Getter for property paintMethod.
     * @return Value of property paintMethod.
     */
    /**
     * Setter for property paintMethod.
     * @param paintMethod New value of property paintMethod.
     */
    /** Holds value of property paintMethod.  */
    var paintMethod = TILE
        set(paintMethod) {
            field = paintMethod
            when (paintMethod) {
                TILE -> {
                }
                else -> field = TILE
            }
        }

    /**
     * Getter for property image.
     * @return Value of property image.
     */
    fun getImage(): Icon? {
        return image
    }

    override fun paint(gfx: Graphics) {
        if (image != null && !this.isOpaque) {
            image!!.paintIcon(this, gfx, imageX, imageY)
        }
        super.paint(gfx)
    }

    /**
     * Setter for property image.
     * @param image New value of property image.
     */
    fun setImage(image: Icon?) {
        requireNotNull(image) { "Argumento para la propiedad image no valido" }
        this.image = image
        this.repaint()
    }

    companion object {
        /**   */
        const val TILE = 0
        const val SCALE = 1
        const val SCALE_MAXFACTOR = 2
        const val TOP_LEFT = 3
        const val TOP_CENTER = 4
        const val TOP_RIGHT = 5
        const val MIDDLE_LEFT = 6
        const val MIDDLE_CENTER = 7
        const val MIDDLE_RIGHT = 8
        const val BOTTOM_LEFT = 9
        const val BOTTOM_CENTER = 10
        const val BOTTOM_RIGHT = 11
    }

    // PENDIENTE: almacenar la imagen internamente como un objeto Graphics2D
    // que es más flexible y da mejor rendimiento
    // PENDIENTE: copiar todos los constructores
    init {
        paintMethod = thePaintMethod
        setImage(theImage)
    }
}
