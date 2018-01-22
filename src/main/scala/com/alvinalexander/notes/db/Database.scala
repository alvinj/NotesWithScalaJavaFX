package com.alvinalexander.notes.db

import java.util.Date

import com.alvinalexander.flatfiledatabase.DataStore
import com.alvinalexander.notes.utils.DateUtils
import com.alvinalexander.notes.{Globals, Note}

/**
  * This class is a wrapper around the FlatFileDatabase `DataStore` class.
  * In theory, methods in here like `save`, `delete`, and `getAll` should be
  * simple to use in the rest of the application.
  */
class Database {

    private val DELIMITER = "‡"

    val dataStore = new DataStore(
        Globals.DB_FILE,
        delimiter = DELIMITER,
        newlineSymbol = "«"
    )

    def save(n: Note): Unit = {
        val s = convertNoteToPipedString(n)
        dataStore.add(s)
    }

    def delete(n: Note): Unit = {
        val s = convertNoteToPipedString(n)
        dataStore.remove(s)
    }

    /**
      * note: i do a `trim` on each field. `Note` holds the date/time
      * reference as a formatted string. need to convert it back to a
      * Long inside this method.
      */
    private def convertNoteToPipedString(n: Note): String = {
        s"${n.getNote.trim}${DELIMITER}${n.getUrl.trim}${DELIMITER}${n.getTags.trim}${DELIMITER}${DateUtils.convertDateToString(n.getDateCreated)}${DELIMITER}${DateUtils.convertDateToString(n.getDateUpdated)}"
    }

    private def createNoteFromDatabaseRec(
        note: String,
        url: String,
        tags: String,
        dateCreated: String,
        dateUpdated: String
    ): Note = {
        new Note(note, url, tags, dateCreated, dateUpdated)
    }

    def getAll(): Seq[Note] = {
        val records: Seq[Seq[String]] = dataStore.getAllItemsSeparatedIntoColumns()
        val notes = for {
            Seq(note,url,tags,dateCreated,dateUpdated) <- records
        } yield createNoteFromDatabaseRec(note,url,tags,dateCreated,dateUpdated)
        notes
    }

    def getAllFullTextSearch(searchFor: String): Seq[Note] = {
        val records: Seq[Seq[String]] = dataStore.getAllItemsSeparatedIntoColumns()
        val notes = for {
            rec <- records
            if anyFieldContainsString(rec, searchFor)
        } yield createNoteFromDatabaseRec(rec(0),rec(1),rec(2),rec(3),rec(4))
        notes
    }

    /**
      * performs a case-insensitive search
      */
    private def anyFieldContainsString(notes: Seq[String], searchFor: String): Boolean = {
        val s = notes.mkString(" ").toLowerCase
        if (s.contains(searchFor.toLowerCase)) true else false
    }

    /**
      * @param tagsUserIsSearchingFor a Seq like `Seq("foo", "bar")`
      * @return all notes that contain all of the tags in `tagsToSearchFor`
      */
    def getAllByTag(tagsUserIsSearchingFor: Seq[String]): Seq[Note] = {
        val records: Seq[Seq[String]] = dataStore.getAllItemsSeparatedIntoColumns()
        val notes = for {
            rec <- records //Seq[String]
            if desiredTagsInTagsField(tagsUserIsSearchingFor, rec(2))
        } yield createNoteFromDatabaseRec(rec(0),rec(1),rec(2),rec(3),rec(4))
        notes
    }

    /**
      * If the intersection of (tagsUserIsSearchingFor, tagsFromDb) is equal to
      * tagsUserIsSearchingFor, that means that *all* of the tagsUserIsSearchingFor
      * were found in tagsFromDb.
      *
      * @param tagsUserIsSearchingFor The tags the user is searching for.
      * @param rawTagsFieldFromDb The raw string from the database.
      * @return true if all tagsUserIsSearchingFor are found.
      */
    private def desiredTagsInTagsField(
        tagsUserIsSearchingFor: Seq[String],
        rawTagsFieldFromDb: String
    ): Boolean = {
        val tagsFromDb = rawTagsFieldFromDb.split(",").map(_.trim).toVector
        tagsFromDb.intersect(tagsUserIsSearchingFor) == tagsUserIsSearchingFor
    }

}








