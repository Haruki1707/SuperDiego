package services

import models.User
import stores.UserStore

/**
 * Handles user authentication.
 * It also stores the current user.
 */
object Auth {
    var user: User? = null
    private var alreadyAttempted: Boolean = false

    fun tryLogin() {
        println("¡Bienvenido al sistema SuperDiego!")
        if (!alreadyAttempted) println("Favor ingresar credenciales.")
        else println("Credenciales incorrectas. Favor intentar de nuevo.")

        print("Nombre de usuario: ")
        val username = readln()
        print("Contraseña: ")
        val password = readln()

        checkCredentials(username, password)
    }

    fun logout() {
        alreadyAttempted = false
        user = null
    }

    private fun checkCredentials(username: String, password: String): Boolean {
        UserStore.users.find { it.username == username && it.password == password }?.let {
            user = it
            alreadyAttempted = false
            return true
        }
        alreadyAttempted = true
        return false
    }
}