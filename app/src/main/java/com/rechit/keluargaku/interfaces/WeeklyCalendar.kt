package com.rechit.keluargaku.interfaces

import com.rechit.keluargaku.models.Event

interface WeeklyCalendar {
    fun updateWeeklyCalendar(events: ArrayList<Event>)
}
