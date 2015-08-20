package com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades.EditarCursada;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import java.util.ArrayList;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Cursada;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionMateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.CursadasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos.DialogoMateria;

public class AdaptadorHistorialCursadasRV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    DataBase db;
    Activity context;
    LayoutInflater inflater;
    ArrayList<Cursada> cursadas;

    public AdaptadorHistorialCursadasRV(Activity context,ArrayList<Cursada> cursadas) {
        this.db = new DataBase(context);
        this.context = context;
        this.cursadas = cursadas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.historial_cursadas_adap, parent, false);
        return new CursadaHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CursadaHolder) holder).nombreMateria.setText(cursadas.get(position).getNombreMateria());
        ((CursadaHolder) holder).fecha.setText(cursadas.get(position).getAnioCursada().toString());
    }

    @Override
    public int getItemCount() {
        return cursadas.size();
    }

    class CursadaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nombreMateria,fecha;

        public CursadaHolder(View itemView) {
            super(itemView);

            nombreMateria = (TextView) itemView.findViewById(R.id.nombreMateria);
            fecha = (TextView) itemView.findViewById(R.id.fechaCursada);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (cursadas.get(getAdapterPosition()).getAnioMateria()!=8) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Cursada de " + cursadas.get(getAdapterPosition()).getNombreMateria());
                alertDialogBuilder.setCancelable(true);
                String[] opciones = {"Editar","Borrar","Detalles Materia"};
                alertDialogBuilder.setSingleChoiceItems(opciones,0,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(context, EditarCursada.class);
                                Bundle bundleOut = new Bundle();
                                bundleOut.putInt("idCursada",cursadas.get(getAdapterPosition()).getIdCursada());
                                bundleOut.putString("nombreMateria", cursadas.get(getAdapterPosition()).getNombreMateria());
                                bundleOut.putInt("fechaCursada",cursadas.get(getAdapterPosition()).getAnioCursada());
                                intent.putExtras(bundleOut);
                                context.startActivityForResult(intent,10);
                                dialog.dismiss();
                                break;
                            case 1:
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setCancelable(true);
                                alertDialogBuilder.setTitle("¿Quiere borrar la cursada?");
                                alertDialogBuilder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        CursadasDB.Delete(db.getConection(), cursadas.get(getAdapterPosition()).getIdCursada());
                                        cursadas.remove(getAdapterPosition());
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
                                DialogoMateria.showDialogoMateria(context, ActionMateriasDB.getMateriaCompleta(db, cursadas.get(getAdapterPosition()).getIdMateria()));
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
