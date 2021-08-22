package com.example.appdocsachv2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appdocsachv2.Adapter.AdapterChuyenMuc;
import com.example.appdocsachv2.Adapter.AdapterThongTin;
import com.example.appdocsachv2.Adapter.AdapterTruyen;
import com.example.appdocsachv2.Database.DatabaseDocTruyen;
import com.example.appdocsachv2.Model.ChuyenMuc;
import com.example.appdocsachv2.Model.TaiKhoan;
import com.example.appdocsachv2.Model.Truyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView,listViewNew,listViewThongTin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArrayList;

    ArrayList<ChuyenMuc> chuyenmucArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;

    AdapterTruyen adapterTruyen;

    AdapterChuyenMuc adapterChuyenMuc;

    AdapterThongTin adapterThongTin;

    DatabaseDocTruyen databaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDocTruyen = new DatabaseDocTruyen(this);

        // lấy dữ liệu ở màn đăng nhập gửi
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        AnhXa();

        ActionViewFlipper();
        
        ActionBar();

        //bắt sk click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,MainNoiDung.class);

                String tent = TruyenArrayList.get(position).getTenTruyen();
                String noidungt = TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });

        //bắt click item cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Đăng bài
                if(position == 0){
                    if (i == 2) {
                        Intent intent = new Intent(MainActivity.this,ManAdmin.class);
                        // gửi id tài khoản qua admin
                        intent.putExtra("Id",idd);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài : ", "Bạn không có quyền");
                    }
                }
                // nếu vị trí ấn vào là thông tinthì chuyển sang màn hình thông tin
                else if (position == 1){
                    Intent intent = new Intent(MainActivity.this,MainThongTin.class);
                    startActivity(intent);
                }
                // Đăng xuất
                else  if (position == 2){
                    finish();
                }
            }
        });
    }

    private void ActionBar() {
        // hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);

        // set nút cho toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        // tạo sự kiện bắt click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //Phương thức chạy quảng cáo
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add vào mảng 4 ảnh
        mangquangcao.add("https://truyencotich.vn/wp-content/uploads/2015/02/edop11-590x440.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/tho-va-rua-210428.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-210415.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/nang-bach-tuyet-va-bay-chu-lun-210383.jpg");


        // thiết lập vòng lặp for gán ảnh vào imageView, rồi từ imgview lên app
        for (int i=0; i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());

            // sử dụng hàm thư viện Picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            // Phương thức chỉnh tấm hình vừa khung quảng cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            // Thêm ảnh từ imageview vào ViewFlipper
            viewFlipper.addView(imageView);
        }
        // thiết lập  tự động chạy cho viewFlipper chạy trong 4 giây
        viewFlipper.setFlipInterval(4000);
        // run auto viewFlipper
        viewFlipper.setAutoStart(true);

        // gọi animation cho vào ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        // gọi Animation vào viewFlipper
        viewFlipper.setAnimation(animation_slide_in);
        viewFlipper.setAnimation(animation_slide_out);
    }

    //Phương thức ánh xạ
    private void AnhXa() {

        toolbar  = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewfliper);
        listViewNew = findViewById(R.id.listviewnew);
        listView = findViewById(R.id.listviewmanhinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);

        //phần của bài list truyện mới

        TruyenArrayList = new ArrayList<>();

        Cursor cursor1 = databaseDocTruyen.getData1();
        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0); //trước 0 là columnlndex:
            String tentruyen = cursor1.getString(1); //trước 1 là columnlndex:
            String noidung = cursor1.getString(2);//trước 2 là columnlndex:
            String anh = cursor1.getString(3);//trước 3 là columnlndex:
            int id_tk = cursor1.getInt(4);//trước 4 là columnlndex:

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new AdapterTruyen(getApplicationContext(),TruyenArrayList);

            listViewNew.setAdapter(adapterTruyen);
        }

        cursor1.moveToFirst();
        cursor1.close();

        // thông tin

        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

        adapterThongTin = new AdapterThongTin(this,R.layout.navigation_thongtin,taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterThongTin);

        // chuyên mục

        chuyenmucArrayList = new ArrayList<>();

        chuyenmucArrayList.add(new ChuyenMuc("Đăng bài",R.drawable.ic_dangbai));
        chuyenmucArrayList.add(new ChuyenMuc("Thông tin",R.drawable.ic_thongtin));
        chuyenmucArrayList.add(new ChuyenMuc("Đăng xuất",R.drawable.ic_dangxuat));

        adapterChuyenMuc = new AdapterChuyenMuc(this,R.layout.chuyenmuc,chuyenmucArrayList);

        listView.setAdapter(adapterChuyenMuc);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu1:
                Intent myintent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(myintent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}