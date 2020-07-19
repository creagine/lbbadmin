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
import com.creagine.lbbadmin.Model.Tutor;
import com.creagine.lbbadmin.ViewHolder.TutorViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddJadwalPilihTutorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private DatabaseReference tutorRef;

    FirebaseRecyclerAdapter<Tutor, TutorViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jadwal_pilih_tutor);
        getSupportActionBar().setTitle("Pilih tutor yang akan mengajar pada jadwal");

        tutorRef = FirebaseDatabase.getInstance().getReference("Tutor");

        recyclerView = findViewById(R.id.RecyclerViewPilihTutor);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Tutor> options = new FirebaseRecyclerOptions.Builder<Tutor>()
                .setQuery(tutorRef,Tutor.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Tutor, TutorViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TutorViewHolder viewHolder, int position, @NonNull Tutor model) {

                viewHolder.txtNamaTutor.setText(model.getNamaTutor());
                viewHolder.txtJurusanTutor.setText(model.getJurusanTutor());

                final Tutor clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //When user select shop, we will save shop id to select service of this shop
                        Common.keyTutorAddJadwalSelected = adapter.getRef(position).getKey();

                        getDataTutor();

                        finish();

                    }
                });
            }

            @NonNull
            @Override
            public TutorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.tutor_item, parent, false);
                return new TutorViewHolder(itemView) {
                };
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void getDataTutor(){

        tutorRef.child(Common.keyTutorAddJadwalSelected)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Tutor selectedTutor = dataSnapshot.getValue(Tutor.class);

                        Common.btnPilihTutorSelected = selectedTutor.getNamaTutor();

                        Common.addJadwalTutorSelected = selectedTutor;

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
