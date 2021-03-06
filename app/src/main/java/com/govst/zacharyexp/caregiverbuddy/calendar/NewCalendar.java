package com.govst.zacharyexp.caregiverbuddy.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.R;
import com.govst.zacharyexp.caregiverbuddy.chore.ChoreNew;
import com.govst.zacharyexp.caregiverbuddy.drug.DrugNew;
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

        LinearLayoutManager llm;

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
            mTextMonthDay = findViewById(R.id.tv_month_day);
            mTextYear = findViewById(R.id.tv_year);
            mTextLunar = findViewById(R.id.tv_lunar);
            mRelativeTool = findViewById(R.id.rl_tool);
            mCalendarView = findViewById(R.id.calendarView);
            mTextCurrentDay = findViewById(R.id.tv_current_day);
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

            findViewById(R.id.new_chore_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newChore();
                }
            });

            findViewById(R.id.new_drug_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newDrug();
                }
            });

            mCalendarLayout = findViewById(R.id.calendarLayout);
            mCalendarView.setOnYearChangeListener(this);
            mCalendarView.setOnCalendarSelectListener(this);
            mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
            mYear = mCalendarView.getCurYear();
            mTextMonthDay.setText(mCalendarView.getCurMonth() + "/" + mCalendarView.getCurDay());
            mTextLunar.setText("");
            mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
            mRecyclerView = findViewById(R.id.recyclerView);
            llm = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(llm);
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
                //for(Article article : articles) {
                    //map.put(getSchemeCalendar(articles.get(0).getYear(), articles.get(0).getMonth() + 1, articles.get(0).getDay() ,0xFF40db25, "T").toString(),
                            //getSchemeCalendar(articles.get(0).getYear(), articles.get(0).getMonth() + 1, articles.get(0).getDay() ,0xFF40db25, "T"));
                //}
                for(Article article : articles) {
                    int temp = article.getMonth() + 1;
                    String m = (temp < 10 ? "0" + temp: Integer.toString(temp));
                    int temp2 = article.getDay();
                    String d = (temp2 < 10 ? "0" + temp2: Integer.toString(temp2));
                //map.put(getSchemeCalendar(article.getYear(), article.getMonth() + 1, article.getDay() ,0xFF40db25, "T").toString(),
                    map.put(article.getYear() + "" + m + "" + d,
                        getSchemeCalendar(article.getYear(), article.getMonth() + 1, article.getDay() ,0xFF40db25, "假"));
                    System.out.println(article.getYear() + "-" + m + "-" + d);
                }
            }
            //map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "假").toString(),
                    //getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
            mCalendarView.setSchemeDate(map);

        }


        @Override
        public void onClick(View v) {

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onResume() {
            super.onResume();
            //initView();
            //mRecyclerView.removeAllViews();
            //mRecyclerView.setLayoutManager(llm);
            //mRecyclerView.addItemDecoration(new GroupItemDecoration<String,Article>());
            ArticleAdapter bb = new ArticleAdapter(this);
            aa = bb;
            mRecyclerView.setAdapter(aa);
            mRecyclerView.notifyDataSetChanged();
            initData();
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

        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        public void onCalendarSelect(Calendar calendar, boolean isClick) {
            //aa = new ArticleAdapter(this);
            //mRecyclerView.setAdapter(aa);
            //mRecyclerView.notifyDataSetChanged();
            mTextLunar.setVisibility(View.VISIBLE);
            mTextYear.setVisibility(View.VISIBLE);
            mTextMonthDay.setText(calendar.getMonth() + "/" + calendar.getDay());
            mTextYear.setText(String.valueOf(calendar.getYear()));
            mTextLunar.setText("");
            System.out.println("Clicked");
            try {
                String temp1, temp2;
                if(calendar.getMonth() < 10) {
                    temp1 = "0" + Integer.toString(calendar.getMonth());
                } else {
                    temp1 = Integer.toString(calendar.getMonth());
                }
                if(calendar.getDay() < 10) {
                    temp2 = "0" + Integer.toString(calendar.getDay());
                } else {
                    temp2 = Integer.toString(calendar.getDay());
                }
                String day = Integer.toString(calendar.getYear()) + "-" + temp1 + "-" + temp2;
                System.out.println(day + "$$");
                llm.scrollToPositionWithOffset(aa.getPos(day), 2);
                //mRecyclerView.scrollToPosition(aa.getPos(String.valueOf(calendar.getYear()) + "-" + String.valueOf(calendar.getMonth()) + "-" + String.valueOf(calendar.getDay())));
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Error moving");
            }
            //mTextLunar.setText(calendar.getLunar());
            mYear = calendar.getYear();
        }

        @Override
        public void onYearChange(int year) {
            mTextMonthDay.setText(String.valueOf(year));
        }

        public void newChore() {
            Intent intent = new Intent(this, ChoreNew.class);
            startActivity(intent);
        }

        public void newDrug() {
            Intent intent = new Intent(this, DrugNew.class);
            startActivity(intent);
        }
    }
