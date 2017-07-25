package com.example.administrator.pandatv.model.entity.livechinaEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lizhuofang on 2017/7/13.
 */

public class LivechinaTSBean implements Serializable {

    private List<LiveBean> live;

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class LiveBean implements Serializable{
        /**
         * title : 天山天池灯杆山
         * brief : 天池以西三公里处是灯杆山，海拔2718米，山体长3公里许，这里是天池观落日和欣赏古冰川地貌的绝好去处。老君庙、东岳庙就建于此。由灯杆山西眺，乌鲁木齐可尽收眼底，尤其在华灯初上之际，远看乌鲁木齐万家灯火，其乐无穷。
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/12/24/1450924981469_923.jpg
         * id : xjtcdgs
         * order : 1
         */

        private String title;
        private String brief;
        private String image;
        private String id;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
