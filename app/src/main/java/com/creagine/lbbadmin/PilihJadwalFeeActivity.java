package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.Model.Jadwal;
import com.creagine.lbbadmin.ViewHolder.JadwalViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PilihJadwalFeeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_jadwal_fee);

        recyclerView = findViewById(R.id.RecyclerViewJadwal);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Jadwal> options = new FirebaseRecyclerOptions.Builder<Jadwal>()
                .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Jadwal")
                        ,Jadwal.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull JadwalViewHolder viewHolder, int position, @NonNull Jadwal model) {

                viewHolder.siswaNama.setText(model.getNamaSiswa());
                viewHolder.siswaJurusan.setText(model.getJurusan());
                viewHolder.tutorNama.setText(model.getTutor());

                final Jadwal clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //When user select shop, we will save shop id to select service of this shop
                        Common.keyFeeJadwalSelected = adapter.getRef(position).getKey();

                        getDataJadwal();

                        Common.btnPilihjadwalSelected = "Jadwal sudah dipilih";

                        finish();

                    }
                });
            }

            @NonNull
            @Override
            public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.jadwal_item, parent, false);
                return new JadwalViewHolder(itemView) {
                };
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void getDataJadwal(){

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Jadwal");

        usersRef.child(Common.keyFeeJadwalSelected)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Jadwal selectedJadwal = dataSnapshot.getValue(Jadwal.class);
                        Common.feeJadwalSelected = selectedJadwal;

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
