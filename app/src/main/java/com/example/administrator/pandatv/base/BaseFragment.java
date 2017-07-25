package com.example.administrator.pandatv.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.model.util.ShowDialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/11.
 */

public abstract class BaseFragment extends Fragment {
    protected Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getViweId(), container, false);
        unbinder=ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setListener();
        loadDate();
        ShowDialogUtils showDialogUtils = ShowDialogUtils.getInsenter().updateConnectedFlags(getContext());
        Boolean netConnected = showDialogUtils.isNetConnected();
        if (!netConnected){
            Toast.makeText(getContext(),"您的网络可能出小差了",Toast.LENGTH_SHORT).show();
            unNet();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            onHidden();
        }else {
            onShow();
        }
    }

    public void unNet(){

    }

    protected abstract int getViweId();
    protected abstract void initView(View view);
    protected abstract void loadDate();
    protected abstract void setListener();
    public void onShow(){}
    public void onHidden(){}
    public void setParams(Bundle bundle){};
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (this != null) {
            FragmentManager f=App.content.getSupportFragmentManager();
            if (f != null) {
                final FragmentTransaction ft = f.beginTransaction();
                if (ft != null) {
                    ft.remove(this).commitAllowingStateLoss();
                }
            }
        }
    }

}
