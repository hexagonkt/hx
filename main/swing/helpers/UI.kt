package com.hexagonkt.sidekick.swing.helpers

import kotlin.Throws
import javax.swing.SwingUtilities
import javax.swing.UIManager
import javax.swing.JFrame
import java.io.File
import java.lang.Exception
import java.lang.IllegalStateException

/**
 * TODO .
 * @author jam
 */
class UI private constructor() {
    companion object {
        /**
         * TODO .
         * @param args
         */
        @Throws(Exception::class)
        fun launch(aFrame: AppFrame) {
            SwingUtilities.invokeAndWait { aFrame.open() }
        }

        /**
         * TODO .
         * @throws Exception
         */
        @Throws(Exception::class)
        fun loadCrossPlatformLaF() {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())
            JFrame.setDefaultLookAndFeelDecorated(true)
        }

        /**
         * TODO .
         * TODO Option to support native colors.
         * @throws Exception
         */
        @Throws(Exception::class)
        fun loadNimbusLaF() {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel")
                JFrame.setDefaultLookAndFeelDecorated(true)
            } catch (e: Exception) {
                loadCrossPlatformLaF()
            }
        }

        /**
         * TODO .
         * @param aSynthFile
         */
        fun loadSynthLaF(aSynthFile: File?) {
            // TODO
        }

        /**
         * TODO .
         * @throws Exception
         */
        @Throws(Exception::class)
        fun loadSystemLaF() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
                JFrame.setDefaultLookAndFeelDecorated(true)
            } catch (e: Exception) {
                loadNimbusLaF()
            }
        }
    }

    /**
     * TODO .
     */
    init {
        throw IllegalStateException("This class should not be instantiated")
    } // TODO Style JComponent
    // TODO Layout helpers
    // TODO Look before in SwingUtilities!
}
