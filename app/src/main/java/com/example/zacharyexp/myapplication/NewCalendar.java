package com.example.zacharyexp.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewCalendar extends BaseActivity implements
    CalendarView.OnCalendarSelectListener,
    CalendarView.OnYearChangeListener,
    View.OnClickListener {

        TextView mTextMonthDay;

        TextView mTextYear;

        TextView mTextLunar;

        TextView mTextCurrentDay;

        CalendarView mCalendarView;

        RelativeLayout mRelativeTool;

        GroupRecyclerView mRecyclerView;
        private int mYear;
        CalendarLayout mCalendarLayout;

        ArticleAdapter aa;

        public static void show(Context context) {
            context.startActivity(new Intent(context, NewCalendar.class));
        }


        @Override
        protected int getLayoutId() {
            return R.layout.activity_new_calendar;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        protected void initView() {
            setStatusBarDarkMode();
            mTextMonthDay = (TextView) findViewById(R.id.tv_month_day);
            mTextYear = (TextView) findViewById(R.id.tv_year);
            mTextLunar = (TextView) findViewById(R.id.tv_lunar);
            mRelativeTool = (RelativeLayout) findViewById(R.id.rl_tool);
            mCalendarView = (CalendarView) findViewById(R.id.calendarView);
            mTextCurrentDay = (TextView) findViewById(R.id.tv_current_day);
            mTextMonthDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mCalendarLayout.isExpand()) {
                        mCalendarView.showYearSelectLayout(mYear);
                        return;
                    }
                    mCalendarView.showYearSelectLayout(mYear);
                    mTextLunar.setVisibility(View.GONE);
                    mTextYear.setVisibility(View.GONE);
                    mTextMonthDay.setText(String.valueOf(mYear));
                }
            });
            findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCalendarView.scrollToCurrent();
                }
            });

            mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
            mCalendarView.setOnYearChangeListener(this);
            mCalendarView.setOnCalendarSelectListener(this);
            mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
            mYear = mCalendarView.getCurYear();
            mTextMonthDay.setText(mCalendarView.getCurMonth() + "/" + mCalendarView.getCurDay());
            mTextLunar.setText("");
            mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
            mRecyclerView = (GroupRecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.addItemDecoration(new GroupItemDecoration<String,Article>());
            aa = new ArticleAdapter(this);
            mRecyclerView.setAdapter(aa);
            mRecyclerView.notifyDataSetChanged();
        }

        @Override
        protected void initData() {

            int year = mCalendarView.getCurYear();
            int month = mCalendarView.getCurMonth();

            Map<String, Calendar> map = new HashMap<>();
            //List<String> list = aa.getTitles();
            LinkedHashMap<String, List<Article>> mapArt = aa.getMap();
            for(List<Article> articles : mapArt.values()) {
                for(Article article : articles) {
                    map.put(getSchemeCalendar(article.getYear(), article.getMonth() + 1, article.getDay() ,0xFF40db25, "T").toString(),
                            getSchemeCalendar(article.getYear(), article.getMonth() + 1, article.getDay() ,0xFF40db25, "T"));
                }
            }
            mCalendarView.setSchemeDate(map);

        }


        @Override
        public void onClick(View v) {

        }

        @SuppressWarnings("all")
        private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
            Calendar calendar = new Calendar();
            calendar.setYear(year);
            calendar.setMonth(month);
            calendar.setDay(day);
            calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
            calendar.setScheme(text);
            return calendar;
        }

        @Override
        public void onCalendarOutOfRange(Calendar calendar) {

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onCalendarSelect(Calendar calendar, boolean isClick) {
            mTextLunar.setVisibility(View.VISIBLE);
            mTextYear.setVisibility(View.VISIBLE);
            mTextMonthDay.setText(calendar.getMonth() + "/" + calendar.getDay());
            mTextYear.setText(String.valueOf(calendar.getYear()));
            mTextLunar.setText("");
            System.out.println("Clicked");
            //mTextLunar.setText(calendar.getLunar());
            mYear = calendar.getYear();
        }

        @Override
        public void onYearChange(int year) {
            mTextMonthDay.setText(String.valueOf(year));
        }

    }
