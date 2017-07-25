package com.example.administrator.pandatv.module.chinaLive.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.config.CleanMessageUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class SettingActivity extends BaseActivity{
    @BindView(R.id.livechina_setting_return)
    ImageView livechinaSettingReturn;
    @BindView(R.id.livechina_setting_msg)
    CheckBox msg;
    @BindView(R.id.livechina_setting_bofang)
    CheckBox bofang;
    @BindView(R.id.livechina_setting_clear)
    LinearLayout livechinaSettingClear;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.livechina_setting_help)
    LinearLayout livechinaSettingHelp;
    @BindView(R.id.livechina_setting_check)
    LinearLayout livechinaSettingCheck;
    @BindView(R.id.livechina_setting_likeours)
    ImageView livechinaSettingLikeours;
    @BindView(R.id.livechina_setting_about)
    LinearLayout livechinaSettingAbout;
    @BindView(R.id.clear)
    TextView clear;
    private PopupWindow popupWindow;
    private TextView pos,neg;
    private TextView verpos,verneg;
    int total=0;
    private String vsinurl;
    @Override
    protected int getViewID() {
        return R.layout.livechina_setting;
    }

    @Override
    protected void initView() {
        try {
            clear.setText(CleanMessageUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {
        msg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        bofang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    protected void setIntent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.livechina_setting_return, R.id.livechina_setting_clear, R.id.livechina_setting_help, R.id.livechina_setting_check, R.id.livechina_setting_likeours, R.id.livechina_setting_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.livechina_setting_return:
                finish();
                break;
            case R.id.livechina_setting_clear:
                textView2.setText("0MB");
                break;
            case R.id.livechina_setting_help:
                Intent intent = new Intent(this, FankuiANDHelpActivity.class);
                startActivity(intent);
                break;
            case R.id.livechina_setting_check:
//                getVersionPop();
                Toast.makeText(SettingActivity.this, "已是最新版本，无需更新", Toast.LENGTH_SHORT).show();
                break;
            case R.id.livechina_setting_likeours:
                Intent intent2 = new Intent(this, LikeoursActivity.class);
                startActivity(intent2);
                break;
            case R.id.livechina_setting_about:
                Intent intent3 = new Intent(this, AboutActivity.class);
                startActivity(intent3);
                break;
        }
    }
    private void getPopWindow() {
        popupWindow = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(
                R.layout.setting_clear, null);
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
        pos = (TextView) view.findViewById(R.id.clear_yes);
        neg = (TextView) view.findViewById(R.id.clear_no);
        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CleanMessageUtil.clearAllCache(App.content);
                clear.setText("0.00MB");
                popupWindow.dismiss();
            }
        });
        neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
    private void getVersionPop(){
        popupWindow = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(
                R.layout.getversion, null);
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
        verpos = (TextView) view.findViewById(R.id.clear_ver_yes);
        verneg = (TextView) view.findViewById(R.id.clear_ver_no);
        verpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewVersionProgress();
                popupWindow.dismiss();
            }
        });
        verneg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final String uri = vsinurl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Log.i("abc", "下载失败");
//                    Toast.makeText(getActivity(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), time + "updata.apk");
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;

            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
