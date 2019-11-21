package com.creagine.lbbadmin.ViewHolder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creagine.lbbadmin.Interface.ItemClickListener;
import com.creagine.lbbadmin.R;

public class TutorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNamaTutor, txtJurusanTutor;

    private ItemClickListener itemClickListener;

    public TutorViewHolder(View itemView) {
        super(itemView);

        //edit sesuaikan service_item
        txtNamaTutor = itemView.findViewById(R.id.textViewNamaTutor);
        txtJurusanTutor = itemView.findViewById(R.id.textViewJurusanTutor);

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
