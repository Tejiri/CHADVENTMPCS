package com.xita.chadventmpcs.android.helpers

import java.text.DecimalFormat

class HelperFunctions {

    fun formatWithCommas(number: String): String {
        val formatter = DecimalFormat("#,###.##")
        try {
            return formatter.format(number.toDouble())
//            return "%,d".format(number.toLong())
        } catch (e: Exception) {
            return number
        } finally {
//            return number
        }
    }

}