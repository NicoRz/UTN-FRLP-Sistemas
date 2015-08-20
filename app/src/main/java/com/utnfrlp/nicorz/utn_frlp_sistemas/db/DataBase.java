package com.utnfrlp.nicorz.utn_frlp_sistemas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static final String baseNombre = "DBFutbol5";
    public static final int baseVersion = 6;

    public DataBase(Context context) {
        super(context, "DBFutbol5", null, 6);
    }

    public DataBase(Context context, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory) {
        super(context, "DBFutbol5", cursorfactory, 6);
    }

    private void crearTablas(SQLiteDatabase sqlitedatabase) {
        MateriasDB.onCreate(sqlitedatabase);
        AprobadaParaCursarDB.onCreate(sqlitedatabase);
        AprobadaParaRendirDB.onCreate(sqlitedatabase);
        CursadaParaCursarDB.onCreate(sqlitedatabase);
        CursadasDB.onCreate(sqlitedatabase);
        FinalesDB.onCreate(sqlitedatabase);
    }
//
//    private void onUpgrade_v1_To_2(SQLiteDatabase sqlitedatabase) {
//        PartidoJugadorDB.onUpgrade_v1_To_2(sqlitedatabase);
//    }
//
//    private void onUpgrade_v2_To_3(SQLiteDatabase sqlitedatabase) {
//        PartidoDB.onUpgrade_v2_To_3(sqlitedatabase);
//    }
//
//    private void onUpgrade_v3_To_4(SQLiteDatabase sqlitedatabase) {
//        EquipoJugadorDB.onUpgrade_v3_To_4(sqlitedatabase);
//    }
//
//    private void onUpgrade_v4_To_5(SQLiteDatabase sqlitedatabase) {
//        PartidoDB.onUpgrade_v4_To_5(sqlitedatabase);
//        PartidoJugadorDB.onUpgrade_v4_To_5(sqlitedatabase);
//    }
//
//    private void onUpgrade_v5_To_6(SQLiteDatabase sqlitedatabase) {
//        EtiquetasDB.onCreate(sqlitedatabase);
//        CanchasDB.onCreate(sqlitedatabase);
//        EventosDB.onCreate(sqlitedatabase);
//    }

    public boolean Commit() {
        getWritableDatabase().setTransactionSuccessful();
        getWritableDatabase().endTransaction();
        return true;
    }

    public boolean RollBack() {
        getWritableDatabase().execSQL("ROLLBACK;");
        getWritableDatabase().endTransaction();
        return true;
    }

    public boolean beginTransaction() {
        getWritableDatabase().beginTransaction();
        return true;
    }

    public SQLiteDatabase getConection() {
        return getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqlitedatabase) {
        crearTablas(sqlitedatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j) {
//        switch (i) {
//            case 1: // '\001'
//                onUpgrade_v1_To_2(sqlitedatabase);
//                onUpgrade_v2_To_3(sqlitedatabase);
//                onUpgrade_v3_To_4(sqlitedatabase);
//                onUpgrade_v4_To_5(sqlitedatabase);
//                onUpgrade_v5_To_6(sqlitedatabase);
//                break;
//
//            case 2: // '\002'
//                onUpgrade_v2_To_3(sqlitedatabase);
//                onUpgrade_v3_To_4(sqlitedatabase);
//                onUpgrade_v4_To_5(sqlitedatabase);
//                onUpgrade_v5_To_6(sqlitedatabase);
//                break;
//
//            case 3: // '\003'
//                onUpgrade_v3_To_4(sqlitedatabase);
//                onUpgrade_v4_To_5(sqlitedatabase);
//                onUpgrade_v5_To_6(sqlitedatabase);
//                break;
//
//            case 4: // '\004'
//                onUpgrade_v4_To_5(sqlitedatabase);
//                onUpgrade_v5_To_6(sqlitedatabase);
//                break;
//
//            case 5: // '\005'
//                onUpgrade_v5_To_6(sqlitedatabase);
//                break;
//            default:
//                break;
//        }
    }
}
