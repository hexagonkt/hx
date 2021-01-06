package com.hexagonkt.sidekick.swing.helpers

import javax.swing.AbstractAction
import javax.swing.KeyStroke
import javax.swing.Icon

/**
 * TODO .
 *
 * @author jam
 */
abstract class BaseAction : AbstractAction() {
    fun setTexts(name: String?, description: String?) {
        setName(name)
        setDescription(description)
    }

    fun setTexts(name: String?, description: String?, longDescription: String?) {
        setName(name)
        setDescription(description)
        setLongDescription(longDescription)
    }

    private fun setName(name: String?) {
        if (name != null) putValue(NAME, name)
    }

    private fun setDescription(description: String?) {
        if (description != null) putValue(SHORT_DESCRIPTION, description)
    }

    private fun setLongDescription(longDescription: String?) {
        if (longDescription != null) putValue(LONG_DESCRIPTION, longDescription)
    }

    fun setKeys(mnemonic: Int, accelerator: KeyStroke?) {
        setMnemonic(mnemonic)
        setAccelerator(accelerator)
    }

    private fun setAccelerator(accelerator: KeyStroke?) {
        if (accelerator != null) putValue(ACCELERATOR_KEY, accelerator)
    }

    private fun setMnemonic(mnemonic: Int) {
        if (mnemonic < 0) putValue(MNEMONIC_KEY, mnemonic)
    }

    fun setIcons(icon: Icon?, largeIcon: Icon?) {
        setIcon(icon)
        setLargeIcon(largeIcon)
    }

    private fun setIcon(icon: Icon?) {
        if (icon != null) putValue(SMALL_ICON, icon)
    }

    private fun setLargeIcon(icon: Icon?) {
        if (icon != null) putValue(LARGE_ICON_KEY, icon)
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
