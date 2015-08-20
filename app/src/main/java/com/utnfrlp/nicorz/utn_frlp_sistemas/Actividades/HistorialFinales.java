package com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Final;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionFinalesDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores.AdaptadorHistorialRV;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;

public class HistorialFinales extends AppCompatActivity {
    AdaptadorHistorialRV adaptadorHistorialRV;
    RecyclerView listaFinalesRV;
    Toolbar mToolbar;
    private DataBase db;
    SharedPreferences def;
    ArrayList<Final> listaFinales;
    Integer ordenarPor;
    public final static int NOTA_ASC = 1;
    public final static int NOTA_DESC = 2;
    public final static int FECHA_ASC = 3;
    public final static int FECHA_DESC = 4;
    public final static int NOMBRE_MATERIA = 5;
    public final static int ANIO_MATERIA = 6;

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.historial);

            db = new DataBase(this);
            def = PreferenceManager.getDefaultSharedPreferences(This());

            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(mToolbar);

            if (getSupportActionBar()!=null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            CrearEventos();
            LogD("GuardarPartido", "Creo Eventos");
            CrearDatos();
            LogD("GuardarPartido", "Creo Datos");
            ordenarPor = FECHA_ASC;
            Ordenar();
            CrearListView();
            LogD("GuardarPartido", "Creo ListView");

        } catch (Exception e) {
            LogD("OnCreate", e.toString());
            Toast.makeText(getBaseContext(), "Error Inesperado - GuardarPartidoActivity", Toast.LENGTH_SHORT).show();
        }
    }

    private void CrearDatos() {
        listaFinales = ActionFinalesDB.GetFinales(db);
        if (listaFinales.size()==0) {
            Final finAl = new Final(1);
            finAl.setNombreMateria("No diste ningun final...");
            finAl.setNota(10);
            finAl.setFecha("26/02/2013");
            finAl.setAnioMateria(8);
            listaFinales.add(finAl);
        }
    }

    private void CrearEventos() {

        listaFinalesRV = (RecyclerView) findViewById(R.id.listaHistorial);

    }

    private void CrearListView() {
        adaptadorHistorialRV = new AdaptadorHistorialRV(this,listaFinales);
        listaFinalesRV.setLayoutManager(new LinearLayoutManager(this));
        listaFinalesRV.setAdapter(adaptadorHistorialRV);
//        LogD("GuardarPartido", "Creo ListView1");
//        AdaptadorHistorialFinales adaptador = new AdaptadorHistorialFinales(This(),listaFinales);
//        LogD("GuardarPartido", "Creo ListView2");
//        historial.setAdapter(adaptador);
//        LogD("GuardarPartido", "Creo ListView3");
//        historial.setFastScrollEnabled(true);
//        LogD("GuardarPartido", "Creo ListView4");
    }

    public Activity This() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_historial_finales, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.notaAscendente:
                if (listaFinales.size() != 1) {
                    ordenarPor = NOTA_ASC;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.notaDescendente:
                if (listaFinales.size() != 1) {
                    ordenarPor = NOTA_DESC;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.fechaAscendente:
                if (listaFinales.size() != 1) {
                    ordenarPor = FECHA_ASC;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.fechaDescendente:
                if (listaFinales.size() != 1) {
                    ordenarPor = FECHA_DESC;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.anioMateria:
                if (listaFinales.size() != 1) {
                    ordenarPor = ANIO_MATERIA;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.nombreMateria:
                if (listaFinales.size() != 1) {
                    ordenarPor = NOMBRE_MATERIA;
                    Ordenar();
                    CrearListView();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Ordenar() {
        switch (ordenarPor) {
            case FECHA_ASC:
                Collections.sort(listaFinales, new Comparator<Final>() {

                    @Override
                    public int compare(Final f1, Final f2) {

                        return f1.getFechaFinal().compareTo(f2.getFechaFinal());
                    }
                });
                break;
            case FECHA_DESC:
                Collections.sort(listaFinales, new Comparator<Final>() {

                    @Override
                    public int compare(Final p1, Final p2) {

                        return p2.getFechaFinal().compareTo(p1.getFechaFinal());
                    }
                });
                break;
            case NOTA_ASC:
                Collections.sort(listaFinales, new Comparator<Final>() {

                    @Override
                    public int compare(Final p1, Final p2) {

                        return p1.getNota().compareTo(p2.getNota());
                    }
                });
                break;
            case NOTA_DESC:
                Collections.sort(listaFinales, new Comparator<Final>() {

                    @Override
                    public int compare(Final p1, Final p2) {

                        return p2.getNota().compareTo(p1.getNota());
                    }
                });
                break;
            case NOMBRE_MATERIA:
                Collections.sort(listaFinales, new Comparator<Final>() {

                    @Override
                    public int compare(Final p1, Final p2) {

                        return p1.getNombreMateria().compareTo(p2.getNombreMateria());
                    }
                });
                break;
            case ANIO_MATERIA:
                Collections.sort(listaFinales, new Comparator<Final>() {

                    @Override
                    public int compare(Final p1, Final p2) {
                        if (p1.getAnioMateria().equals(p2.getAnioMateria())) {
                            return p1.getNombreMateria().compareTo(p2.getNombreMateria());
                        } else {
                            return p1.getAnioMateria().compareTo(p2.getAnioMateria());
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case EditarFinal.MODIFICO_FINAL:
                CrearDatos();
                CrearListView();
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("HistorialFinales", nombreMetodo + " " + mensaje);
    }
}
