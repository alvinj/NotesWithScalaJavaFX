package com.alvinalexander.notes.actions

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.TableView

import com.alvinalexander.notes.Note
import com.alvinalexander.notes.controller.MainController
import com.alvinalexander.notes.db.Database
import com.alvinalexander.notes.utils.SearchUtils

class TextSearchEventHandler (mainController: MainController, db: Database)
    extends EventHandler[ActionEvent] {
    override def handle(event: ActionEvent): Unit = {
        SearchUtils.handleFullTextSearchProcess(mainController, db)
    }
}
