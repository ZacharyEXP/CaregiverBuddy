package com.govst.zacharyexp.caregiverbuddy.tbd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelin.calendarlistview.library.BaseCalendarItemAdapter;
import com.kelin.calendarlistview.library.BaseCalendarItemModel;

import java.util.Calendar;

public class ChoreCalendarItemModel extends BaseCalendarItemModel {
    private int newsCount;
    private boolean isFav;

    //public ChoreCalendarItemModel(Chore c) {
       // setDayNumber(Integer.toString(c.getStartDate().get(Calendar.DAY_OF_MONTH)));
     //   if(c.getStartDate().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)) {
     //       setCurrentMonth(true);
     //   }
     //   else {
      //      setCurrentMonth(false);
    //    }
    //}

    public int getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}