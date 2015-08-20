package com.utnfrlp.nicorz.utn_frlp_sistemas.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;

public class FinalesDB extends EntidadesDB {

    public static final String Estructura = "(idFinal INTEGER primary key" +
            ", idMateria INTEGER  not null" +
            ", fecha STRING not null" +
            ", nota INTEGER not null" +
            ", cursada INTEGER" +
            ", libro INTEGER" +
            ", folio INTEGER)";

    public static final String Tabla = "Finales";

    public static final String getTablaNombre() {
        return Tabla;
    }

    public static final  String getEstructura() {
        return Estructura;
    }

    public static final void onCreate(SQLiteDatabase conection) {
        // TODO Auto-generated method stub
        onCreate(conection,getTablaNombre(),getEstructura());
    }
    public static void onDrop(SQLiteDatabase conection) {
        onDrop(conection, getTablaNombre());
    }

    public static void onUpgrade(SQLiteDatabase conection, int versionAnterior,int versionNueva) {
        // TODO Auto-generated method stub
        onDrop(conection,getTablaNombre());
        onCreate(conection, getTablaNombre(), getEstructura());
    }

    public static int Insert(SQLiteDatabase conection,Integer idMateria,String fecha,Integer nota) {

        int id=(FinalesDB.getMaxId(conection)+1);
        ContentValues nuevoRegistro = new ContentValues();

        nuevoRegistro.put("idFinal",id);
        nuevoRegistro.put("idMateria",idMateria.toString());
        nuevoRegistro.put("fecha",fecha);
        nuevoRegistro.put("nota", nota.toString());

        int res = Insert(conection,getTablaNombre(),nuevoRegistro) ;
        return res;
    }

    public static int getMaxId(SQLiteDatabase conection) {
        int idMax= 0 ;
        try {
            Cursor c = conection.rawQuery(" SELECT max(idFinal) as n_max FROM "+ getTablaNombre() ,null);

            if (c.moveToFirst()) {
                idMax = c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            LogD("getMaxId", e.toString());
        }
        return idMax;
    }

    public static int Update(SQLiteDatabase conection,Integer idFinal,Integer nota,String fecha) {
        ContentValues nuevoRegistro = new ContentValues();

        String[] claves=null;
        String clave = "idFinal =" + idFinal.toString();
        nuevoRegistro.put("fecha",fecha);
        nuevoRegistro.put("nota",nota.toString());

        return Update( conection,getTablaNombre(),nuevoRegistro,clave,claves);
    }

    public static int Delete(SQLiteDatabase conection,Integer id) {
        String clave = "idFinal =" + id.toString();

        return Delete(conection, getTablaNombre(), clave);
    }

    public static Integer getId(SQLiteDatabase conection, Integer idMateria) {
        String[] campos = {"idFinal","idMateria"};
        String where;
        Integer id=0;
        try {
            where=" idMateria = '" + idMateria.toString() +"'";
            Cursor c = conection.query(getTablaNombre(), campos, where,  null, null, null, null);

            if (c.moveToFirst()) {
                id=c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            LogD("FinalesDB.getId",e.toString());
        }
        return id;
    }

    public static final Cursor getFinalesCompletos(SQLiteDatabase conection) {
        String sql="";
        //String where="";
        sql = "select ";
        sql = sql + "f.idMateria as idMateria";
        sql = sql + ",m.nombre as nombreMateria";
        sql = sql + ",m.anio as anioMateria";
        sql = sql +  ",f.nota as notaFinal";
        sql = sql +  ",f.fecha as fechaFinal";
        sql = sql +  ",f.idFinal as idFinal";

        sql = sql + " from " + getTablaNombre() + " f ";

        sql = sql + " inner join " + MateriasDB.getTablaNombre() + " m ";
        sql = sql + " on f.idMateria = m.idMateria";

        //sql = sql + " where " + where;

        //sql = sql + " order by f.idFinal";

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static final Cursor getAnaliticoSinAplazos(SQLiteDatabase conection) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "COUNT(f.idFinal) as cantidadFinales";
        sql = sql + ",AVG(f.nota) as promedioNota";

        sql = sql + " from " + getTablaNombre() + " f ";

        where = " f.nota > 2 ";
        sql = sql + " where " + where;

        //sql = sql + " group by f.idFinal";

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static final Cursor getAnaliticoConAplazos(SQLiteDatabase conection) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "COUNT(f.idFinal) as cantidadFinales";
        sql = sql + ",AVG(f.nota) as promedioNota";

        sql = sql + " from " + getTablaNombre() + " f ";

        //sql = sql + " where " + where;

        //sql = sql + " group by f.idFinal";

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("FinalesDB", nombreMetodo + " " + mensaje);
    }
}
