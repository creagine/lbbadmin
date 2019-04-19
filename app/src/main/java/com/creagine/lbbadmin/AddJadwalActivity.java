package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.creagine.lbbadmin.Model.Jadwal;
import com.creagine.lbbadmin.Model.Tutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddJadwalActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave, btnCancel;
    private EditText edtNamaSiswa;
    private Spinner spinnerJurusan, spinnerTutor, spinnerHari, spinnerJam, spinnerGradeKeyboard, 
            spinnerGradeVocal, spinnerGradePopularPiano, spinnerGradeClassicalPiano;
    private ProgressBar progressBar;
    
    private String jurusan, grade;

    private DatabaseReference jadwalRef, tutorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jadwal);

        // get reference to 'Siswa'
        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");
        tutorRef = FirebaseDatabase.getInstance().getReference("Tutor");

        widgets();

        spinnerJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                if(position > 0){
                    // get spinner value
                    jurusan = spinnerJurusan.getSelectedItem().toString();

                    generateGrade();

                    generateTutor();

                    //generate tutor berdasarkan jurusan
//                    spinnerTutor.setAdapter(new ArrayAdapter<String>
//                            (AddJadwalActivity.this,android.R.layout.simple_list_item_1,retrieve()));
                }else{
                    // show toast select gender
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    private void generateGrade() {

        if (jurusan.equals("Keyboard")){

            spinnerGradeKeyboard.setVisibility(View.VISIBLE);
            spinnerGradeVocal.setVisibility(View.GONE);
            spinnerGradePopularPiano.setVisibility(View.GONE);
            spinnerGradeClassicalPiano.setVisibility(View.GONE);
            grade = spinnerGradeKeyboard.getSelectedItem().toString();

        }

        if (jurusan.equals("Vocal")){

            spinnerGradeVocal.setVisibility(View.VISIBLE);
            spinnerGradeKeyboard.setVisibility(View.GONE);
            spinnerGradePopularPiano.setVisibility(View.GONE);
            spinnerGradeClassicalPiano.setVisibility(View.GONE);
            grade = spinnerGradeVocal.getSelectedItem().toString();

        }

        if (jurusan.equals("Popular Piano")){

            spinnerGradePopularPiano.setVisibility(View.VISIBLE);
            spinnerGradeVocal.setVisibility(View.GONE);
            spinnerGradeKeyboard.setVisibility(View.GONE);
            spinnerGradeClassicalPiano.setVisibility(View.GONE);
            grade = spinnerGradePopularPiano.getSelectedItem().toString();

        }

        if (jurusan.equals("Classical Piano")){

            spinnerGradeClassicalPiano.setVisibility(View.VISIBLE);
            spinnerGradePopularPiano.setVisibility(View.GONE);
            spinnerGradeVocal.setVisibility(View.GONE);
            spinnerGradeKeyboard.setVisibility(View.GONE);
            grade = spinnerGradeClassicalPiano.getSelectedItem().toString();

        }

    }

    private void widgets() {

        spinnerGradeKeyboard = findViewById(R.id.spinnerGradeKeyboard);
        spinnerGradeVocal = findViewById(R.id.spinnerGradeVocal);
        spinnerGradePopularPiano = findViewById(R.id.spinnerGradePopularPiano);
        spinnerGradeClassicalPiano = findViewById(R.id.spinnerGradeClassicalPiano);
        spinnerHari = findViewById(R.id.spinnerHari);
        spinnerJam = findViewById(R.id.spinnerJam);
        spinnerJurusan = findViewById(R.id.spinnerJurusan);
        spinnerTutor = findViewById(R.id.spinnerTutor);
        edtNamaSiswa = findViewById(R.id.editTextNamaSiswa);
        btnSave = findViewById(R.id.buttonSaveJadwal);
        btnCancel = findViewById(R.id.buttonCancelJadwal);
        progressBar = findViewById(R.id.progressBarJadwal);

    }

//    public ArrayList<String> retrieve()
//    {
//        final ArrayList<String> tutors = new ArrayList<>();
//
//        tutorRef.orderByChild("jurusanTutor").equalTo(jurusan)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        fetchData(dataSnapshot,tutors);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//        return tutors;
//    }

//    private void fetchData(DataSnapshot snapshot,ArrayList<String> tutors)
//    {
//        tutors.clear();
//        for (DataSnapshot ds:snapshot.getChildren())
//        {
//            String name=ds.getValue(Tutor.class).getNamaTutor();
//            tutors.add(name);
//        }
//
//    }

    private void generateTutor(){

        tutorRef.orderByChild("jurusanTutor").equalTo(jurusan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> tutors = new ArrayList<String>();

                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name=ds.getValue(Tutor.class).getNamaTutor();
                    tutors.add(name);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddJadwalActivity.this, android.R.layout.simple_spinner_item, tutors);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTutor.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonSaveJadwal){

            saveJadwal();

        } else if (i == R.id.buttonCancelJadwal){
            Intent intent = new Intent (AddJadwalActivity.this, JadwalActivity.class);
            startActivity(intent);
        }
    }

    private void saveJadwal() {

        progressBar.setVisibility(View.VISIBLE);

        final String namaSiswa = edtNamaSiswa.getText().toString();
        final String tutor = spinnerTutor.getSelectedItem().toString();
        final String hari = spinnerHari.getSelectedItem().toString();
        final String jam = spinnerJam.getSelectedItem().toString();

        if (TextUtils.isEmpty(namaSiswa)) {
            Toast.makeText(getApplicationContext(), "Masukkan nama!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (jurusan.equals("")){
            Toast.makeText(this, "Pilih jurusan!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }

        String jadwalId = jadwalRef.push().getKey();

        Jadwal newJadwal = new Jadwal(namaSiswa, jurusan, grade, tutor, hari, jam);

        jadwalRef.child(jadwalId).setValue(newJadwal);

    }


}
