package com.rushikesh.tasks

import java.io.InputStreamReader

object Helper {
    fun readFile(fileName: String): String{
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach { builder.append(it) }
        return builder.toString()
    }
}