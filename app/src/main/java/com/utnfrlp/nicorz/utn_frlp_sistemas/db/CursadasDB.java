package com.utnfrlp.nicorz.utn_frlp_sistemas.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;

public class CursadasDB extends EntidadesDB {

    public static final String Estructura = "(idCursada INTEGER primary key" +
            ", idMateria INTEGER  not null" +
            ", fecha INTEGER not null)";

    public static final String Tabla = "Cursadas";

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

    public static int Insert(SQLiteDatabase conection,Integer idMateria,Integer anio) {

        int id=(CursadasDB.getMaxId(conection)+1);
        ContentValues nuevoRegistro = new ContentValues();

        nuevoRegistro.put("idCursada",id);
        nuevoRegistro.put("idMateria",idMateria.toString());
        nuevoRegistro.put("fecha", anio);

        int res = Insert(conection,getTablaNombre(),nuevoRegistro) ;
        return res;
    }

    public static int getMaxId(SQLiteDatabase conection) {
        int idMax= 0 ;
        try {
            Cursor c = conection.rawQuery(" SELECT max(idCursada) as n_max FROM "+ getTablaNombre() ,null);

            if (c.moveToFirst()) {
                idMax = c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            LogD("getMaxId", e.toString());
        }
        return idMax;
    }

    public static int Update(SQLiteDatabase conection,Integer idCursada,Integer anio) {
        ContentValues nuevoRegistro = new ContentValues();

        String[] claves=null;
        String clave = "idCursada =" + idCursada.toString();
        nuevoRegistro.put("fecha",anio);

        return Update( conection,getTablaNombre(),nuevoRegistro,clave,claves);
    }

    public static int Delete(SQLiteDatabase conection,Integer id) {
        String clave = "idCursada =" + id.toString();

        return Delete(conection, getTablaNombre(), clave);
    }

    public static Integer getId(SQLiteDatabase conection, Integer idMateria) {
        String[] campos = {"idCursada","idMateria"};
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
            LogD("CursadasDB.getId",e.toString());
        }
        return id;
    }


    public static boolean PuedoAgregar(SQLiteDatabase conection, Integer idMateria) {
        boolean lResultado=false;
        int resultado;
        String where;
        try {
            //LogD("EquipoDB","PuedoAgregar");
            where = " idMateria = '" + idMateria.toString() +"'";

            resultado = CursadasDB.getCantidad(conection, where);
            if (resultado < 0) {
                //mensaje[Interceptor.Xn_I_Mensaje] ="Error en la verificacion de si puede agregar";
                lResultado=false;
            } else {
                if (resultado > 0) {
                    //mensaje[Interceptor.Xn_I_Mensaje] ="Ya existe un equipo con el nombre "+ equipo.getNombre();
                    lResultado=false;
                } else {
                    lResultado=true;
                }
            }


        } catch (Exception e) {
            LogD("MateriasDB","PuedoAgregar:"+e.toString());
            //mensaje[Interceptor.Xn_I_Mensaje] ="error inseperado - puedo agregar";
            //mensaje[Interceptor.Xn_I_Exception] = e.toString();
        }
        return lResultado;
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
            LogD("CursadasDB","getCantidad:"+e.toString());
            cantidad=-1;
        }
        return cantidad;
    }

    public static final Cursor getCursadasCompletos(SQLiteDatabase conection) {
        String sql="";
        //String where="";
        sql = "select ";
        sql = sql + "c.idMateria as idMateria";
        sql = sql + ",m.nombre as nombreMateria";
        sql = sql + ",m.anio as anioMateria";
        sql = sql +  ",c.fecha as anioCursada";
        sql = sql +  ",c.idCursada as idCursada";

        sql = sql + " from " + getTablaNombre() + " c ";

        sql = sql + " inner join " + MateriasDB.getTablaNombre() + " m ";
        sql = sql + " on c.idMateria = m.idMateria";

        //sql = sql + " where " + where;

        //sql = sql + " order by f.idFinal";

        Cursor c = conection.rawQuery(sql, null );

        return c;
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("CursadasDB", nombreMetodo + " " + mensaje);
    }
}