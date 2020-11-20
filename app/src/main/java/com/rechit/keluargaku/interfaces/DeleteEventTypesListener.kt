package com.rechit.keluargaku.interfaces

import com.rechit.keluargaku.models.EventType
import java.util.*

interface DeleteEventTypesListener {
    fun deleteEventTypes(eventTypes: ArrayList<EventType>, deleteEvents: Boolean): Boolean
}
