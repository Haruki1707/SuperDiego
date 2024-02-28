package controllers

import stores.ProductStore

object SaleController {
    fun sale() {
        println("Nombre del producto | Unidades disponibles | Precio por unidad ")
        ProductStore.products.forEachIndexed { index, p ->
            println((index+1).toString() + ". " + p.name  + " | " + p.quantity + " " + p.unit + " | $" + p.price)
        }

        println("Ingrese los productos y cantidad que desea comprar, separados por comas")

        val products = readln()
        var productId = -1

        products.split(',').forEachIndexed { index, s ->
            if (index % 2 == 0) checkInventory(productId, s.toInt())
            else productId = s.toInt()-1
        };

    }

    private fun checkInventory(id: Int, quantity: Int): Boolean {
        if (ProductStore.products[id] != null && ProductStore.products[id].quantity >= quantity) return true
        return false
    }

    private fun printTicket() {

    }

    private fun generateTicket() {

    }
}