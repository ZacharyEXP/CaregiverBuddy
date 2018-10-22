package com.example.zacharyexp.myapplication;

import android.os.Bundle;
import android.os.Handler;
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
    public static final SimpleDateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyyMM");

    private TreeMap<String, List<ChoreService.Chores.StoriesBean>> listTreeMap = new TreeMap<>();

    private CalendarListView calendarListView;
    private CalendarListAdapter calendarListAdapter;
    private CalendarItemAdapter calendarItemAdapter;

    private Handler handler = new Handler();

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
        calendar.add(Calendar.MONTH, -7);
        loadChoresList(DAY_FORMAT.format(calendar.getTime()));
        //actionBar.setTitle(YEAR_MONTH_FORMAT.format(calendar.getTime()));

        // deal with refresh and load more event.
        calendarListView.setOnListPullListener(new CalendarListView.onListPullListener() {
            @Override
            public void onRefresh() {
                String date = listTreeMap.firstKey();
                Calendar calendar = getCalendarByYearMonthDay(date);
                calendar.add(Calendar.MONTH, -1);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                loadChoresList(DAY_FORMAT.format(calendar.getTime()));
            }

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

    private void loadChoresList(String date) {
        Calendar calendar = getCalendarByYearMonthDay(date);
        String key = CalendarHelper.YEAR_MONTH_FORMAT.format(calendar.getTime());

        // just not care about how data to create.
        Random random = new Random();
        final List<String> set = new ArrayList<>();
        while (set.size() < 5) {
            int i = random.nextInt(29);
            if (i > 0) {
                if (!set.contains(key + "-" + i)) {
                    if (i < 10) {
                        set.add(key + "-0" + i);
                    } else {
                        set.add(key + "-" + i);
                    }
                }
            }
        }

        Observable<Notification<ChoreService.Chores>> newsListOb =
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
        });*/

        newsListOb.filter(Notification::isOnNext)
                .map(n -> n.getValue())
                .filter(m -> !m.getStories().isEmpty())
                .flatMap(m -> Observable.from(m.getStories()))
                .doOnNext(i -> {
                    int index = random.nextInt(5);
                    String day = set.get(index);
                    if (listTreeMap.get(day) != null) {
                        List<ChoreService.Chores.StoriesBean> list = listTreeMap.get(day);
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
                });
    }

    private void loadCalendarData(String date) {
        new Thread() {
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
