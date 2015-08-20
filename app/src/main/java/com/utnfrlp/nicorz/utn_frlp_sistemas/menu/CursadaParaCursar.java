package com.utnfrlp.nicorz.utn_frlp_sistemas.menu;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import java.util.ArrayList;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Materia;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionCursadaParaCursar;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionMateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores.AdaptadorMaterias;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.AprobadaParaCursarDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.AprobadaParaRendirDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.CursadaParaCursarDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.MateriasDB;

public class CursadaParaCursar extends Fragment {
    RecyclerView listaMateriasRV;
    AdaptadorMaterias adaptadorMateriasRV;
    private DataBase db;
    Spinner elegirAnio,elegirMateria;
    ArrayAdapter<String> adaptadorAnios,adaptadorMaterias;
    ArrayList<Materia> materias;
    String[] datosAnios,datosMaterias;
    ImageView flechaMateria,flechaAnio;
    Bundle saved;
    Boolean restore;
    private ProgressDialog pCargarBaseDialog;
    private LoadingCargarBaseDialog tareaFondoCB;
    private ProgressDialog pActualizarBaseDialog;
    private LoadingActualizarBaseDialog tareaFondoAB;
    SharedPreferences prefs,def;
    //Paso el del no necesito actualizar al actualizar y aumento el no necesito
    private static int NECESITO_ACTUALIZAR_BASE = 3;
    private static int NO_NECESITO_ACTUALIZAR_BASE = 4;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.lista_materias, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        db= new DataBase(this.getActivity());
        prefs = This().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        def = PreferenceManager.getDefaultSharedPreferences(This());
        saved = savedInstanceState;
        LogD("CursadaParaCursar","1");
        CrearEventos();
        LogD("CursadaParaCursar","3");

        if (!prefs.getBoolean("CursadaParaCursar",false)) {
            CrearExplicacionPestania();
        }

        if (saved!=null) {
            restore = false;
        } else {
            restore = true;
        }

        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }

    public Activity This() {
        return this.getActivity();
    }

    private void CrearEventos() {
        elegirAnio = (Spinner) getView().findViewById(R.id.elegirAnio);
        elegirMateria = (Spinner) getView().findViewById(R.id.elegirMateria);
        flechaAnio = (ImageView) getView().findViewById(R.id.flecha1);
        flechaMateria = (ImageView) getView().findViewById(R.id.flecha2);

        listaMateriasRV = (RecyclerView) getView().findViewById(R.id.listaMaterias);

        flechaAnio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elegirAnio.performClick();
            }
        });
        flechaMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elegirMateria.performClick();
            }
        });

        if (!prefs.getBoolean("BaseDeDatosCargada",false)) {
            pCargarBaseDialog = new ProgressDialog(getActivity());
            pCargarBaseDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pCargarBaseDialog.setMessage("Cargando Materias y correlatividades (Plan 2008)... (Por unica vez)");
            pCargarBaseDialog.setCancelable(false);
            pCargarBaseDialog.setMax(100);

            tareaFondoCB = new LoadingCargarBaseDialog();
            tareaFondoCB.execute();

            LogD("CargoBase","PrimeraVez");
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("BaseDeDatosCargada",true);
            edit.apply();
        } else {
            if (prefs.getInt("NecesitoActualizar",NECESITO_ACTUALIZAR_BASE)<=NECESITO_ACTUALIZAR_BASE) {
                pActualizarBaseDialog = new ProgressDialog(getActivity());
                pActualizarBaseDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pActualizarBaseDialog.setMessage("Actualizando Materias y correlatividades");
                pActualizarBaseDialog.setCancelable(false);
                pActualizarBaseDialog.setMax(100);

                tareaFondoAB = new LoadingActualizarBaseDialog();
                tareaFondoAB.execute();

                LogD("CargoBase","Actualizo");
                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt("NecesitoActualizar", NO_NECESITO_ACTUALIZAR_BASE);
                edit.apply();
            } else {
                CrearListaAnios();
            }
        }
    }

    private void CrearExplicacionPestania() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(This());
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle("Cursada para cursar");
        alertDialogBuilder.setMessage("En esta seccion de la aplicacion se pueden ver las cursadas que habilita la cursada de la materia seleccionada");
        alertDialogBuilder.setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNeutralButton("No volver a mostrar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean("CursadaParaCursar",true);
                edit.apply();
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
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
                try {
                    if (saved!=null & !restore) {
                        Integer se = saved.getInt("MateriaElegida");
                        elegirMateria.setSelection(saved.getInt("MateriaElegida"));
                        restore = true;
                        LogD("MateriaElegida",se.toString());
                    }
                    CrearDatos();
                    CrearListView();
                } catch (Exception e) {
                    LogD("onItemSelected",e.toString());
                }
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
        if (def.getString("ordenameMaterias","Ordenar por orden alfabetico").equals("Ordenar por año descendiente")) {
            orden = 2;
        }

        String[] lista = ActionMateriasDB.getNombreMaterias(db,anioSeleccionado,orden);

        return lista;
    }

    private void CrearDatos() {
        String nombreMateria = elegirMateria.getSelectedItem().toString();
        materias = ActionCursadaParaCursar.getMateriasCursadaParaCursar(db,nombreMateria);
        if (materias.size()==0) {
            Materia materia = new Materia("No tiene correlatividad");
            materia.setAnio(7);
            materias.add(materia);
        }
    }

    private void CrearListView() {
        adaptadorMateriasRV = new AdaptadorMaterias(getActivity(),materias);
        listaMateriasRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaMateriasRV.setAdapter(adaptadorMateriasRV);
    }

    private class LoadingCargarBaseDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            ActionMateriasDB.CargarMaterias(db);

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPreExecute() {
            pCargarBaseDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt("NecesitoActualizar",NO_NECESITO_ACTUALIZAR_BASE);
                edit.apply();

                CrearListaAnios();
                pCargarBaseDialog.dismiss();
            }
        }
    }

    private class LoadingActualizarBaseDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            ActionMateriasDB.CargarMaterias(db);

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPreExecute() {
            pActualizarBaseDialog.show();

            MateriasDB.onDrop(db.getConection());
            AprobadaParaCursarDB.onDrop(db.getConection());
            AprobadaParaRendirDB.onDrop(db.getConection());
            CursadaParaCursarDB.onDrop(db.getConection());
            MateriasDB.onCreate(db.getConection());
            AprobadaParaCursarDB.onCreate(db.getConection());
            AprobadaParaRendirDB.onCreate(db.getConection());
            CursadaParaCursarDB.onCreate(db.getConection());
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt("NecesitoActualizar",NO_NECESITO_ACTUALIZAR_BASE);
                edit.apply();

                CrearListaAnios();
                pActualizarBaseDialog.dismiss();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("MateriaElegida",elegirMateria.getSelectedItemPosition());

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        db.close();
        super.onStop();
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("CursadaParaCursar", nombreMetodo + " " + mensaje);
    }
}
