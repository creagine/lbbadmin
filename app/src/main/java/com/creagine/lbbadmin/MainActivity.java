package com.creagine.lbbadmin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.creagine.lbbadmin.Fragment.AccountFragment;
import com.creagine.lbbadmin.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO tambahin auth
        //TODO tambahin tombol ke halaman notif di pojok kanan atas
        //TODO munculkan ganti jadwal dimana?

        // kita set default nya Home Fragment
        loadFragment(new HomeFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
//            case R.id.search_menu:
//                fragment = new SearchFragment();
//                break;
//            case R.id.favorite_menu:
//                fragment = new FavoriteFragment();
//                break;
            case R.id.account_menu:
                fragment = new AccountFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Keluar dari aplikasi")
                .setMessage("Anda yakin akan keluar dari aplikasi?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }

}
