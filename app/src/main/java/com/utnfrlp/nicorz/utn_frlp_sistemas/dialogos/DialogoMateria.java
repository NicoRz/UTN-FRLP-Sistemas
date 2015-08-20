package com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Materia;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;

public class DialogoMateria {

    @SuppressLint("InflateParams")
    public static  void showDialogoMateria(final Activity miActivity, Materia materia) {
        try {
            LayoutInflater li = LayoutInflater.from(miActivity);
            View desJugador = li.inflate(R.layout.dialogo_materia, null);

            final TextView nombreMateria = (TextView) desJugador.findViewById(R.id.nombreMateria);
            final TextView anio = (TextView) desJugador.findViewById(R.id.anio);
            final TextView electiva = (TextView) desJugador.findViewById(R.id.electiva);
            final TextView cuatrimestre = (TextView) desJugador.findViewById(R.id.anual);

            nombreMateria.setText(materia.getNombre());
            anio.setText(materia.getAnio() + "º Año");
            if (materia.getIsAnual()) {
                cuatrimestre.setText("Anual");
            } else {
                cuatrimestre.setText(materia.getEnQueCuatrimestre().toString() + "º Cuatrimestre");
            }
            LogD("Cuatrimestre",materia.getEnQueCuatrimestre().toString());
            LogD("Id",materia.getId().toString());

            if (materia.getElectiva()) {
                electiva.setText("Electiva");
            } else {
                electiva.setVisibility(View.GONE);
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(miActivity);
            alertDialogBuilder.setView(desJugador);
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.setPositiveButton(miActivity.getString(R.string.aceptar), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });


            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();

        } catch (Exception e) {
            LogD("showDialogoMateria", e.toString());
        }
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("DialogoMateria", nombreMetodo + " " + mensaje);
    }
}
