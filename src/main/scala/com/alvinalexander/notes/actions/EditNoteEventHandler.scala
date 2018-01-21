package com.alvinalexander.notes.actions


import javafx.event.EventHandler
import javafx.scene.control.TableView
import javafx.scene.input.MouseEvent

import com.alvinalexander.notes.view.{EditNotePane, GuiUtils}
import com.alvinalexander.notes.Note
import com.alvinalexander.notes.db.Database
import com.alvinalexander.notes.utils.DateUtils

class EditNoteEventHandler(tableView: TableView[Note], db: Database)
extends EventHandler[MouseEvent]
{

    override def handle(event: MouseEvent): Unit = {
        // confirmed that the "not null" check is needed
        if (event.getClickCount() > 1 && tableView.getSelectionModel().getSelectedItem() != null) {
            handleDoubleClickOnPopulatedTableRow()
        }
    }

    private def handleDoubleClickOnPopulatedTableRow(): Unit = {
        // get the selected note and its index
        val originalNote = tableView.getSelectionModel.getSelectedItem
        val originalNoteIndex = tableView.getSelectionModel.selectedIndexProperty().get
        // populate the note in the editor
        val editNotePane = new EditNotePane
        editNotePane.notesField.setText(originalNote.getNote)
        editNotePane.tagsField.setText(originalNote.getTags)
        editNotePane.urlField.setText(originalNote.getUrl)
        editNotePane.dateCreatedField.setText(DateUtils.convertDateToString(originalNote.getDateCreated))
        editNotePane.dateUpdatedField.setText(DateUtils.convertDateToString(originalNote.getDateUpdated))
        // show the editor in a dialog, wait for OK/Cancel
        val resultAsOption = GuiUtils.showNoteEditor("Edit Note", editNotePane)
        resultAsOption match {
            case Some(modifiedNote) =>
                // set the dates properly
                modifiedNote.setDateCreated(originalNote.getDateCreated)
                modifiedNote.setDateUpdated(DateUtils.getCurrentDate)
                // can update the table row in-place
                tableView.getItems.set(originalNoteIndex, modifiedNote)
                // in the db, Edit = Delete+Add
                db.delete(originalNote)
                db.save(modifiedNote)
            case None => //nothing to do, user pressed Cancel
        }
    }

}

