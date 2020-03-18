package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class Adapter_SinhVien extends ArrayAdapter<Model_SinhVien> {
    private ListSinhVien context;
    private int resource;
    private ArrayList<Model_SinhVien> sinhVienArrayList;
    String gioitinh;

    public Adapter_SinhVien (@NonNull ListSinhVien context, int resource, @NonNull ArrayList<Model_SinhVien> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.sinhVienArrayList = objects;
    }

    @NonNull
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.layout_sv, parent,false);

        TextView tv_hovaten, tv_ngaysinh, tv_truonghoc, tv_gioitinh, tv_sothich;
        ImageButton delete, edit;

        delete               = convertView.findViewById(R.id.delete);
        edit               = convertView.findViewById(R.id.edit);
        tv_hovaten     = convertView.findViewById(R.id.tv_hovaten);
        tv_ngaysinh    = convertView.findViewById(R.id.tv_ngaysinh);
        tv_truonghoc      = convertView.findViewById(R.id.tv_truonghoc);
        tv_gioitinh    = convertView.findViewById(R.id.tv_gioitinh);
        tv_sothich     = convertView.findViewById(R.id.tv_sothich);

        final Model_SinhVien sinhVien = sinhVienArrayList.get(position);

        if(sinhVien.getGioitinh()==0){
            gioitinh="Nam";
        }
        else gioitinh = "Nữ";

        tv_hovaten.setText(sinhVien.getHoten());
        tv_ngaysinh.setText(sinhVien.getNgaysinh());
        tv_truonghoc.setText(sinhVien.getTruong());
        tv_gioitinh.setText(gioitinh);
        tv_sothich.setText(sinhVien.getSothich());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogEdit(sinhVien.getId(),sinhVien.getHoten(),sinhVien.getNgaysinh(),sinhVien.getTruong(),sinhVien.getGioitinh(),sinhVien.getSothich());
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(sinhVien.getId(),sinhVien.getHoten());
            }
        });

        return convertView;
    }
}
