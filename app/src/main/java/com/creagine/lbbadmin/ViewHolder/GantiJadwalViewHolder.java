package com.creagine.lbbadmin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.R;

public class GantiJadwalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView siswa, tutor, tanggal, jam, hari, ruang, status;

    private ItemClickListener itemClickListener;

    public GantiJadwalViewHolder(View itemView) {
        super(itemView);

        siswa = itemView.findViewById(R.id.textViewNamaSiswaGantiJadwal);
        tutor = itemView.findViewById(R.id.textViewTutorGantiJadwal);
        status = itemView.findViewById(R.id.textViewStatusGantiJadwal);

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
