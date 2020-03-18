package com.example.sqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;

public class ListSinhVien extends AppCompatActivity{


    private ListView lv_SinhVien;
    final ArrayList<Model_SinhVien> arrayList = new ArrayList<>();
    ArrayList<Parcelable> listSV = new ArrayList<>();

    EditText edt_hovaten, edt_ngaysinh, edt_truonghoc;
    RadioButton rd_nam, rd_nu;
    CheckBox cb_thethao, cb_dulich, cb_docsach;
    Button btn_ok, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listSV = getIntent().getParcelableArrayListExtra("listSVData");
        setContentView(R.layout.layout_list_sv);

        lv_SinhVien = findViewById(R.id.lv_SinhVien);

        Adapter_SinhVien adapter_sinhVien = new Adapter_SinhVien(this, R.layout.layout_sv, arrayList);
        for (int i = 0; i < listSV.size(); i++) {
            arrayList.add((Model_SinhVien) listSV.get(i));
        }
        lv_SinhVien.setAdapter(adapter_sinhVien);

    }

    public void DialogEdit(final int ID, String hovaten, String ngaysinh, String truonghoc, final int gioitinh, String sothich){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_data);

        edt_hovaten       = dialog.findViewById(R.id.dialog_hovaten);
        edt_ngaysinh    = dialog.findViewById(R.id.dialog_ngaysinh);
        edt_truonghoc   = dialog.findViewById(R.id.dialog_truonghoc);
        rd_nam      = dialog.findViewById(R.id.dialog_nam);
        rd_nu       = dialog.findViewById(R.id.dialog_nu);
        cb_thethao     = dialog.findViewById(R.id.dialog_thethao);
        cb_dulich       = dialog.findViewById(R.id.dialog_dulich);
        cb_docsach     = dialog.findViewById(R.id.dialog_docsach);
        btn_ok       = dialog.findViewById(R.id.btn_ok);
        btn_cancel   = dialog.findViewById(R.id.btn_cancel);

        edt_hovaten.setText(hovaten);
        edt_ngaysinh.setText(ngaysinh);
        edt_truonghoc.setText(truonghoc);
        if (gioitinh==1){
            rd_nu.setChecked(true);
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoten_new = edt_hovaten.getText().toString().trim();
                String ngaysinh_new = edt_ngaysinh.getText().toString().trim();
                String truonghoc_new = edt_truonghoc.getText().toString().trim();
                int gioitinh_new = 0;
                if (rd_nu.isChecked()){
                    gioitinh_new = 1;
                }
                String sothich_new = "";
                if (cb_thethao.isChecked()){
                    sothich_new += cb_thethao.getText().toString() + ", ";
                }
                if (cb_docsach.isChecked()){
                    sothich_new += cb_docsach.getText().toString() + ", ";
                }
                if (cb_dulich.isChecked()){
                    sothich_new += cb_dulich.getText().toString() + ", ";
                }if (hoten_new.equals("")|| ngaysinh_new.equals("") || truonghoc_new.equals("") || sothich_new.equals("")) {
                    Toast.makeText(ListSinhVien.this, "Vui Lòng nhập đủ thông tin để thêm dữ liệu!", Toast.LENGTH_SHORT).show();
                }
                else {
                    QuanLySVActivity.getInstance().update(hoten_new, ngaysinh_new, truonghoc_new, gioitinh_new, sothich_new, ID);
                    Toast.makeText(ListSinhVien.this, "Đã sửa!", Toast.LENGTH_SHORT).show();
                }

                QuanLySVActivity.getInstance().GetDataToList();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public  void DialogDelete(final int ID, String hovaten){
        final AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Xóa "+ hovaten +"?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                QuanLySVActivity.getInstance().delete(ID);
                finish();
                QuanLySVActivity.getInstance().GetDataToList();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}