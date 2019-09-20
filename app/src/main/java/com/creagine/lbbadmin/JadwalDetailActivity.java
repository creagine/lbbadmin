package com.creagine.lbbadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Model.Jadwal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JadwalDetailActivity extends AppCompatActivity {

    private TextView txtNama, txtTutor, txtJurusan, txtGrade, txtHarga, txtTanggal, txtJam, txtHari,
            txtRuangan;

    private DatabaseReference jadwalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_detail);

        txtNama = findViewById(R.id.textViewNamaSiswaDetail);
        txtTutor = findViewById(R.id.textViewNamaSiswaDetail);
        txtJurusan = findViewById(R.id.textViewNamaSiswaDetail);
        txtGrade = findViewById(R.id.textViewNamaSiswaDetail);
        txtHarga = findViewById(R.id.textViewNamaSiswaDetail);
        txtTanggal = findViewById(R.id.textViewNamaSiswaDetail);
        txtJam = findViewById(R.id.textViewNamaSiswaDetail);
        txtHari = findViewById(R.id.textViewNamaSiswaDetail);
        txtRuangan = findViewById(R.id.textViewNamaSiswaDetail);

        //TODO tes jadwal detail
        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");

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
}
