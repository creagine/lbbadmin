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
import com.creagine.lbbadmin.Model.GantiJadwal;
import com.creagine.lbbadmin.ViewHolder.GantiJadwalViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GantiJadwalListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<GantiJadwal, GantiJadwalViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal_list);
        getSupportActionBar().setTitle("List Ganti Jadwal");

        recyclerView = findViewById(R.id.RecyclerViewListGantiJadwal);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<GantiJadwal> options = new FirebaseRecyclerOptions.Builder<GantiJadwal>()
                .setQuery(FirebaseDatabase.getInstance()
                        .getReference()
                        .child("GantiJadwal")
                        ,GantiJadwal.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<GantiJadwal, GantiJadwalViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GantiJadwalViewHolder viewHolder, int position, @NonNull GantiJadwal model) {

                viewHolder.siswa.setText(model.getSiswa());
                viewHolder.tutor.setText(model.getTutor());
                viewHolder.status.setText(model.getStatus());

                final GantiJadwal clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //When user select shop, we will save shop id to select service of this shop
                        Common.keyJadwalGantiSelected = adapter.getRef(position).getKey();

                        getDataJadwal();
                        Intent intent = new Intent(GantiJadwalListActivity.this,
                                GantiJadwalLihatActivity.class);
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public GantiJadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ganti_jadwal_item, parent, false);
                return new GantiJadwalViewHolder(itemView) {
                };
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void getDataJadwal(){

        DatabaseReference jadwalPenggantiRef = FirebaseDatabase.getInstance().getReference("GantiJadwal");

        jadwalPenggantiRef.child(Common.keyJadwalGantiSelected)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        GantiJadwal selectedJadwal = dataSnapshot.getValue(GantiJadwal.class);
                        Common.jadwalPenggantiSelected = selectedJadwal;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        fetch();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
