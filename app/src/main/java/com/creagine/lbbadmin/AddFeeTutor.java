package com.creagine.lbbadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddFeeTutor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fee_tutor);

        //TODO add fee tutor, halaman ini nanti merupakan respon dari request fee yang dilakukan tutor dari halaman absensi
        // fee akan dibayar pada akhir bulan sesuai dengan absensi (ex: 3/4 = masuk 3 dari 4x kelas)
        // x 40% x harga
        // inputnya (nama siswa, jurusan, grade) = intent list, get harga, get fee, get absensi
    }
}
