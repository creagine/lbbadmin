package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.Model.Siswa;
import com.creagine.lbbadmin.ViewHolder.SiswaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SppActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String keySiswa;

    FirebaseRecyclerAdapter<Siswa, SiswaViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spp);

        recyclerView = findViewById(R.id.recyclerViewSiswa);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Siswa> options = new FirebaseRecyclerOptions.Builder<Siswa>()
                .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Siswa")
                        ,Siswa.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Siswa, SiswaViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SiswaViewHolder viewHolder, int position, @NonNull Siswa model) {

                viewHolder.siswaNama.setText(model.getNamaSiswa());
                viewHolder.siswaJurusan.setText(model.getJurusanSiswa());

                final Siswa clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //Get CategoryId and send to new Activity
                        Intent intent = new Intent(SppActivity.this, SppDetailActivity.class);

                        //When user select shop, we will save shop id to select service of this shop
                        keySiswa = adapter.getRef(position).getKey();

                        getDataSiswa();

                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public SiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.siswa_item, parent, false);
                return new SiswaViewHolder(itemView) {
                };
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void getDataSiswa(){

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Siswa");

        usersRef.child(keySiswa)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Siswa selectedsiswa = dataSnapshot.getValue(Siswa.class);
                        Common.siswaSelected = selectedsiswa;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
       finish();
    }

}
