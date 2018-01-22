package com.alvinalexander.notes.actions

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.TableView
import com.alvinalexander.notes.Note
import com.alvinalexander.notes.controller.MainController
import com.alvinalexander.notes.db.Database

class TagSearchEventHandler (mainController: MainController, db: Database)
extends EventHandler[ActionEvent] {
    override def handle(event: ActionEvent): Unit = {
        val tagStringToSearchFor = mainController.currentSearchText()
        if (tagStringToSearchFor.trim == "") {
            // when user searches for nothing, show the whole table
            // (a way of clearing the search)
            val notes = db.getAll()
            mainController.setTableViewNotes(notes)
        } else {
            // tagStringToSearchFor is like "foo, bar" or just "foo". `split`
            // works fine with both
            val tagsToSearchFor = tagStringToSearchFor.split(",").map(_.trim).toVector
            val notes = db.getAllByTag(tagsToSearchFor)
            mainController.setTableViewNotes(notes)
        }
    }
}

