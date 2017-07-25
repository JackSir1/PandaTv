package com.example.administrator.pandatv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.util.ACache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/21.
 */
public class SuccessActivity extends BaseActivity {
    @BindView(R.id.loginsuc_return_imageView)
    ImageView loginsucReturnImageView;
    @BindView(R.id.imge)
    ImageView imge;
    @BindView(R.id.touxiang)
    LinearLayout touxiang;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.nicheng)
    LinearLayout nicheng;
    @BindView(R.id.loginsuccess)
    Button loginsuccess;
    private Intent intent;
    private PopupWindow popupWindow;
    private Button mButPhoto,mButPz,mButCancel;
    private static final int CODE_REQUEST_CAMERA_PHOTO = 200;
    private static final int CODE_REQUEST_CROP_PHOTO = 201;
    private static final int CODE_REQUEST_PICTURE = 202;
    private String mCurrentPhotoName;
    private String mNewCurrentPhotoName;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;
    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径
    private Bitmap bitmapima;

    @Override
    protected int getViewID() {
        return R.layout.loginsuccess;
    }

    @Override
    protected void initView() {
        intent = getIntent();
        String nameme = intent.getStringExtra("nameme");
        bitmapima = intent.getParcelableExtra("image");
        name.setText(nameme);
        imge.setImageBitmap(bitmapima);
}

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }



    @OnClick({R.id.loginsuc_return_imageView, R.id.touxiang, R.id.nicheng,R.id.loginsuccess})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginsuc_return_imageView:
                finish();
                break;
           case R.id.touxiang:
               init();
               popwindow();
                break;
            case R.id.nicheng:
                Intent intent1=new Intent(SuccessActivity.this,UpdateInfo.class);
                intent1.putExtra("UpdateName",name.getText().toString().trim());
                startActivityForResult(intent1,400);
                break;
            case R.id.loginsuccess:
               ACache aCache = ACache.get(SuccessActivity.this);
                aCache.remove("loginentity");
                setResult(300,intent);
                finish();
                break;
        }
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
        startActivityForResult(intent2, 2);// 采用ForResult打开
        popupWindow.dismiss();
    }


    private void init() {

        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            imge.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }
public void popwindow() {
    popupWindow = new PopupWindow();
    View view = LayoutInflater.from(this).inflate(
            R.layout.add_popup_dialog, null);
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
    popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    mButPhoto = (Button) view.findViewById(R.id.add_photo);
    mButPz = (Button) view.findViewById(R.id.add_pz);
    mButCancel = (Button) view.findViewById(R.id.add_cancel);

    mButPhoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 从本地相册选取图片作为头像
            Intent intent1 = new Intent(Intent.ACTION_PICK, null);
            intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent1, 1);
            popupWindow.dismiss();
        }
    });
    mButPz.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            choseHeadImageFromCameraCapture();
        }
    });
    mButCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    });

}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        imge.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
