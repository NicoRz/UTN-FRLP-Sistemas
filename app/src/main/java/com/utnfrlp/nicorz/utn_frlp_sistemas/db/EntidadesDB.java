// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.utnfrlp.nicorz.utn_frlp_sistemas.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;

public abstract class EntidadesDB {
    public static  String getTablaNombre() {
        return null;
    }
    public static  String getEstructura() {
        return null;
    }
    public static void onCreate(SQLiteDatabase db,String nombreTabla,String estructura) {
        String sql;

        sql = "CREATE TABLE " + nombreTabla + " " + estructura;
        LogD(nombreTabla,sql);
        LogD("OnCreate",sql);
        db.execSQL(sql);
        inicializo(db);

    }
    public static void inicializo(SQLiteDatabase db) {

    }
    public static void onDrop(SQLiteDatabase db,String nombreTabla) {
        LogD("DROP",nombreTabla);
        db.execSQL("DROP TABLE IF EXISTS " + nombreTabla);
    }
    public static void onUpgrade(SQLiteDatabase db, int versionAnterior,int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aqui utilizamos directamente
        // la opcion de eliminar la tabla anterior y crearla de nuevo
        // vacia con el nuevo formato.
        // Sin embargo lo normal sera que haya que migrar datos de la
        // tabla antigua a la nueva, por lo que este metodo deberia
        // ser mas elaborado.

        //Se elimina la version anterior de la tabla
        onDrop(db, getTablaNombre());
        //Se crea la nueva version de la tabla
        onCreate(db, getTablaNombre(), getEstructura());
    }
    public static void AlterTable(SQLiteDatabase db,String nombreTabla,String sql) {
        //Insertamos el registro en la base de datos
        db.execSQL("ALTER TABLE " + nombreTabla + " " + sql);
    }
    public static int Insert(SQLiteDatabase db,String nombreTabla,ContentValues nuevoRegistro) {
        //Insertamos el registro en la base de datos
        int res=0;
        try {
            db.insert(nombreTabla, null, nuevoRegistro);
            res=1;
        } catch (Exception e) {
            LogD("Insert",e.toString());
        }
        return res;
    }
    public static int Delete(SQLiteDatabase db,String nombreTabla,String where) {
        //	LogD("EntidadesDB","Delete "+where);
        db.delete(nombreTabla, where, null );
        return 1;
    }

    public static int Insert(SQLiteDatabase db,String nombreTabla,Bundle bundle) {
        String sql;
        String campos ="";
        String valores = "";
        String coma="";

        sql = "INSERT INTO " + nombreTabla;
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            LogD("com/utnfrlp/nicorz/utn_frlp_sistemas/db", String.format("%s %s (%s)", key,
                    value.toString(), value.getClass().getName()));
            campos = campos  + coma + key.toString();
            valores = valores  + coma + value.toString();
            coma=",";
        }
        sql = sql + "(" + campos +") values (" + valores + ")";
        db.execSQL(sql);
        return 1;
    }
    public static int Update(SQLiteDatabase db,String nombreTabla,ContentValues valores,String clave, String[] claves) {
        //Establecemos los campos-valores a actualizar
        //Actualizamos el registro en la base de datos
        db.update(nombreTabla, valores, clave, claves);
        return 1;
    }
    public static int getCantidad(SQLiteDatabase conection,String tabla,String vWhere) {
        int cantidad =0;
        String[] campos = {"count(*)"};
        try {
            Cursor c = conection.query(tabla, campos, vWhere,  null, null, null, null);
            if (c.moveToFirst()) {
                cantidad=c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            LogD("EntidadesDb","getCantidad:"+e.toString());
            cantidad=-1;
        }
        return cantidad;
    }
    public static void onUpgrade_v1_To_2(SQLiteDatabase conection) {

    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("EntidadesDB", nombreMetodo + " " + mensaje);
    }
}