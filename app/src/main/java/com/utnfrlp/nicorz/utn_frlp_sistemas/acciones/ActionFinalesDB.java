package com.utnfrlp.nicorz.utn_frlp_sistemas.acciones;

import android.database.Cursor;

import java.util.ArrayList;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Analitico;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Final;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.CursadasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.FinalesDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.MateriasDB;

public class ActionFinalesDB {

    public static void AgregarFinal (DataBase db,String nombreMateria,String fecha,Integer nota) {
        Integer idMateria = MateriasDB.getId(db.getConection(), nombreMateria);
        FinalesDB.Insert(db.getConection(),idMateria,fecha,nota);
        String[] separated = fecha.split("/");
        if (CursadasDB.PuedoAgregar(db.getConection(),idMateria)) {
            ActionCursadasDB.AgregarCursada(db,nombreMateria,Integer.parseInt(separated[2]));
        }
    }

    public static void EditarFinal (DataBase db,Integer idFinal,String fecha,Integer nota) {
        FinalesDB.Update(db.getConection(),idFinal,nota,fecha);
    }

    public static ArrayList<Final> GetFinales(DataBase db) {
        ArrayList<Final> listaFinales = new ArrayList<>();

        Cursor cursor = FinalesDB.getFinalesCompletos(db.getConection());

        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                int columnaAnioMateria = cursor.getColumnIndex("anioMateria");
                int columnaIdMateria = cursor.getColumnIndex("idMateria");
                int columnaIdFinal = cursor.getColumnIndex("idFinal");
                int columnaNotaFinal = cursor.getColumnIndex("notaFinal");
                int columnaFechaFinal = cursor.getColumnIndex("fechaFinal");

                Final finAl = new Final(cursor.getInt(columnaIdFinal));
                finAl.setNombreMateria(cursor.getString(columnaNombreMateria));
                finAl.setAnioMateria(cursor.getInt(columnaAnioMateria));
                finAl.setFecha(cursor.getString(columnaFechaFinal));
                finAl.setNota(cursor.getInt(columnaNotaFinal));
                finAl.setIdMateria(cursor.getInt(columnaIdMateria));

                listaFinales.add(finAl);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return listaFinales;
    }

    public static Analitico GetAnalitico(DataBase db) {
        Analitico analitFinal = new Analitico();
        Analitico analit1 = new Analitico();
        Analitico analit2 = new Analitico();
        Cursor cursor = FinalesDB.getAnaliticoConAplazos(db.getConection());

        if (cursor.moveToFirst()) {
            do {
                int columnaCantidadFinales = cursor.getColumnIndex("cantidadFinales");
                int columnaPromedio = cursor.getColumnIndex("promedioNota");

                analit1.setCantidadFinales(cursor.getInt(columnaCantidadFinales));
                analit1.setPromedioConAplazos(cursor.getFloat(columnaPromedio));

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        Cursor cursor2 = FinalesDB.getAnaliticoSinAplazos(db.getConection());

        if (cursor2.moveToFirst()) {
            do {
                int columnaCantidadFinales = cursor2.getColumnIndex("cantidadFinales");
                int columnaPromedio = cursor2.getColumnIndex("promedioNota");

                analit2.setCantidadFinales(cursor2.getInt(columnaCantidadFinales));
                analit2.setPromedioSinAplazos(cursor2.getFloat(columnaPromedio));

                cursor2.moveToNext();
            } while (!cursor2.isAfterLast());
        }

        analitFinal.setCantidadFinales(analit1.getCantidadFinales());
        analitFinal.setPromedioConAplazos(analit1.getPromedioConAplazos());
        analitFinal.setPromedioSinAplazos(analit2.getPromedioSinAplazos());

        return analitFinal;
    }
}
