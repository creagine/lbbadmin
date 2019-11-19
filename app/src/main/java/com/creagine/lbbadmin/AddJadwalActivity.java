package com.creagine.lbbadmin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.creagine.lbbadmin.Model.Jadwal;
import com.creagine.lbbadmin.Model.Siswa;
import com.creagine.lbbadmin.Model.Tutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddJadwalActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave, btnCancel, btnJamMulai, btnJamSelesai, btnTanggal;
    private Spinner spinnerSiswa, spinnerTutor, spinnerHari, spinnerRuang, spinnerPertemuan;
    private EditText edtHarga, edtGrade, edtJurusan;
    private ProgressBar progressBar;
    
    private String tanggal, jamMulai, jamSelesai;

    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private DatabaseReference jadwalRef, tutorRef, siswaRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jadwal);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        // get reference to 'Siswa'
        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");
        tutorRef = FirebaseDatabase.getInstance().getReference("Tutor");
        siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");

        widgets();

        generateSiswa();

        generateTutor();

        btnTanggal.setOnClickListener(this);
        btnJamMulai.setOnClickListener(this);
        btnJamSelesai.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    private void generateSiswa() {

        siswaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> siswas = new ArrayList<String>();

                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name=ds.getValue(Siswa.class).getNamaSiswa();
                    siswas.add(name);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddJadwalActivity.this, android.R.layout.simple_spinner_item, siswas);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSiswa.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void widgets() {

        edtHarga = findViewById(R.id.editTextHarga);
        edtGrade = findViewById(R.id.editTextGrade);
        btnJamMulai = findViewById(R.id.buttonJamMulai);
        btnJamSelesai = findViewById(R.id.buttonJamSelesai);
        btnTanggal = findViewById(R.id.buttonTanggal);
        edtJurusan = findViewById(R.id.editTextJurusan);
        spinnerRuang = findViewById(R.id.spinnerRuang);
        spinnerHari = findViewById(R.id.spinnerHari);
        spinnerTutor = findViewById(R.id.spinnerTutor);
        spinnerSiswa = findViewById(R.id.spinnerSiswa);
        spinnerPertemuan = findViewById(R.id.spinnerPertemuan);
        btnSave = findViewById(R.id.buttonSaveJadwal);
        btnCancel = findViewById(R.id.buttonCancelJadwal);
        progressBar = findViewById(R.id.progressBarJadwal);

    }

    private void generateTutor(){

        tutorRef.addListenerForSingleValueEvent(new ValueEventListener() {
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

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                btnTanggal.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
                tanggal = dateFormatter.format(newDate.getTime());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void showTimeDialogJamMulai() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                jamMulai = hourOfDay+":"+minute;
                btnJamMulai.setText(jamMulai);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    private void showTimeDialogJamSelesai() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                jamSelesai = hourOfDay+":"+minute;
                btnJamSelesai.setText(jamSelesai);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonSaveJadwal){

            saveJadwal();

        } if(i == R.id.buttonTanggal){

            showDateDialog();

        } if(i == R.id.buttonJamMulai){

            showTimeDialogJamMulai();

        } if(i == R.id.buttonJamSelesai){

            showTimeDialogJamSelesai();

        } else if (i == R.id.buttonCancelJadwal){
            Intent intent = new Intent (AddJadwalActivity.this, JadwalActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void saveJadwal() {

        progressBar.setVisibility(View.VISIBLE);

        final String namaSiswa = spinnerSiswa.getSelectedItem().toString();
        final String tutor = spinnerTutor.getSelectedItem().toString();
        final String hari = spinnerHari.getSelectedItem().toString();
        String grade = edtGrade.getText().toString();
        String harga = edtHarga.getText().toString();
        String jurusan = edtJurusan.getText().toString();
        String jam = jamMulai+" - "+jamSelesai;
        String ruang = spinnerRuang.getSelectedItem().toString();
        String pertemuan = spinnerPertemuan.getSelectedItem().toString();

        if (TextUtils.isEmpty(namaSiswa)) {
            Toast.makeText(getApplicationContext(), "Masukkan nama siswa!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (TextUtils.isEmpty(tutor)) {
            Toast.makeText(getApplicationContext(), "Masukkan nama tutor!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(hari)) {
            Toast.makeText(getApplicationContext(), "Masukkan hari!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(grade)) {
            Toast.makeText(getApplicationContext(), "Masukkan grade!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(getApplicationContext(), "Masukkan harga!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(jurusan)) {
            Toast.makeText(getApplicationContext(), "Masukkan jurusan!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(jam)) {
            Toast.makeText(getApplicationContext(), "Masukkan jam!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(ruang)) {
            Toast.makeText(getApplicationContext(), "Masukkan ruang!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(pertemuan)) {
            Toast.makeText(getApplicationContext(), "Masukkan jumlah pertemuan!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        String jadwalId = jadwalRef.push().getKey();

        Jadwal newJadwal = new Jadwal(namaSiswa, jurusan, grade, harga, tutor, hari, jam, tanggal,
                ruang, pertemuan);

        jadwalRef.child(jadwalId).setValue(newJadwal);

        finish();

    }


}
