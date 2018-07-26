package app.concertor.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {

        private const val DATE_PATTERN = "YYYY-MM-DD"

        fun convertTimestampToFormattedDate(timeStampMillis: Long): String {
            return SimpleDateFormat(DATE_PATTERN, Locale.US)
                    .format(convertTimestampToDate(timeStampMillis))
        }

        fun convertStringToDate(formattedDate: String): Date {
            return SimpleDateFormat(DATE_PATTERN, Locale.US).parse(formattedDate)
        }

        private fun convertTimestampToDate(timeStampMillis: Long): Date {
            return Date(timeStampMillis)
        }
    }
}