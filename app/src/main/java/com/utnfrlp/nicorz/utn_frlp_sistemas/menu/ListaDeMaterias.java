package com.utnfrlp.nicorz.utn_frlp_sistemas.menu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionMateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.adaptadores.AdaptadorMaterias;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;

public class ListaDeMaterias extends Fragment {
    private DataBase db;
    RecyclerView listaMateriasRV;
    AdaptadorMaterias adaptadorMaterias;
    Spinner elegirAnio;
    ArrayAdapter<String> adaptadorAnios;
    ArrayList<Materia> materias;
    String[] datosAnios;
    ImageView flechaAnio;
    TextView titulo3,titulo2;
    SharedPreferences prefs;
    ViewGroup tablaMaterias;

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

        LogD("ListaMaterias", "1");
        CrearEventos();
        LogD("ListaMaterias","3");

        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }

    public Activity This() {
        return this.getActivity();
    }

    private void CrearEventos() {
        elegirAnio = (Spinner) getView().findViewById(R.id.elegirAnio);
        flechaAnio = (ImageView) getView().findViewById(R.id.flecha1);
        titulo2 = (TextView) getView().findViewById(R.id.titulo2);
        titulo3 = (TextView) getView().findViewById(R.id.titulo3);
        tablaMaterias = (ViewGroup) getView().findViewById(R.id.ocultarSeleccionarMateria);
        listaMateriasRV = (RecyclerView) getView().findViewById(R.id.listaMaterias);

        //listaMaterias = (ListView) getView().findViewById(R.id.listaMaterias);

        tablaMaterias.setVisibility(View.GONE);
        titulo2.setVisibility(View.GONE);

        flechaAnio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elegirAnio.performClick();
            }
        });

        CrearListaAnios();
    }

    protected void CrearListaAnios() {
        AdaptadorAnios();
        adaptadorAnios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elegirAnio.setAdapter(adaptadorAnios);
        elegirAnio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                try {
                    CrearDatos();
                    CrearListView();
                    Integer posicion = position;
                    if (position > 0) {
                        titulo3.setText("Materias de " + posicion.toString() + "º año:");
                    } else {
                        titulo3.setText("Todas las materias");
                    }
                } catch (Exception e) {
                    LogD("CrearLista", e.toString());
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

    private void CrearDatos() {
        materias = ActionMateriasDB.getTodasMaterias(db,elegirAnio.getSelectedItemPosition());
        LogD("Hasta Aca","1");

        if (materias.isEmpty()) {
            Materia materia = new Materia("No deberias ver esto");
            materia.setAnio(8);
            materias.add(materia);
        }

    }

    private void CrearListView() {
        adaptadorMaterias = new AdaptadorMaterias(getActivity(),materias);
        listaMateriasRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaMateriasRV.setAdapter(adaptadorMaterias);

//        AdaptadorCursadaParaCursar adaptador = new AdaptadorCursadaParaCursar(getActivity(),materias);
//        listaMaterias.setAdapter(adaptador);
//        listaMaterias.setFastScrollEnabled(true);
    }

    @Override
    public void onStop() {
        db.close();
        super.onStop();
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("ListaMaterias", nombreMetodo + " " + mensaje);
    }
}
