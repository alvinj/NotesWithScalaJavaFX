package com.alvinalexander.notes.utils

import com.alvinalexander.notes.Note
import com.alvinalexander.notes.controller.MainController
import com.alvinalexander.notes.db.Database

object SearchUtils {

    /**
      * @param textToSearchFor The text to search for. No telling what happens if this is blank or null.
      * @param db
      * @return A list of Notes from the database.
      */
    def searchFullDatabaseRecordForString(textToSearchFor: String, db: Database): Seq[Note] = {
        db.getAllFullTextSearch(textToSearchFor)
    }

    def handleFullTextSearchProcess(mainController: MainController, db: Database) = {
        val textToSearchFor = mainController.currentSearchText().trim
        if (textToSearchFor.trim == "") {
            // when user searches for nothing, show the whole table (like clearing the search)
            val notes = db.getAll()
            mainController.setTableViewNotes(notes)
        } else {
            val notes = SearchUtils.searchFullDatabaseRecordForString(textToSearchFor, db)
            mainController.setTableViewNotes(notes)
        }
    }

}
