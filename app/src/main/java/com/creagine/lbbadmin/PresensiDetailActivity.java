package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Model.Presensi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PresensiDetailActivity extends AppCompatActivity {

    private TextView txtBulan, txtPekan, txtTanggal, txtMateri, txtKeterangan, txtKehadiranTutor,
            tztKehadiranSiswa, txtStatus;

    private Button btnValidasiPresensi;

    private DatabaseReference presensiRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi_detail);

        presensiRef = FirebaseDatabase.getInstance().getReference("Presensi");

        widgetInit();

        if(Common.pekanSelected.equals("1")){
            getPresensiPekan1();
        }
        if(Common.pekanSelected.equals("2")){
            getPresensiPekan2();
        }
        if(Common.pekanSelected.equals("3")){
            getPresensiPekan3();
        }
        if(Common.pekanSelected.equals("4")){
            getPresensiPekan4();
        }
        if(Common.pekanSelected.equals("5")){
            getPresensiPekan5();
        }
        if(Common.pekanSelected.equals("6")){
            getPresensiPekan6();
        }
        if(Common.pekanSelected.equals("7")){
            getPresensiPekan7();
        }
        if(Common.pekanSelected.equals("8")){
            getPresensiPekan8();
        }
        if(Common.pekanSelected.equals("penggantian")){
            getPresensiPekanPenggantian();
        }
        if(Common.pekanSelected.equals("penggantian2")){
            getPresensiPekanPenggantian2();
        }



        btnValidasiPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presensiRef.child(Common.presensiSelected).child("status").setValue("Terlaksana");

                Intent intent = new Intent(PresensiDetailActivity.this, JadwalDetail4PertemuanActivity.class);
                startActivity(intent);

            }
        });

    }

    private void widgetInit(){

        txtBulan = findViewById(R.id.textViewBulan);
        txtPekan = findViewById(R.id.textViewPekan);
        txtTanggal = findViewById(R.id.textViewTanggal);
        txtMateri = findViewById(R.id.textViewMateri);
        txtKeterangan = findViewById(R.id.textViewKeterangan);
        txtKehadiranTutor = findViewById(R.id.textViewKehadiranTutor);
        tztKehadiranSiswa = findViewById(R.id.textViewKehadiranSiswa);
        txtStatus = findViewById(R.id.textViewStatus);
        btnValidasiPresensi = findViewById(R.id.buttonValidasiPresensi);

    }

    private void getPresensiPekan1(){
        if(Common.statusPresensiPekan1.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan",
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void getPresensiPekan2(){
        if(Common.statusPresensiPekan2.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getPresensiPekan3(){
        if(Common.statusPresensiPekan3.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan",
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void getPresensiPekan4(){
        if(Common.statusPresensiPekan4.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan",
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void getPresensiPekan5(){
        if(Common.statusPresensiPekan5.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan, silahkan mengisi data presensi",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getPresensiPekan6(){
        if(Common.statusPresensiPekan6.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan, silahkan mengisi data presensi",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getPresensiPekan7(){
        if(Common.statusPresensiPekan7.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan, silahkan mengisi data presensi",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getPresensiPekan8(){
        if(Common.statusPresensiPekan8.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan, silahkan mengisi data presensi",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getPresensiPekanPenggantian(){
        if(Common.statusPresensiPekanPengganti.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan",
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void getPresensiPekanPenggantian2(){
        if(Common.statusPresensiPekanPengganti2.equals("true")){
            getPresensiSiswa(Common.jadwalSelected);
        }
        else {
            Toast.makeText(PresensiDetailActivity.this,
                    "Data belum ditemukan, silahkan mengisi data presensi",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getPresensiSiswa(String idJadwal){

        Query query = presensiRef.orderByChild("idJadwal").equalTo(idJadwal);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {

                        if (data.getValue(Presensi.class).getBulan().equals(Common.bulanSelected) &&
                                data.getValue(Presensi.class).getPekan().equals(Common.pekanSelected)) {
                            txtBulan.setText(data.getValue(Presensi.class).getBulan());
                            txtPekan.setText(data.getValue(Presensi.class).getPekan());
                            txtTanggal.setText(data.getValue(Presensi.class).getTanggalPresensi());
                            txtMateri.setText(data.getValue(Presensi.class).getMateri());
                            txtKeterangan.setText(data.getValue(Presensi.class).getKeterangan());
                            txtKehadiranTutor.setText(data.getValue(Presensi.class).getPresensiTutor());
                            tztKehadiranSiswa.setText(data.getValue(Presensi.class).getPresensiSiswa());
                            txtStatus.setText(data.getValue(Presensi.class).getStatus());

                            Common.presensiSelected = data.getKey();

                            btnValidasiPresensi.setVisibility(View.VISIBLE);
                        }
                    }

                } else {

                    Toast.makeText(PresensiDetailActivity.this,
                            "Data belum ditemukan",
                            Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
