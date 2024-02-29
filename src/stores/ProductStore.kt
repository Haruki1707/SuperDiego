package stores

import helpers.FileBase
import helpers.Model
import models.Product
import java.io.File

/**
 * ProductsStore is an object that extends FileBase.
 * It is responsible for handling operations related to Product data.
 */
object ProductStore: FileBase() {
    override val file = File("resources/products.txt")
    val products = data as ArrayList<Product>

    init {
        baseInit()
    }

    override fun lineToModel(data: List<String>): Model {
        return Product(data[0], data[1].toInt(), data[2], data[3].toDouble())
    }

    override fun modelToLine(model: Model): String {
        val product = model as Product
        return "${product.name},${product.quantity},${product.unit},${product.price}"
    }
}