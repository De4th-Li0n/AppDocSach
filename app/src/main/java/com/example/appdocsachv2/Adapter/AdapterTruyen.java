package com.example.appdocsachv2.Adapter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdocsachv2.Model.Truyen;
import com.example.appdocsachv2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public AdapterTruyen(Context context,ArrayList<Truyen> listTruyen){

        this.context = context;
        this.listTruyen = listTruyen;

    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // filter
    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen = filteredList;
        notifyDataSetChanged();
    }

    public class  ViewHoIder{
        TextView txtTenTruyen;
        ImageView imgtruyen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHoIder viewHoIder = null;
        viewHoIder = new ViewHoIder();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen,null);

        viewHoIder.txtTenTruyen = convertView.findViewById(R.id.textviewTentruyenNew);
        viewHoIder.imgtruyen = convertView.findViewById(R.id.imgNewTruyen);
        convertView.setTag(viewHoIder);

        Truyen truyen = (Truyen) getItem(position);
        viewHoIder.txtTenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHoIder.imgtruyen);

        return convertView;
    }
}
