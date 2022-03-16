package com.mkdev.currencyexchange.extension

import java.text.DecimalFormat

fun Double.formatTwoDecimalNumber(): Float {
    val decimalFormat = DecimalFormat("#.##")
    return java.lang.Float.valueOf(decimalFormat.format(this))
}