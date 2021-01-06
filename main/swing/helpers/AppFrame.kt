package com.hexagonkt.sidekick.swing.helpers

import javax.swing.JFrame

/**
 * TODO .
 * @author jam
 */
class AppFrame : JFrame {
    /**
     * TODO .
     * @throws HeadlessException
     */
    constructor() : super() {}

    /**
     * TODO .
     * @param aTitle
     * @throws HeadlessException
     */
    constructor(aTitle: String?) : super(aTitle) {}

    /**
     * TODO .
     */
    fun open() {
        pack()
        isVisible = true
    }

    companion object {
        private const val serialVersionUID = 1L
    }

    // TODO Fullscreen
    // TODO Ask to exit app
    // TODO Maximize only height or width
    // TODO Stack to a border
    // TODO Center
    // TODO Create default structure (menu, toolbar, statusbar) each part will be optional
    // TODO Store load coordinates and state
    // TODO Splashscreen (see java.awt.SplashScreen)
    // TODO Close procedure
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
    }
}
