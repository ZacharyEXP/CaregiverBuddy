package com.govst.zacharyexp.caregiverbuddy.tbd;

import android.content.Context;

import com.govst.zacharyexp.caregiverbuddy.chore.Chore;
import com.govst.zacharyexp.caregiverbuddy.chore.ChoreTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface ChoreService {
    //@GET("/api/4/news/before/{chores}")
    //public Observable<Chores> getChoresList(@Path("chores") String date);

    public class Chores {

        public Chores(Context c) {
            ChoreTools ct = new ChoreTools();
            ArrayList<Chore> ch = ct.readAnArray(c);
            int i = 0;
            for(Chore chore : ch) {
                stories.add(new StoriesBean(new StoriesBean.ExtraField(false, "")));
                stories.get(i).setTitle(chore.getChoreName());
                setDate(ct.dateToStringValue(chore.getStartDate()));
                i++;
            }
            //super();
            //date = "nn";
        }

        private String date;

        private List<StoriesBean> stories;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<StoriesBean> getStories() {
            return stories;
        }

        public StoriesBean getStories(int i) {
            return stories.get(i);
        }

        public void setStories(List<StoriesBean> stories) {
            this.stories = stories;
        }


        public static class StoriesBean implements Serializable {
            private ExtraField extraField;
            private String title;
            private String ga_prefix;
            private boolean multipic;
            private int type;
            private long id;
            private List<String> images;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getGa_prefix() {
                return ga_prefix;
            }

            public void setGa_prefix(String ga_prefix) {
                this.ga_prefix = ga_prefix;
            }

            public boolean isMultipic() {
                return multipic;
            }

            public void setMultipic(boolean multipic) {
                this.multipic = multipic;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public ExtraField getExtraField() {
                return extraField;
            }

            public void setExtraField(ExtraField extraField) {
                this.extraField = extraField;
            }

            public String toString(){
                return title + id;
            }

            public static class ExtraField  implements Serializable {
                private boolean isHeader;
                private String date;

                public ExtraField(boolean isHeader, String date) {
                    this.isHeader = isHeader;
                    this.date = date;
                }

                public boolean isHeader() {
                    return isHeader;
                }

                public void setHeader(boolean header) {
                    isHeader = header;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }
            }

            public StoriesBean(ExtraField extraField) {
                this.extraField = extraField;
            }
        }


    }
}