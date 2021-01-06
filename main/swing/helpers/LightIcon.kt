package com.hexagonkt.sidekick.swing.helpers

import javax.swing.Icon
import java.awt.Color
import java.awt.Component
import java.awt.Graphics

/**
 * TODO .
 * TODO Create only two, one for enabled and one for disabled and share them.
 * Square icon with a color
 * @author jamming
 */
class LightIcon private constructor(private val mColor: Color, private val mSideLenght: Int) : Icon {
    override fun paintIcon(aC: Component, aG: Graphics, aX: Int, aY: Int) {
        aG.color = mColor
        aG.fillRect(aX, aY, mSideLenght, mSideLenght)
    }

    override fun getIconWidth(): Int {
        return mSideLenght
    }

    override fun getIconHeight(): Int {
        return mSideLenght
    }

    companion object {
        @JvmStatic
        operator fun get(aColor: Color, aSideLenght: Int): LightIcon {
            return LightIcon(aColor, aSideLenght)
        }
    }
}
