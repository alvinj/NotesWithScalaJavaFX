package com.alvinalexander.notes.view

import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.scene.control.ButtonBar.ButtonData
import javafx.scene.control.{ButtonType, Dialog}
import javafx.stage.Screen

import com.alvinalexander.notes.Note

object GuiUtils {

    def screenHeight(): Double = getScreenBounds.getHeight
    def screenWidth(): Double = getScreenBounds.getWidth
    def getScreenBounds(): Rectangle2D = Screen.getPrimary.getVisualBounds

    def showNoteEditor(
        title: String = "Add Note",
        editNotePane: EditNotePane
    ): Option[Note] = {
        // create the "Edit Note" dialog
        val editNoteDialog = new Dialog[Option[Note]]()
        editNoteDialog.setTitle(title)
        val okButton = new ButtonType("OK", ButtonData.OK_DONE)
        editNoteDialog.getDialogPane.getButtonTypes.addAll(okButton, ButtonType.CANCEL)
        editNoteDialog.getDialogPane.setContent(editNotePane)
        editNoteDialog.setResizable(true)

        // set initial focus
        Platform.runLater(() => editNotePane.notesField.requestFocus)

        editNoteDialog.setResultConverter(dialogButton => {
            if (dialogButton == okButton) {
                val note = new Note(
                    editNotePane.notesField.getText,
                    editNotePane.urlField.getText,
                    editNotePane.tagsField.getText
                )
                Some(note)
            } else {
                None
            }
        })

        val result = editNoteDialog.showAndWait()
        val resultAsOption: Option[Note] = result.get
        resultAsOption
    }

}
