package com.example.appdocsachv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdocsachv2.Database.DatabaseDocTruyen;

public class MainDangNhap extends AppCompatActivity {
    //tạo biến cho màn hình đăng nhập
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnDangKy;

    DatabaseDocTruyen databaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        AnhXa();

        databaseDocTruyen = new DatabaseDocTruyen(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this,MainDangKy.class);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gán các biến giá trị nhập từ editText
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                //Sử dụng con trỏ lấy dữ liệu, ở các ô tương ứng/ 1 tk 2 mk 3 email 4 phanquyen
                Cursor cursor = databaseDocTruyen.getData();

                while(cursor.moveToNext()){

                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);


                    if(datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau))
                    {
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);

                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Intent intent = new Intent(MainDangNhap.this,MainActivity.class);

                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentaikhoan);

                        startActivity(intent);
                    }
                }
                cursor.moveToFirst();
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtTaiKhoan = findViewById(R.id.taikhoan);
        edtMatKhau = findViewById(R.id.matkhau);
        btnDangNhap = findViewById(R.id.dangnhap);
        btnDangKy = findViewById(R.id.dangky);
    }
}