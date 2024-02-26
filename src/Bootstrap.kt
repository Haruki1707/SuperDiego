import helpers.clearScreen
import services.Auth
import services.Menu

/**
 * Main entry point of the application.
 * Authenticates the user and shows the menu if successful.
 * Continues as long as the user is logged in.
 * Starts over when the user logs out.
 */
fun runApp () {
    clearScreen()
    while (true) {
        Auth.tryLogin()

        while (Auth.user != null) {
            clearScreen()
            Menu.show()
            val option = readln()
            clearScreen()
            Menu.executeOption(option)
        }

        clearScreen()
    }
}