package com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import java.util.ArrayList;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Materia;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;

public class AdaptadorMaterias extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity context;
    LayoutInflater inflater;
    ArrayList<Materia> materias;

    public AdaptadorMaterias(Activity context,ArrayList<Materia> materias) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.materias = materias;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (materias.get(viewType).getAnio().equals(8)) {
            view = inflater.inflate(R.layout.categoria_adap, parent, false);
            return new CategoriaHolder(view);
        } else {
            view = inflater.inflate(R.layout.materia_adap, parent, false);
            return new MateriaHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //((MateriaHolder) holder).nombreMateria.setText(materias.get(position).getNombre());

        if (materias.get(position).getAnio().equals(8)) {
            ((CategoriaHolder) holder).nombreMateria.setText(materias.get(position).getNombre());
        } else {
            ((MateriaHolder) holder).nombreMateria.setText(materias.get(position).getNombre());
        }
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MateriaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreMateria;

        public MateriaHolder(View itemView) {
            super(itemView);

            nombreMateria = (TextView) itemView.findViewById(R.id.nombreMateria);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (materias.get(getAdapterPosition()).getAnio()!=8 & materias.get(getAdapterPosition()).getAnio()!=7) {
                LayoutInflater li = LayoutInflater.from(context);
                View desJugador = li.inflate(R.layout.dialogo_materia, null);

                final TextView nombreMateria = (TextView) desJugador.findViewById(R.id.nombreMateria);
                final TextView anio = (TextView) desJugador.findViewById(R.id.anio);
                final TextView electiva = (TextView) desJugador.findViewById(R.id.electiva);
                final TextView cuatrimestre = (TextView) desJugador.findViewById(R.id.anual);

                nombreMateria.setText(materias.get(getAdapterPosition()).getNombre());
                anio.setText(materias.get(getAdapterPosition()).getAnio() + "º Año");
                if (materias.get(getAdapterPosition()).getIsAnual()) {
                    cuatrimestre.setText("Anual");
                } else {
                    cuatrimestre.setText(materias.get(getAdapterPosition()).getEnQueCuatrimestre().toString() + "º Cuatrimestre");
                }
                LogD("Cuatrimestre", materias.get(getAdapterPosition()).getEnQueCuatrimestre().toString());
                LogD("Id",materias.get(getAdapterPosition()).getId().toString());

                if (materias.get(getAdapterPosition()).getElectiva()) {
                    electiva.setText("Electiva");
                } else {
                    electiva.setVisibility(View.GONE);
                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(desJugador);
                alertDialogBuilder.setCancelable(true);
                //alertDialogBuilder.setTitle(context.getString(R.string.desemJugador));
                alertDialogBuilder.setPositiveButton(context.getString(R.string.aceptar), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                final AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
            }
        }
    }

    class CategoriaHolder extends RecyclerView.ViewHolder {
        TextView nombreMateria;

        public CategoriaHolder(View itemView) {
            super(itemView);

            nombreMateria = (TextView) itemView.findViewById(R.id.nombreMateria);
        }
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("ActionAprobadaParaCursar", nombreMetodo + " " + mensaje);
    }
}
