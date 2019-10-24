package com.creagine.lbbadmin;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.creagine.lbbadmin.Model.Fee;
import com.creagine.lbbadmin.ViewHolder.FeeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class FeeTutorDetailActivity extends AppCompatActivity {

    private TextView txtNama, txtJurusan;

    private FloatingActionButton btnAddFee;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<Fee, FeeViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_tutor_detail);

        //TODO fee tutor detail isinya lihat dari add fee tutor
        // halaman ini merupakan itemclick listener dari list fee tutor

    }
}
