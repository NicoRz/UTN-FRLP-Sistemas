package com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades.EditarFinal;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import java.util.ArrayList;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Final;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionMateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.FinalesDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos.DialogoMateria;

public class AdaptadorHistorialRV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    DataBase db;
    Activity context;
    LayoutInflater inflater;
    ArrayList<Final> finales;

    public AdaptadorHistorialRV(Activity context,ArrayList<Final> finales) {
        this.db = new DataBase(context);
        this.context = context;
        this.finales = finales;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.historial_finales_adap, parent, false);
        return new FinalHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FinalHolder) holder).nombreMateria.setText(finales.get(position).getNombreMateria());
        ((FinalHolder) holder).fecha.setText(finales.get(position).getFecha());
        ((FinalHolder) holder).nota.setText(finales.get(position).getNota().toString());
    }

    @Override
    public int getItemCount() {
        return finales.size();
    }

    class FinalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreMateria,fecha,nota;

        public FinalHolder(View itemView) {
            super(itemView);

            nombreMateria = (TextView) itemView.findViewById(R.id.nombreMateria);
            fecha = (TextView) itemView.findViewById(R.id.fechaFinal);
            nota = (TextView) itemView.findViewById(R.id.notaMateria);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (finales.get(getAdapterPosition()).getAnioMateria()!=8) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Final de " + finales.get(getAdapterPosition()).getNombreMateria());
                alertDialogBuilder.setCancelable(true);
                String[] opciones = {"Editar","Borrar","Detalles Materia"};
                alertDialogBuilder.setSingleChoiceItems(opciones,0,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(context, EditarFinal.class);
                                Bundle bundleOut = new Bundle();
                                bundleOut.putInt("idFinal",finales.get(getAdapterPosition()).getIdFinal());
                                bundleOut.putInt("notaFinal",finales.get(getAdapterPosition()).getNota());
                                bundleOut.putString("nombreMateria", finales.get(getAdapterPosition()).getNombreMateria());
                                bundleOut.putString("fechaFinal", finales.get(getAdapterPosition()).getFecha());
                                intent.putExtras(bundleOut);
                                context.startActivityForResult(intent,10);
                                dialog.dismiss();
                                break;
                            case 1:
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setCancelable(true);
                                alertDialogBuilder.setTitle("¿Quiere borrar el final?");
                                alertDialogBuilder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FinalesDB.Delete(db.getConection(), finales.get(getAdapterPosition()).getIdFinal());
                                        finales.remove(getAdapterPosition());
                                        notifyItemRemoved(getAdapterPosition());
                                        dialog.dismiss();
                                    }
                                });
                                alertDialogBuilder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });


                                final AlertDialog alertDialog = alertDialogBuilder.create();

                                alertDialog.show();
                                dialog.dismiss();
                                break;
                            case 2:
                                DialogoMateria.showDialogoMateria(context, ActionMateriasDB.getMateriaCompleta(db, finales.get(getAdapterPosition()).getIdMateria()));
                                dialog.dismiss();
                                break;
                            default:
                                break;

                        }
                    }
                });
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
}
