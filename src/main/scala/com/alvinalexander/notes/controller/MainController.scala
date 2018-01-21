package com.alvinalexander.notes.controller

import javafx.beans.binding.Bindings
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control._
import javafx.scene.input.MouseEvent
import javafx.util.Callback

import com.alvinalexander.notes.actions._
import com.alvinalexander.notes.view.{EditNotePane, GuiUtils, MainGridPane}
import com.alvinalexander.notes.Note
import com.alvinalexander.notes.db.Database

/**
  * This is the main “controller” (as in MVC) for the application.
  */
class MainController {

    private val db = new Database()

    val mainGridPane = new MainGridPane()

    // connect to these widgets so we can handle their events
    private val tagSearchButton  = mainGridPane.tagSearchButton
    private val textSearchButton = mainGridPane.textSearchButton
    private val searchField      = mainGridPane.searchField
    private val tableView        = mainGridPane.tableView
    private val addNoteButton    = mainGridPane.addNoteButton
    private val deleteNoteButton = mainGridPane.deleteNoteButton

    // event handlers
    tagSearchButton.setOnAction(new TagSearchEventHandler(this, db))
    textSearchButton.setOnAction(new TextSearchEventHandler(this, db))
    searchField.setOnAction(new SearchFieldEventHandler(this, db))
    addNoteButton.setOnAction(new AddNoteEventHandler(tableView, db))
    deleteNoteButton.setOnAction(new DeleteNotesEventHandler(tableView, db))
    // handle a double-click on a table tow
    tableView.setOnMouseClicked(new EditNoteEventHandler(tableView, db))

    // read the database and populate the table
    val notes: Seq[Note] = db.getAll()
    setTableViewNotes(notes)

    def setTableViewNotes(notes: Seq[Note]): Unit = {
        tableView.getItems.clear()
        //TODO can do things like this with ObservableList
        for (n <- notes) {
            tableView.getItems.add(n)
        }
    }

    def currentSearchText(): String = searchField.getText

}

