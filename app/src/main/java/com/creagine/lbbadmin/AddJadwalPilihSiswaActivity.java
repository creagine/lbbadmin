package com.creagine.lbbadmin;

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
import com.creagine.lbbadmin.Model.Siswa;
import com.creagine.lbbadmin.ViewHolder.SiswaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddJadwalPilihSiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private DatabaseReference siswaRef;

    FirebaseRecyclerAdapter<Siswa, SiswaViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jadwal_pilih_siswa);

        getSupportActionBar().setTitle("Pilih siswa yang akan mengikuti jadwal");

        siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");

        recyclerView = findViewById(R.id.RecyclerViewPilihSiswa);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Siswa> options = new FirebaseRecyclerOptions.Builder<Siswa>()
                .setQuery(siswaRef,Siswa.class)
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

                        //When user select shop, we will save shop id to select service of this shop
                        Common.keySiswaAddJadwalSelected = adapter.getRef(position).getKey();

                        getDataSiswa();

                        finish();

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

        siswaRef.child(Common.keySiswaAddJadwalSelected)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Siswa selectedSiswa = dataSnapshot.getValue(Siswa.class);

                        Common.btnPilihSiswaSelected = selectedSiswa.getNamaSiswa();

                        Common.addJadwalSiswaSelected = selectedSiswa;

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
