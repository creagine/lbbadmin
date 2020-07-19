package com.creagine.lbbadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Model.Jadwal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class GantiJadwalLihatActivity extends AppCompatActivity {

    private TextView txtNama, txtNamaLama, txtTutor, txtTutorLama, txtTanggal, txtTanggalLama,
            txtJam, txtJamLama, txtHari, txtHariLama, txtRuangan, txtRuanganLama, txtStatus;

    private Button btnUpdate, btnTolak;

    private DatabaseReference jadwalRef, gantiJadwalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal_lihat);
        getSupportActionBar().setTitle("Ganti Jadwal Detail");

        //TODO TEST GANTI JADWAL

        //todo tampilkan jadwal lama dan jadwal baru, tombol update jadwal dan tolak ganti jadwal

        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");
        gantiJadwalRef = FirebaseDatabase.getInstance().getReference("GantiJadwal");

        widgetInit();

        getDataJadwalLama();

        getDataJadwal();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(GantiJadwalLihatActivity.this)
                        .setTitle("Update jadwal")
                        .setMessage("Anda yakin akan mengkonfirmasi dan update jadwal lama ke jadwal baru?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                                jadwalRef.child(Common.jadwalPenggantiSelected.getJadwal())
                                        .child("tanggal").setValue(Common.jadwalPenggantiSelected.getTanggal());
                                jadwalRef.child(Common.jadwalPenggantiSelected.getJadwal())
                                        .child("jam").setValue(Common.jadwalPenggantiSelected.getJam());
                                jadwalRef.child(Common.jadwalPenggantiSelected.getJadwal())
                                        .child("hari").setValue(Common.jadwalPenggantiSelected.getHari());
                                jadwalRef.child(Common.jadwalPenggantiSelected.getJadwal())
                                        .child("ruangan").setValue(Common.jadwalPenggantiSelected.getRuang());
                                gantiJadwalRef.child(Common.keyJadwalGantiSelected)
                                        .child("status").setValue("Dikonfirmasi");
                                finish();

                            }
                        }).create().show();

            }
        });

        btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(GantiJadwalLihatActivity.this)
                        .setTitle("Tolak pergantian jadwal")
                        .setMessage("Anda yakin akan menolak pergantian jadwal?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                                jadwalRef.child(Common.jadwalPenggantiSelected.getJadwal())
                                        .setValue(null);

                                finish();

                            }
                        }).create().show();

            }
        });

    }

    private void widgetInit(){

        txtNamaLama = findViewById(R.id.textViewNamaSiswaLihatLama);
        txtTutorLama = findViewById(R.id.textViewNamaTutorLihatLama);
        txtTanggalLama = findViewById(R.id.textViewTanggalLihatLama);
        txtJamLama = findViewById(R.id.textViewJamLihatLama);
        txtHariLama = findViewById(R.id.textViewHariLihatLama);
        txtRuanganLama = findViewById(R.id.textViewRuanganLihatLama);
        txtNama = findViewById(R.id.textViewNamaSiswaLihat);
        txtTutor = findViewById(R.id.textViewNamaTutorLihat);
        txtTanggal = findViewById(R.id.textViewTanggalLihat);
        txtJam = findViewById(R.id.textViewJamLihat);
        txtHari = findViewById(R.id.textViewHariLihat);
        txtRuangan = findViewById(R.id.textViewRuanganLihat);
        txtStatus = findViewById(R.id.textViewStatusLihat);
        btnUpdate = findViewById(R.id.buttonUpdateJadwal);
        btnTolak = findViewById(R.id.buttonTolakJadwal);

    }

    private void getDataJadwalLama() {

        Query query = jadwalRef.child(Common.jadwalPenggantiSelected.getJadwal());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Jadwal dataJadwal = dataSnapshot.getValue(Jadwal.class);
                txtNamaLama.setText(dataJadwal.getNamaSiswa());
                txtTutorLama.setText(dataJadwal.getTutor());
                txtTanggalLama.setText(dataJadwal.getTanggal());
                txtJamLama.setText(dataJadwal.getJam());
                txtHariLama.setText(dataJadwal.getHari());
                txtRuanganLama.setText(dataJadwal.getRuang());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getDataJadwal(){

        txtNama.setText(Common.jadwalPenggantiSelected.getSiswa());
        txtTutor.setText(Common.jadwalPenggantiSelected.getTutor());
        txtTanggal.setText(Common.jadwalPenggantiSelected.getTanggal());
        txtJam.setText(Common.jadwalPenggantiSelected.getJam());
        txtHari.setText(Common.jadwalPenggantiSelected.getHari());
        txtRuangan.setText(Common.jadwalPenggantiSelected.getRuang());
        txtStatus.setText(Common.jadwalPenggantiSelected.getStatus());

    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
