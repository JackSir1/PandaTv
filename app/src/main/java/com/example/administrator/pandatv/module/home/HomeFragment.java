package com.example.administrator.pandatv.module.home;

import android.view.View;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{
    @Override
    protected int getViweId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadDate() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {


    }

    @Override
    public void setReferesh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void setResult(HomeBean homeBean) {
        List<HomeBean.DataBean.BigImgBean> bigImgBeanList = homeBean.getData().getBigImg();
    }

    @Override
    public void showErrorMassage(String errorMessage) {

    }

}
