package com.creagine.lbbadmin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Model.Tagihan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTagihanActivity extends AppCompatActivity {

    private EditText edtNoTagihan, edtDeskripsiTagihan, edtNominalTagihan;
    private Button btnTanggal, btnSubmit, btnCancel;
    private ProgressBar progressBar;

    private String tanggal;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private DatabaseReference tagihanRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tagihan);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tagihanRef = FirebaseDatabase.getInstance().getReference("Tagihan");

        //TODO test halaman add tagihan
        widgetinit();

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDateDialog();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveTagihan();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (AddTagihanActivity.this, SppDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void widgetinit(){

        progressBar = findViewById(R.id.progressBar3);
        edtNoTagihan = findViewById(R.id.editTextNoTagihan);
        edtDeskripsiTagihan = findViewById(R.id.editTextDeskripsiTagihan);
        edtNominalTagihan = findViewById(R.id.editTextNominalTagihan);
        btnTanggal = findViewById(R.id.buttonTanggalJatuhTempoTagihan);
        btnSubmit = findViewById(R.id.buttonSubmitTagihan);
        btnCancel = findViewById(R.id.buttonCancelTagihan);

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

    private void saveTagihan(){

        progressBar.setVisibility(View.VISIBLE);

        String namaSiswa = Common.siswaSelected.getNamaSiswa();
        String noTagihan = edtNoTagihan.getText().toString();
        String deskripsiTagihan = edtDeskripsiTagihan.getText().toString();
        String nominalTagihan = edtNominalTagihan.getText().toString();
        String statusTagihan = "Belum Lunas";

        if (TextUtils.isEmpty(noTagihan)) {
            Toast.makeText(getApplicationContext(), "Masukkan nomor tagihan!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(deskripsiTagihan)) {
            Toast.makeText(getApplicationContext(), "Masukkan deskripsi tagihan!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(nominalTagihan)) {
            Toast.makeText(getApplicationContext(), "Masukkan nominal tagihan!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        Tagihan newTagihan = new Tagihan(noTagihan, deskripsiTagihan, nominalTagihan, tanggal, namaSiswa, statusTagihan);

        tagihanRef.child(noTagihan).setValue(newTagihan);

        Intent intent = new Intent (AddTagihanActivity.this, SppDetailActivity.class);
        startActivity(intent);
        finish();

    }

}
