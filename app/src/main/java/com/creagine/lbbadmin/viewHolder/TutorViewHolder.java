package com.creagine.lbbadmin.viewHolder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creagine.lbbadmin.R;

public class TutorViewHolder extends RecyclerView.ViewHolder{

    public ConstraintLayout root;
    public TextView txtNamaTutor;

    public TutorViewHolder(@NonNull View itemView) {
        super(itemView);

        root = itemView.findViewById(R.id.list_tutor);
        txtNamaTutor = itemView.findViewById(R.id.txtNamaTutor);
    }
}
