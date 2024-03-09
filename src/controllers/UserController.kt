package controllers

import stores.UserStore

object UserController {
    fun changePassword() {
        println("Cambiando contraseña")
        println("Seleccione a un usuario: ")
        UserStore.users.forEach { user -> println("${user.id}. ${user.username}") }
        val userId = readln().toInt()

        UserStore.users.find { it.id == userId }?.let {
            print("Ingrese la nueva contraseña: ")
            val newPassword = readln()
            print("Confirme la nueva contraseña: ")
            val confirmPassword = readln()
            if (newPassword == confirmPassword) {
                it.password = newPassword
                UserStore.saveFile()
                println("Contraseña cambiada con éxito")
            } else {
                println("Las contraseñas no coinciden")
            }
        } ?: println("Usuario no encontrado")

        readln()
    }
}