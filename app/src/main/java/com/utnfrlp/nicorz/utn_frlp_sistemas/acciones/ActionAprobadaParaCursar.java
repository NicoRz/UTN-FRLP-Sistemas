package com.utnfrlp.nicorz.utn_frlp_sistemas.acciones;

import android.database.Cursor;
import java.util.ArrayList;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Materia;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.AprobadaParaCursarDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.MateriasDB;

public class ActionAprobadaParaCursar {
    public static ArrayList<Materia> getQueNecesitoParaCursar(DataBase db,String nombreMateria) {
        ArrayList<Materia> listaMaterias = new ArrayList<>();
        Integer idMateriaTrabada = MateriasDB.getId(db.getConection(), nombreMateria);
        LogD("IdMateria", "Nombre:" + nombreMateria + " id:" + idMateriaTrabada.toString());

        Cursor cursor = AprobadaParaCursarDB.getQueNecesitoParaCursar(db.getConection(), idMateriaTrabada);
        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                int columnaAnioMateria = cursor.getColumnIndex("anioMateria");
                int columnaIdMateria = cursor.getColumnIndex("idMateria");
                int columnaDuracion = cursor.getColumnIndex("esAnual");
                int columnaCuatrimestre = cursor.getColumnIndex("queCuatrimestre");
                int columnaElectiva = cursor.getColumnIndex("electiva");

                Materia materia = new Materia(cursor.getString(columnaNombreMateria));
                materia.setAnio(cursor.getInt(columnaAnioMateria));
                materia.setId(cursor.getInt(columnaIdMateria));
                if (cursor.getInt(columnaDuracion)==1) {
                    materia.setIsAnual(false);
                } else {
                    materia.setIsAnual(true);
                }
                materia.setEnQueCuatrimestre(cursor.getInt(columnaCuatrimestre));
                if (cursor.getInt(columnaElectiva)==1) {
                    materia.setElectiva(true);
                } else {
                    materia.setElectiva(false);
                }

                listaMaterias.add(materia);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return listaMaterias;
    }

    public static ArrayList<Materia> getFinalParaCursar(DataBase db,String nombreMateria) {
        ArrayList<Materia> listaMaterias = new ArrayList<>();
        Integer idMateria = MateriasDB.getId(db.getConection(), nombreMateria);
        LogD("IdMateria", "Nombre:" + nombreMateria + " id:" + idMateria.toString());

        Cursor cursor = AprobadaParaCursarDB.getFinalParaCursar(db.getConection(), idMateria);
        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                int columnaAnioMateria = cursor.getColumnIndex("anioMateria");
                int columnaIdMateria = cursor.getColumnIndex("idMateria");
                int columnaDuracion = cursor.getColumnIndex("esAnual");
                int columnaCuatrimestre = cursor.getColumnIndex("queCuatrimestre");
                int columnaElectiva = cursor.getColumnIndex("electiva");

                Materia materia = new Materia(cursor.getString(columnaNombreMateria));
                materia.setAnio(cursor.getInt(columnaAnioMateria));
                materia.setId(cursor.getInt(columnaIdMateria));
                if (cursor.getInt(columnaDuracion)==1) {
                    materia.setIsAnual(false);
                } else {
                    materia.setIsAnual(true);
                }
                materia.setEnQueCuatrimestre(cursor.getInt(columnaCuatrimestre));
                if (cursor.getInt(columnaElectiva)==1) {
                    materia.setElectiva(true);
                } else {
                    materia.setElectiva(false);
                }

                listaMaterias.add(materia);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return listaMaterias;
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("ActionAprobadaParaCursar",nombreMetodo + " " + mensaje);
    }
}
