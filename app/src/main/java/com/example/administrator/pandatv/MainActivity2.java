package com.example.administrator.pandatv;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by lizhuofang on 2017/7/22.
 */

public class MainActivity2 extends Activity {

    private AvatarImageView avatarImageView;
    private static final int CODE_REQUEST_CAMERA_PHOTO = 200;
    private static final int CODE_REQUEST_CROP_PHOTO = 201;
    private static final int CODE_REQUEST_PICTURE = 202;
    private ImageView pictureImg;
    private String mCurrentPhotoName;
    private String mNewCurrentPhotoName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //初始化控件
        avatarImageView = (AvatarImageView) findViewById(R.id.avatarIv);
        avatarImageView.setDialogBackgroundColor("#00AAAA"); //设置对话框的背景色
        avatarImageView.setBtnClickedColor("#00AAAA"); //设置按钮点击后的颜色

        avatarImageView.setAnimResId(R.style.ACPLDialog); //设置dialog显示的动画
        avatarImageView.setTitleColor("#FFEEAA");  //设置标题的颜色
        avatarImageView.setBtnBackgroundColor("#FFEEAA"); //设置按钮的背景色
        avatarImageView.setTitleLineColor("#FFEEAA"); //设置标题下的分割线的颜色
        avatarImageView.setLineColor("#FFEEAA"); //设置按钮之间的分割线的颜色
        avatarImageView.setTitlePaddingTopBottom(30); //设置标题的padding
        avatarImageView.setBtnPaddingTopBottom(30); //设置按钮的padding
        avatarImageView.setTitleText("testTitle"); //设置标题的文字
        avatarImageView.setPhotoButtonText("testPhotoText"); //设置拍照按钮的文字
        avatarImageView.setChoosePicButtonText("testChooseText"); //设置选择照片的文字
        avatarImageView.setDialogCorner(20); //设置dialog的角度
        avatarImageView.setBtnTextColor("#FFEEAA"); //设置按钮文本的颜色
        avatarImageView.setTitleTextSize(30); //设置标题的文字大小
        avatarImageView.setBtnTextSize(30); //设置按钮的文字大小

        avatarImageView.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo) {
                Toast.makeText(MainActivity2.this,"设置新的头像成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //在拍照、选取照片、裁剪Activity结束后，调用的方法
        if (avatarImageView != null) {
            avatarImageView.onActivityResult(requestCode, resultCode, data);
        }

        /*@Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            checkPermission();
            setContentView(R.layout.activity_main);
            Button cameraBtn = (Button) findViewById(R.id.btn_take_camera);
            Button photoBtn = (Button) findViewById(R.id.btn_take_photo);
            pictureImg = (ImageView) findViewById(R.id.img_picture);
            assert cameraBtn != null;
            cameraBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    mCurrentPhotoName = System.currentTimeMillis() + ".jpg";
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), mCurrentPhotoName)));
                    startActivityForResult(intent, CODE_REQUEST_CAMERA_PHOTO);
                }
            });
            assert photoBtn != null;
            photoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentFromGallery = new Intent();
                    intentFromGallery.setType("image*//*");//选择图片
                    intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intentFromGallery, CODE_REQUEST_PICTURE);
                }
            });
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

    *//**
         * 判断是否有存储卡，有返回TRUE，否则FALSE
         * @return
         *//*
    public static boolean isSDcardExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_REQUEST_CAMERA_PHOTO){
            if(resultCode == Activity.RESULT_OK){
                if(data == null) {
                    return;
                }
                File tempFile = new File(
                        Environment.getExternalStorageDirectory(),
                        mCurrentPhotoName);
                mNewCurrentPhotoName = "pz_" + mCurrentPhotoName;
                String outPath = Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
                CropPhotoUtil.cropRawPhoto(this, Uri.fromFile(tempFile), CODE_REQUEST_CROP_PHOTO, outPath, 200, 200);
            }
        }else if(requestCode == CODE_REQUEST_CROP_PHOTO){
            if(resultCode == Activity.RESULT_OK){
                String path =  Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
                ImageUtils.getInstance(this).setHeadViewFile(pictureImg, path, ImageUtils.ImageType.IMAGE_TYPE_CRICLE);
            }
        }else if(requestCode == CODE_REQUEST_PICTURE && resultCode == Activity.RESULT_OK){
            if(data == null) return;
            mNewCurrentPhotoName = System.currentTimeMillis() + ".jpg";
            String outPath = Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
            CropPhotoUtil.cropRawPhoto(this, data.getData(), CODE_REQUEST_CROP_PHOTO, outPath, 200, 200);
        }
    }*/
    }
}
