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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSiswaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnCancel;

    private EditText editTextNamaSiswa,editTextTempatLahir,editTextTanggalLahir,editTextBulanLahir,
            editTextTahunLahir,editTextAlamat,editTextKecamatan,editTextKota,editTextProvinsi,
            editTextAgama,editTextKebangsaan,editTextNomorTelp,editTextNomorHp,editTextEmail,
            editTextPassword,editTextConfirmPassword, edtJurusan;

    private Spinner spinnerJenisKelamin;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference siswaRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siswa);
        getSupportActionBar().setTitle("Tambah siswa");

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        // get reference to 'Siswa'
        siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");

        widgets();

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    private void widgets() {
        editTextNamaSiswa = findViewById(R.id.editTextNamaSiswa);
        editTextTempatLahir = findViewById(R.id.editTextTempatLahir);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextBulanLahir = findViewById(R.id.editTextBulanLahir);
        editTextTahunLahir = findViewById(R.id.editTextTahunLahir);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextKecamatan = findViewById(R.id.editTextKecamatan);
        editTextKota = findViewById(R.id.editTextKota);
        editTextProvinsi = findViewById(R.id.editTextProvinsi);
        editTextAgama = findViewById(R.id.editTextAgama);
        editTextKebangsaan = findViewById(R.id.editTextKebangsaan);
        editTextNomorTelp = findViewById(R.id.editTextNomorTelp);
        editTextNomorHp = findViewById(R.id.editTextNomorHp);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        progressBar = findViewById(R.id.progressBar);
        edtJurusan = findViewById(R.id.editTextJurusan);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        btnSave = findViewById(R.id.buttonSaveSiswa);
        btnCancel = findViewById(R.id.buttonCancelSiswa);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if ( i == R.id.buttonSaveSiswa){
            saveSiswa();
        } else if (i == R.id.buttonCancelSiswa){
            finish();
        }
    }

    private void saveSiswa(){

        final String namaSiswa = editTextNamaSiswa.getText().toString();
        final String searchname = namaSiswa.toLowerCase().replace(" ", "");
        final String tempatLahir = editTextTempatLahir.getText().toString();
        String tanggalLahir = editTextTanggalLahir.getText().toString();
        String bulanLahir = editTextBulanLahir.getText().toString();
        String tahunLahir = editTextTahunLahir.getText().toString();
        final String alamat  = editTextAlamat.getText().toString();
        final String kecamatan = editTextKecamatan.getText().toString();
        final String kota = editTextKota.getText().toString();
        final String provinsi = editTextProvinsi.getText().toString();
        final String agama = editTextAgama.getText().toString();
        final String kebangsaan = editTextKebangsaan.getText().toString();
        final String nomorTelp = editTextNomorTelp.getText().toString();
        final String nomorHp = editTextNomorHp.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String confirmPassword = editTextConfirmPassword.getText().toString();

        final String jenisKelamin = spinnerJenisKelamin.getSelectedItem().toString();
        final String jurusan = edtJurusan.getText().toString();

        final String lahir = tanggalLahir + "-" + bulanLahir + "-" + tahunLahir;

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Masukkan email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(namaSiswa)) {
            Toast.makeText(getApplicationContext(), "Masukkan nama!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(tempatLahir)) {
            Toast.makeText(getApplicationContext(), "Masukkan tempat lahir!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(tanggalLahir)) {
            Toast.makeText(getApplicationContext(), "Masukkan tanggal lahir!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(bulanLahir)) {
            Toast.makeText(getApplicationContext(), "Masukkan bulan lahir!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(tahunLahir)) {
            Toast.makeText(getApplicationContext(), "Masukkan tahun lahir!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(alamat)) {
            Toast.makeText(getApplicationContext(), "Masukkan alamat!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(kecamatan)) {
            Toast.makeText(getApplicationContext(), "Masukkan kecamatan!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(kota)) {
            Toast.makeText(getApplicationContext(), "Masukkan kota!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(provinsi)) {
            Toast.makeText(getApplicationContext(), "Masukkan provinsi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(agama)) {
            Toast.makeText(getApplicationContext(), "Masukkan agama!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(kebangsaan)) {
            Toast.makeText(getApplicationContext(), "Masukkan kebangsaan!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(nomorTelp)) {
            Toast.makeText(getApplicationContext(), "Masukkan nomor telepon!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(nomorHp)) {
            Toast.makeText(getApplicationContext(), "Masukkan nomor hp!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(jenisKelamin)) {
            Toast.makeText(getApplicationContext(), "pilih jenis kelamin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(jurusan)) {
            Toast.makeText(getApplicationContext(), "pilih jurusan!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Masukkan password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Masukkan konfirmasi password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Password tidak sesuai dengan konfirmasi!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(AddSiswaActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(AddSiswaActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(AddSiswaActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            pushToDatabase(searchname, namaSiswa, jurusan,
                                    jenisKelamin, tempatLahir, lahir,
                                    alamat, kecamatan, kota, provinsi,
                                    agama, kebangsaan, nomorTelp,
                                    nomorHp, email, password);

                            finish();
                        }
                    }
                });

    }

    private void pushToDatabase(String searchnameSiswa, String namaSiswa, String jurusanSiswa,
                                String jenisKelaminSiswa, String tempatLahirSiswa, String tanggalLahirSiswa,
                                String alamatSiswa, String kecamatanSiswa, String kotaSiswa, String provinsiSiswa,
                                String agamaSiswa, String kebangsaanSiswa, String nomorTelpSiswa,
                                String nomorHpSiswa, String emailSiswa, String passwordSiswa){

        //get current user
        user = FirebaseAuth.getInstance().getCurrentUser();
        String siswaId = user.getUid();

        Siswa newSiswa = new Siswa(searchnameSiswa, namaSiswa, jurusanSiswa,
                jenisKelaminSiswa, tempatLahirSiswa, tanggalLahirSiswa,
                alamatSiswa, kecamatanSiswa, kotaSiswa, provinsiSiswa,
                agamaSiswa, kebangsaanSiswa, nomorTelpSiswa,
                nomorHpSiswa, emailSiswa, passwordSiswa);
        siswaRef.child(siswaId).setValue(newSiswa);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
