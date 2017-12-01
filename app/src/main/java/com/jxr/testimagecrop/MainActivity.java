package com.jxr.testimagecrop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button mButton;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatar = (ImageView) findViewById(R.id.avatar);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startToImagePicker();
            }
        });
    }


    /**
     * 进入选取头像界面
     */
    private void startToImagePicker() {

        Log.i("aaa", "startToImagePicker");

        Bundle bundle = new Bundle();
        bundle.putInt("outputX", 80);
        bundle.putInt("outputY", 160);
        bundle.putInt("aspectX", 1);
        bundle.putInt("aspectY", 2);
        Intent intent = new Intent("android.intent.action.ImageCrop");
        intent.putExtra("bundle", bundle);

        startActivityForResult(intent, 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Log.i(TAG, "头像选取成功");
                Bitmap photo = extras.getParcelable("data");
                avatar.setImageBitmap(photo);
            }
        } else {
            Log.i(TAG, "头像选取失败");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
