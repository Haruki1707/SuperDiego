import controllers.SaleController
import helpers.Role
import services.Menu

fun main() {
    // Example of how to add options to the menu
    // The first parameter is the name of the option
    // The second parameter is the action to be executed when the option is selected
    // The third parameter is the role required to execute the option
    // Menu.addOption("Example", ExampleController::newExample, Role.ADMIN)

    Menu.addOption("Realizar venta", SaleController::sale, Role.VENDEDOR)

    runApp()
}