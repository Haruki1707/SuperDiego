package models

import helpers.Model

/**
 * Models.Product class represents a product in the system.
 * It contains a name, quantity and price.
 */
data class Product(val name: String, val quantity: Int, val unit: String, val price: Double): Model {
}