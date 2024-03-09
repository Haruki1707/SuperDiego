package controllers

import models.Product
import stores.ProductStore

object InventoryController {
    fun search(){
        println("Para realizar la consulta de inventario, introduce el nombre del producto o su código.")
        val search = readln().lowercase()
        println("Nombre del producto \t Unidades disponibles \t Precio por unidad ")

        if (search == "todos")
            printInventory()
        else {
            ProductStore.products.find { p -> p.id.toString() == search }?.let { product: Product ->
                println("${product.id}. ${product.name} \t| ${product.quantity} ${product.unit} \t| $${product.price}")
            } ?: ProductStore.products.find { p -> p.name.lowercase() == search }?.let { product ->
                println("${product.id}. ${product.name} \t| ${product.quantity} ${product.unit} \t| $${product.price}")
            } ?: println("Producto no encontrado")
        }

        readln()
    }
    fun printInventory() {
        ProductStore.products.forEach { p ->
            println("${p.id}. ${p.name} \t| ${p.quantity} ${p.unit} \t| $${p.price}")
        }
    }

}