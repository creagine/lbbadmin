package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class TutorActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    Button btnAddTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);


        recyclerView = findViewById(R.id.categoryTutor);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

        btnAddTutor = findViewById(R.id.buttonAddTutor);

        btnAddTutor.setOnClickListener(this);

    }

    private void fetch() {
        //TODO : Recycle View Tutor

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
        if (i == R.id.buttonAddTutor){
            //TODO : add tutor fuction
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseRecyclerAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

}
