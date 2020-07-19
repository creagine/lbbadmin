package com.creagine.lbbadmin;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class AddFeeTutorActivity extends AppCompatActivity implements View.OnClickListener{

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

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i > 0){
                    // get spinner value
                    bulanPembayaran = spinnerBulan.getSelectedItem().toString();
                    Common.bulanFeeSelected = spinnerBulan.getSelectedItem().toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnTanggalSpp.setOnClickListener(this);

        btnSubmit.setOnClickListener(this);

        btnCancel.setOnClickListener(this);

        btnPilihJadwal.setOnClickListener(this);

        btnPresensi.setOnClickListener(this);

        btnHitungFee.setOnClickListener(this);

    }

    private void widgetinit(){

        progressBar = findViewById(R.id.progressBar4);
        edtNo = findViewById(R.id.editTextNo);
        spinnerBulan = findViewById(R.id.spinnerBulan);
        edtTahun = findViewById(R.id.editTextTahun);
        edtPresensi = findViewById(R.id.editTextJumlahKbm);
        edtTotalPresensi = findViewById(R.id.editTextTotalKbm);
        btnTanggalSpp = findViewById(R.id.buttonTanggalSpp);
        btnPilihJadwal = findViewById(R.id.buttonPilihJadwalAddFee);
        btnPresensi = findViewById(R.id.buttonPresensiAddFee);
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
        Long feeDibulatkan = Math.round((doubleFee*100)/100.0f);
        String dbFee = feeDibulatkan.toString();
        txtFee.setText("Rp. " + dbFee);

    }

    private void saveFee(){

        progressBar.setVisibility(View.VISIBLE);

        String idTutor = Common.feeJadwalSelected.getIdTutor();
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
        if (bulanPembayaran.equals("Pilih bulan")){
            Toast.makeText(getApplicationContext(), "Pilih bulan !", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }


        Fee newFee = new Fee(noPembayaran, idTutor, namaTutor, namaSiswa, bulanPembayaran, tahunPembayaran,
                jurusan, grade, tarif, fee, presensi, tglSpp);

        feeRef.child(noPembayaran).setValue(newFee);

        finish();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnPilihJadwal.setText(Common.btnPilihjadwalSelected);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.buttonTanggalSpp){

            showDateDialog();

        } if(i == R.id.buttonSubmitAddFee){

            saveFee();

        } if(i == R.id.buttonCancelAddFee){

            finish();

        } if(i == R.id.buttonPilihJadwalAddFee){

            Intent intent = new Intent(AddFeeTutorActivity.this, PilihJadwalFeeActivity.class);
            startActivity(intent);

        } if(i == R.id.buttonPresensiAddFee){

            //jika jadwal belum dipilih atau key jadwal masih kosong, maka muncul pilih jadwal
            if(!Common.keyFeeJadwalSelected.equals("")){

                Intent intent = new Intent(AddFeeTutorActivity.this, CekPresensiActivity.class);
                startActivity(intent);

            } else {

                Toast.makeText(AddFeeTutorActivity.this,
                        "Pilih jadwal terlebih dahulu", Toast.LENGTH_SHORT).show();

            }

        } else if(i == R.id.buttonHitungFee){

            if(Common.feeJadwalSelected != null){
                hitungFee();
            } else {

                Toast.makeText(this, "Pilih Jadwal terlebih dahulu", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
