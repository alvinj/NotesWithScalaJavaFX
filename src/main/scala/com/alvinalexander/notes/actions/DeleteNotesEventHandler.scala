package com.alvinalexander.notes.actions

import java.util
import java.util.Optional
import javafx.collections.ObservableList
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{Alert, ButtonType, TableView}

import com.alvinalexander.notes.Note
import com.alvinalexander.notes.db.Database

class DeleteNotesEventHandler(tableView: TableView[Note], db: Database)
    extends EventHandler[ActionEvent] {

    def handle(event: ActionEvent) {
        val tableViewSelectedItems: ObservableList[Note] = tableView.getSelectionModel.getSelectedItems

        // show an "Are you sure?" confirmation dialog
        if (tableViewSelectedItems.size() > 0) {
            val alert = new Alert(AlertType.CONFIRMATION)
            if (tableViewSelectedItems.size==1) {
                alert.setTitle("Delete Note?")
                alert.setContentText("Delete the selected note?")
            }
            else {
                alert.setTitle("Delete Notes?")
                alert.setContentText("Delete the selected notes?")
            }

            val result: Optional[ButtonType] = alert.showAndWait
            if (result.get() == ButtonType.OK) {
                deleteNotesFromDb(tableViewSelectedItems)
                val allTableViewItems: ObservableList[Note] = tableView.getItems
                allTableViewItems.removeAll(tableViewSelectedItems)
                tableView.getSelectionModel.clearSelection()
            }
        }
    }

    private def deleteNotesFromDb(selectedItems: ObservableList[Note]): Unit = {
        // this approach is a bit of a hack b/c selectedItems can't be
        // used in a for-expression (doesn't have a foreach method)
        val it: util.Iterator[Note] = selectedItems.iterator
        while (it.hasNext) {
            val note = it.next
            db.delete(note)
        }
    }

}
