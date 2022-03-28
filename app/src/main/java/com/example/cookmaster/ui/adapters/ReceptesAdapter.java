package com.example.cookmaster.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cookmaster.R;
import com.example.cookmaster.ui.classes.Receptes;

import java.util.ArrayList;

public class ReceptesAdapter extends RecyclerView.Adapter<ReceptesAdapter.ReceptesViewHolder>{

    ArrayList<Receptes> llistaReceptes;

    public ReceptesAdapter(ArrayList<Receptes> llistaReceptes) {
        this.llistaReceptes=llistaReceptes;
    }



    @Override
    public ReceptesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,null,false);
        return new ReceptesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceptesViewHolder holder, int position) {
        holder.txtNom.setText(llistaReceptes.get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return llistaReceptes.size();
    }

    public class ReceptesViewHolder extends RecyclerView.ViewHolder {
        Button txtNom;

        public ReceptesViewHolder(View itemView) {
            super(itemView);
            txtNom= (Button) itemView.findViewById(R.id.button);
        }
    }
}
