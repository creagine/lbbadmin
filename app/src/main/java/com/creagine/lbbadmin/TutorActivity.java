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
import android.widget.Button;
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.Model.Tutor;
import com.creagine.lbbadmin.ViewHolder.TutorViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class TutorActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton btnAddTutor;
    private TextView txtNamaTutor, txtJurusanTutor;

    FirebaseRecyclerAdapter<Tutor, TutorViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        getSupportActionBar().setTitle("List tutor");

        btnAddTutor = findViewById(R.id.buttonAddTutor);
        txtNamaTutor = findViewById(R.id.textViewNamaTutor);
        txtJurusanTutor = findViewById(R.id.textViewJurusanTutor);
        recyclerView = findViewById(R.id.recyclerViewTutor);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetch();

        btnAddTutor.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonAddTutor){
            Intent intent = new Intent(TutorActivity.this, AddTutorActivity.class);
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

        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
