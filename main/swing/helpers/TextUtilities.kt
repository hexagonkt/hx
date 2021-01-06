package com.hexagonkt.sidekick.swing.helpers

import java.awt.*
import java.io.*
import kotlin.Throws

/**
 * @author jamming
 */
object TextUtilities {
    private const val TRUETYPE_EXTENSION = ".ttf"
    fun drawOutlinedText(
        gfx: Graphics, x: Int, y: Int, text: String?, outlineOffset: Int, outlineColor: Color?
    ) {
        // PENDIENTE: hacer que la posición en la que se dibuja sea la del borde,
        // y no la del texto dibujado dentro de los bordes
        if (gfx is Graphics2D) {
            var gfx2D: Graphics2D? = gfx
            gfx2D!!.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            )
            gfx2D = null
        }
        val initialColor = gfx.color
        gfx.color = outlineColor
        gfx.drawString(text, x - outlineOffset, y - outlineOffset)
        gfx.drawString(text, x + outlineOffset, y - outlineOffset)
        gfx.drawString(text, x - outlineOffset, y + outlineOffset)
        gfx.drawString(text, x + outlineOffset, y + outlineOffset)
        gfx.color = initialColor
        gfx.drawString(text, x, y)
    }

    fun drawShadowedText(
        gfx: Graphics?, x: Int, y: Int, text: String?, shadowXOffset: Int, shadowYOffset: Int, shadowColor: Color?
    ) {
        require(!(gfx == null || x < 0 || y < 0 || text == null || shadowColor == null)) { "" }
        // Esto mejor debe estar en el componente que muestre el texto
        if (gfx is Graphics2D) {
            var gfx2D = gfx as Graphics2D?
            gfx2D!!.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            )
            gfx2D = null
        }
        val initialColor = gfx.color
        gfx.color = shadowColor
        gfx.drawString(text, x + shadowXOffset, y + shadowYOffset)
        gfx.color = initialColor
        gfx.drawString(text, x, y)
    }

    fun drawStripedText(
        gfx: Graphics?, x: Int, y: Int, text: String?, separtion: Int, stripesColor: Color?
    ) {
    }

    /**
     * @param fontsDir .
     * @throws FileNotFoundException e.
     * @throws IOException e.
     * @throws FontFormatException e.
     */
    @Deprecated("")
    @Throws(FileNotFoundException::class, IOException::class, FontFormatException::class)
    fun loadFonts(fontsDir: File) {
        require(fontsDir.isDirectory) { "" }
        val fonts = fontsDir.listFiles { dir, name -> name.toLowerCase().endsWith(TRUETYPE_EXTENSION) }
        var currentFontFile: FileInputStream? = null
        for (ii in fonts.indices) {
            currentFontFile = FileInputStream(fonts[ii])
            Font.createFont(Font.TRUETYPE_FONT, currentFontFile)
            currentFontFile.close()
        }
    }
}
