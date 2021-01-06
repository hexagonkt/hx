package com.hexagonkt.sidekick.swing.helpers.widget

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Line2D
import java.util.*
import javax.swing.JComponent

class JClock : JComponent(), Runnable {
    protected var scanLinesColor: Color? = null
        set(aColor) {
            field = aColor
            repaint()
        }
    protected var scanLinesShadowColor: Color? = null
    protected var scanLinesWidth: Byte = 0
    protected var scanLinesSeparation: Byte = 0
    protected var textShadowColor: Color? = null
    protected var textShadowXShift: Byte = 0
    protected var textShadowYShift: Byte = 0
    fun createBackgroundPattern(gfx: Graphics) {
        val backgroundPattern = gfx as Graphics2D
        val scanLine = Line2D.Float()
        var i = scanLinesSeparation.toInt()
        while (i < height) {
            backgroundPattern.color = scanLinesColor
            scanLine.setLine(x.toFloat(), i.toFloat(), width.toFloat(), i.toFloat())
            backgroundPattern.draw(scanLine)
            i++
            backgroundPattern.color = scanLinesShadowColor
            scanLine.setLine(x.toFloat(), i.toFloat(), width.toFloat(), i.toFloat())
            backgroundPattern.draw(scanLine)
            i += scanLinesSeparation + 1
        }
    }

    fun drawText(g: Graphics, xPos: Int, yPos: Int, text: String?) {
        val gfx = g as Graphics2D
        // Dibuja la sombra
        gfx.font = Font("Dialog", 1, 16)
        gfx.color = textShadowColor
        gfx.drawString(text, xPos + textShadowXShift, yPos + textShadowYShift)
        // Dibuja el texto
        gfx.color = Color(0, 100, 0)
        gfx.drawString(text, xPos, yPos)
    }

    override fun paint(g: Graphics) {
        createBackgroundPattern(g)
        drawText(g, 20, 20, Date().toString())
    }

    // PENDIENTE: poner condición de salida al finalizar el bean, porque sino el
    // thread permanece ejecutandose "zombi"
    // NOTA: hacer que parpadeen los dos puntos ":"
    override fun run() {
        var lastTime = System.currentTimeMillis()
        while (true) {
            if (System.currentTimeMillis() - lastTime > 950) {
                lastTime = System.currentTimeMillis()
                repaint()
            }
        }
    }

    init {
        // Asigna los valores por defecto
        scanLinesColor = Color(0, 20, 0, 75)
        scanLinesShadowColor = Color(0, 20, 0, 30)
        scanLinesWidth = 1.toByte()
        scanLinesSeparation = 1.toByte()
        textShadowColor = Color(0, 100, 0, 64)
        textShadowXShift = 3.toByte()
        textShadowYShift = 3.toByte()
        // Arranca el Thread que actualiza la hora
        /*
        try
        {
         */
        val timer = Thread(this)
        timer.priority = 1
        timer.start()
        /*
        }
        catch (InterruptedException exception)
        {
            exception.printStackTrace ();
        }
        catch (InvocationTargetException exception)
        {
            exception.printStackTrace ();
        }
         */
    }
}
