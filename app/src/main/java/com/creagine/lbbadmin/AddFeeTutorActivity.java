package com.creagine.lbbadmin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.creagine.lbbadmin.Common.Common;
import com.creagine.lbbadmin.Model.Fee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddFeeTutorActivity extends AppCompatActivity {

    private EditText edtNo, edtTahun, edtPresensi, edtTotalPresensi;
    private Spinner spinnerBulan;
    private Button btnPilihJadwal, btnPresensi, btnSubmit, btnCancel, btnTanggalSpp, btnHitungFee;
    private TextView txtFee;
    private ProgressBar progressBar;

    private String tanggal, tarif, totalKbm, presensi, bulanPembayaran;
    private Double doubleFee;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private DatabaseReference feeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fee_tutor);
        getSupportActionBar().setTitle("Tambah fee tutor");

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        feeRef = FirebaseDatabase.getInstance().getReference("Fee");

        widgetinit();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {

            } else {
                btnPilihJadwal.setText(extras.getString("btnjadwal"));
            }
        } else {
            String strbtnjadwal;
            strbtnjadwal = (String) savedInstanceState.getSerializable("btnjadwal");
            btnPilihJadwal.setText(strbtnjadwal);
        }



        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i > 0){
                    // get spinner value
                    bulanPembayaran = spinnerBulan.getSelectedItem().toString();
                    Common.bulanFeeSelected = spinnerBulan.getSelectedItem().toString();
                }else{
                    // show toast select gender
                    Toast.makeText(AddFeeTutorActivity.this, "Pilih bulan", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnTanggalSpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDateDialog();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveFee();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        btnPilihJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddFeeTutorActivity.this, PilihJadwalActivity.class);
                startActivity(intent);
//                btnPilihJadwal.setText("Jadwal sudah dipilih");

            }
        });

        btnPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //jika jadwal belum dipilih atau key jadwal masih kosong, maka muncul pilih jadwal
                if(!Common.keyFeeJadwalSelected.equals("")){

                    Intent intent = new Intent(AddFeeTutorActivity.this, CekPresensiActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(AddFeeTutorActivity.this,
                            "Pilih jadwal terlebih dahulu", Toast.LENGTH_SHORT).show();

                }



            }
        });

        btnHitungFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hitungFee();

            }
        });

    }

    private void widgetinit(){

        progressBar = findViewById(R.id.progressBar4);
        edtNo = findViewById(R.id.editTextNo);
        spinnerBulan = findViewById(R.id.spinnerBulan);
        edtTahun = findViewById(R.id.editTextTahun);
        edtPresensi = findViewById(R.id.editTextJumlahKbm);
        edtTotalPresensi = findViewById(R.id.editTextTotalKbm);
        btnTanggalSpp = findViewById(R.id.buttonTanggalSpp);
        btnPilihJadwal = findViewById(R.id.buttonPilihJadwal);
        btnPresensi = findViewById(R.id.buttonPresensi);
        btnHitungFee = findViewById(R.id.buttonHitungFee);
        btnSubmit = findViewById(R.id.buttonSubmitAddFee);
        btnCancel = findViewById(R.id.buttonCancelAddFee);
        txtFee = findViewById(R.id.textViewFee);

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
                btnTanggalSpp.setText("Tanggal spp : "+dateFormatter.format(newDate.getTime()));
                tanggal = dateFormatter.format(newDate.getTime());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void hitungFee(){

        presensi = edtPresensi.getText().toString();
        totalKbm = edtTotalPresensi.getText().toString();
        tarif = Common.feeJadwalSelected.getHarga();

        double intPresensi = Integer.parseInt(presensi);
        double intTotalKbm = Integer.parseInt(totalKbm);
        double intTarif = Integer.parseInt(tarif);

        //ex: 3/4 = masuk 3 dari 4x kelas) x 40% x harga
        doubleFee = (intPresensi/intTotalKbm)* 0.4 * intTarif;
        String dbFee = doubleFee.toString();
        txtFee.setText(dbFee);

    }

    private void saveFee(){

        progressBar.setVisibility(View.VISIBLE);

        String namaSiswa = Common.feeJadwalSelected.getNamaSiswa();
        String namaTutor = Common.feeJadwalSelected.getTutor();
        String noPembayaran = edtNo.getText().toString();
        String tahunPembayaran = edtTahun.getText().toString();
        String jurusan = Common.feeJadwalSelected.getJurusan();
        String grade = Common.feeJadwalSelected.getGrade();
        String tglSpp = tanggal;
        String fee = doubleFee.toString();

        if (TextUtils.isEmpty(noPembayaran)) {
            Toast.makeText(getApplicationContext(), "Masukkan nomor pembayaran!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(bulanPembayaran)) {
            Toast.makeText(getApplicationContext(), "Masukkan bulan pembayaran!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(tahunPembayaran)) {
            Toast.makeText(getApplicationContext(), "Masukkan tahun pembayaran!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(presensi)) {
            Toast.makeText(getApplicationContext(), "Masukkan jumlah KBM!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(totalKbm)) {
            Toast.makeText(getApplicationContext(), "Masukkan jumlah KBM!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(fee)) {
            Toast.makeText(getApplicationContext(), "Lakukan hitung fee!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }


        Fee newFee = new Fee(noPembayaran, namaTutor, bulanPembayaran, tahunPembayaran,
                namaSiswa, jurusan, grade, tarif, fee, presensi, tglSpp);

        feeRef.child(noPembayaran).setValue(newFee);

        Intent intent = new Intent (AddFeeTutorActivity.this, FeeTutorDetailActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
