package com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import java.util.Calendar;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionCursadasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;

public class EditarCursada extends AppCompatActivity {
    Toolbar mToolbar;
    private DataBase db;
    private Integer cYear;
    private Button fecha,guardar,cancelar;
    SharedPreferences def;
    Integer idCursada,fechaCursada;
    String nombreMateria;
    TextView nombreMateriaPantalla;
    private Bundle bundle;
    public static final int MODIFICO_CURSADA = 1;

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialogo_editar_cursada);

            db = new DataBase(this);
            def = PreferenceManager.getDefaultSharedPreferences(This());

            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(mToolbar);

            if (getSupportActionBar()!=null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            bundle = getIntent().getExtras();

            idCursada = bundle.getInt("idCursada");
            fechaCursada = bundle.getInt("fechaCursada");
            nombreMateria = bundle.getString("nombreMateria");

            CrearEventos();
            LogD("GuardarPartido", "Creo Eventos");

        } catch (Exception e) {
            LogD("OnCreate", e.toString());
            Toast.makeText(getBaseContext(), "Error Inesperado - GuardarPartidoActivity", Toast.LENGTH_SHORT).show();
        }
    }

    private void CrearEventos() {

        cancelar = (Button) findViewById(R.id.cancelar);
        guardar = (Button) findViewById(R.id.guardar);
        fecha = (Button) findViewById(R.id.btnFecha);
        nombreMateriaPantalla = (TextView) findViewById(R.id.nombreMateria);

        nombreMateriaPantalla.setText(nombreMateria);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(EditarCursada.MODIFICO_CURSADA);
                ActionCursadasDB.EditarCursada(db,idCursada,Integer.parseInt(fecha.getText().toString()));
                finish();
            }
        });

        fecha.setText(fechaCursada.toString());

        Calendar cDate = Calendar.getInstance();
        cYear=cDate.get(Calendar.YEAR);

        fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    LayoutInflater li = LayoutInflater.from(This());
                    View analitico = li.inflate(R.layout.dialogo_anio, null);

                    final EditText seleccionarAnio = (EditText) analitico.findViewById(R.id.anioCursada);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(This());
                    alertDialogBuilder.setView(analitico);
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (seleccionarAnio.getText().length()==0) {
                                Toast.makeText(This(),"Tiene que poner un año",Toast.LENGTH_SHORT).show();
                            } else {
                                Integer anio = Integer.parseInt(seleccionarAnio.getText().toString());
                                fecha.setText(anio.toString());
                                dialog.dismiss();
                            }
                        }
                    });

                    final AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();
                } else {
                    LayoutInflater li = LayoutInflater.from(This());
                    View analitico = li.inflate(R.layout.dialogo_anio_cursada, null);

                    final NumberPicker seleccionarAnio = (NumberPicker) analitico.findViewById(R.id.numberPicker);

                    seleccionarAnio.setMaxValue(cYear);
                    seleccionarAnio.setMinValue(1985);
                    seleccionarAnio.setValue(cYear);
                    seleccionarAnio.setWrapSelectorWheel(false);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(This());
                    alertDialogBuilder.setView(analitico);
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Integer anio = seleccionarAnio.getValue();
                            fecha.setText(anio.toString());
                            dialog.dismiss();
                        }
                    });

                    final AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();
                }
            }
        });

    }

    public Activity This() {
        return this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("EditarCursada", nombreMetodo + " " + mensaje);
    }
}