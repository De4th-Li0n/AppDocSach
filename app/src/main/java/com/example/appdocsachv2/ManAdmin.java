package com.example.appdocsachv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdocsachv2.Adapter.AdapterTruyen;
import com.example.appdocsachv2.Database.DatabaseDocTruyen;
import com.example.appdocsachv2.Model.Truyen;

import java.util.ArrayList;

public class ManAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    AdapterTruyen adapterTruyen;

    DatabaseDocTruyen DatabaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonThemTruyen);

        initList();

        buttonThem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // Lấy id tài khoản để biết tài khoản admin đã chỉnh sửa
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id", 0);


                // tiếp tục gửi id qua màn hình thêm truyện
                Intent intent = new Intent(ManAdmin.this,MainDangBai.class);
                intent.putExtra("Id", id);
                startActivity(intent);

            }
        });

        // click item Long sẽ xóa item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                int idtruyen = TruyenArrayList.get(position).getID();

                // Xóa dữ liệu
                DatabaseDocTruyen.Delete(idtruyen);

                // Cập nhật  lại Activity
                Intent intent =new Intent(ManAdmin.this,ManAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(ManAdmin.this, "Xóa Truyện Thành Công",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    // gán dữ liệu cho listview
    private void initList() {
        TruyenArrayList = new ArrayList<>();

        DatabaseDocTruyen = new DatabaseDocTruyen(this);

        Cursor cursor1 = DatabaseDocTruyen.getData2();

        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new AdapterTruyen(getApplicationContext(),TruyenArrayList);

            listView.setAdapter(adapterTruyen);

        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}