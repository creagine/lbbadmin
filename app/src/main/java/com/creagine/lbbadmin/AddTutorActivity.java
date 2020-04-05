package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.creagine.lbbadmin.Model.Siswa;
import com.creagine.lbbadmin.Model.Tutor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTutorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnCancel;
    private EditText editTextNamaTutor, edtEmailTutor, edtPasswordTutor, edtConfirmTutor,
            edtJurusanTutor;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference tutorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutor);
        getSupportActionBar().setTitle("Tambah tutor");

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        // get reference to 'Siswa'
        tutorRef = FirebaseDatabase.getInstance().getReference("Tutor");

        widgets();

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    private void widgets() {

        editTextNamaTutor = findViewById(R.id.editTextNamaTutor);
        edtEmailTutor = findViewById(R.id.editTextEmail);
        edtPasswordTutor = findViewById(R.id.editTextPassword);
        edtConfirmTutor = findViewById(R.id.editTextConfirmPassword);
        edtJurusanTutor = findViewById(R.id.editTextJurusanTutor);
        btnSave = findViewById(R.id.buttonSaveTutor);
        btnCancel = findViewById(R.id.buttonCancelTutor);
        progressBar = findViewById(R.id.progressBar2);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonSaveTutor){

            saveTutor();

        } else if (i == R.id.buttonCancelTutor){
            finish();
        }
    }

    private void saveTutor(){

        final String namaTutor = editTextNamaTutor.getText().toString();
        final String emailTutor = edtEmailTutor.getText().toString();
        final String passwordTutor = edtPasswordTutor.getText().toString();
        final String confirmTutor = edtConfirmTutor.getText().toString();
        final String searchname = namaTutor.toLowerCase().replace(" ", "");

        final String jurusan = edtJurusanTutor.getText().toString();

        if (TextUtils.isEmpty(namaTutor)) {
            Toast.makeText(getApplicationContext(), "Masukkan nama!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(emailTutor)) {
            Toast.makeText(getApplicationContext(), "Masukkan email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(passwordTutor)) {
            Toast.makeText(getApplicationContext(), "Masukkan password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmTutor)) {
            Toast.makeText(getApplicationContext(), "Masukkan konfirmasi password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (passwordTutor.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordTutor.equals(confirmTutor)) {
            Toast.makeText(getApplicationContext(), "Password tidak sesuai dengan konfirmasi!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        mAuth.createUserWithEmailAndPassword(emailTutor, passwordTutor)
                .addOnCompleteListener(AddTutorActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(AddTutorActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(AddTutorActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            pushToDatabase(namaTutor, searchname, emailTutor, passwordTutor, jurusan);

                            startActivity(new Intent(AddTutorActivity.this, TutorActivity.class));
                            finish();
                        }
                    }
                });

    }

    private void pushToDatabase(String namaTutor, String searchnameTutor, String emailTutor, String passwordTutor, String jurusanTutor){

        //get current user
        user = FirebaseAuth.getInstance().getCurrentUser();
        String tutorId = user.getUid();

        Tutor newTutor = new Tutor(namaTutor, searchnameTutor, emailTutor, passwordTutor,
                jurusanTutor);

        tutorRef.child(tutorId).setValue(newTutor);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
