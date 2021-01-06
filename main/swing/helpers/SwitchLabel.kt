package com.hexagonkt.sidekick.swing.helpers

import com.hexagonkt.sidekick.swing.helpers.BoxPanel.Companion.verticalBoxPanel
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.border.LineBorder

internal class LightBorder(aColor: Color?, aThickness: Int, aRounded: Boolean) :
    LineBorder(aColor, aThickness, aRounded) {
    companion object {
        private const val serialVersionUID = 1L
        operator fun get(aColor: Color?, aSideLenght: Int): LightBorder {
            return LightBorder(aColor, aSideLenght, false)
        }
    }
}

/**
 * TODO .
 * @author jamming
 */
class SwitchLabel : JLabel {
    private var mShowIcon = false
    private var mShowBorder = false
    private var mLightBackground = false
    private var mLightText = false
    private var mShadeText = false
    private var mOn = false
    private var mShade: Color? = null
    private var mFore: Color? = null
    private var mBack: Color? = null
    private fun shade(aColor: Color?): Color {
        return Color(aColor!!.rgb).brighter().brighter().brighter()
    }

    constructor() {
        updateSwitch()
        setIconTextGap(ICON_TEXT_GAP) // TODO
    }

    constructor(aOn: Boolean) {
        isOn = aOn
        updateSwitch()
        setIconTextGap(ICON_TEXT_GAP) // TODO
    }

    constructor(aText: String?) : super(aText) {
        updateSwitch()
        setIconTextGap(ICON_TEXT_GAP) // TODO
    }

    constructor(aOn: Boolean, aText: String?) : super(aText) {
        isOn = aOn
        updateSwitch()
        setIconTextGap(ICON_TEXT_GAP) // TODO
    }

    var isShowIcon: Boolean
        get() = mShowIcon
        set(aShowIcon) {
            if (mShowIcon != aShowIcon) {
                mShowIcon = aShowIcon
                val c = if (mOn) sOnColor else sOffColor
                setIcon(if (mShowIcon) LightIcon[c, sIconSide] else null)
            }
        }
    var isShowBorder: Boolean
        get() = mShowBorder
        set(aShowBorder) {
            if (mShowBorder != aShowBorder) {
                mShowBorder = aShowBorder
                val c = if (mOn) sOnColor else sOffColor
                setBorder(if (mShowBorder) LightBorder[c, sBorderWidth] else null)
            }
        }
    var isLightBackground: Boolean
        get() = mLightBackground
        set(aLightBackground) {
            if (mLightBackground != aLightBackground) {
                mLightBackground = aLightBackground
                if (mLightBackground && mBack == null) {
                    mBack = getBackground()
                }
                val c = if (mOn) sOnColor else sOffColor
                setBackground(if (mLightBackground) c else mBack)
                setOpaque(mLightBackground)
            }
        }
    var isLightText: Boolean
        get() = mLightText
        set(aLightText) {
            if (mLightText != aLightText) {
                mLightText = aLightText
                val c = if (mOn) sOnColor else sOffColor
                setForeground(if (mLightText) c else mFore)
            }
        }
    var isShadeText: Boolean
        get() = mShadeText
        set(aShadeText) {
            if (mShadeText != aShadeText) {
                mShadeText = aShadeText
                if (mShadeText && mFore == null) {
                    mFore = getForeground()
                    mShade = shade(mFore)
                }
                val c = if (mOn) mFore else mShade
                setForeground(if (mShadeText) c else mFore)
            }
        }
    var isOn: Boolean
        get() = mOn
        set(aOn) {
            if (aOn != mOn) {
                mOn = aOn
                updateSwitch()
            }
        }

    private fun updateSwitch() {
        val c = if (mOn) sOnColor else sOffColor
        if (mShowIcon) {
            setIcon(LightIcon[c, sIconSide])
        }
        if (mShowBorder) {
            setBorder(LightBorder[c, sBorderWidth])
        }
        if (mLightBackground) {
            setBackground(c)
        }
        if (mLightText) {
            setForeground(c)
        }
        if (mShadeText) {
            setForeground(if (mOn) mFore else mShade)
        }
    }

    fun showIcon(aShowIcon: Boolean): SwitchLabel {
        isShowIcon = aShowIcon
        return this
    }

    fun showBorder(aShowBorder: Boolean): SwitchLabel {
        isShowBorder = aShowBorder
        return this
    }

    fun lightBackground(aLightBackground: Boolean): SwitchLabel {
        isLightBackground = aLightBackground
        return this
    }

    fun lightText(aLightText: Boolean): SwitchLabel {
        isLightText = aLightText
        return this
    }

    fun shadeText(aShadeText: Boolean): SwitchLabel {
        isShadeText = aShadeText
        return this
    }

    fun horizontalAlignment(aPosition: Int): SwitchLabel {
        setHorizontalAlignment(aPosition)
        return this
    }

    fun horizontalTextPosition(aTextPosition: Int): SwitchLabel {
        setHorizontalTextPosition(aTextPosition)
        return this
    }

    fun font(aFont: Font?): SwitchLabel {
        setFont(aFont)
        return this
    }

    protected override fun paintComponent(aG: Graphics) {
        // Hack to display the icon aligned to the right
        if (mShowIcon && getHorizontalTextPosition() == SwingConstants.LEFT) {
            val g2d: Graphics2D = aG as Graphics2D
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            val fm: FontMetrics = g2d.getFontMetrics()
            val mid: Int = getHeight() / 2
            //            Rectangle2D stringBounds = fm.getStringBounds (getText(), g2d);
            g2d.drawString(getText(), (getWidth() * 0.2) as Int, mid + fm.ascent / 2)
            val icon: Icon = getIcon()
            icon.paintIcon(this, g2d, getWidth() - icon.getIconWidth(), mid - icon.getIconHeight() / 2)
        } else {
            super.paintComponent(aG)
        }
    }

    companion object {
        private const val serialVersionUID = 1L
        private const val ICON_TEXT_GAP = 20
        private const val ICON_SIDE = 16
        private const val BORDER_WIDTH = 8

        // Shared switch properties
        private val sOnColor = Color.GREEN
        private val sOffColor = Color.RED
        private const val sBorderWidth = BORDER_WIDTH
        private const val sIconSide = ICON_SIDE
        private fun switchTest() {
            val appFrm = ApplicationFrame("Switch test", WindowConstants.DISPOSE_ON_CLOSE)
            val def = SwitchLabel("Default")
            val ico = SwitchLabel("Icon").showIcon(true)
            val bor = SwitchLabel("Border").showBorder(true)
            val bg = SwitchLabel("Background").lightBackground(true)
            val fg = SwitchLabel("Text").lightText(true)
            val sha = SwitchLabel("Shade").shadeText(true)
            val btn = JButton("Change")
            btn.addActionListener(ActionListener { aE: ActionEvent? ->
                def.isOn = !def.isOn
                ico.isOn = !ico.isOn
                bor.isOn = !bor.isOn
                bg.isOn = !bg.isOn
                fg.isOn = !fg.isOn
                sha.isOn = !sha.isOn
            })
            appFrm.setContentPane(verticalBoxPanel(def, ico, bor, bg, fg, sha, btn))
            appFrm.open()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val defaultFont = Font("Arial", Font.BOLD, 20)
            LaF.setLaFProperties(
                "Label.font", defaultFont,
                "Button.font", defaultFont,
                "TextField.font", defaultFont
            )
            EventQueue.invokeLater { switchTest() }
        }
    }
}
