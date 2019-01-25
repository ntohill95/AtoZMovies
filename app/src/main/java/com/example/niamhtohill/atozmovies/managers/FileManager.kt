package com.example.niamhtohill.atozmovies.managers

import com.example.niamhtohill.atozmovies.data.LocalMovie
import android.util.Log
import java.io.FileInputStream
import java.io.ObjectInputStream


class FileManager(var directoryManager: DirectoryManager) {

    fun getMoviesFor(listName:String):ArrayList<LocalMovie> {
        directoryManager.getProjectDirectory(listName)
        return readMoviesFromFile(listName)
    }

    private fun readMoviesFromFile(fileName:String): ArrayList<LocalMovie> {
        val fin: FileInputStream
        var ois: ObjectInputStream? = null
        var movies = ArrayList<LocalMovie>()
        try {
            fin = directoryManager.context.openFileInput(fileName + ".txt")
            ois = ObjectInputStream(fin)
             movies= ois.readObject() as ArrayList<LocalMovie>
            ois.close()
            Log.v(this.javaClass.simpleName, "Records read successfully")
            return movies
        } catch (e: Exception) {
            Log.e(this.javaClass.simpleName, "Cant read saved records" + e.message)
        } finally {
            if (ois != null)
                try {
                    ois.close()
                } catch (e: Exception) {
                    Log.e(this.javaClass.simpleName, "Error in closing stream while reading records" + e.message)
                }
        }
        return movies
    }

}