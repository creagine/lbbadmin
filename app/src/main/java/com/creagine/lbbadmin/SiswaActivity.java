package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.creagine.lbbadmin.models.Siswa;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SiswaActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private Button btnAddSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

        btnAddSiswa = findViewById(R.id.buttonAddSiswa);
        recyclerView = findViewById(R.id.categorySiswa);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();


        btnAddSiswa.setOnClickListener(this);

    }

    private void fetch() {
        //TODO : Recycle View Siswa

//        Query query = FirebaseDatabase.getInstance().getReference().child("categorys").orderByChild("shopId").equalTo(firebaseUser.getUid());
//
//        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(query, new SnapshotParser<Category>() {
//            @NonNull
//            @Override
//            public Category parseSnapshot(@NonNull DataSnapshot snapshot) {
//                return new Category(snapshot.child("nameCategory").getValue().toString());
//            }
//        }).build();
//
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {
//
////                final DatabaseReference catRef = getRef(position);
//
//                holder.txtCategory(model.getNameCategory());
//            }
//
//            @NonNull
//            @Override
//            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_category,viewGroup,false);
//
//                return new CategoryViewHolder(view);
//            }
//        };
//        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonAddSiswa){
            Intent intent = new Intent(SiswaActivity.this, AddSiswaActivity.class);
            startActivity(intent);
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        firebaseRecyclerAdapter.startListening();
//
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        firebaseRecyclerAdapter.stopListening();
//    }

}
