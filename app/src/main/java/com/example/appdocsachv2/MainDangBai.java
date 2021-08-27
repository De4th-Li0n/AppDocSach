package com.example.appdocsachv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdocsachv2.Database.DatabaseDocTruyen;
import com.example.appdocsachv2.Model.Truyen;

public class MainDangBai extends AppCompatActivity {

    EditText edtTenTruyen,edtNoiDung,edtAnh;
    Button btnDangBai;
    DatabaseDocTruyen databaseDocTruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);

        edtAnh = findViewById(R.id.dbimg);
        edtTenTruyen = findViewById(R.id.dbTenTruyen);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);

        databaseDocTruyen = new DatabaseDocTruyen(this);

        //buton đăng bài
        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();


                Truyen truyen = CreateTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(MainDangBai.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERR", "Nhập đầy đủ thông tin");
                }
                //Nhập đủ thông tin thực hiện
                else{
                    databaseDocTruyen.AddTruyen(truyen);

                    //Chuyển qua admin và cập nhật dữ liệu
                    Intent intent = new Intent(MainDangBai.this,ManAdmin.class);
                    finish();
                    startActivity(intent);
                }

            }
        });
    }
    //Phương thức tạo truyện
    private Truyen CreateTruyen(){

        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id",0);

        Truyen truyen = new Truyen(tentruyen,noidung,img,id);
        return truyen;
    }
}