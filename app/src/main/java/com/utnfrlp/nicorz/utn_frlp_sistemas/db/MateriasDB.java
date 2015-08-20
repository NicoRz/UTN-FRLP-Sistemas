package com.utnfrlp.nicorz.utn_frlp_sistemas.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;

public class MateriasDB extends EntidadesDB {

    public static final String Estructura = "(idMateria INTEGER primary key" +
            ", nombre TEXT  not null" +
            ", anio INTEGER not null" +
            ", electiva INTEGER not null" +
            ", cuatrimestre INTEGER not null" +
            ", duracion INTEGER not null)";
    //Duracion = si es anual o cuatrimestral -- cuatrimestre = 1 o segundo cuatrimestre
    public static final String Tabla = "Materias";

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

    public static int Insert(SQLiteDatabase conection,String nombre,Integer anio,Integer electiva,Integer duracion,Integer cuatrimestre) {

        int id=(MateriasDB.getMaxId(conection)+1);
        ContentValues nuevoRegistro = new ContentValues();

        nuevoRegistro.put("idMateria",id);
        nuevoRegistro.put("nombre",nombre);
        nuevoRegistro.put("anio",anio);
        nuevoRegistro.put("electiva",electiva);
        nuevoRegistro.put("cuatrimestre",cuatrimestre);
        nuevoRegistro.put("duracion", duracion);

        int res = Insert(conection,getTablaNombre(),nuevoRegistro) ;
        return res;
    }

    public static int getMaxId(SQLiteDatabase conection) {
        int idMax= 0 ;
        try {
            Cursor c = conection.rawQuery(" SELECT max(idMateria) as n_max FROM "+ getTablaNombre() ,null);

            if (c.moveToFirst()) {
                idMax = c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            LogD("getMaxId", e.toString());
        }
        return idMax;
    }

    public static int Update(SQLiteDatabase conection,String name,Integer idMateria) {
        ContentValues nuevoRegistro = new ContentValues();

        String[] claves=null;
        String clave = "idMateria =" + idMateria.toString();
        nuevoRegistro.put("nombre",name);

        return Update( conection,getTablaNombre(),nuevoRegistro,clave,claves);
    }

    public static int Delete(SQLiteDatabase conection,Integer id) {
        String clave = "idMateria =" + id.toString();

        return Delete(conection, getTablaNombre(), clave);
    }

    public static Integer getId(SQLiteDatabase conection, String nombre) {
        String[] campos = {"idMateria","nombre"};
        String where;
        Integer id=0;
        try {
            where=" nombre = '" + nombre +"'";
            Cursor c = conection.query(getTablaNombre(), campos, where,  null, null, null, null);

            if (c.moveToFirst()) {
                id=c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            LogD("MateriasDB.getId",e.toString());
        }
        return id;
    }

    public static int getCantidad(SQLiteDatabase conection, String where) {
        int cantidad =0;
        String[] campos = {"count(*)"};
        //String sql;
        try {
            //sql = "select count(*) from " + getTablaNombre();
            //sql = sql + vWhere;
            Cursor c = conection.query(getTablaNombre(), campos, where,  null, null, null, null);
            //cantidad= c.getCount();
            if (c.moveToFirst()) {
                cantidad=c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            LogD("MateriasDB","getCantidad:"+e.toString());
            cantidad=-1;
        }
        return cantidad;
    }

    public static Cursor getCantidadMaterias(SQLiteDatabase conection) {
        String sql="";
        //String where="";
        sql = "select ";
        sql = sql + "COUNT(m.idMateria) as cantidadMaterias";

        sql = sql + " from " + getTablaNombre() + " m ";

        //sql = sql + " where " + where;

        sql = sql + "order by m.nombre";

        return conection.rawQuery(sql, null );
    }

    public static final Cursor getMateriasPorAnio(SQLiteDatabase conection,Integer anio,Integer orden) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "m.idMateria";
        sql = sql + ",m.nombre as nombreMateria";

        sql = sql + " from " + getTablaNombre() + " m ";

        if (anio>0) {
            where = "m.anio= " + anio.toString();
            sql = sql + " where " + where;
        }

        if (orden>0) {
            if (orden==2) {
                sql = sql + " order by m.anio desc , m.idMateria asc";
            } else {
                sql = sql + " order by m.anio asc , m.idMateria asc";
            }

        } else {
            sql = sql + " order by m.nombre asc";
        }

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static final Cursor getMateriasPorAnioAgregarCursada(SQLiteDatabase conection,Integer anio,Integer orden) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "m.idMateria";
        sql = sql + ",m.nombre as nombreMateria";

        sql = sql + " from " + getTablaNombre() + " m ";

        where = "m.idMateria not in (select idMateria from Cursadas)";

        where = where + " and m.idMateria not in (select idMateria from Finales)";

        if (anio>0) {
            where = where + " and m.anio= " + anio.toString();
        }

        sql = sql + " where " + where;

        if (orden>0) {
            sql = sql + " order by m.anio asc , m.idMateria asc";
        } else {
            sql = sql + " order by m.nombre asc";
        }

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static final Cursor getMateriasPorAnioAgregarFinal(SQLiteDatabase conection,Integer anio,Integer orden) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "m.idMateria";
        sql = sql + ",m.nombre as nombreMateria";

        sql = sql + " from " + getTablaNombre() + " m ";

        where = "m.idMateria not in (select idMateria from Finales where nota > 2)";

        if (anio>0) {
            where = where + " and m.anio= " + anio.toString();
        }

        sql = sql + " where " + where;

        if (orden>0) {
            sql = sql + " order by m.anio asc , m.idMateria asc";
        } else {
            sql = sql + " order by m.nombre asc";
        }

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static Cursor getTodasMaterias(SQLiteDatabase conection, Integer anio) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "m.idMateria as idMateria";
        sql = sql + ",m.nombre as nombreMateria";
        sql = sql + ",m.anio as anioMateria";
        sql = sql +  ",m.electiva as electiva";
        sql = sql +  ",m.cuatrimestre as queCuatrimestre";
        sql = sql +  ",m.duracion as esAnual";

        sql = sql + " from " + getTablaNombre() + " m ";

        if (anio>0) {
            where = "m.anio= " + anio.toString();
            sql = sql + " where " + where;
        }

        sql = sql + " order by m.anio asc,m.idMateria asc";

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static Cursor getMateriaCompleta(SQLiteDatabase conection, Integer idMateria) {
        String sql="";
        String where="";
        sql = "select ";
        sql = sql + "m.idMateria as idMateria";
        sql = sql + ",m.nombre as nombreMateria";
        sql = sql + ",m.anio as anioMateria";
        sql = sql +  ",m.electiva as electiva";
        sql = sql +  ",m.cuatrimestre as queCuatrimestre";
        sql = sql +  ",m.duracion as esAnual";

        sql = sql + " from " + getTablaNombre() + " m ";

        where = "m.idMateria= " + idMateria.toString();
        sql = sql + " where " + where;

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("MateriasDB", nombreMetodo + " " + mensaje);
    }
}
