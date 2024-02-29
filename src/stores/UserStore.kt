package stores

import helpers.FileBase
import helpers.Model
import models.User
import java.io.File

/**
 * UserStore is an object that extends FileBase.
 * It is responsible for handling operations related to User data.
 */
object UserStore: FileBase() {
    override val file = File("resources/users.txt")
    val users = data as ArrayList<User>

    init {
        baseInit()
    }

    override fun lineToModel(data: List<String>): Model {
        return User(data[0], data[1], data[2])
    }

    override fun modelToLine(model: Model): String {
        val user = model as User
        return "${user.username},${user.role},${user.password}"
    }
}