package com.example.administrator.pandatv.model.entity.livechinaEntity;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class EMeiShanBean {

    private List<LiveBean> live;

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class LiveBean {
        /**
         * title : 云海日出观赏点
         * brief : 此处是峨眉山观日出的最佳点位，踞高望远，日出景象更加浩瀚壮阔。黎明前的天空是美妙的，渐渐地，地平线上天开一线，飘起缕缕红霞，托着三两朵金色镶边的彩云，一个辉煌的白昼即将降临。彩云下，空旷的紫蓝色的天幕上，一刹间，吐出一点紫红，缓慢上升，逐渐变成小弧、半圆；变成桔红、金红；然后微微一个跳跃，拖着一抹瞬息即逝的尾光，一轮圆圆的红日嵌在天边。
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/12/28/1451291907950_701.jpg
         * id : emsarm
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
