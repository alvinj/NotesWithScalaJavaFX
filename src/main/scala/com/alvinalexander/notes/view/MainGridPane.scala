package com.alvinalexander.notes.view

import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.control._
import javafx.scene.layout._

import MainGridPaneUtils._
import com.alvinalexander.notes.Note

/**
  * I prefer to keep GUI classes as dumb as possible, so you can easily modify
  * them later.
  *
  * I moved a lot of the “setup/configuration” code to a separates “utils” class
  * named MainGridPaneUtils. I don’t always do that, but this approach (a) makes
  * those methods look a little more like pure functions, and (b) I find that
  * it gets confusing when I have those some methods in this class, and I
  * modify the fields, which feel like “global” values. This approach results in
  * more code, but I think it’s a little easier to understand.
  *
  * Note to self: You can’t extend a Scene, so i’m extending GridPane, which
  * I’ll later add to a Scene.
  */
class MainGridPane() extends GridPane {

    // PRIVATE WIDGETS
    private val topHbox = new HBox(10)
    private val bottomHbox = new HBox(10)

    // PUBLIC WIDGETS
    val tableView = new TableView[Note]()
    tableView.getSelectionModel.setSelectionMode(SelectionMode.MULTIPLE)
    val searchField = new TextField;  searchField.setPromptText("<search>")
    val textSearchButton = new Button("all")
    val tagSearchButton = new Button("tags")
    val addNoteButton = new Button("add note")
    val deleteNoteButton = new Button("delete note")

    textSearchButton.setTooltip(new Tooltip("Search text and tags"))
    tagSearchButton.setTooltip(new Tooltip("Search tags"))

    // TABLE COLUMNS
    private val noteColumn = buildNoteColumn()
    private val urlColumn = buildUrlColumn()
    private val tagsColumn = buildTagsColumn()
    private val dateColumn = buildDateColumn()

    // TOP HBOX CONFIG
    configureTopHBoxGeometry(topHbox, searchField)
    addWidgetsToTopHbox(topHbox, searchField, tagSearchButton, textSearchButton)

    // TABLE CONFIG
    addColumnsToTableView(tableView, noteColumn, urlColumn, tagsColumn, dateColumn)
    makeTableColumnsFillWidth(tableView)
    //addContextMenuToTableView(tableView, tableViewContextMenu, addNoteMenuItem, deleteNoteMenuItem)

    // BOTTOM HBOX
    configureBottomHBoxGeometry(bottomHbox)
    addWidgetsToBottomHbox(bottomHbox, addNoteButton, deleteNoteButton)
    bindDeleteButtonToTableView(tableView, deleteNoteButton)

    // GRID PANE CONFIG
    configureGridPaneGeometry(this)
    addWidgetsToGridPane(this, tableView, topHbox, bottomHbox)
    configureGridPaneColumnConstraints(this)
    configureGridPaneRowConstraints(this)

}









