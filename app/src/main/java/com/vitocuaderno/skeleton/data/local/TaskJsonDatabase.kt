package com.vitocuaderno.skeleton.data.local
import android.content.Context
import com.google.gson.Gson
import com.vitocuaderno.skeleton.data.local.models.Task
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter

class TaskJsonDatabase(
    private val context: Context
) {

    private val name = "tasks.json"

    private fun getFile(): File {
        val path = context.applicationContext.filesDir
        val file = File(path, name)
        file.createNewFile()
        return file
    }

    fun read(): List<Task> {
        var gson = Gson()

        val bufferedReader: BufferedReader = getFile().bufferedReader()

        val inputString = bufferedReader.use { it.readText() }

        try {
            var tasks = gson.fromJson(inputString, Array<Task>::class.java)

            return tasks.asList()
        } catch (e: Exception) {
            return emptyList()
        }
    }

    fun write(tasks: List<Task>) {
        try {
            val gson = Gson()
            val jsonString = gson.toJson(tasks)

            var writer = FileWriter(getFile())
            writer.write(jsonString)
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
