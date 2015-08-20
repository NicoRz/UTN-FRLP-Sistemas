package com.utnfrlp.nicorz.utn_frlp_sistemas.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades.AgregarCursada;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades.AgregarFinal;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades.HistorialCursadas;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades.HistorialFinales;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Analitico;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionFinalesDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos.DialogoAnalitico;
import com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos.Dialogos;
import com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos.iDialogoResult;

public class MiCarrera extends Fragment implements iDialogoResult {
    private DataBase db;
    Button agregarFinales,agregarCursadas,analitico,historialDeFinales,historialDeCursadas;
    TextView nombreAlumno,legajoAlumno;
    SharedPreferences prefs;
    public final static int DIALOGO_NOMBRE = 1;
    public final static int DIALOGO_LEGAJO = 2;
    public final static int NO_AGREGO_CURSADA = 3;
    public final static int AGREGO_CURSADA = 4;
    public final static int NO_AGREGO_FINAL = 5;
    public final static int AGREGO_FINAL = 6;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.micarrera, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        db= new DataBase(this.getActivity());
        prefs = This().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        LogD("CursadaParaCursar", "1");
        CrearEventos();
        LogD("CursadaParaCursar","3");

        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }

    public Activity This() {
        return this.getActivity();
    }

    private void CrearEventos() {
        agregarFinales = (Button) getView().findViewById(R.id.agregarFinales);
        agregarCursadas = (Button) getView().findViewById(R.id.agregarCursadas);
        analitico = (Button) getView().findViewById(R.id.analitico);
        historialDeFinales = (Button) getView().findViewById(R.id.historialDeFinales);
        historialDeCursadas = (Button) getView().findViewById(R.id.historialDeCursadas);
        nombreAlumno = (TextView) getView().findViewById(R.id.nombreAlumno);
        legajoAlumno = (TextView) getView().findViewById(R.id.legajoAlumno);

        if (prefs.getString("NombreAlumno","NO").equals("NO")) {
            nombreAlumno.setText("Presione para cambiar el nombre");
        } else {
            nombreAlumno.setText(prefs.getString("NombreAlumno","NoLoVes"));
        }

        if (prefs.getString("LegajoAlumno","NO").equals("NO")) {
            legajoAlumno.setText("Presione para cambiar el legajo");
        } else {
            legajoAlumno.setText(prefs.getString("LegajoAlumno","NoLoVes"));
        }

        nombreAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogos.showDialogoText(This(), "Nombre", "", DIALOGO_NOMBRE, ThisIDialog());
            }
        });

        legajoAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogos.showDialogoText(This(), "Legajo", "", DIALOGO_LEGAJO, ThisIDialog());
            }
        });

        agregarFinales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(), AgregarFinal.class);
                startActivityForResult(intent, 10);
            }
        });

        agregarCursadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(), AgregarCursada.class);
                startActivityForResult(intent, 10);
            }
        });


        analitico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Analitico analitico = ActionFinalesDB.GetAnalitico(db);
                DialogoAnalitico.showDialogoAnalitico(This(),analitico);
            }
        });

        historialDeFinales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(), HistorialFinales.class);
                startActivityForResult(intent, 10);
            }
        });

        historialDeCursadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(), HistorialCursadas.class);
                startActivityForResult(intent, 10);
            }
        });
    }

    private iDialogoResult ThisIDialog() {
        return this;
    }

    @Override
    public void onDialogoResult(int requestCode, int resultCode, Intent data) {
        String nombre;
        try {
            switch (resultCode) {
                case DIALOGO_NOMBRE:
                    switch (requestCode) {
                        case Dialogos.RESPUESTA_RESULTADO:
                            Dialogos.showDialogoText(This(),"Nombre","", DIALOGO_NOMBRE,ThisIDialog());
                            break;
                        case Dialogos.RESPUESTA_RESULTADO_OK:
                            nombre = Dialogos.getTextoDialogo(data);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("NombreAlumno",nombre);
                            edit.apply();
                            nombreAlumno.setText(nombre);
                            break;
                        case Dialogos.RESPUESTA_RESULTADO_CANCEL:

                            break;
                        default:
                            break;
                    }
                    break;
                case DIALOGO_LEGAJO:
                    switch (requestCode) {
                        case Dialogos.RESPUESTA_RESULTADO:
                            Dialogos.showDialogoText(This(),"Legajo","", DIALOGO_LEGAJO,ThisIDialog());
                            break;
                        case Dialogos.RESPUESTA_RESULTADO_OK:
                            nombre = Dialogos.getTextoDialogo(data);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("LegajoAlumno",nombre);
                            edit.apply();
                            legajoAlumno.setText(nombre);
                            break;
                        case Dialogos.RESPUESTA_RESULTADO_CANCEL:

                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LogD("onDialogoResult",e.toString());
        }
    }

    @Override
    public void onStop() {
        db.close();
        super.onStop();
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("MiCarrera", nombreMetodo + " " + mensaje);
    }
}
