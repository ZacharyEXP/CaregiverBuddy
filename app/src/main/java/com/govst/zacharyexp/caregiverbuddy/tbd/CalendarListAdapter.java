package com.govst.zacharyexp.caregiverbuddy.tbd;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.govst.zacharyexp.caregiverbuddy.R;
import com.govst.zacharyexp.caregiverbuddy.drug.Drug;
import com.govst.zacharyexp.caregiverbuddy.chore.Chore;
import com.govst.zacharyexp.caregiverbuddy.tbd.ChoreMonthActivity;
import com.kelin.calendarlistview.library.BaseCalendarListAdapter;
import com.kelin.calendarlistview.library.CalendarHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

public class CalendarListAdapter extends BaseCalendarListAdapter<Chore> {
    TreeMap<String, List<Chore>> dateDataMapChore = new TreeMap<>();
    TreeMap<String, List<Drug>> dateDataMapDrug = new TreeMap<>();
    TreeMap<String, Integer> dateMapToPos = new TreeMap<>();

    public CalendarListAdapter(Context context) {
        super(context);
    }

    @Override
    public void setDateDataMap(TreeMap<String, List<Chore>> dateDataMap) {
        this.dateDataMapChore = dateDataMap;
        indexToTimeList = new ArrayList<>(dateDataMapChore.keySet());
        for(String s : dateDataMapDrug.keySet()) {
            if(!indexToTimeList.contains(s)) {
                indexToTimeList.add(s);
            }
        }
        int pos = 0;
        for (String key : indexToTimeList) {
            dateMapToPos.put(key, pos);
            try {
                pos += dateDataMapChore.get(key).size() + 1;
            } catch (Exception e) {

            }
            try {
                pos += dateDataMapDrug.get(key).size() + 1;
            } catch (Exception e) {

            }
            //pos++;
        }
    }

    public void setDateDataMap(TreeMap<String, List<Chore>> dateDataMapC, TreeMap<String, List<Drug>> dateDataMapD) {
        this.dateDataMapChore = dateDataMapC;
        this.dateDataMapDrug = dateDataMapD;
        indexToTimeList = new ArrayList<>(dateDataMapChore.keySet());
        indexToTimeList.addAll(dateDataMapDrug.keySet());
        //for(String s : dateDataMapDrug.keySet()) {
            //if(!indexToTimeList.contains(s)) {
             //   indexToTimeList.add(s);
            //}
        //}
        int pos = 0;
        for (String key : indexToTimeList) {
            dateMapToPos.put(key, pos);
            try {
                pos += dateDataMapChore.get(key).size() + 1;
            } catch (Exception e) {

            }
            //try {
              //  pos += dateDataMapDrug.get(key).size() + 1;
            //} catch (Exception e) {

            //}
        }
    }

    @Override
    public View getSectionHeaderView(String date, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        List<Chore> modelList = dateDataMap.get(date);
        if (convertView != null) {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.listitem_calendar_header, null);
            headerViewHolder = new HeaderViewHolder();
            //headerViewHolder.dayText = (TextView) convertView.findViewById(R.id.header_day);
            //headerViewHolder.yearMonthText = (TextView) convertView.findViewById(R.id.header_year_month);
            //headerViewHolder.isFavImage = (ImageView) convertView.findViewById(R.id.header_btn_fav);
            headerViewHolder.setDayText((AppCompatTextView) convertView.findViewById(R.id.header_day));
            headerViewHolder.setYearMonthText((AppCompatTextView) convertView.findViewById(R.id.header_year_month));
            headerViewHolder.setIsFavImage((ImageView) convertView.findViewById(R.id.header_btn_fav));
            convertView.setTag(headerViewHolder);
        }

        Calendar calendar = CalendarHelper.getCalendarByYearMonthDay(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day < 10) {
            dayStr = "0" + dayStr;
        }
        headerViewHolder.dayText.setText(dayStr);
        headerViewHolder.yearMonthText.setText(ChoreMonthActivity.YEAR_MONTH_FORMAT.format(calendar.getTime()));
        return convertView;
    }

    @Override
    public View getItemView(Chore model, String date, int pos, View convertView, ViewGroup parent) {
        ContentViewHolder contentViewHolder;
        if (convertView != null) {
            contentViewHolder = (ContentViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.listitem_calendar_content, null);
            contentViewHolder = new ContentViewHolder();
            contentViewHolder.titleTextView = (AppCompatTextView) convertView.findViewById(R.id.title);
            contentViewHolder.timeTextView = (AppCompatTextView) convertView.findViewById(R.id.time);
            contentViewHolder.newsImageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(contentViewHolder);
        }

        contentViewHolder.titleTextView.setText(model.getChoreName());
        contentViewHolder.timeTextView.setText(date);
//        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(convertView.getResources())
//                .setRoundingParams(RoundingParams.asCircle())
//                .build();
//        contentViewHolder.newsImageView.setHierarchy(hierarchy);
//        contentViewHolder.newsImageView.setImageURI(Uri.parse(model.getImages().get(0)));
        //Picasso.with(convertView.getContext()).load(Uri.parse(model.getImages().get(0)))
                //.into(contentViewHolder.newsImageView);
        return convertView;
    }

    public View getItemView(Drug model, String date, int pos, View convertView, ViewGroup parent) {
        ContentViewHolder contentViewHolder;
        if (convertView != null) {
            contentViewHolder = (ContentViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.listitem_calendar_content, null);
            contentViewHolder = new ContentViewHolder();
            contentViewHolder.titleTextView = (AppCompatTextView) convertView.findViewById(R.id.title);
            contentViewHolder.timeTextView = (AppCompatTextView) convertView.findViewById(R.id.time);
            contentViewHolder.newsImageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(contentViewHolder);
        }

        contentViewHolder.titleTextView.setText(model.getDrugName());
        contentViewHolder.timeTextView.setText(date);
//        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(convertView.getResources())
//                .setRoundingParams(RoundingParams.asCircle())
//                .build();
//        contentViewHolder.newsImageView.setHierarchy(hierarchy);
//        contentViewHolder.newsImageView.setImageURI(Uri.parse(model.getImages().get(0)));
        //Picasso.with(convertView.getContext()).load(Uri.parse(model.getImages().get(0)))
        //.into(contentViewHolder.newsImageView);
        return convertView;
    }

    @Override
    public Integer getDataListIndexByDate(String date) {
        int i;
        for (i = 0; i < indexToTimeList.size(); i++) {
            String key = indexToTimeList.get(i);
            if (key.compareTo(date) > 0) {
                if (i > 0) {
                    return dateMapToPos.get(indexToTimeList.get(i - 1)) + 1;
                } else if (i == 0) {
                    return 1;
                }
            }
        }
        return dateMapToPos.get(indexToTimeList.get(i - 1)) + 1;
    }


    private static class HeaderViewHolder {
        AppCompatTextView dayText;
        AppCompatTextView yearMonthText;
        ImageView isFavImage;

        public AppCompatTextView getDayText() {
            return dayText;
        }

        public void setDayText(AppCompatTextView dayText) {
            this.dayText = dayText;
        }

        public AppCompatTextView getYearMonthText() {
            return yearMonthText;
        }

        public void setYearMonthText(AppCompatTextView yearMonthText) {
            this.yearMonthText = yearMonthText;
        }

        public ImageView getIsFavImage() {
            return isFavImage;
        }

        public void setIsFavImage(ImageView isFavImage) {
            this.isFavImage = isFavImage;
        }
    }

    private static class ContentViewHolder {
        AppCompatTextView titleTextView;
        AppCompatTextView timeTextView;
        ImageView newsImageView;
    }
}
