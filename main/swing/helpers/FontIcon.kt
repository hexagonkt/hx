package com.hexagonkt.sidekick.swing.helpers

import java.awt.*
import java.awt.font.LineMetrics
import java.awt.image.BufferedImage
import javax.swing.Icon

private const val DEBUG = false

class FontIcon(
    aFontPath: String,
    aColor: Color,
    aChar: Char,
    private val aSideLength: Int
) : Icon {

    private val fontStream = javaClass.getResourceAsStream(aFontPath)
    private val mFont: Font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(aSideLength.toFloat())
    private val mColor: Color = aColor
    private val mChar: Char = aChar
    private val mImage: BufferedImage = BufferedImage(iconHeight, iconHeight, BufferedImage.TYPE_INT_ARGB)

    override fun getIconWidth(): Int =
        aSideLength

    override fun getIconHeight(): Int =
        aSideLength

    init {
        render()
    }

    private fun render() {
        // Draw in an image
        val chars = charArrayOf(mChar) // Used by J2D API
        val gfx: Graphics2D = mImage.graphics as Graphics2D
        val lm: LineMetrics = mFont.getLineMetrics(
            chars, 0, 1, gfx.fontMetrics.fontRenderContext
        )

        // Debugging
        if (DEBUG) {
            gfx.color = Color.RED
            gfx.drawRect(0, 0, iconHeight - 1, iconHeight - 1)
//            log.debug(
//                String.format(
//                    ">>> Chars %d Ascend %f Descent %f (%d) Height %f Leading %f",
//                    lm.getNumChars(),
//                    lm.getAscent(),
//                    lm.getDescent(),
//                    lm.getDescent() as Int,
//                    lm.getHeight(),
//                    lm.getLeading()
//                )
//            )
        }

        // Setup rendering quality
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        gfx.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

        // Fonts and colors
        gfx.font = mFont
        gfx.color = mColor

        // Calculate text size and position
        val bl = mFont.getBaselineFor(mChar)
        val w: Int = gfx.getFontMetrics(mFont).stringWidth(String(chars))
        val x = (iconHeight - w) / 2
        val y = iconHeight - lm.descent as Int - bl - 1 // The last '- 1' is to 'center'
        gfx.drawChars(chars, 0, 1, x, y)
    }

    override fun paintIcon(aC: Component, aG: Graphics, aX: Int, aY: Int) {
        aG.drawImage(mImage, aX, aY, null)
    }
}
