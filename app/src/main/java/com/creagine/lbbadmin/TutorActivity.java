package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorActivity extends AppCompatActivity {

    Button btnAddTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        btnAddTutor = findViewById(R.id.buttonAddTutor);

        btnAddTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TutorActivity.this, AddTutorActivity.class);
                startActivity(intent);

            }
        });

    }
}
