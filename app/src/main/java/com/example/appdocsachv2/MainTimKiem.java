package com.example.appdocsachv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appdocsachv2.Adapter.AdapterTruyen;
import com.example.appdocsachv2.Database.DatabaseDocTruyen;
import com.example.appdocsachv2.Model.Truyen;

import java.util.ArrayList;

public class MainTimKiem extends AppCompatActivity {

    ListView listView;

    EditText edt;

    ArrayList<Truyen> TruyenArrayList;
    //ArrayList<Truyen> arrayList;

    AdapterTruyen adapterTruyen;

    DatabaseDocTruyen DatabaseDocTruyen;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);

        listView = findViewById(R.id.listviewTimKiem);
        edt = findViewById(R.id.timkiem);

        initList();

        // bật click cho item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainTimKiem.this,MainNoiDung.class);
                String tent = TruyenArrayList.get(position).getTenTruyen();
                String noidungt = TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });
    }

    private void initList() {
        TruyenArrayList = new ArrayList<>();

        //arrayList = new ArrayList<>();

        DatabaseDocTruyen = new DatabaseDocTruyen(this);

        Cursor cursor = DatabaseDocTruyen.getData2();

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new AdapterTruyen(getApplicationContext(),TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}