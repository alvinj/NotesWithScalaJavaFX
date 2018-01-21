package com.alvinalexander.notes.utils

import java.util.Calendar
import org.scalatest.FunSuite

class DateUtilsTests extends FunSuite {

    test("date to string") {
        val cal = Calendar.getInstance
        cal.set(Calendar.MONTH,          11)  //11=Dec
        cal.set(Calendar.DAY_OF_MONTH,    7)
        cal.set(Calendar.YEAR,         1995)
        cal.set(Calendar.HOUR_OF_DAY,    17)
        cal.set(Calendar.MINUTE,          1)
        cal.set(Calendar.SECOND,          0)
        cal.set(Calendar.MILLISECOND,     0)

        val d = cal.getTime
        val res = DateUtils.convertDateToString(d)
        assert(res == "Thu, Dec 07, 1995 5:01 PM")
    }

    test("string to date") {
        // get a date from a string
        val s = "Thu, Dec 07, 1995 5:01 PM"
        val d = DateUtils.convertStringToDate(s)

        // create a date to compare to
        val cal = Calendar.getInstance
        cal.set(Calendar.MONTH,          11)
        cal.set(Calendar.DAY_OF_MONTH,    7)
        cal.set(Calendar.YEAR,         1995)
        cal.set(Calendar.HOUR_OF_DAY,    17)
        cal.set(Calendar.MINUTE,          1)
        cal.set(Calendar.SECOND,          0)
        cal.set(Calendar.MILLISECOND,     0)
        val expectedDate = cal.getTime

        assert(d == expectedDate)
    }

}
