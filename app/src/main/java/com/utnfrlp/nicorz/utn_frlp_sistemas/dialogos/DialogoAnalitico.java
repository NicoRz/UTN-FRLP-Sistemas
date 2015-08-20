package com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import java.text.DecimalFormat;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Analitico;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;

public class DialogoAnalitico {

    @SuppressLint("InflateParams")
    public static  void showDialogoAnalitico(final Activity miActivity, Analitico analit) {
        try {
            LayoutInflater li = LayoutInflater.from(miActivity);
            View analitico = li.inflate(R.layout.dialogo_analitico, null);

            TextView cantFinales = (TextView) analitico.findViewById(R.id.cantidadFinales);
            TextView promSin = (TextView) analitico.findViewById(R.id.promedioSinAplazos);
            TextView promCon = (TextView) analitico.findViewById(R.id.promedioConAplazos);

            DecimalFormat df = new DecimalFormat("0.00");

            LogD("DialogoAnalitico","1");
            cantFinales.setText(analit.getCantidadFinales().toString());
            LogD("DialogoAnalitico","2");
            promSin.setText(df.format(analit.getPromedioSinAplazos()));
            LogD("DialogoAnalitico","3");
            promCon.setText(df.format(analit.getPromedioConAplazos()));
            LogD("DialogoAnalitico","4");

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(miActivity);
            alertDialogBuilder.setView(analitico);
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();

        } catch (Exception e) {
            LogD("showDialogoAnalitico", e.toString());
        }
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("DialogoAnalitico", nombreMetodo + " " + mensaje);
    }
}
