package com.hexagonkt.sidekick.swing.helpers

import java.lang.IllegalArgumentException
import java.awt.Color
import java.lang.Exception
import javax.swing.UIManager
import kotlin.Throws
import javax.swing.UnsupportedLookAndFeelException
import javax.swing.BorderFactory
import javax.swing.plaf.metal.MetalLookAndFeel
import javax.swing.plaf.metal.OceanTheme
import javax.swing.plaf.ColorUIResource
import javax.swing.JFrame
import java.lang.IllegalStateException
import javax.swing.border.BevelBorder

/**
 * TODO .
 * @author jamming
 */
class LaF private constructor() {
    companion object {
        fun setLaFProperties(vararg aElement: Any?) {
            require(aElement.size % 2 == 0)
            var ii = 0
            while (ii < aElement.size) {
                setLaFProperty(aElement[ii], aElement[ii + 1])
                ii += 2
            }
        }

        fun setLaFProperty(aKey: Any?, aValue: Any?) {
            val k = UIManager.get(aKey) ?: throw IllegalArgumentException()
            UIManager.put(aKey, aValue)
        }

        @Throws(UnsupportedLookAndFeelException::class)
        fun setupOceanLaF() {
            UIManager.put("RootPane.frameBorder", BorderFactory.createBevelBorder(BevelBorder.RAISED))
            MetalLookAndFeel.setCurrentTheme(object : OceanTheme() {
                override fun getWindowTitleBackground(): ColorUIResource {
                    return ColorUIResource(Color(0xe2f4fd))
                }
            })
            UIManager.setLookAndFeel(MetalLookAndFeel())
            JFrame.setDefaultLookAndFeelDecorated(true)
        }

        fun setupPlatformLaf() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            } catch (e: Exception) {
                val message = "Platform look and feel could not be loaded, falling back to default"
                println(message + e.message)
            }
        }
    }

    init {
        throw IllegalStateException("This class is not meant to be instantiated")
    }
}
