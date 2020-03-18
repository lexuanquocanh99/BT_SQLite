package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuanLySVActivity extends AppCompatActivity {

    private static QuanLySVActivity instance;
    EditText edt_hovaten, edt_ngaysinh, edt_truonghoc;
    RadioButton rd_nam, rd_nu;
    CheckBox cb_thethao, cb_dulich, cb_docsach;
    Button btn_nhaplieu, btn_nhaplai, btn_danhsachSV;

    public DB_SinhVien db_sinhvien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quan_ly_sv);
        instance = this;

        ID();
        db_sinhvien = new DB_SinhVien(this,"SINHVIEN.sqlite",null,1);
        db_sinhvien.QueryData("CREATE TABLE IF NOT EXISTS SINHVIEN(ID INTEGER PRIMARY KEY AUTOINCREMENT, TEN text, NGAYSINH text, TRUONG text, GIOITINH integer, SOTHICH text)");


        btn_nhaplai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_hovaten.setText("");
                edt_ngaysinh.setText("");
                edt_truonghoc.setText("");
                rd_nam.setChecked(true);
                cb_docsach.setChecked(false);
                cb_dulich.setChecked(false);
                cb_thethao.setChecked(false);
            }
        });

        btn_nhaplieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gioitinh = 0;
                String hoten = edt_hovaten.getText().toString();
                String ngaysinh = edt_ngaysinh.getText().toString();
                String truong = edt_truonghoc.getText().toString();
                String sothich = "";
                if (cb_thethao.isChecked()){
                    sothich += cb_thethao.getText().toString() + ", ";
                }
                if (cb_docsach.isChecked()){
                    sothich += cb_docsach.getText().toString() + ", ";
                }
                if (cb_dulich.isChecked()){
                    sothich += cb_dulich.getText().toString() + ", ";
                }

                if (rd_nam.isChecked()){
                    gioitinh = 1;
                }


                if (hoten.equals("")|| ngaysinh.equals("") || truong.equals("") || sothich.equals("")) {
                    Toast.makeText(QuanLySVActivity.this, "Vui Lòng nhập đủ thông tin để thêm dữ liệu!", Toast.LENGTH_SHORT).show();
                }
                else {
                    db_sinhvien.AddSV(hoten,ngaysinh,truong,gioitinh,sothich);
                    Toast.makeText(QuanLySVActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_danhsachSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataToList();

            }
        });
    }

    private void ID(){
        edt_hovaten       = findViewById(R.id.edt_hovaten);
        edt_ngaysinh    = findViewById(R.id.edt_ngaysinh);
        edt_truonghoc   = findViewById(R.id.edt_truonghoc);
        rd_nam      = findViewById(R.id.rd_nam);
        rd_nu       = findViewById(R.id.rd_nu);
        cb_thethao     = findViewById(R.id.cb_thethao);
        cb_dulich       = findViewById(R.id.cb_dulich);
        cb_docsach    = findViewById(R.id.cb_docsach);
        btn_nhaplai     = findViewById(R.id.btn_nhaplieu);
        btn_nhaplai      = findViewById(R.id.btn_nhaplai);
        btn_danhsachSV      = findViewById(R.id.btn_danhsachSV);
    }

    public static QuanLySVActivity getInstance() {
        return instance;
    }

    public void update(String hoten_new,String namsinh_new, String truong_new, int gioitinh_new, String sothich_new, int ID){
        db_sinhvien.QueryData("UPDATE SINHVIEN SET TEN = '"+hoten_new+"' WHERE ID = '"+ID+"'");
        db_sinhvien.QueryData("UPDATE SINHVIEN SET NGAYSINH = '"+namsinh_new+"' WHERE ID = '"+ID+"'");
        db_sinhvien.QueryData("UPDATE SINHVIEN SET TRUONG = '"+truong_new+"' WHERE ID = '"+ID+"'");
        db_sinhvien.QueryData("UPDATE SINHVIEN SET GIOITINH = '"+gioitinh_new+"' WHERE ID = '"+ID+"'");
        db_sinhvien.QueryData("UPDATE SINHVIEN SET SOTHICH = '"+sothich_new+"' WHERE ID = '"+ID+"'");

    }

    public void delete(int ID){
        db_sinhvien.QueryData("DELETE FROM SINHVIEN WHERE ID = '"+ID+"'");
    }

    public void GetDataToList(){
        Intent listSV = new Intent(QuanLySVActivity.this, ListSinhVien.class);
        ArrayList<Parcelable> listSVData = new ArrayList<>();

        Cursor dataSinhVien = db_sinhvien.GetData("SELECT * FROM SINHVIEN");
        while (dataSinhVien.moveToNext()) {
            int id = dataSinhVien.getInt(0);
            String hovaten = dataSinhVien.getString(1);
            String ngaysinh = dataSinhVien.getString(2);
            String truong = dataSinhVien.getString(3);
            int gioitinh = dataSinhVien.getInt(4);
            String sothich = dataSinhVien.getString(5);

            listSVData.add(new Model_SinhVien(hovaten, ngaysinh, truong, sothich, gioitinh, id));
        }
        listSV.putParcelableArrayListExtra("listSVData", listSVData);
        startActivity(listSV);
    }
}
