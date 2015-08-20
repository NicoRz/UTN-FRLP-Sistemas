package com.utnfrlp.nicorz.utn_frlp_sistemas.acciones;

import android.database.Cursor;
import java.util.ArrayList;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Cursada;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.CursadasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.MateriasDB;

public class ActionCursadasDB {

    public static void AgregarCursada (DataBase db,String nombreMateria,Integer anio) {
        Integer idMateria = MateriasDB.getId(db.getConection(),nombreMateria);
        CursadasDB.Insert(db.getConection(),idMateria,anio);
    }

    public static void EditarCursada (DataBase db,Integer idCursada,Integer anio) {
        CursadasDB.Update(db.getConection(),idCursada,anio);
    }

    public static ArrayList<Cursada> GetCursadas(DataBase db) {
        ArrayList<Cursada> listaCursadas = new ArrayList<>();

        Cursor cursor = CursadasDB.getCursadasCompletos(db.getConection());

        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                int columnaAnioMateria = cursor.getColumnIndex("anioMateria");
                int columnaIdMateria = cursor.getColumnIndex("idMateria");
                int columnaIdCursada = cursor.getColumnIndex("idCursada");
                int columnaFechaCursada = cursor.getColumnIndex("anioCursada");

                Cursada cursada = new Cursada(cursor.getInt(columnaIdCursada));
                cursada.setNombreMateria(cursor.getString(columnaNombreMateria));
                cursada.setAnioMateria(cursor.getInt(columnaAnioMateria));
                cursada.setAnioCursada(cursor.getInt(columnaFechaCursada));
                cursada.setIdMateria(cursor.getInt(columnaIdMateria));

                listaCursadas.add(cursada);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return listaCursadas;
    }

}
