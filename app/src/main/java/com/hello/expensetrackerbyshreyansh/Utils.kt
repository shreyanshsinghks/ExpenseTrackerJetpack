package com.hello.expensetrackerbyshreyansh

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun formatDateToHumanReadableForm(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }

    fun getMilliFromDate(dateFormat: String?): Long {
        var date = Date()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            date = formatter.parse(dateFormat)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date.time
    }
}