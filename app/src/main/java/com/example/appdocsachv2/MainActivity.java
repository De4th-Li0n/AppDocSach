package com.example.appdocsachv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.NavigableSet;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView,listViewNew,listViewThongtin;
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        ActionBar();
        ActionViewFlipper();
    }
    private void ActionBar(){
        // hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);

        // set nut cho toolbar
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

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://i.ytimg.com/vi/IGiCAOkGXIo/maxresdefault.jpg");
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2020/03/cau-be-chan-cuu-noi-doi.png");
        mangquangcao.add("https://tskids.vn/image/5e3858961d41c817059dd6d8/thumbnail.jpg");

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

    private void AnhXa() {
        toolbar  = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewfliper);
        listViewNew = findViewById(R.id.listviewnew);
        listView = findViewById(R.id.listviewmanhinh);
        listViewThongtin = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);

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
                Intent intent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}