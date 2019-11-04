package com.creagine.lbbadmin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Model.Jadwal;
import com.creagine.lbbadmin.Model.Presensi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class JadwalDetailActivity extends AppCompatActivity {

    private TextView txtNama, txtTutor, txtJurusan, txtGrade, txtHarga, txtTanggal, txtJam, txtHari,
            txtRuangan, txtStatusPekan1, txtStatusPekan2, txtStatusPekan3, txtStatusPekan4, txtStatusPekanPengganti;
    private CardView cardPekan1, cardPekan2, cardPekan3, cardPekan4, cardPekanPengganti;
    private Spinner spinnerBulan;

    private DatabaseReference jadwalRef, presensiRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_detail);

        widgetInit();

        presensiRef = FirebaseDatabase.getInstance().getReference("Presensi");
        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");

        getDataSiswa();

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i > 0){
                    // get spinner value

                    Common.bulanSelected = spinnerBulan.getSelectedItem().toString();

                    //set default
                    txtStatusPekan1.setText("Belum Terlaksana");
                    txtStatusPekan2.setText("Belum Terlaksana");
                    txtStatusPekan3.setText("Belum Terlaksana");
                    txtStatusPekan4.setText("Belum Terlaksana");
                    txtStatusPekanPengganti.setText("Tidak Dilaksanakan");
                    txtStatusPekan1.setTextColor(Color.RED);
                    txtStatusPekan2.setTextColor(Color.RED);
                    txtStatusPekan3.setTextColor(Color.RED);
                    txtStatusPekan4.setTextColor(Color.RED);
                    txtStatusPekanPengganti.setTextColor(Color.RED);
                    Common.statusPresensiPekan1 = "";
                    Common.statusPresensiPekan2 = "";
                    Common.statusPresensiPekan3 = "";
                    Common.statusPresensiPekan4 = "";
                    Common.statusPresensiPekanPengganti = "";

                    //get presensi siswa per-pekan
                    getPresensiSiswaPekan1();
                    getPresensiSiswaPekan2();
                    getPresensiSiswaPekan3();
                    getPresensiSiswaPekan4();
                    getPresensiSiswaPekanPenggantian();


                }else{
                    // show toast select gender

                    Toast.makeText(JadwalDetailActivity.this, "Pilih bulan", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cardPekan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "1";

                Intent intent = new Intent(JadwalDetailActivity.this, PresensiDetailActivity.class);
                startActivity(intent);

            }
        });

        cardPekan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "2";

                Intent intent = new Intent(JadwalDetailActivity.this, PresensiDetailActivity.class);
                startActivity(intent);

            }
        });

        cardPekan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "3";

                Intent intent = new Intent(JadwalDetailActivity.this, PresensiDetailActivity.class);
                startActivity(intent);

            }
        });

        cardPekan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "4";

                Intent intent = new Intent(JadwalDetailActivity.this, PresensiDetailActivity.class);
                startActivity(intent);

            }
        });

        cardPekanPengganti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "penggantian";

                Intent intent = new Intent(JadwalDetailActivity.this, PresensiDetailActivity.class);
                startActivity(intent);

            }
        });

    }

    private void widgetInit(){

        txtNama = findViewById(R.id.textViewNamaSiswaDetail);
        txtTutor = findViewById(R.id.textViewNamaTutorDetail);
        txtJurusan = findViewById(R.id.textViewJurusanDetail);
        txtGrade = findViewById(R.id.textViewGradeDetail);
        txtHarga = findViewById(R.id.textViewHargaDetail);
        txtTanggal = findViewById(R.id.textViewTanggalDetail);
        txtJam = findViewById(R.id.textViewJamDetail);
        txtHari = findViewById(R.id.textViewHariDetail);
        txtRuangan = findViewById(R.id.textViewRuanganDetail);
        cardPekan1 = findViewById(R.id.CardViewPekan1);
        cardPekan2 = findViewById(R.id.CardViewPekan2);
        cardPekan3 = findViewById(R.id.CardViewPekan3);
        cardPekan4 = findViewById(R.id.CardViewPekan4);
        cardPekanPengganti = findViewById(R.id.CardViewPekanPengganti);
        txtStatusPekan1 = findViewById(R.id.textViewStatusPekan1);
        txtStatusPekan2 = findViewById(R.id.textViewStatusPekan2);
        txtStatusPekan3 = findViewById(R.id.textViewStatusPekan3);
        txtStatusPekan4 = findViewById(R.id.textViewStatusPekan4);
        txtStatusPekanPengganti = findViewById(R.id.textViewStatusPekanPengganti);
        spinnerBulan = findViewById(R.id.spinnerBulan);

    }

    private void getDataSiswa(){
        jadwalRef.child(Common.jadwalSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Jadwal jadwal = dataSnapshot.getValue(Jadwal.class);

                txtNama.setText(jadwal.getNamaSiswa());
                txtTutor.setText(jadwal.getTutor());
                txtJurusan.setText(jadwal.getJurusan());
                txtGrade.setText(jadwal.getGrade());
                txtHarga.setText(jadwal.getHarga());
                txtTanggal.setText(jadwal.getTanggal());
                txtJam.setText(jadwal.getJam());
                txtHari.setText(jadwal.getHari());
                txtRuangan.setText(jadwal.getRuang());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getPresensiSiswaPekan1(){

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.jadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanSelected)&&
                            data.getValue(Presensi.class).getPekan().equals("1")) {
                        txtStatusPekan1.setText(data.getValue(Presensi.class).getStatus());
                        txtStatusPekan1.setTextColor(Color.BLACK);
                        Common.statusPresensiPekan1 = "true";

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void getPresensiSiswaPekan2(){

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.jadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanSelected)&&
                            data.getValue(Presensi.class).getPekan().equals("2")) {
                        txtStatusPekan2.setText(data.getValue(Presensi.class).getStatus());
                        txtStatusPekan2.setTextColor(Color.BLACK);
                        Common.statusPresensiPekan2= "true";
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void getPresensiSiswaPekan3(){

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.jadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanSelected)&&
                            data.getValue(Presensi.class).getPekan().equals("3")) {
                        txtStatusPekan3.setText(data.getValue(Presensi.class).getStatus());
                        txtStatusPekan3.setTextColor(Color.BLACK);
                        Common.statusPresensiPekan3 = "true";
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void getPresensiSiswaPekan4(){

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.jadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanSelected)&&
                            data.getValue(Presensi.class).getPekan().equals("4")) {
                        txtStatusPekan4.setText(data.getValue(Presensi.class).getStatus());
                        txtStatusPekan4.setTextColor(Color.BLACK);
                        Common.statusPresensiPekan4 = "true";
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void getPresensiSiswaPekanPenggantian(){

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.jadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanSelected)&&
                            data.getValue(Presensi.class).getPekan().equals("penggantian")) {
                        txtStatusPekanPengganti.setText(data.getValue(Presensi.class).getStatus());
                        txtStatusPekanPengganti.setTextColor(Color.BLACK);
                        Common.statusPresensiPekanPengganti = "true";
                    }

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
