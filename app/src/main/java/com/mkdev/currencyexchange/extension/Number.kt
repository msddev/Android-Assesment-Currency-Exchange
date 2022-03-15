package com.mkdev.currencyexchange.extension

import java.text.DecimalFormat

fun Double.formatTwoDecimalNumber(): Double {
    val decimalFormat = DecimalFormat("#.##")
    return java.lang.Double.valueOf(decimalFormat.format(this))
}