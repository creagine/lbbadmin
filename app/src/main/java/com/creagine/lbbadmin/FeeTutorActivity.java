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
import com.creagine.lbbadmin.Model.Tutor;
import com.creagine.lbbadmin.ViewHolder.TutorViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeeTutorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String keyTutor;

    FirebaseRecyclerAdapter<Tutor, TutorViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_tutor);

        //TODO fee tutor activity
        // add fee tutor
        // disini isinya list tutor true fee tutor detail isinya seperti spp siswa tapi fee

        recyclerView = findViewById(R.id.recyclerViewTutor);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Tutor> options = new FirebaseRecyclerOptions.Builder<Tutor>()
                .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Tutor")
                        ,Tutor.class)
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

                        //Get CategoryId and send to new Activity
                        Intent intent = new Intent(FeeTutorActivity.this, FeeTutorDetailActivity.class);

                        //When user select shop, we will save shop id to select service of this shop
                        keyTutor = adapter.getRef(position).getKey();

                        getDataTutor();

                        startActivity(intent);

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

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Tutor");

        usersRef.child(keyTutor)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Tutor selectedTutor = dataSnapshot.getValue(Tutor.class);
                        Common.tutorSelected = selectedTutor;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

}
