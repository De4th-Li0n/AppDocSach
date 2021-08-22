package com.example.appdocsachv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appdocsachv2.Adapter.AdapterTruyen;
import com.example.appdocsachv2.Database.DatabaseDocTruyen;
import com.example.appdocsachv2.Model.Truyen;

import java.util.ArrayList;

public class MainTimKiem extends AppCompatActivity {

    private RecyclerView rcvUser;

    ListView listView;

    EditText edt;

    ArrayList<Truyen> TruyenArrayList;

    ArrayList<Truyen> arrayList;

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
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainTimKiem.this,MainNoiDung.class);
                String tent = arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });

        // editText search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }
    // Search
    private void filter(String text){

        // Xóa dữ liệu mảng
        arrayList.clear();

        ArrayList<Truyen> filteredList = new ArrayList<>();

        for (Truyen item : TruyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){

                // thêm item vào filteredList
                filteredList.add(item);

                // thêm vào mảng
                arrayList.add(item);
            }
        }
        adapterTruyen.filterList(filteredList);

    }


    private void initList() {
        TruyenArrayList = new ArrayList<>();

        arrayList = new ArrayList<>();

        DatabaseDocTruyen = new DatabaseDocTruyen(this);

        Cursor cursor = DatabaseDocTruyen.getData2();

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            arrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new AdapterTruyen(getApplicationContext(),TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}