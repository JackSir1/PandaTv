package com.example.administrator.pandatv.model.util.playVideoUtil;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;

/**
 * Created by Administrator on 2017/7/18.
 */

public interface IPlayVideoContract {
    interface View extends BaseView<Presenter>{
        void getVedioUrl(PlayVideoBean playVideoBean);
    }
    interface Presenter extends BasePresenter{
        void setVedioPid(String pid);
    }
}
