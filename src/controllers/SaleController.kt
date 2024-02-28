package controllers

import models.Product
import stores.ProductStore

import java.io.File
import java.time.LocalDateTime

object SaleController {
    val items: ArrayList<String> = ArrayList()

    fun sale() {
        printInventory()

        val productRequest = readln()
        var productId = -1

        productRequest.split(',').forEachIndexed { index, it ->
            var lineItem = ""

            if ((index+1) % 2 == 0)
            {
                ProductStore.products.find { p -> p.id == productId }?.let {product ->
                    if (product.quantity >= it.toInt()) {
                        product.quantity -= it.toInt()

                        lineItem = "${product.name},${product.quantity},${product.unit},${product.price}, ${product.price*product.quantity}"
                        items.add(lineItem)
                    }
                } ?: println("No existe un producto con el ID ${productId}")

            }
            else productId = it.toInt()
        };

        ProductStore.saveFile()
        generateTicket()
        readln()
    }

    private fun printInventory() {
        println("Nombre del producto | Unidades disponibles | Precio por unidad ")
        ProductStore.products.forEach { p ->
            println("${p.id}. ${p.name} | ${p.quantity} ${p.unit} | $${p.price}")
        }

        println("Ingrese los productos y cantidad que desea comprar, separados por comas")
    }

    private fun printTicket() {

    }

    private fun generateTicket() {
        val ticket = File("Factura ${LocalDateTime.now().toString()}")
        ticket.appendText(items.toString())
    }
}