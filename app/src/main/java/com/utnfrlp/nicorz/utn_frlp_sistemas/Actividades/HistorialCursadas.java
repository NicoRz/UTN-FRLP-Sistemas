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
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Cursada;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionCursadasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores.AdaptadorHistorialCursadasRV;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;

public class HistorialCursadas extends AppCompatActivity {
    AdaptadorHistorialCursadasRV adaptadorHistorialCursadasRV;
    RecyclerView listaCursadasRV;
    Toolbar mToolbar;
    private DataBase db;
    SharedPreferences def;
    ArrayList<Cursada> listaCursadas;
    Integer ordenarPor;
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
        listaCursadas = ActionCursadasDB.GetCursadas(db);
        if (listaCursadas.size()==0) {
            Cursada cursada = new Cursada (1);
            cursada.setNombreMateria("No cursaste nada...");
            cursada.setAnioCursada(2012);
            cursada.setAnioMateria(8);
            listaCursadas.add(cursada);
        }
    }

    private void CrearEventos() {

        listaCursadasRV = (RecyclerView) findViewById(R.id.listaHistorial);

    }

    private void CrearListView() {
        adaptadorHistorialCursadasRV = new AdaptadorHistorialCursadasRV(this,listaCursadas);
        listaCursadasRV.setLayoutManager(new LinearLayoutManager(this));
        listaCursadasRV.setAdapter(adaptadorHistorialCursadasRV);
//        LogD("GuardarPartido", "Creo ListView1");
//        AdaptadorHistorialCursadas adaptador = new AdaptadorHistorialCursadas(This(),listaCursadas);
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
        getMenuInflater().inflate(R.menu.menu_historial_cursadas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.fechaAscendente:
                if (listaCursadas.size() != 1) {
                    ordenarPor = FECHA_ASC;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.fechaDescendente:
                if (listaCursadas.size() != 1) {
                    ordenarPor = FECHA_DESC;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.anioMateria:
                if (listaCursadas.size() != 1) {
                    ordenarPor = ANIO_MATERIA;
                    Ordenar();
                    CrearListView();
                }
                return true;
            case R.id.nombreMateria:
                if (listaCursadas.size() != 1) {
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
                Collections.sort(listaCursadas, new Comparator<Cursada>() {

                    @Override
                    public int compare(Cursada f1, Cursada f2) {
                        return f1.getAnioCursada().compareTo(f2.getAnioCursada());
                    }
                });
                break;
            case FECHA_DESC:
                Collections.sort(listaCursadas, new Comparator<Cursada>() {

                    @Override
                    public int compare(Cursada f1, Cursada f2) {
                        return f2.getAnioCursada().compareTo(f1.getAnioCursada());
                    }
                });
                break;
            case NOMBRE_MATERIA:
                Collections.sort(listaCursadas, new Comparator<Cursada>() {

                    @Override
                    public int compare(Cursada p1, Cursada p2) {

                        return p1.getNombreMateria().compareTo(p2.getNombreMateria());
                    }
                });
                break;
            case ANIO_MATERIA:
                Collections.sort(listaCursadas, new Comparator<Cursada>() {

                    @Override
                    public int compare(Cursada p1, Cursada p2) {
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
            case EditarCursada.MODIFICO_CURSADA:
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
        L.LogD("HistorialCursadas", nombreMetodo + " " + mensaje);
    }
}
