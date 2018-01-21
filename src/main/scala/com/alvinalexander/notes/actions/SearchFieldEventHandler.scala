package com.alvinalexander.notes.actions

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.TableView

import com.alvinalexander.notes.controller.MainController
import com.alvinalexander.notes.Note
import com.alvinalexander.notes.db.Database
import com.alvinalexander.notes.utils.SearchUtils

class SearchFieldEventHandler (mainController: MainController, db: Database)
    extends EventHandler[ActionEvent] {

    // this is the same process as the TextSearchEventHandler
    override def handle(event: ActionEvent): Unit = {
        SearchUtils.handleFullTextSearchProcess(mainController, db)
    }

}
