package com.alvinalexander.notes.view

import java.util.Date
import javafx.beans.binding.Bindings
import javafx.event.EventHandler
import javafx.geometry.{Insets, Pos}
import javafx.scene.control._
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.input.{MouseButton, MouseEvent}
import javafx.scene.layout.{ColumnConstraints, HBox, Priority, RowConstraints}

import com.alvinalexander.notes.Note

/**
  * This object provides “helper” functions for the MainGridPane class.
  * I put these methods in a separate object to make more obvious the fields
  * that each method was working with.
  */
object MainGridPaneUtils {

    val NOTE_HEADER = "Note"
    val URL_HEADER  = "URL"
    val TAGS_HEADER = "Tags"
    val DATE_HEADER = "Date"

    def addColumnsToTableView(
        tableView: TableView[Note],
        noteColumn: TableColumn[Note, String],
        urlColumn: TableColumn[Note, String],
        tagsColumn: TableColumn[Note, String],
        dateCreatedColumn: TableColumn[Note, Date]
    ): Unit = {
        tableView.getColumns.addAll(noteColumn, urlColumn, tagsColumn, dateCreatedColumn)
    }

    def makeTableColumnsFillWidth(tableView: TableView[Note]): Unit = {
        // if you don't do this, the TableView will add an extra column if all of your
        // pixel-width values don't match the actual width
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY)
    }

    def configureGridPaneGeometry(gridPane: MainGridPane): Unit = {
        gridPane.setPadding(new Insets(10))
        gridPane.setHgap(10)
        gridPane.setVgap(10)
        //this.setPrefWidth(1000)
    }

    def addWidgetsToGridPane(
        gridPane: MainGridPane,
        tableView: TableView[Note],
        topHbox: HBox,
        bottomHbox: HBox
    ): Unit = {
        // add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
        gridPane.add(topHbox,    0, 0)
        gridPane.add(tableView,  0, 1)
        gridPane.add(bottomHbox, 0, 2)
    }

    def configureGridPaneColumnConstraints(gridPane: MainGridPane): Unit = {
        val col1 = new ColumnConstraints
        col1.setPercentWidth(100)
        gridPane.getColumnConstraints.addAll(col1)
    }

    def configureGridPaneRowConstraints(gridPane: MainGridPane): Unit = {
        // row constraints (currently two rows)
        val rowAlwaysGrows = new RowConstraints; rowAlwaysGrows.setVgrow(Priority.ALWAYS)
        val rowNeverGrows = new RowConstraints;  rowNeverGrows.setVgrow(Priority.NEVER)
        gridPane.getRowConstraints.addAll(rowNeverGrows, rowAlwaysGrows)
    }

    def buildNoteColumn(): TableColumn[Note, String] = {
        val noteColumn = new TableColumn[Note, String](NOTE_HEADER)
        noteColumn.setMinWidth(500)
        noteColumn.setCellValueFactory(new PropertyValueFactory[Note,String]("Note"))  //getNote()
        noteColumn
    }

    def buildUrlColumn(): TableColumn[Note, String] = {
        val urlColumn = new TableColumn[Note, String](URL_HEADER)
        urlColumn.setMinWidth(200)
        urlColumn.setCellValueFactory(new PropertyValueFactory[Note,String]("Url"))    //getUrl()
        urlColumn
    }

    def buildTagsColumn(): TableColumn[Note, String] = {
        val tagsColumn = new TableColumn[Note, String](TAGS_HEADER)
        tagsColumn.setMinWidth(150)
        tagsColumn.setCellValueFactory(new PropertyValueFactory[Note,String]("Tags"))  //getTags()
        tagsColumn
    }

    def buildDateColumn(): TableColumn[Note, Date] = {
        val dateColumn = new TableColumn[Note, Date](DATE_HEADER)
        dateColumn.setMinWidth(150)
        dateColumn.setCellValueFactory(new PropertyValueFactory[Note,Date]("DateUpdated"))  //getDateUpdated()
        dateColumn
    }

    def addWidgetsToTopHbox(
        topHbox: HBox,
        searchField: TextField,
        tagSearchButton: Button,
        textSearchButton: Button
    ): Unit = {
        topHbox.getChildren.addAll(searchField, textSearchButton, tagSearchButton)
    }

    def configureTopHBoxGeometry(
        topHbox: HBox,
        searchField: TextField
    ): Unit = {
        searchField.setPrefWidth(400)                //keep small, for when user resizes window smaller
        HBox.setHgrow(searchField, Priority.ALWAYS)  //always grow in hbox
        HBox.setHgrow(topHbox, Priority.ALWAYS)      //hbox always grows/fills width
    }

    def configureBottomHBoxGeometry(
        bottomHbox: HBox
    ): Unit = {
        bottomHbox.setAlignment(Pos.BASELINE_CENTER)
        HBox.setHgrow(bottomHbox, Priority.ALWAYS)      //hbox always grows/fills width
    }

    def addWidgetsToBottomHbox(
        bottomHbox: HBox,
        addNoteButton: Button,
        deleteNoteButton: Button
    ): Unit = {
        bottomHbox.getChildren.addAll(addNoteButton, deleteNoteButton)
    }

    def bindDeleteButtonToTableView(
        tableView: TableView[Note],
        deleteNoteButton: Button
    ): Unit = {
        // only enabled when a user selects a row in the table
        deleteNoteButton.disableProperty().bind(Bindings.isEmpty(tableView.getSelectionModel.getSelectedItems))
    }

}




