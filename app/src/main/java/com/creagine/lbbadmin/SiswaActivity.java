package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SiswaActivity extends AppCompatActivity {

    Button btnAddSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

        btnAddSiswa = findViewById(R.id.buttonAddSiswa);

        btnAddSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SiswaActivity.this, AddSiswaActivity.class);
                startActivity(intent);

            }
        });

    }
}
