package com.rechit.keluargaku.interfaces

import android.util.SparseArray
import com.rechit.keluargaku.models.DayYearly
import java.util.*

interface YearlyCalendar {
    fun updateYearlyCalendar(events: SparseArray<ArrayList<DayYearly>>, hashCode: Int)
}
