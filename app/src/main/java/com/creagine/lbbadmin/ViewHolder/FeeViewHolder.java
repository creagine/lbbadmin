package com.creagine.lbbadmin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.R;

public class FeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView no, namaTutor, namaSiswa, bulan, tahun, jurusan, grade, tarif, fee, presensi,
            tglSpp;

    private ItemClickListener itemClickListener;

    public FeeViewHolder(View itemView) {
        super(itemView);

        no = itemView.findViewById(R.id.textViewNoFeeItem);
        namaTutor = itemView.findViewById(R.id.textViewNamaTutorItem);
        namaSiswa = itemView.findViewById(R.id.textViewNamaSiswaItem);
        bulan = itemView.findViewById(R.id.textViewBulanItem);
        tahun = itemView.findViewById(R.id.textViewTahunItem);
        jurusan = itemView.findViewById(R.id.textViewJurusanItem);
        grade = itemView.findViewById(R.id.textViewGradeItem);
        tarif = itemView.findViewById(R.id.textViewTarifItem);
        fee = itemView.findViewById(R.id.textViewFeeItem);
        presensi = itemView.findViewById(R.id.textViewPresensiItem);
        tglSpp = itemView.findViewById(R.id.textViewTglSppItem);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

}
