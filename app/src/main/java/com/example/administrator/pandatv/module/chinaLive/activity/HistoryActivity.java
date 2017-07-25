package com.example.administrator.pandatv.module.chinaLive.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.module.chinaLive.adapter.HistoryInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class HistoryActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.history_return_iv)
    ImageView istoryReturnIv;
    @BindView(R.id.livechina_history_bianji)
    TextView bianji;
    @BindView(R.id.livechina_history_recycleview)
    RecyclerView recycleview;
    @BindView(R.id.livechina_cancel)
    Button livechinaCancel;
    @BindView(R.id.livechina_delete)
    Button livechinaDelete;
    @BindView(R.id.livachina_linear_button)
    LinearLayout livachinaLinearButton;
    private HistoryInfoAdapter infoAdapter;
    private List<String> mList = new ArrayList<>();
    private PopupWindow popupWindow;
    private TextView button,buttonone;

    @Override
    protected int getViewID() {
        return R.layout.livechina_history;
    }

    @Override
    protected void initView() {
        recycleview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        infoAdapter = new HistoryInfoAdapter(this, mList);
        recycleview.setAdapter(infoAdapter);
        infoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void setListener() {
        infoAdapter.setOnclick(new HistoryInfoAdapter.OnClickListeren() {
            @Override
            public void getListeren(View view, int position) {

            }
        });
    }

    @Override
    protected void setIntent() {

    }



    @OnClick({R.id.history_return_iv,R.id.livechina_history_bianji,R.id.livechina_cancel, R.id.livechina_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_return_iv:
                finish();
                break;
            case R.id.livechina_history_bianji:
                if(bianji.getText().toString().equals("编辑")) {
                    bianji.setText("取消");
                    livachinaLinearButton.setVisibility(View.VISIBLE);
                }else if(bianji.getText().toString().equals("取消")){
                    bianji.setText("编辑");
                    livachinaLinearButton.setVisibility(View.GONE);
                }
                break;
            case R.id.livechina_cancel:
                if(livechinaCancel.getText().toString().equals("全选")) {
                    livechinaCancel.setText("取消全选");
//                    if (bianji.getText().equals("取消")) {
//                        for (int i = 0; i < his_array.size(); i++) {
//                            his_array.get(i).setFlg_bulen(true);
//                        }
//                        his_ array.size();
//                        livechinaDelete.setText("删除" + number);
//                    }
//                } else {
//                    for (int i = 0; i < his_array.size(); i++) {
//                        his_array.get(i).setFlg_bulen(false);
//                    }
//                    number = 0;
                    livechinaDelete.setText("删除");
                    livechinaCancel.setText("全选");
                }

                break;
            case R.id.livechina_delete:
                getDeletePop();
                break;

        }
    }
    private void getDeletePop(){
        popupWindow = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(
                R.layout.delete_dialog, null);
        popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);// 取得焦点
        ColorDrawable colorDrawable=new ColorDrawable(0x30000000);
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(colorDrawable);
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        button = (TextView) view.findViewById(R.id.delete_yes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        buttonone= (TextView) view.findViewById(R.id.delete_no);
        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
}
