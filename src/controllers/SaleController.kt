package controllers

import models.Product
import stores.ProductStore

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object SaleController {
    private val items: ArrayList<String> = ArrayList()

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

                        lineItem = "${product.id}\t${product.name}\t${product.quantity}\t${product.unit}\t${product.price}\t${ String.format("%.2f", product.price*product.quantity)}"
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
        println("Nombre del producto \t Unidades disponibles \t Precio por unidad ")
        ProductStore.products.forEach { p ->
            println("${p.id}. ${p.name} \t\t ${p.quantity} ${p.unit} \t\t $${p.price}")
        }

        println("Ingrese los productos y cantidad que desea comprar, separados por comas")
    }

    private fun printTicket() {

    }

    private fun generateTicket() {
        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"))
        val ticket = File("resources/tickets/Factura ${date}.txt")

        ticket.appendText("FACTURA DIGITAL - SuperDiego\nFecha ${date} \n\n")
        ticket.appendText("Cód\tProducto\t\tCantidad\t\tPrecio unitario\t\tTotal\n")
        items.forEach { item -> ticket.appendText(item+"\n") }
        ticket.createNewFile()
    }
}