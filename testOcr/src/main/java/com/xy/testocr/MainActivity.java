package com.xy.testocr;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Autor: Administrator
 * CreatedTime: 2020/3/28 0028
 * UpdateTime:2020/3/28 0028 10:21
 * Des:首页
 * UpdateContent:
 **/
public class MainActivity extends AppCompatActivity {

    private Button mAt;
    private Button mFg;
    String[] permissions = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OcrContentActivity.class);
                startActivity(intent);
            }
        });
        mFg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragentContainerActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 判断哪些权限未授予
         * 以便必要的时候重新申请
         */
        ActivityCompat.requestPermissions(this, permissions, 1002);
    }

    private void initViews() {
        mAt = findViewById(R.id.at);
        mFg = findViewById(R.id.fg);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
