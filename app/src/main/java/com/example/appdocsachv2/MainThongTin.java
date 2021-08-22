package com.example.appdocsachv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {

    TextView txtThongtinapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);

        txtThongtinapp = findViewById(R.id.textviewthongtin);

        String thongtin = "Ứng dụng được phát hành bởi 'nhóm 10'\n";

        txtThongtinapp.setText(thongtin);
    }
}