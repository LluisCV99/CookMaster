package com.example.cookmaster.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.BundleCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookmaster.R;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.ReceptaFragment;

import java.util.ArrayList;

public class ReceptesAdapter extends RecyclerView.Adapter<ReceptesAdapter.ReceptesViewHolder>{

    ArrayList<Receptes> llistaReceptes;
    String dia;
    boolean boolRecepta;

    public ReceptesAdapter(ArrayList<Receptes> llistaReceptes, boolean recepta, String dia) {
        this.llistaReceptes = llistaReceptes;
        this.dia = dia;
        this.boolRecepta = recepta;
    }



    @NonNull
    @Override
    public ReceptesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ReceptesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceptesViewHolder holder, int position) {
        holder.txtNom.setText(llistaReceptes.get(position).getNom());
        holder.recepta = llistaReceptes.get(position);
        holder.boolRecepta = this.boolRecepta;
        if(!boolRecepta){
            holder.dia = this.dia;
        }

    }

    @Override
    public int getItemCount() {
        return llistaReceptes.size();
    }

    public static class ReceptesViewHolder extends RecyclerView.ViewHolder {
        Button txtNom;
        String dia;
        boolean boolRecepta;
        Receptes recepta;

        public ReceptesViewHolder(View itemView) {
            super(itemView);
            txtNom = (Button) itemView.findViewById(R.id.button);
            itemView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idRecepta", recepta.getId());
                    if (boolRecepta) {
                        Navigation.findNavController(view).navigate(R.id.nav_recepta, bundle);
                    }else{
                        bundle.putString("dia", dia);
                        Navigation.findNavController(view).navigate(R.id.nav_home, bundle);
                    }
                }
            });

        }
    }
}
