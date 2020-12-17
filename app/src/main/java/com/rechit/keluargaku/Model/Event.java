package com.rechit.keluargaku.Model;

import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.Calendar;

public class Event {
    private Calendar mDay;
    private int mImageResource;

    public Event() {
    }

    /**
     * @param day Calendar object which represents a date of the event
     */
    public Event(Calendar day) {
        mDay = day;
    }

    /**
     * @param day           Calendar object which represents a date of the event
     * @param imageResource Resource of an image which will be displayed in a day cell
     */
    public Event(Calendar day, int imageResource) {
        DateUtils.setMidnight(day);
        mDay = day;
        mImageResource = imageResource;
    }


    /**
     * @return An image resource which will be displayed in the day row
     */
    public int getImageResource() {
        return mImageResource;
    }

    /**
     * @return Calendar object which represents a date of current event
     */
    public Calendar getCalendar() {
        return mDay;
    }
}
