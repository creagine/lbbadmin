package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTutorActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSave, btnCancel;
    EditText editTextNamaTutor;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutor);

        widgets();

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    private void widgets() {

        editTextNamaTutor = findViewById(R.id.editTextNamaTutor);
        spinner = findViewById(R.id.spinner);
        btnSave = findViewById(R.id.buttonSaveTutor);
        btnCancel = findViewById(R.id.buttonCancelTutor);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonSaveTutor){

            //TODO : add save tutor function here

        } else if (i == R.id.buttonCancelTutor){
            Intent intent = new Intent (AddTutorActivity.this, TutorActivity.class);
            startActivity(intent);
        }
    }
}
