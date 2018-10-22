package com.example.zacharyexp.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelin.calendarlistview.library.BaseCalendarItemAdapter;
import com.kelin.calendarlistview.library.BaseCalendarItemModel;

public class ChoreCalendarItemModel extends BaseCalendarItemModel {
    private int newsCount;
    private boolean isFav;

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