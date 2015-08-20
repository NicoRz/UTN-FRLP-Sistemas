package com.utnfrlp.nicorz.utn_frlp_sistemas.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CursadaParaCursarDB extends EntidadesDB{

    public static final String Estructura = "(idMateria INTEGER not null" +
            ", idMateriaTrabada INTEGER  not null)";

    //Duracion = si es anual o cuatrimestral -- cuatrimestre = 1 o segundo cuatrimestre
    public static final String Tabla = "CursadaParaCursarDB";

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
        onDrop(conection,getTablaNombre());
    }

    public static void onUpgrade(SQLiteDatabase conection, int versionAnterior,int versionNueva) {
        // TODO Auto-generated method stub
        onDrop(conection,getTablaNombre());
        onCreate(conection,getTablaNombre(),getEstructura());
    }

    public static int Insert(SQLiteDatabase conection,Integer idMateria, Integer idMateriaTrabada) {
        ContentValues nuevoRegistro = new ContentValues();

        nuevoRegistro.put("idMateria",idMateria);
        nuevoRegistro.put("idMateriaTrabada",idMateriaTrabada);

        int res = Insert(conection,getTablaNombre(),nuevoRegistro) ;
        return res;
    }

    public static int Update(SQLiteDatabase conection,Integer idMateriaTrabada,Integer idMateria) {
        ContentValues nuevoRegistro = new ContentValues();

        String[] claves=null;
        String clave = "idMateria =" + idMateria.toString();
        nuevoRegistro.put("idMateriaTrabada",idMateriaTrabada);

        return Update( conection,getTablaNombre(),nuevoRegistro,clave,claves);
    }

    public static int Delete(SQLiteDatabase conection,Integer id) {
        String clave = "idMateria =" + id.toString();

        return Delete(conection,getTablaNombre(),clave);
    }

    public static final Cursor getMateriasCursadaParaCursar(SQLiteDatabase conection,Integer idMateria) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "cc.idMateriaTrabada as idMateria";
        sql = sql + ",m.nombre as nombreMateria";
        sql = sql + ",m.anio as anioMateria";
        sql = sql + ",m.electiva as electiva";
        sql = sql + ",m.cuatrimestre as queCuatrimestre";
        sql = sql +  ",m.duracion esAnual";

        sql = sql + " from " + getTablaNombre() + " cc ";

        sql = sql + " inner join " + MateriasDB.getTablaNombre() + " m ";
        sql = sql + " on cc.idMateriaTrabada = m.idMateria";

        where = "cc.idMateria = " + idMateria.toString();
        sql = sql + " where " + where;

        //sql = sql + " order by m.nombre asc";

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static final Cursor getQueNecesitoParaCursar(SQLiteDatabase conection,Integer idMateriaTrabada) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "cc.idMateria as idMateria";
        sql = sql + ",m.nombre as nombreMateria";
        sql = sql + ",m.anio as anioMateria";
        sql = sql + ",m.electiva as electiva";
        sql = sql + ",m.cuatrimestre as queCuatrimestre";
        sql = sql +  ",m.duracion esAnual";

        sql = sql + " from " + getTablaNombre() + " cc ";

        sql = sql + " inner join " + MateriasDB.getTablaNombre() + " m ";
        sql = sql + " on cc.idMateria = m.idMateria";

        where = "cc.idMateriaTrabada = " + idMateriaTrabada.toString();
        sql = sql + " where " + where;

        //sql = sql + " order by m.nombre asc";

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static Cursor getCantidadMaterias(SQLiteDatabase conection) {
        String sql="";
        //String where="";
        sql = "select ";
        sql = sql + "COUNT(m.idMateria) as cantidadMaterias";

        sql = sql + " from " + getTablaNombre() + " m ";

        //sql = sql + " where " + where;

        return conection.rawQuery(sql, null );
    }
}
