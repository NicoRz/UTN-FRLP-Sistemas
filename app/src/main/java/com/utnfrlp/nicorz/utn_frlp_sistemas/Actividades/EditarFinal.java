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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import java.util.Calendar;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionFinalesDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;

public class EditarFinal extends AppCompatActivity {
    Toolbar mToolbar;
    private DataBase db;
    private static final int MY_DATE_DIALOG_ID = 3;
    private Integer cYear,cDay,cMonth,sDay,sMonth,sYear;
    private Button fecha,guardar,cancelar,nota;
    SharedPreferences def;
    Calendar cDate;
    Integer idFinal,notaFinal;
    String fechaFinal,nombreMateria;
    TextView nombreMateriaPantalla;
    private Bundle bundle;
    public static final int MODIFICO_FINAL = 1;

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialogo_editar_final);

            db = new DataBase(this);
            def = PreferenceManager.getDefaultSharedPreferences(This());

            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(mToolbar);

            if (getSupportActionBar()!=null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            bundle = getIntent().getExtras();

            idFinal = bundle.getInt("idFinal");
            notaFinal = bundle.getInt("notaFinal");
            fechaFinal = bundle.getString("fechaFinal");
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
        nota = (Button) findViewById(R.id.btnNota);
        nombreMateriaPantalla = (TextView) findViewById(R.id.nombreMateria);

        nombreMateriaPantalla.setText(nombreMateria);

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

        nota.setText(notaFinal.toString());

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(EditarFinal.MODIFICO_FINAL);
                ActionFinalesDB.EditarFinal(db,idFinal,fecha.getText().toString(),Integer.parseInt(nota.getText().toString()));
                finish();
            }
        });

        fecha.setText(fechaFinal);

        cDate= Calendar.getInstance();
        cDay=cDate.get(Calendar.DAY_OF_MONTH);
        cMonth=cDate.get(Calendar.MONTH);
        cYear=cDate.get(Calendar.YEAR);

        fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(MY_DATE_DIALOG_ID);
            }
        });

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
        L.LogD("EditarFinal", nombreMetodo + " " + mensaje);
    }

}