package com.example.administrator.pandatv.module.pandaObserver;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PandaObserverBean;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface PandaObserverContract {
    interface View extends BaseView<Presenter> {
        void setResult(PandaObserverBean observerBean);
        void setError(String error);
    }
    interface Presenter extends BasePresenter {

    }
}
