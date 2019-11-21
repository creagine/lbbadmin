package com.creagine.lbbadmin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.R;

public class TagihanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView noTagihan, namaSiswa, deskripsiTagihan, tanggalTagihan, nominalTagihan,
            statusTagihan;

    private ItemClickListener itemClickListener;

    public TagihanViewHolder(View itemView) {
        super(itemView);

        noTagihan = itemView.findViewById(R.id.textViewNoTagihanItem);
        namaSiswa = itemView.findViewById(R.id.textViewNamaSiswaTagihanItem);
        deskripsiTagihan = itemView.findViewById(R.id.textViewDeskripsiTagihanItem);
        tanggalTagihan = itemView.findViewById(R.id.textViewTanggalTagihanItem);
        nominalTagihan = itemView.findViewById(R.id.textViewNominalTagihanItem);
        statusTagihan = itemView.findViewById(R.id.textViewStatusTagihanItem);

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
