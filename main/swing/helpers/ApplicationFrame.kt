package com.hexagonkt.sidekick.swing.helpers

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JMenuBar
import javax.swing.JToolBar
import javax.swing.JComponent
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JButton
import java.awt.BorderLayout
import java.awt.Image
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

/**
 * TODO .
 * TODO Support adding actions
 * @author jamming
 */
class ApplicationFrame(aTitle: String?, aCloseOperation: Int) : JFrame(aTitle) {
    /**
     * @return The statusBar actual value.
     */
    val statusBar = JLabel("Status")
    private val mMenuBar = JMenuBar()

    /**
     * @return The toolBar actual value.
     */
    val toolBar = JToolBar()

    /**
     * Create the frame.
     * @param aTitle .
     * @param aIcon .
     * @param aCloseOperation .
     */
    constructor(aTitle: String?, aIcon: Image?, aCloseOperation: Int) : this(aTitle, aCloseOperation) {
        iconImage = aIcon
    }

    /**
     * TODO Position, size and state (maximized, minimized).
     * @param aState .
     */
    fun open(aState: Int) {
        pack()
        extendedState = aState
        isVisible = true
    }

    /**
     * TODO Position, size and state (maximized, minimized).
     */
    fun open() {
        pack()
        setLocation(5, 5) // Upper left corner to avoid overlapping with the app
        isVisible = true
    }

    /**
     * TODO Position, size and state (maximized, minimized).
     * @param aWidth .
     * @param aHeight .
     */
    fun open(aWidth: Int, aHeight: Int) {
        setSize(aWidth, aHeight)
        setLocation(5, 5) // Upper left corner to avoid overlapping with the app
        isVisible = true
    }

    /**
     * Adds a list of components to this application frame.
     *
     * @param aComponents The list of components to be added. If 'null', no component will be added.
     */
    fun add(aComponents: List<JComponent?>?) {
        if (aComponents == null) {
            return
        }
        for (c in aComponents) {
            contentPane.add(c)
        }
    }

    fun setup(): ApplicationFrame {
        val exitAction = ExitAction(this)
        val mnuFile = JMenu("File")
        mnuFile.add(JMenuItem(exitAction))
        mMenuBar.isBorderPainted = false
        mMenuBar.add(mnuFile)
        toolBar.isBorderPainted = false
        toolBar.isRollover = true
        toolBar.isFloatable = false
        val btnExit = JButton(exitAction)
        btnExit.isRolloverEnabled = true
        btnExit.isFocusable = false
        toolBar.add(btnExit)
        jMenuBar = mMenuBar
        add(toolBar, BorderLayout.NORTH)
        add(statusBar, BorderLayout.SOUTH)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                exitAction.actionPerformed(null) // The event isn't used in the action
            }
        })

        // Allows to ask prior to shutdown
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
        return this
    }

    companion object {
        private const val serialVersionUID = 1L
    }

    /**
     * Create the frame.
     * TODO Change close operation for a boolean confirm exit, allow to pass a close callback
     *
     * @param aTitle .
     * @param aCloseOperation .
     */
    init {
        defaultCloseOperation = aCloseOperation
        setup()
    }
}
