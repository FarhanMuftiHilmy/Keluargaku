package com.rechit.keluargaku.extensions

import com.rechit.keluargaku.helpers.MONTH
import com.rechit.keluargaku.helpers.WEEK
import com.rechit.keluargaku.helpers.YEAR

fun Int.isXWeeklyRepetition() = this != 0 && this % WEEK == 0

fun Int.isXMonthlyRepetition() = this != 0 && this % MONTH == 0

fun Int.isXYearlyRepetition() = this != 0 && this % YEAR == 0
