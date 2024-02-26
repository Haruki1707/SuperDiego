package models

import helpers.Model

/**
 * Models.Product class represents a product in the system.
 * It contains a name, quantity and price.
 */
data class Products(val name: String, val quantity: Int, val price: Double): Model {
}