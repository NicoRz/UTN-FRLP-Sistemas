package com.utnfrlp.nicorz.utn_frlp_sistemas.menu;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import java.util.ArrayList;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Materia;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionAprobadaParaRendir;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionMateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores.AdaptadorMaterias;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;

public class QueNecesitoParaFinal extends Fragment{
    RecyclerView listaMateriasRV;
    AdaptadorMaterias adaptadorMateriasRV;
    private DataBase db;
    Spinner elegirAnio,elegirMateria;
    ArrayAdapter<String> adaptadorAnios,adaptadorMaterias;
    ArrayList<Materia> materias;
    String[] datosAnios,datosMaterias;
    ImageView flechaMateria,flechaAnio;
    TextView titulo3,titulo2;
    Bundle saved;
    Boolean restore;
    SharedPreferences prefs,def;

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
        LogD("CursadaParaCursar", "1");
        CrearEventos();
        LogD("CursadaParaCursar","3");

        if (!prefs.getBoolean("QueNecesitoParaFinal",false)) {
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
        listaMateriasRV = (RecyclerView) getView().findViewById(R.id.listaMaterias);
        flechaAnio = (ImageView) getView().findViewById(R.id.flecha1);
        flechaMateria = (ImageView) getView().findViewById(R.id.flecha2);
        titulo3 = (TextView) getView().findViewById(R.id.titulo3);
        titulo2 = (TextView) getView().findViewById(R.id.titulo2);

        titulo3.setText("Necesito finales de:");
        titulo2.setText("Final de:");

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

        CrearListaAnios();
    }

    private void CrearExplicacionPestania() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(This());
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle("Que necesito para final");
        alertDialogBuilder.setMessage("En esta seccion de la aplicacion se pueden ver los finales que se necesitan para dar el final de la materia seleccionada");
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
                edit.putBoolean("QueNecesitoParaFinal", true);
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
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                try {
                    CrearListaMaterias();
                } catch (Exception e) {
                    LogD("onItemSelected", e.toString());
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
        Integer orden = 1;
        if (def.getString("ordenameMaterias","Ordenar por orden alfabetico").equals("Ordenar por orden alfabetico")) {
            orden = 0;
        }
        if (def.getString("ordenameMaterias","Ordenar por orden alfabetico").equals("Ordenar por año descendiente")) {
            orden = 2;
        }
        String[] lista = ActionMateriasDB.getNombreMaterias(db, anioSeleccionado,orden);

        return lista;
    }

    private void CrearDatos() {
        String nombreMateria = elegirMateria.getSelectedItem().toString();
        materias = ActionAprobadaParaRendir.getQueNecesitoParaFinal(db, nombreMateria);
        LogD("Hasta Aca","1");

        if (materias.isEmpty()) {
            Materia materia = new Materia("No necesita ningun final");
            materia.setAnio(7);
            materias.add(materia);
        }

    }

    private void CrearListView() {
        adaptadorMateriasRV = new AdaptadorMaterias(getActivity(),materias);
        listaMateriasRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaMateriasRV.setAdapter(adaptadorMateriasRV);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("MateriaElegida", elegirMateria.getSelectedItemPosition());

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        db.close();
        super.onStop();
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("QueNecesitoParaFinal", nombreMetodo + " " + mensaje);
    }
}
