package com.rechit.keluargaku.helpers

import android.content.Context
import com.rechit.keluargaku.extensions.eventsHelper
import com.rechit.keluargaku.interfaces.WeeklyCalendar
import com.rechit.keluargaku.models.Event
import com.simplemobiletools.commons.helpers.DAY_SECONDS
import com.simplemobiletools.commons.helpers.WEEK_SECONDS
import java.util.*

class WeeklyCalendarImpl(val callback: WeeklyCalendar, val context: Context) {
    var mEvents = ArrayList<Event>()

    fun updateWeeklyCalendar(weekStartTS: Long) {
        val endTS = weekStartTS + 2 * WEEK_SECONDS
        context.eventsHelper.getEvents(weekStartTS - DAY_SECONDS, endTS) {
            mEvents = it
            callback.updateWeeklyCalendar(it)
        }
    }
}
