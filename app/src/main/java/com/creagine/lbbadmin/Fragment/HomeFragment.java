package com.creagine.lbbadmin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.creagine.lbbadmin.JadwalActivity;
import com.creagine.lbbadmin.MainActivity;
import com.creagine.lbbadmin.R;
import com.creagine.lbbadmin.SiswaActivity;
import com.creagine.lbbadmin.TutorActivity;

public class HomeFragment extends Fragment {

    Button btnJadwal, btnSiswa, btnTutor;
    CardView cardJadwal,cardSiswa,cardTutor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        btnJadwal = view.findViewById(R.id.buttonJadwal);
//        btnSiswa = view.findViewById(R.id.buttonSiswa);
//        btnTutor = view.findViewById(R.id.buttonTutor);

        cardJadwal = view.findViewById(R.id.cardJadwal);
        cardSiswa = view.findViewById(R.id.cardSiswa);
        cardTutor = view.findViewById(R.id.cardTutor);

        cardJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JadwalActivity.class);
                startActivity(intent);
            }
        });

        cardSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SiswaActivity.class);
                startActivity(intent);
            }
        });

        cardTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TutorActivity.class);
                startActivity(intent);
            }
        });


//        btnJadwal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), JadwalActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        btnSiswa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), SiswaActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        btnTutor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), TutorActivity.class);
//                startActivity(intent);
//
//            }
//        });

        return view;
    }
}