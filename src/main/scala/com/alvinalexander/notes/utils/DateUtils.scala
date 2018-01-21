package com.alvinalexander.notes.utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

object DateUtils {

    val DATE_FORMAT = "EEE, MMM dd, yyyy h:mm a"

    def convertDateToString(d: Date): String = {
        val dateFormat = new SimpleDateFormat(DATE_FORMAT)
        dateFormat.format(d)
    }

    def convertStringToDate(s: String): Date = {
        val formatter = new SimpleDateFormat(DATE_FORMAT)
        formatter.parse(s)
    }

    def getCurrentDate: Date = Calendar.getInstance.getTime

}
