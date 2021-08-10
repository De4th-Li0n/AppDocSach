package com.example.appdocsach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdocsach.Database.Databasedocsach;

public class MainDangNhap extends AppCompatActivity {

    //Tạo biến cho màn hình đăng nhập
    EditText editTaiKhoan, editMatKhau;
    Button btnDangNhap, btnDangKy;

    //tạo đối tượng cho database sách
    Databasedocsach Databasedocsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        Anhxa();

        //Đối tượng databasesach
        Databasedocsach = new Databasedocsach(this);

        //Tạo sự kiện click button chuyển sang màn hình đăng ký với intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this, MainDangKy.class);
                startActivity(intent);
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gán cho các biến giá trị nhập vào từ edit Text
                String tenTaiKhoan = editTaiKhoan.getText().toString();
                String MatKhau =  editMatKhau.getText().toString();

                //Sử dụng con trỏ lấy dữ liệu, gọi tới getData để lấy tất cả tài khoản trong database
                Cursor cursor = Databasedocsach.getData();

                //thực hiện vòng lặp để lấy dữ liệu từ cursor với movenext di chuyển tiếp
                while (cursor.moveToNext()) {
                    //Lấy dữ liệu và gán vào biến, dữ liệu tài khoản ô 1 và mật khẩu ô 2, ô 0 là idtaikhoan,  ô 3 là email ô 4 là phanquyen
                   String datatentaikhoan = cursor.getString(1);
                   String datamatkhau = cursor.getString(2);

                    //Nếu tài khoản và mật khẩu khớp với database
                    if(datatentaikhoan.equals(tenTaiKhoan) && datamatkhau.equals(MatKhau)){
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        //Chuyển qua màn hình MainActivity
                        Intent intent = new Intent(MainDangNhap.this , MainActivity.class);

                        //Gửi dữ liệu qua activity
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                    }

                    //thực hiện trả còn trỏ về đầu
                    cursor.moveToFirst();
                    //đóng khi không dùng
                    cursor.close();
                }
            }
        });
    }

    private void Anhxa(){
        editMatKhau = findViewById(R.id.matkhau);
        editTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
}