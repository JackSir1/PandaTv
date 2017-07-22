package com.example.administrator.pandatv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
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
import android.widget.Toast;

import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.util.ACache;

import java.io.File;

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


    @Override
    protected int getViewID() {
        return R.layout.loginsuccess;
    }

    @Override
    protected void initView() {
        checkPermission();
        intent = getIntent();
        String nameme = intent.getStringExtra("nameme");
        name.setText("央视网"+nameme);
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
               popwindow();
                break;
            case R.id.nicheng:
                break;
            case R.id.loginsuccess:
               ACache aCache = ACache.get(SuccessActivity.this);
                aCache.remove("loginentity");
                setResult(300,intent);
                finish();
                break;
        }
    }
private void getPop(){
    View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    View view = LayoutInflater.from(this).inflate(R.layout.add_popup_dialog, null);
    int w = this.getWindowManager().getDefaultDisplay().getWidth();
    int width = getResources().getDisplayMetrics().widthPixels;
    int height = getResources().getDisplayMetrics().heightPixels;

    popupWindow = new PopupWindow(view,width/2+50,height);
    popupWindow.setFocusable(true);
    popupWindow.setBackgroundDrawable(null);
    popupWindow.setOutsideTouchable(true);
    ColorDrawable dw = new ColorDrawable(0x30000000);
    popupWindow.setBackgroundDrawable(dw);
    mButPhoto = (Button) view.findViewById(R.id.add_photo);
    mButPz = (Button) view.findViewById(R.id.add_pz);
    mButCancel = (Button) view.findViewById(R.id.add_cancel);
    mButPhoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentFromGallery = new Intent();
                intentFromGallery.setType("image/*");//选择图片
                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentFromGallery, CODE_REQUEST_PICTURE);
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
    popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

}
    private void checkPermission(){
        String permission1 = "android.permission.CAMERA";
        String permission2 = "android.permission.WRITE_EXTERNAL_STORAGE";
        String permission3 = "android.permission.READ_EXTERNAL_STORAGE";
        String[] permissionArray  = {permission1, permission2, permission3};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArray, 123);
        }
    }

    /**
     * 判断是否有存储卡，有返回TRUE，否则FALSE
     * @return
     */
    public static boolean isSDcardExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //在拍照、选取照片、裁剪Activity结束后，调用的方法
//        if(requestCode == CODE_REQUEST_CAMERA_PHOTO){
//            if(resultCode == Activity.RESULT_OK){
//                if(data == null) {
//                    return;
//                }
//                File tempFile = new File(
//                        Environment.getExternalStorageDirectory(),
//                        mCurrentPhotoName);
//                mNewCurrentPhotoName = "pz_" + mCurrentPhotoName;
//                String outPath = Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
//                CropPhotoUtil.cropRawPhoto(this, Uri.fromFile(tempFile), CODE_REQUEST_CROP_PHOTO, outPath, 200, 200);
//
//            }
//        }else if(requestCode == CODE_REQUEST_CROP_PHOTO){
//            if(resultCode == Activity.RESULT_OK){
//                String path =  Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
//                ImageUtils.getInstance(this).setHeadViewFile(imge, path, ImageUtils.ImageType.IMAGE_TYPE_CRICLE);
//            }
//        }else if(requestCode == CODE_REQUEST_PICTURE && resultCode == Activity.RESULT_OK){
//            if(data == null) return;
//            mNewCurrentPhotoName = System.currentTimeMillis() + ".jpg";
//            String outPath = Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
//            CropPhotoUtil.cropRawPhoto(this, data.getData(), CODE_REQUEST_CROP_PHOTO, outPath, 200, 200);
//            setImageToHeadView(data);
//        }
//    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

    }*/
    /**
     * 裁剪原始的图片
     */
//    public void cropRawPhoto(Uri uri) {
//
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//
//        // 设置裁剪
//        intent.putExtra("crop", "true");
//
//        // aspectX , aspectY :宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//
//        // outputX , outputY : 裁剪图片宽高
//        intent.putExtra("outputX", output_X);
//        intent.putExtra("outputY", output_Y);
//        intent.putExtra("return-data", true);
//
//        startActivityForResult(intent, CODE_RESULT_REQUEST);
//    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
//    private void setImageToHeadView(Intent intent) {
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            Bitmap photo = extras.getParcelable("data");
//            imge.setImageBitmap(photo);
//        }
//    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
//    ==================================================
public void popwindow() {
    popupWindow = new PopupWindow();
    View view = LayoutInflater.from(SuccessActivity.this).inflate(
            R.layout.add_popup_dialog, null);
    popupWindow = new PopupWindow(view,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    popupWindow.setFocusable(true);// 取得焦点
    //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
    popupWindow.setBackgroundDrawable(new BitmapDrawable());
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

            Intent intentFromGallery = new Intent();
            // 设置文件类型
            intentFromGallery.setType("image/*");
            intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
        }
    });
    mButPz.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 启动手机相机拍摄照片作为头像

            Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // 判断存储卡是否可用，存储照片文件
            if (hasSdcard()) {
                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                        .fromFile(new File(Environment
                                .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
            }

            startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
        }
    });
    mButCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    });

}
    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            imge.setImageBitmap(photo);
//            AlertDialog.Builder dilog = new AlertDialog.Builder(this);
//            dilog.setMessage("您的头像已提交审核请稍后回来确认吧");
//            dilog.setNegativeButton("我知道啦", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {

                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }
    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

}
