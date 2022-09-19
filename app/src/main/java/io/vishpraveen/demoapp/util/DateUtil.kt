package io.vishpraveen.demoapp.util

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun formatDate(date: String?): String? {
        try {
            val outputFormat: DateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH)
            val inputFormat: DateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val newDate: Date? = inputFormat.parse(date)

            return newDate?.let { outputFormat.format(it) }
        } catch (ex: Exception) {
            Log.e(this@DateUtil::class.simpleName, "formatDate error: ${ex.localizedMessage}")
            return ""
        }
    }
}