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
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.Model.Siswa;
import com.creagine.lbbadmin.ViewHolder.SiswaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SiswaActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton btnAddSiswa;

    FirebaseRecyclerAdapter<Siswa, SiswaViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

        btnAddSiswa = findViewById(R.id.buttonAddSiswa);
        recyclerView = findViewById(R.id.recyclerViewSiswa);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

        btnAddSiswa.setOnClickListener(this);

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

//                        //Get CategoryId and send to new Activity
//                        Intent serviceList = new Intent(BarbershopActivity.this, BarbermanActivity.class);
//
//                        //When user select shop, we will save shop id to select service of this shop
//                        Common.serviceSelected = adapter.getRef(position).getKey();
//
//                        startActivity(serviceList);

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

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonAddSiswa){
            Intent intent = new Intent(SiswaActivity.this, AddSiswaActivity.class);
            startActivity(intent);
        }
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

}
