package controllers

import helpers.clearScreen
import stores.ProductStore

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object SaleController {
    private var items: ArrayList<String> = ArrayList()

    fun sale() {
        printInventory()
        var footer = "¡Gracias por su compra!"
        items = ArrayList()
        var productId = -1
        var total = 0.0

        val productRequest = readln()

        productRequest.split(',').forEachIndexed { index, it ->
            var lineItem = ""

            if ((index+1) % 2 == 0)
            {
                ProductStore.products.find { p -> p.id == productId }?.let {product ->
                    if (product.quantity >= it.toInt()) {
                        product.quantity -= it.toInt()
                        total += String.format("%.2f", product.price*it.toInt()).toDouble()
                        lineItem = "${product.id}\t${product.name}\t${it}\t${product.unit}\t${product.price}\t${total}"
                        items.add(lineItem)
                    } else if (product.quantity > 0) {
                        total += String.format("%.2f", product.price*product.quantity).toDouble()
                        lineItem = "${product.id}\t${product.name}\t${product.quantity}\t${product.unit}\t${product.price}\t${total}"
                        items.add(lineItem)
                        product.quantity = 0
                        footer = "\nAl parecer no tenemos las unidades solicitades en existencia.\nSe realizó la venta con las unidades disponibles\n¡Gracias por su compra!"
                    }
                    else println("No hay unidades en existencias de ${product.name}")
                } ?: println("No existe un producto con el ID $productId")
            }
            else productId = it.toInt()
        };

        if (items.isNotEmpty()) {
            ProductStore.saveFile()
            generateTicket(total, footer)
        }

        readln()
    }

    private fun printInventory() {
        clearScreen()
        println("Nombre del producto \t Unidades disponibles \t Precio por unidad")
        ProductStore.products.forEach { p ->
            println("${p.id}. ${p.name} \t| ${p.quantity} ${p.unit} \t| $${p.price}")
        }

        println("Ingrese los productos y cantidad que desea comprar, separados por comas:")
    }

    private fun generateTicket(subtotal: Double, footer: String = "¡Gracias por su compra!") {
        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"))
        val ticket = File("resources/tickets/Factura ${date}.txt")

        ticket.appendText("FACTURA DIGITAL - SuperDiego\nFecha $date \n\n")
        ticket.appendText("Cód\tProducto\t\tCantidad\t\tPrecio unitario\t\tTotal\n")

        println("\nFACTURA DIGITAL - SuperDiego")
        println("Fecha $date \n")
        println("Cód\tProducto\t\tCantidad\t\tPrecio unitario\t\tTotal")

        items.forEach { item ->
            ticket.appendText(item+"\n")
            println(item)
        }

        if (subtotal > 20) {
            val discount = subtotal * 0.03
            val total = subtotal - discount
            ticket.appendText("\nDescuento 3%: $${String.format("%.2f", discount)}\n")
            ticket.appendText("Subtotal: $${String.format("%.2f", subtotal)}\n")
            ticket.appendText("Total de factura: $${String.format("%.2f", total)}\n")

            println("\nDescuento 3%: $${String.format("%.2f", discount)}")
            println("Subtotal: $${String.format("%.2f", subtotal)}")
            println("Total de factura: $${String.format("%.2f", total)}")
        } else {
            ticket.appendText("\nTotal de factura: $${String.format("%.2f", subtotal)}")
            println("Total de factura: $${String.format("%.2f", subtotal)}")
        }

        ticket.createNewFile()

        println(footer)
        println("\nVenta realizada con éxito. Se ha generado la factura ${ticket.name}")
    }
}