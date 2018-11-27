package com.govst.zacharyexp.caregiverbuddy;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelin.calendarlistview.library.BaseCalendarItemAdapter;
import com.kelin.calendarlistview.library.BaseCalendarItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CalendarItemAdapter extends BaseCalendarItemAdapter<ChoreCalendarItemModel> {
    protected TreeMap<String, ChoreCalendarItemModel> dayModelList = new TreeMap<>();
    public CalendarItemAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(String date, ChoreCalendarItemModel model, View convertView, ViewGroup parent) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.griditem_calendar_item, null);

        TextView dayNum = (TextView) view.findViewById(R.id.tv_day_num);
        dayNum.setText(model.getDayNumber());

        view.setBackgroundResource(com.kelin.calendarlistview.library.R.drawable.bg_shape_calendar_item_normal);

        if (model.isToday()) {
            dayNum.setTextColor(mContext.getResources().getColor(com.kelin.calendarlistview.library.R.color.red_ff725f));
            //dayNum.setText(mContext.getResources().getString(com.kelin.calendarlistview.library.R.string.today));
            dayNum.setText("Today");
        }

        if (model.isHoliday()) {
            dayNum.setTextColor(mContext.getResources().getColor(com.kelin.calendarlistview.library.R.color.red_ff725f));
        }


        if (model.getStatus() == BaseCalendarItemModel.Status.DISABLE) {
            dayNum.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
        }

        if (!model.isCurrentMonth()) {
            dayNum.setTextColor(mContext.getResources().getColor(com.kelin.calendarlistview.library.R.color.gray_bbbbbb));
            view.setClickable(true);
        }

        TextView dayNewsCount = (TextView) view.findViewById(R.id.tv_day_new_count);
        if (model.getNewsCount() > 0) {
            dayNewsCount.setText(String.format(mContext.getResources().getString(R.string.calendar_item_new_count), model.getNewsCount()));
            dayNewsCount.setVisibility(View.VISIBLE);
        } else {
            dayNewsCount.setVisibility(View.GONE);
        }

        ImageView isFavImageView = (ImageView) view.findViewById(R.id.image_is_fav);
        if (model.isFav()) {
            isFavImageView.setVisibility(View.VISIBLE);
        } else {
            isFavImageView.setVisibility(View.GONE);
        }


        return view;
    }

    //@Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public TreeMap<String, ChoreCalendarItemModel> getDayModelList(Context c) {
        ////
        List<Chore> ch = ChoreTools.readAnArray(c);
        TreeMap<String, ChoreCalendarItemModel> listTreeMapChore = new TreeMap<>();
        for(Chore chore : ch) {
            String day = ChoreTools.dateToStringValue(chore.getStartDate());
            System.out.println(day);
            if (listTreeMapChore.get(day) != null ) {
                List<ChoreCalendarItemModel> list = new ArrayList<ChoreCalendarItemModel>();
                //list = listTreeMapChore.get(day);
                // System.out.println(listTreeMap);
                //list.add(new ChoreCalendarItemModel(chore));
                //listTreeMap.put(day, list);
                //listTreeMapChore.put(day, new ChoreCalendarItemModel(chore));
                //System.out.println(listTreeMap);
            } else {
                List<ChoreCalendarItemModel> list = new ArrayList<ChoreCalendarItemModel>();
                //listTreeMapChore.put(day, new ChoreCalendarItemModel(chore));
                //listTreeMapChore.put(day, list);
                //System.out.println(listTreeMap);
            }
        }
        ////
        return listTreeMapChore;
    }

    @Override
    public void setDayModelList(TreeMap<String, ChoreCalendarItemModel> dayModelList) {
        this.dayModelList = dayModelList;
        indexToTimeMap.clear();
        for (String time : this.dayModelList.keySet()) {
            indexToTimeMap.add(time);
        }
    }
}