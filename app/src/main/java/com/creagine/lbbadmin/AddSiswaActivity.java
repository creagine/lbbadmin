package com.creagine.lbbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddSiswaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSave, btnCancel;
    EditText editTextNamaSiswa,editTextTempatLahir,editTextTanggalLahir,editTextAlamat,
            editTextKecamatan,editTextKota,editTextProvinsi,editTextAgama,editTextKebangsaan,editTextNomorTelp,
            editTextNomorHp,editTextPinBBM,editTextEmail;
    Spinner spinnerJurusan,spinnerJenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siswa);

        widgets();

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    private void widgets() {
        editTextNamaSiswa = findViewById(R.id.editTextNamaSiswa);
        editTextTempatLahir = findViewById(R.id.editTextTempatLahir);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextKecamatan = findViewById(R.id.editTextKecamatan);
        editTextKota = findViewById(R.id.editTextKota);
        editTextProvinsi = findViewById(R.id.editTextProvinsi);
        editTextAgama = findViewById(R.id.editTextAgama);
        editTextKebangsaan = findViewById(R.id.editTextKebangsaan);
        editTextNomorTelp = findViewById(R.id.editTextNomorTelp);
        editTextNomorHp = findViewById(R.id.editTextNomorHp);
        editTextPinBBM = findViewById(R.id.editTextPinBBM);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinnerJurusan = findViewById(R.id.spinnerJurusan);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        btnSave = findViewById(R.id.buttonSaveSiswa);
        btnCancel = findViewById(R.id.buttonCancelSiswa);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if ( i == R.id.buttonSaveSiswa){
            //TODO: button save siswa function
//            saveSiswa();
        } else if (i == R.id.buttonCancelSiswa){
            Intent intent = new Intent (AddSiswaActivity.this, SiswaActivity.class);
            startActivity(intent);
        }
    }
}
