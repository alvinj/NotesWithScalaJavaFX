package com.alvinalexander.notes.actions

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.TableView
import com.alvinalexander.notes.Note
import com.alvinalexander.notes.controller.MainController
import com.alvinalexander.notes.db.Database

class TagSearchEventHandler (mainController: MainController, db: Database)
extends EventHandler[ActionEvent] {
    override def handle(event: ActionEvent): Unit = {
        val tagToSearchFor = mainController.currentSearchText()
        if (tagToSearchFor.trim == "") {
            // when user searches for nothing, show the whole table (like clearing the search)
            val notes = db.getAll()
            mainController.setTableViewNotes(notes)
        } else {
            val notes = db.getAllByTag(tagToSearchFor)
            mainController.setTableViewNotes(notes)
        }
    }
}

