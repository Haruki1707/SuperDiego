package services

import helpers.Role

/**
 * This object represents the Menu in the application.
 * It contains a list of options, each with a corresponding action.
 */
object Menu {
    private val options = ArrayList<Triple<String, () -> Unit, Int>>()

    fun addOption(option: String, action: () -> Unit, role: Role) {
        options.add(Triple(option, action, role.value))
    }

    fun show() {
        println("Bienvenido ${Auth.user!!.username} | Rol: ${Auth.user!!.roleAsEnum().name}")
        println("¿Qué desea hacer?")

        var counter = 1
        for (i in 0 until options.size) {
            if (options[i].third <= Auth.user!!.roleAsEnum().value) {
                println("${counter}. ${options[i].first}")
                counter++
            }
        }
        println("${counter}. Cerrar sesión")
    }

    private fun calculateOption(option: Int): Int {
        var counter = 1
        for (i in 0 until options.size) {
            if (options[i].third <= Auth.user!!.roleAsEnum().value) {
                if (counter == option) return i
                counter++
            }
        }
        if (option == counter) return -1
        throw Exception("Invalid option.")
    }

    fun executeOption(option: String) {
        var index = 0

        try {
            index = calculateOption(option.toInt())
        } catch (e: Exception) {
            println("Opción inválida. Presione enter para continuar.")
            readln()
            return
        }

        if (index == -1) {
            Auth.logout()
            println("Sesión cerrada. Presione enter para continuar.")
            readln()
        } else {
            options[index].second()
        }
    }
}