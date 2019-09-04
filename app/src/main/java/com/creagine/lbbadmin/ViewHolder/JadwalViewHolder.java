package com.creagine.lbbadmin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.R;

public class JadwalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView siswaNama, siswaJurusan, tutorNama;

    private ItemClickListener itemClickListener;

    public JadwalViewHolder(View itemView) {
        super(itemView);

        siswaNama = itemView.findViewById(R.id.textViewNamaJadwal);
        siswaJurusan = itemView.findViewById(R.id.textViewJurusanJadwal);
        tutorNama = itemView.findViewById(R.id.textViewTutorJadwal);

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
