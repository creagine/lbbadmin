package com.creagine.lbbadmin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.R;

public class SiswaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView siswaNama, siswaJurusan;

    private ItemClickListener itemClickListener;

    public SiswaViewHolder(View itemView) {
        super(itemView);

        siswaNama = itemView.findViewById(R.id.textViewNamaSiswaSpp);
        siswaJurusan = itemView.findViewById(R.id.textViewTutorSiswa);

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
