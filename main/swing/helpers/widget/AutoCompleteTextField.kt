package com.hexagonkt.sidekick.swing.helpers.widget

import java.util.*
import javax.swing.JTextField

/**
 * Este bean es un campo de texto que mantiene sincronizado su texto con el
 * valor seleccionado de otro bean que pueda tener varios valores (listas,
 * combos, tablas y arboles de forma que al cambiar la seleccion en el bean que
 * contiene el origen de los datos cambie el valor del texto y que proporcione
 * autocompletado con las opciones disponibles dentro del bean de datos al que
 * esta asignado
 * PENDIENTE: reservar el control del teclado para teclas como el tabulador,
 * retorno y escape (ver Keymaps y FocusManager)
 * NOTA: por el momento el autocompletado solo se llevara a cabo al pulsar la
 * tecla tabulador
 * NOTA: hacer una clase abstracta, y de esta derivar las que ejecuten el
 * autocompletado sobre cada tipo de bean: listas (y combos), arboles y tablas
 */
abstract class AutoCompleteTextField : JTextField() {
    protected var autoCompleteTimer: Timer? = null
    var autoCompleteDelay = 0 // Retardo del autocompletado
        protected set
    protected var popupKeys = 0 // Teclas que invocan al autocompletado
    protected var completeKeys = 0 // Teclas que completan la selección con los resultados del autocompletado
    protected var cancelKeys = 0 // Teclas que cancelan el completado
    fun completeWithMatch() {}
    abstract val matchingElement: String?
    fun setPopupDelay(newPopupDelay: Int) {
        // Si el intervalo es negativo, desactivar el autocompletado
        autoCompleteDelay = newPopupDelay
    }

    init {
        autoCompleteTimer = Timer()
        autoCompleteDelay = 1
    }
}
