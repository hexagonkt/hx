package com.hexagonkt.sidekick.swing.helpers

import java.awt.event.ActionEvent
import java.awt.event.InputEvent
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.JOptionPane
import java.lang.System
import javax.swing.KeyStroke

class ExitAction(aFrame: JFrame?) : BaseAction() {
    private val mFrame: JFrame
    override fun actionPerformed(aE: ActionEvent?) {
        val dialogAnswer = JOptionPane.showConfirmDialog(
            mFrame, "Are you sure you want to exit?", "Exit application", JOptionPane.YES_NO_OPTION
        )
        if (dialogAnswer == JOptionPane.YES_OPTION) {
            mFrame.dispose()
            System.exit(0)
        }
    }

    companion object {
        private const val serialVersionUID = 1L
    }

    init {
        requireNotNull(aFrame) { "Exit action needs a non null frame" }
        mFrame = aFrame
        setKeys(KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK))
        setTexts("Exit \u2026", "Exit the application")
    }
}
