package com.alvinalexander.notes

import java.io.IOException
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

import com.alvinalexander.notes.controller.MainController
import com.alvinalexander.notes.view.GuiUtils

object NotesMain {
    def main(args: Array[String]) {
        Application.launch(classOf[NotesMain], args: _*)
    }
}

class NotesMain extends Application {

    val mainController = new MainController

    @Override
    @throws(classOf[IOException])
    def start(mainStage: Stage) {
        val mainGridPane = mainController.mainGridPane

        val scene = new Scene(mainGridPane, initialWidth(), initialHeight())
        scene.getStylesheets.add(getClass.getResource(Globals.APP_CSS).toExternalForm)

        mainStage.setTitle(Globals.APP_TITLE)
        mainStage.setScene(scene)
        mainStage.sizeToScene()  //like pack()
        mainStage.show

    }

    def initialHeight() = GuiUtils.screenHeight*2/3
    def initialWidth() = GuiUtils.screenWidth()*2/3

}