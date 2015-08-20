package com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import java.util.Calendar;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionFinalesDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionMateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.menu.MiCarrera;

public class AgregarFinal extends AppCompatActivity {
    Toolbar mToolbar;
    private DataBase db;
    private static final int MY_DATE_DIALOG_ID = 3;
    private Integer cDay,cMonth,cYear,sDay,sMonth,sYear;
    private Calendar cDate;
    private Button fecha,guardar,cancelar,nota;
    private ImageView flecha1,flecha2;
    private Spinner elegirAnio,elegirMateria;
    String[] datosAnios,datosMaterias;
    ArrayAdapter<String> adaptadorAnios,adaptadorMaterias;
    SharedPreferences def;

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialogo_agregar_final);

            db = new DataBase(this);
            def = PreferenceManager.getDefaultSharedPreferences(This());

            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(mToolbar);

            if (getSupportActionBar()!=null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            setResult(MiCarrera.NO_AGREGO_FINAL);

            CrearEventos();
            LogD("GuardarPartido", "Creo Eventos");
            CrearListaAnios();

        } catch (Exception e) {
            LogD("OnCreate", e.toString());
            Toast.makeText(getBaseContext(), "Error Inesperado - GuardarPartidoActivity", Toast.LENGTH_SHORT).show();
        }
    }

    private void CrearEventos() {

        elegirAnio = (Spinner) findViewById(R.id.elegirAnio);
        elegirMateria = (Spinner) findViewById(R.id.elegirMateria);
        flecha1 = (ImageView) findViewById(R.id.flecha1);
        flecha2 = (ImageView) findViewById(R.id.flecha2);
        cancelar = (Button) findViewById(R.id.cancelar);
        guardar = (Button) findViewById(R.id.guardar);
        fecha = (Button) findViewById(R.id.btnFecha);
        nota = (Button) findViewById(R.id.btnNota);

        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] notas = {"2","4","5","6","7","8","9","10"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(This());
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Nota del final");
                alertDialogBuilder.setSingleChoiceItems(notas, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nota.setText(notas[which]);
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elegirMateria.getSelectedItem().toString().equals("Ya tenes todos los finales!")) {
                    Toast.makeText(This(),"Que queres guardar?",Toast.LENGTH_SHORT).show();
                } else {
                    setResult(MiCarrera.AGREGO_FINAL);
                    ActionFinalesDB.AgregarFinal(db, elegirMateria.getSelectedItem().toString(),fecha.getText().toString(),Integer.parseInt(nota.getText().toString()));
                    finish();
                }
            }
        });

        flecha1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                elegirAnio.performClick();
            }
        });

        flecha2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                elegirMateria.performClick();
            }
        });

        cDate=Calendar.getInstance();
        cDay=cDate.get(Calendar.DAY_OF_MONTH);
        cMonth=cDate.get(Calendar.MONTH);
        cYear=cDate.get(Calendar.YEAR);

        setFechaTxt(cDay,cMonth,cYear);

        fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(MY_DATE_DIALOG_ID);
            }
        });

    }

    protected void CrearListaAnios() {
        AdaptadorAnios();
        adaptadorAnios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elegirAnio.setAdapter(adaptadorAnios);
        elegirAnio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
                try {
                    CrearListaMaterias();
                } catch (Exception e) {
                    LogD("onItemSelected",e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void AdaptadorAnios() {
        datosAnios = crearItemsListaAnios();
        adaptadorAnios = new ArrayAdapter<>(This(),android.R.layout.simple_spinner_item, datosAnios);
    }

    private String[] crearItemsListaAnios() {
        String[] lista = {"Todos","1º Año","2º Año","3º Año","4º Año","5º Año"};

        return lista;
    }

    protected void CrearListaMaterias() {
        AdaptadorMaterias();
        adaptadorMaterias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elegirMateria.setAdapter(adaptadorMaterias);
        elegirMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void AdaptadorMaterias() {
        datosMaterias = crearItemsListaMaterias();
        adaptadorMaterias = new ArrayAdapter<>(This(),android.R.layout.simple_spinner_item, datosMaterias);
    }

    private String[] crearItemsListaMaterias() {
        Integer anioSeleccionado = elegirAnio.getSelectedItemPosition();
        Integer orden = 0;
        if (def.getString("ordenameMaterias","Ordenar por orden alfabetico").equals("Ordenar por año")) {
            orden = 1;
        }

        String[] lista = ActionMateriasDB.getNombreMateriasAgregarFinal(db, anioSeleccionado, orden);

        if (lista.length==0) {
            String[] lista2 = new String[1];
            lista2[0] = "Ya tenes todos los finales!";
            return lista2;
        } else {
            return lista;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case MY_DATE_DIALOG_ID:
                return new DatePickerDialog(this, onDateSet, cYear, cMonth,	cDay);
        }
        return null;
    }

    protected void setFechaTxt(Integer dia, Integer mes, Integer anio) {
        mes++;
        String fechaText = dia.toString() + "/" + mes.toString() + "/" + anio.toString();

        fecha.setText(fechaText);
    }

    private DatePickerDialog.OnDateSetListener onDateSet=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            sYear=year;
            sMonth=monthOfYear;
            sDay=dayOfMonth;
            setFechaTxt(sDay,sMonth,sYear);
        }
    };

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
        L.LogD("AgregarFinal", nombreMetodo + " " + mensaje);
    }
}
