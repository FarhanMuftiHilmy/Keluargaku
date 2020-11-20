package com.rechit.keluargaku.extensions

import com.rechit.keluargaku.helpers.Formatter
import com.rechit.keluargaku.models.Event

fun Long.isTsOnProperDay(event: Event): Boolean {
    val dateTime = Formatter.getDateTimeFromTS(this)
    val power = Math.pow(2.0, (dateTime.dayOfWeek - 1).toDouble()).toInt()
    return event.repeatRule and power != 0
}
