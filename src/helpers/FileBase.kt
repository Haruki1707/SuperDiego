package helpers

import java.io.File

/**
 * Abstract class that provides base functionality for file operations.
 * It includes methods for initializing, reading, saving, and adding data to a file.
 * Must be used as a base class for stores.
 */
abstract class FileBase {
    abstract val file : File
    val data: ArrayList<Model> = ArrayList()


    fun baseInit() {
        if (file.exists())
            readFile()
        else
            throw Exception("File not found")
    }

    private fun readFile() {
        file.forEachLine {
            this.data.add(lineToModel(it.split(",")))
        }
    }

    abstract fun lineToModel(data: List<String>): Model

    private fun saveFile() {
        file.writeText("")
        data.forEach {
            file.appendText(modelToLine(it))
        }
    }

    abstract fun modelToLine(model: Model): String

    fun add(model: Model) {
        data.add(model)
        saveFile()
    }
}