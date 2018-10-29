package com.example.zacharyexp.myapplication;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 适配器
 * Created by huanghaibin on 2017/12/4.
 */

public class ArticleAdapter extends GroupRecyclerAdapter<String, Article> {
    private RequestManager mLoader;

    private List<Chore> ch;
    private List<Drug> dr;
    List<String> titles;
    LinkedHashMap<String, List<Article>> map;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArticleAdapter(Context context) {
        super(context);
        mLoader = Glide.with(context.getApplicationContext());
        map = new LinkedHashMap<>();
        titles = new ArrayList<>();

        int id = 0;

        ch = ChoreTools.readAnArray(context);
        dr = DrugTools.readAnArray(context);

        for(Chore chore : ch) {
            String day = ChoreTools.dateToStringValue(chore.getStartDate());
            System.out.println(day);
            if (map.get(day) != null ) {
                List<Article> list = new ArrayList<Article>();
                list = map.get(day);
                // System.out.println(listTreeMap);
                list.add(create(chore.getChoreName(), chore.gettypeName(),
                        "http://blog.oasisdigital.com/files/2014/11/check.jpg",
                        chore.getStartDate().get(Calendar.YEAR), chore.getStartDate().get(Calendar.MONTH), chore.getStartDate().get(Calendar.DAY_OF_MONTH), id));
                //listTreeMap.put(day, list);
                map.replace(day, list);
                if(!titles.contains(day)) {
                    titles.add(day);
                }
                //System.out.println(listTreeMap);
            } else {
                List<Article> list = new ArrayList<Article>();
                list.add(create(chore.getChoreName(), chore.gettypeName(), "http://blog.oasisdigital.com/files/2014/11/check.jpg",
                        chore.getStartDate().get(Calendar.YEAR), chore.getStartDate().get(Calendar.MONTH), chore.getStartDate().get(Calendar.DAY_OF_MONTH), id));
                map.put(day, list);
                if(!titles.contains(day)) {
                    titles.add(day);
                }
                //System.out.println(listTreeMap);
            }

            id++;
        }

        for(Drug drug : dr) {
            String day = DrugTools.dateToStringValue(drug.getStartDate());
            System.out.println(day);
            if (map.get(day) != null ) {
                List<Article> list = new ArrayList<Article>();
                list = map.get(day);
                // System.out.println(listTreeMap);
                list.add(create(drug.getDrugName(), drug.getLaboratoryName(), "https://image.freepik.com/free-icon/pill_318-42938.jpg",
                        drug.getStartDate().get(Calendar.YEAR), drug.getStartDate().get(Calendar.MONTH), drug.getStartDate().get(Calendar.DAY_OF_MONTH), id));
                //listTreeMap.put(day, list);
                map.replace(day, list);
                if(!titles.contains(day)) {
                    titles.add(day);
                }
                //System.out.println(listTreeMap);
            } else {
                List<Article> list = new ArrayList<Article>();
                list.add(create(drug.getDrugName(), drug.getLaboratoryName(), "https://image.freepik.com/free-icon/pill_318-42938.jpg",
                        drug.getStartDate().get(Calendar.YEAR), drug.getStartDate().get(Calendar.MONTH), drug.getStartDate().get(Calendar.DAY_OF_MONTH), id));
                map.put(day, list);
                if(!titles.contains(day)) {
                    titles.add(day);
                }
                //System.out.println(listTreeMap);
            }

            id++;
        }

        //map.put("今日推荐", create(0));
        //map.put("每周热点", create(1));
        //map.put("最高评论", create(2));
        //titles.add("今日推荐");
        //titles.add("每周热点");
        //titles.add("最高评论");

        resetGroups(map,titles);
    }

    public List<String> getTitles(){return titles;}

    public LinkedHashMap<String, List<Article>> getMap() {return map;}

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ArticleViewHolder(mInflater.inflate(R.layout.item_list_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Article item, int position) {
        ArticleViewHolder h = (ArticleViewHolder) holder;
        h.mTextTitle.setText(item.getTitle());
        h.mTextContent.setText(item.getContent());
        mLoader.load(item.getImgUrl())
                .asBitmap()
                .centerCrop()
                .into(h.mImageView);
        h.setPos(position);
    }

    private static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle,
                mTextContent;
        private ImageView mImageView;
        private int position;

        private ArticleViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTextContent = (TextView) itemView.findViewById(R.id.tv_content);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        private void setPos(int pos) {
            position = pos;
        }
    }


    private static Article create(String title, String content, String imgUrl) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setImgUrl(imgUrl);
        return article;
    }

    private static Article create(String title, String content, String imgUrl, int year, int month, int day) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setImgUrl(imgUrl);
        article.setYear(year);
        article.setMonth(month);
        article.setDay(day);
        return article;
    }

    private static Article create(String title, String content, String imgUrl, int year, int month, int day, int id) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setImgUrl(imgUrl);
        article.setYear(year);
        article.setMonth(month);
        article.setDay(day);
        article.setId(id);
        return article;
    }

    private static List<Article> create(int p) {
        List<Article> list = new ArrayList<>();

        return list;
    }
}
