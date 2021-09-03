package com.example.appdocsachv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdocsachv2.Model.ChuyenMuc;
import com.example.appdocsachv2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterChuyenMuc extends BaseAdapter {

    private Context context;
    private  int layout;
    private List<ChuyenMuc> chuyenMucList;

    public AdapterChuyenMuc(Context context, int layout, List<ChuyenMuc> chuyenMucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenMucList = chuyenMucList;
    }

    @Override
    public int getCount() {
        return chuyenMucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout,null);

        ImageView img = (ImageView) convertView.findViewById(R.id.imgchuyenmuc);

        TextView txt = (TextView)  convertView.findViewById(R.id.textviewTenchuyenmuc);

        ChuyenMuc cm = chuyenMucList.get(position);

        txt.setText(cm.getTenchuyenmuc());

        Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);

        return convertView;
    }
}
