package com.creagine.lbbadmin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
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

public class CekPresensiActivity extends AppCompatActivity {

    private TextView txtNama, txtJurusan, txtGrade, txtHari, txtJam, txtBulan,
            txtStatusPekan1, txtStatusPekan2, txtStatusPekan3, txtStatusPekan4, txtStatusPekanPengganti;
    private CardView cardPekan1, cardPekan2, cardPekan3, cardPekan4, cardPekanPengganti;
    private ProgressBar progressBar;
    private DatabaseReference jadwalRef, presensiRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_presensi);
        getSupportActionBar().setTitle("Cek presensi");

        widgetInit();

        presensiRef = FirebaseDatabase.getInstance().getReference("Presensi");
        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");

        getDataTutor();

        //set bulan presensi diambil dari spinner bulan pada halaman add fee tutor

        txtBulan.setText(Common.bulanFeeSelected);

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

    }

    private void widgetInit(){

        cardPekan1 = findViewById(R.id.CardViewPekan1);
        cardPekan2 = findViewById(R.id.CardViewPekan2);
        cardPekan3 = findViewById(R.id.CardViewPekan3);
        cardPekan4 = findViewById(R.id.CardViewPekan4);
        cardPekanPengganti = findViewById(R.id.CardViewPekanPengganti);
        txtNama = findViewById(R.id.textViewNamaSiswaJadwal);
        txtJurusan = findViewById(R.id.textViewJurusan);
        txtGrade = findViewById(R.id.textViewGradeSiswaJadwal);
        txtHari = findViewById(R.id.textViewHari);
        txtJam = findViewById(R.id.textViewJam);
        txtStatusPekan1 = findViewById(R.id.textViewStatusPekan1);
        txtStatusPekan2 = findViewById(R.id.textViewStatusPekan2);
        txtStatusPekan3 = findViewById(R.id.textViewStatusPekan3);
        txtStatusPekan4 = findViewById(R.id.textViewStatusPekan4);
        txtStatusPekanPengganti = findViewById(R.id.textViewStatusPekanPengganti);
        txtBulan = findViewById(R.id.textViewBulan);
        progressBar = findViewById(R.id.progressBarPresensi);

    }

    private void getDataTutor() {

        progressBar.setVisibility(View.VISIBLE);

        jadwalRef.child(Common.keyFeeJadwalSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Jadwal jadwalSelected = dataSnapshot.getValue(Jadwal.class);

                txtNama.setText(jadwalSelected.getNamaSiswa());
                txtJurusan.setText(jadwalSelected.getJurusan());
                txtGrade.setText(jadwalSelected.getGrade());
                txtHari.setText(jadwalSelected.getHari());
                txtJam.setText(jadwalSelected.getJam());

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getPresensiSiswaPekan1(){

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.keyFeeJadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanFeeSelected)&&
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

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.keyFeeJadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanFeeSelected)&&
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

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.keyFeeJadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanFeeSelected)&&
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

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.keyFeeJadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanFeeSelected)&&
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

        Query query = presensiRef.orderByChild("idJadwal").equalTo(Common.keyFeeJadwalSelected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.getValue(Presensi.class).getBulan().equals(Common.bulanFeeSelected)&&
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

}
