package controllers

import models.Product
import stores.ProductStore

object InventoryController {

    fun printInventory() {
        ProductStore.products.forEach { p ->
            println("${p.id}. ${p.name} \t| ${p.quantity} ${p.unit} \t| $${p.price}")
        }
    }

}