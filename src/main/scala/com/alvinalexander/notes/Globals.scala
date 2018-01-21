package com.alvinalexander.notes

object Globals {

    private val USER_HOME = System.getProperty("user.home")
    private val SLASH     = System.getProperty("file.separator")

    val DB_FILE   = s"${USER_HOME}${SLASH}Notes.data"
    val APP_TITLE = "Notes"

    // under src/main/scala
    val APP_CSS   = "styles/notes.css"

}


