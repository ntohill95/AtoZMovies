package com.example.niamhtohill.atozmovies.managers

import android.content.Context
import com.example.niamhtohill.atozmovies.R
import java.io.File

class DirectoryManager(val context: Context) {

    private lateinit var userDirectoryPath: String
    private val userDirectoryCreationException = Exception(context.getString(R.string.user_directory_error))
    private val projectDirectoryCreationException = Exception(context.getString(R.string.create_project_error))

    fun createDirectoryForUser(email: String) {
        val bundleFilesPath: String = context.filesDir.absolutePath
        val directoryPath = "$bundleFilesPath/$email"
        val directory = File(directoryPath)
        userDirectoryPath = when {
            directory.exists() -> directoryPath
            directory.mkdirs() -> directoryPath
            else -> throw userDirectoryCreationException
        }
    }

    fun createDirectoryForList(listName: String): String {
        val verifiedUserDirectory = getUserDirectory()
        val projectPath = "$verifiedUserDirectory/$listName"
        val projectDirectory = File(projectPath)
        return when {
            projectDirectory.exists() -> throw projectDirectoryCreationException
            projectDirectory.mkdirs() -> listName
            else -> throw projectDirectoryCreationException
        }
    }

    fun getUserDirectory(): String {
        if (File(userDirectoryPath).exists()) {
            return userDirectoryPath
        } else {
            throw userDirectoryCreationException
        }
    }

    fun getProjectDirectory(listName: String): String {
        val userDirectoryPath = getUserDirectory()
        return "$userDirectoryPath/$listName"
    }
}