package com.creagine.lbbadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Model.Tagihan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TagihanDetailActivity extends AppCompatActivity {

    TextView txtNoTagihan, txtDeskripsiTagihan, txtNominalTagihan, txtTanggalTagihan,
            txtStatusTagihan;
    Button btnKonfirmasiPembayaran, btnBatalkanPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagihan_detail);
        getSupportActionBar().setTitle("Detail tagihan");

        txtNoTagihan = findViewById(R.id.textViewNoTagihanDetail);
        txtDeskripsiTagihan = findViewById(R.id.textViewDeskripsiTagihanDetail);
        txtNominalTagihan = findViewById(R.id.textViewNominalTagihanDetail);
        txtTanggalTagihan = findViewById(R.id.textViewTanggalTagihanDetail);
        txtStatusTagihan = findViewById(R.id.textViewStatusTagihan);
        btnKonfirmasiPembayaran = findViewById(R.id.buttonKonfirmasiPembayaran);
        btnBatalkanPembayaran = findViewById(R.id.buttonBatalkanStatusPembayaran);

        getDataTagihan();

        btnBatalkanPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBelumLunas();

                finish();

            }
        });

        btnKonfirmasiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLunas();

                finish();

            }
        });

    }

    private void getDataTagihan(){

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Tagihan");

        usersRef.child(Common.tagihanSelected)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Tagihan selectedtagihan = dataSnapshot.getValue(Tagihan.class);

                        txtNoTagihan.setText(selectedtagihan.getNoTagihan());
                        txtDeskripsiTagihan.setText(selectedtagihan.getDeskripsiTagihan());
                        txtNominalTagihan.setText(selectedtagihan.getNominalTagihan());
                        txtTanggalTagihan.setText(selectedtagihan.getTanggalJatuhTempoTagihan());
                        txtStatusTagihan.setText(selectedtagihan.getStatusTagihan());

                        if(selectedtagihan.getStatusTagihan().equals("Lunas")){
                            btnBatalkanPembayaran.setVisibility(View.VISIBLE);
                        } else {
                            btnKonfirmasiPembayaran.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    private void setBelumLunas(){

        DatabaseReference tagihanRef = FirebaseDatabase.getInstance().getReference("Tagihan")
                .child(Common.tagihanSelected).child("statusTagihan");

        tagihanRef.setValue("Belum Lunas");



    }

    private void setLunas(){

        DatabaseReference tagihanRef = FirebaseDatabase.getInstance().getReference("Tagihan")
                .child(Common.tagihanSelected).child("statusTagihan");

        tagihanRef.setValue("Lunas");

        getDataTagihan();

    }

}
