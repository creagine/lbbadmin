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
import com.creagine.lbbadmin.Model.Tagihan;
import com.creagine.lbbadmin.ViewHolder.TagihanViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SppDetailActivity extends AppCompatActivity {

    //TODO test detail spp

    private FloatingActionButton btnAddTagihan;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<Tagihan, TagihanViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spp_detail);

        btnAddTagihan = findViewById(R.id.buttonAddTagihan);

        recyclerView = findViewById(R.id.recyclerViewSpp);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

        btnAddTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SppDetailActivity.this, AddTagihanActivity.class);
                startActivity(intent);

            }
        });

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Tagihan> options = new FirebaseRecyclerOptions.Builder<Tagihan>()
                .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Spp")
                        ,Tagihan.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Tagihan, TagihanViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TagihanViewHolder viewHolder, int position, @NonNull Tagihan model) {

                viewHolder.noTagihan.setText(model.getNoTagihan());
                viewHolder.namaSiswa.setText(model.getNamaSiswaTagihan());
                viewHolder.deskripsiTagihan.setText(model.getDeskripsiTagihan());
                viewHolder.tanggalTagihan.setText(model.getTanggalJatuhTempoTagihan());
                viewHolder.nominalTagihan.setText(model.getNominalTagihan());
                viewHolder.statusTagihan.setText(model.getStatusTagihan());

                final Tagihan clickItem = model;

                //TODO ketika di click masuk ke halaman detail tagihan siswa isinya merubah status
//                viewHolder.setItemClickListener(new ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//
//                        //Get CategoryId and send to new Activity
//                        Intent intent = new Intent(SppDetailActivity.this, SppDetailActivity.class);
//
//                        //When user select shop, we will save shop id to select service of this shop
//                        Common.siswaSelected = adapter.getRef(position).getKey();
//
//                        startActivity(intent);
//
//                    }
//                });
            }

            @NonNull
            @Override
            public TagihanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.siswa_item, parent, false);
                return new TagihanViewHolder(itemView) {
                };
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
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