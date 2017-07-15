package com.example.administrator.pandatv.model.entity.livechinaEntity;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class HuangShanBean {

    private List<LiveBean> live;

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class LiveBean {
        /**
         * title : 黄山始信峰
         * brief : 始信峰为黄山36小峰之一，海拔1683米。这里巧石争艳，奇松林立，三面临空，悬崖千丈，云蒸霞蔚，风姿独秀。相传，明代黄习远自云谷寺游至此峰，如入画境，似幻而真，方信黄山风景奇绝，并题名“始信”。
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/12/31/1451533077853_22.jpg
         * id : hssxf
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
