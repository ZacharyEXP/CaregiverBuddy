package com.example.zacharyexp.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kelin.calendarlistview.library.CalendarHelper;
import com.kelin.calendarlistview.library.CalendarListView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;

import static com.kelin.calendarlistview.library.CalendarHelper.getCalendarByYearMonthDay;

public class ChoreMonthActivity extends RxAppCompatActivity {
    public static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");

    private TreeMap<String, List<Chore>> listTreeMap = new TreeMap<>();

    private CalendarListView calendarListView;
    private CalendarListAdapter calendarListAdapter;
    private CalendarItemAdapter calendarItemAdapter;

    private Handler handler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_month);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowHomeEnabled(false);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        calendarListView = (CalendarListView) findViewById(R.id.calendar_listview);

        calendarListAdapter = new CalendarListAdapter(this);
        calendarItemAdapter = new CalendarItemAdapter(this);

        calendarListView.setCalendarListViewAdapter(calendarItemAdapter, calendarListAdapter);

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        calendar.add(Calendar.MONTH, -7);
        loadChoresList(DAY_FORMAT.format(calendar.getTime()));
        //System.out.println();
        //actionBar.setTitle(YEAR_MONTH_FORMAT.format(calendar.getTime()));

        // deal with refresh and load more event.
        calendarListView.setOnListPullListener(new CalendarListView.onListPullListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onRefresh() {
                String date = listTreeMap.firstKey();
                Calendar calendar = getCalendarByYearMonthDay(date);
                calendar.add(Calendar.MONTH, -1);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                loadChoresList(DAY_FORMAT.format(calendar.getTime()));
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onLoadMore() {
                String date = listTreeMap.lastKey();
                Calendar calendar = getCalendarByYearMonthDay(date);
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                loadChoresList(DAY_FORMAT.format(calendar.getTime()));
            }
        });

        //
        calendarListView.setOnMonthChangedListener(new CalendarListView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(String yearMonth) {
                Calendar calendar = CalendarHelper.getCalendarByYearMonth(yearMonth);
                //actionBar.setTitle(YEAR_MONTH_FORMAT.format(calendar.getTime()));
                loadCalendarData(yearMonth);
                //Toast.makeText(CalendarActivity.this, YEAR_MONTH_FORMAT.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
            }
        });

        calendarListView.setOnCalendarViewItemClickListener(new CalendarListView.OnCalendarViewItemClickListener() {
            @Override
            public void onDateSelected(View View, String selectedDate, int listSection, SelectedDateRegion selectedDateRegion) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadChoresList(String date) {
        Calendar calendar = getCalendarByYearMonthDay(date);
        String key = CalendarHelper.YEAR_MONTH_FORMAT.format(calendar.getTime());

        // just not care about how data to create.
        //Random random = new Random();
        /*List<String> set = new ArrayList<>();
        int i = 1;
        while (set.size() < 32) {
            if (i > 0) {
                if (!set.contains(key + "-" + i)) {
                    if (i < 10) {
                        set.add(key + "-0" + i);
                    } else {
                        set.add(key + "-" + i);
                    }
                }
            }
            i++;
        }*/

        //ChoreTools ct = new ChoreService.Chores(getApplicationContext());
        //ArrayList<Chore> temp = ChoreTools.readAnArray(getApplicationContext());
        List<Chore> ch = new ArrayList<Chore>();
        ch = ChoreTools.readAnArray(getApplicationContext());
        listTreeMap.clear();
        for(Chore chore : ch) {
            String day = ChoreTools.dateToStringValue(chore.getStartDate());
            System.out.println(day);
            if (listTreeMap.get(day) != null ) {
                List<Chore> list = new ArrayList<Chore>();
                list = listTreeMap.get(day);
                // System.out.println(listTreeMap);
                list.add(chore);
                //listTreeMap.put(day, list);
                listTreeMap.replace(day, list);
                //System.out.println(listTreeMap);
            } else {
                List<Chore> list = new ArrayList<Chore>();
                list.add(chore);
                listTreeMap.put(day, list);
                //System.out.println(listTreeMap);
            }
        }
        /*for(int j = 0; j < 32; j++) {
            String day = set.get(j);
            if (ch.size() != 0) {
                for (int k = 0; k < ch.size(); k++) {
                    if (listTreeMap.get(day) != null) {
                        if(ChoreTools.dateToStringValue(ch.get(k).getStartDate()).equals(day)) {
                            List<Chore> list = listTreeMap.get(day);
                            // System.out.println(listTreeMap);
                            list.add(ch.get(k));
                        } else {

                        }
                    } else {
                        if(ChoreTools.dateToStringValue(ch.get(k).getStartDate()).equals(day)) {
                            List<Chore> list = new ArrayList<Chore>();
                            list.add(ch.get(k));
                            listTreeMap.put(day, list);
                        } else {

                        }
                    }
                }
            }
        }*/

        calendarListAdapter.setDateDataMap(listTreeMap);
        calendarListAdapter.notifyDataSetChanged();
        calendarItemAdapter.notifyDataSetChanged();

        /*Observable<Notification<ChoreService.Chores>> newsListOb =
                RetrofitProvider.getInstance().create(ChoreService.class)
                        .getChoresList(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(bindToLifecycle())
                        .materialize().share();

        /*RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                Log.w("Error", e);
            }
        });

        newsListOb.filter(Notification::isOnNext)
                .map(n -> n.getValue())
                .filter(m -> !m.getStories().isEmpty())
                .flatMap(m -> Observable.from(m.getStories()))
                .doOnNext(i -> {
                    int index = random.nextInt(5);
                    String day = set.get(index);
                    System.out.println(day);
                    if (listTreeMap.get(day) != null) {
                        List<ChoreService.Chores.StoriesBean> list = listTreeMap.get(day);
                        System.out.println(listTreeMap);
                        list.add(i);
                    } else {
                        List<ChoreService.Chores.StoriesBean> list = new ArrayList<ChoreService.Chores.StoriesBean>();
                        list.add(i);
                        listTreeMap.put(day, list);
                    }

                })
                .toList()
                .subscribe(new Subscriber<List<ChoreService.Chores.StoriesBean>>() {
                    @Override
                    public void onCompleted() {
                        calendarListAdapter.setDateDataMap(listTreeMap);
                        calendarListAdapter.notifyDataSetChanged();
                        calendarItemAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("getAppsError", e.getMessage());
                        //raise Toast, play sound, etc
                    }

                    @Override
                    public void onNext(List<ChoreService.Chores.StoriesBean> storiesBeans) {

                    }
                    //calendarListAdapter.setDateDataMap(listTreeMap);
                    //calendarListAdapter.notifyDataSetChanged();
                    //calendarItemAdapter.notifyDataSetChanged();
                });*/
    }

    private void loadCalendarData(String date) {
        /*new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Random random = new Random();
                            if (date.equals(calendarListView.getCurrentSelectedDate().substring(0, 7))) {
                                for (String d : listTreeMap.keySet()) {
                                    if (date.equals(d.substring(0, 7))) {
                                        ChoreCalendarItemModel customCalendarItemModel = calendarItemAdapter.getDayModelList().get(d);
                                        if (customCalendarItemModel != null) {
                                            customCalendarItemModel.setNewsCount(listTreeMap.get(d).size());
                                            customCalendarItemModel.setFav(random.nextBoolean());
                                        }

                                    }
                                }
                                calendarItemAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();*/

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Random random = new Random();
                            if (date.equals(calendarListView.getCurrentSelectedDate())) {
                                for (String d : listTreeMap.keySet()) {
                                    if (date.equals(d)) {
                                        ChoreCalendarItemModel customCalendarItemModel = calendarItemAdapter.getDayModelList().get(d);
                                        if (customCalendarItemModel != null) {
                                            customCalendarItemModel.setNewsCount(listTreeMap.get(d).size());
                                            customCalendarItemModel.setFav(random.nextBoolean());
                                        }

                                    }
                                }
                                calendarItemAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    public static Calendar getCalendarByYearMonthDay(String yearMonthDay) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeInMillis(DAY_FORMAT.parse(yearMonthDay).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
