package com.alvinalexander.notes.actions

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.TableView
import com.alvinalexander.notes.view.{EditNotePane, GuiUtils}
import com.alvinalexander.notes.Note
import com.alvinalexander.notes.db.Database

/**
  * good resource JavaFX dialogs resource:
  * http://code.makery.ch/blog/javafx-dialogs-official/
  */
class AddNoteEventHandler (tableView: TableView[Note], db: Database)
    extends EventHandler[ActionEvent]
{
    override def handle(event: ActionEvent): Unit = {
        val resultAsOption = GuiUtils.showNoteEditor("Add Note", new EditNotePane)
        // note: can also use match/case here
        resultAsOption.foreach{ note =>
            tableView.getItems.add(note)
            db.save(note)
        }
    }

}

