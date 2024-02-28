package services

import models.User
import stores.UserStore

/**
 * Handles user authentication.
 * It also stores the current user.
 */
object Auth {
    val user: User?
        get() = userId?.let { UserStore.users.find { user -> user.id == it } }
    private var userId: Int? = null
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
        userId = null
    }

    private fun checkCredentials(username: String, password: String): Boolean {
        UserStore.users.find { it.username == username && it.password == password }?.let {
            userId = it.id
            alreadyAttempted = false
            return true
        }
        alreadyAttempted = true
        return false
    }
}