package models

import helpers.Model
import helpers.Role
import java.util.Locale

/**
 * Models.User class represents a user in the system.
 * It contains a name, role and password.
 */
data class User(val username: String, val role: String, var password: String): Model() {
    fun roleAsEnum(): Role {
        // Check if the role is valid, if not return INVITADO
        return try {
            Role.valueOf(role.uppercase(Locale.getDefault()))
        } catch (e: IllegalArgumentException) {
            Role.INVITADO
        }
    }
}
