package models

import helpers.Model

/**
 * Models.Product class represents a product in the system.
 * It contains a name, quantity and price.
 */

data class Product(val name: String, var quantity: Int, val unit: String, var price: Double): Model() {

}