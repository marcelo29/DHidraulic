package br.com.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.android.dhidraulic.BanheiroActivity;
import br.com.android.dhidraulic.R;

public class CasaAdapter extends RecyclerView.Adapter<CasaAdapter.CasaViewHolder> {

    ArrayList<Integer> fotos = new ArrayList<>();
    ArrayList<String> titulos = new ArrayList<>();
    public static Context ctx;

    public CasaAdapter(Context ctx) {
        this.ctx = ctx;
        fotos.add(R.drawable.area);
        fotos.add(R.drawable.banheiro);
        fotos.add(R.drawable.cozinha);
        titulos.add("Área de serviço");
        titulos.add("Banheiro");
        titulos.add("Cozinha");
    }

    @Override
    public CasaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        CasaViewHolder cvh = new CasaViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CasaViewHolder casaViewHolder, int i) {
        casaViewHolder.txtCompartimento.setText(titulos.get(i));
        casaViewHolder.ivCompartimento.setImageResource(fotos.get(i));
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class CasaViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtCompartimento;
        ImageView ivCompartimento;

        public CasaViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            txtCompartimento = (TextView) itemView.findViewById(R.id.txtCompartimento);
            ivCompartimento = (ImageView) itemView.findViewById(R.id.ivCompartimento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(txtCompartimento.getText().equals("Banheiro")) {
                        Intent intent = new Intent(ctx, BanheiroActivity.class);
                        ctx.startActivity(intent);
                    } else if (txtCompartimento.getText().equals("Cozinha")){
                        Log.i("appHidraulic", "Cozinha ok");
                    } else {
                        Log.i("appHidraulic", "Area de servico ok");
                    }
                }
            });
        }
    }
}