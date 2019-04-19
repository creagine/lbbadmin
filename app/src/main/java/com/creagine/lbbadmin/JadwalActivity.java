package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JadwalActivity extends AppCompatActivity {

    Button btnFilterJadwal, btnAddJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        btnAddJadwal = findViewById(R.id.buttonAddJadwal);
        btnFilterJadwal = findViewById(R.id.buttonFIlterJadwal);

        btnAddJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(JadwalActivity.this, AddJadwalActivity.class);
                startActivity(intent);

            }
        });

        btnFilterJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(JadwalActivity.this, JadwalActivity.class);
                startActivity(intent);

            }
        });

    }
}
