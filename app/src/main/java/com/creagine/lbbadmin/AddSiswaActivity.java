package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddSiswaActivity extends AppCompatActivity {

    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siswa);

        btnSave = findViewById(R.id.buttonSaveSiswa);
        btnCancel = findViewById(R.id.buttonCancelSiswa);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (AddSiswaActivity.this, SiswaActivity.class);
                startActivity(intent);

            }
        });

    }
}
