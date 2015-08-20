package com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.acciones.ActionMateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.AprobadaParaCursarDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.AprobadaParaRendirDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.CursadaParaCursarDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.CursadasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.FinalesDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.MateriasDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos.DialogoEasterEgg;

public class Preferences extends PreferenceActivity {
    SharedPreferences prefs;
    DataBase db;
    Integer easteregg;
    private Preference mailPreference;
    private Preference versionPreference;
    private Preference mostrarAyudasPreference;
    private Preference restaurarBaseDeDatos;
    private Preference restaurarMiCarrera;
//    private Preference backupPreference;
//    private Preference restorePreference;
    private SharedPreferences def;
    private ProgressDialog pCargarBaseDialog;
    private LoadingCargarBaseDialog tareaFondoCB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogD("Hasta aca", "0.5");
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        LogD("Hasta aca","1");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
            Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.preferences_toolbar, root, false);
            bar.setTitleTextColor(getResources().getColor(R.color.white));
            root.addView(bar, 0); // insert at top
            bar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    This().onBackPressed();
                }
            });
        }

        LogD("Hasta aca","2");

        db= new DataBase(This());

        LogD("Hasta aca","3");

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        def = PreferenceManager.getDefaultSharedPreferences(This());
        Integer valor1 = def.getInt("Ordenar por año",20);
        LogD("Valor or año",valor1.toString());
        Integer valor2 = def.getInt("1",20);
        LogD("Valor or anio",valor2.toString());
        String valor4 = def.getString("ordenameMaterias","Nada");
        LogD("Valor or anio3",valor4);
        LogD("Hasta aca","4");
        CrearEventos();


    }

    @SuppressWarnings("deprecation")
    private void CrearEventos() {

        mostrarAyudasPreference = findPreference("mostrarAyudas");
        mostrarAyudasPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean("CursadaParaCursar",false);
                edit.putBoolean("FinalParaCursar",false);
                edit.putBoolean("QueNecesitoParaCursar",false);
                edit.putBoolean("QueNecesitoParaFinal",false);
                edit.remove("ordenameMaterias");
                edit.apply();

                Toast.makeText(This(),"Ayudas restauradas",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        restaurarBaseDeDatos = findPreference("borrarTodo");
        restaurarBaseDeDatos.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                pCargarBaseDialog = new ProgressDialog(This());
                pCargarBaseDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pCargarBaseDialog.setMessage("Cargando Materias...");
                pCargarBaseDialog.setCancelable(false);
                pCargarBaseDialog.setMax(100);

                tareaFondoCB = new LoadingCargarBaseDialog();
                tareaFondoCB.execute();
                return true;
            }
        });

        restaurarMiCarrera = findPreference("borrarMiCarrera");
        restaurarMiCarrera.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(This());
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Seguro?");
                alertDialogBuilder.setMessage("¿Seguro que quiere borrar todo?");
                alertDialogBuilder.setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FinalesDB.onDrop(db.getConection());
                        CursadasDB.onDrop(db.getConection());
                        FinalesDB.onCreate(db.getConection());
                        CursadasDB.onCreate(db.getConection());
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
                return true;
            }
        });

        mailPreference = findPreference("mail");
        mailPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "rozasni@gmail.com" });
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "UTNFRLP - Feedback");
                sendIntent.setType("plain/text");
                startActivity(Intent.createChooser(sendIntent,"Enviar mail"));
                return true;
            }
        });

        versionPreference = findPreference("version");

        String versionName;

        try {
            versionName = getApplicationContext().getPackageManager()
                    .getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e1) {
            versionName = "Error";
        }

        versionPreference.setSummary(versionName);
        easteregg = 0;

        versionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference arg0) {

                easteregg = easteregg + 1;
                if (easteregg==5) {
                    DialogoEasterEgg.showDialogoEasterEgg(This());
                }

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        easteregg = 0;
                    }
                }, 2000);
                return true;
            }
        });
//
//        backupPreference = findPreference("Backup");
//        backupPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                if (TieneSD()) {
//                    try {
//                        switch (BackupDatabase()) {
//                            case 1:
//                                Toast.makeText(getBaseContext(), getString(R.string.settings1), Toast.LENGTH_SHORT).show();
//                                break;
//                            case 3:
//                                String mensaje = getString(R.string.settings2);
//                                Toast.makeText(getBaseContext(), mensaje + " /futbol/FutbolBackup", Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Toast.makeText(getBaseContext(), getString(R.string.settings1), Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });
//
//        LogD("Hasta aca","8");
//
//        restorePreference = findPreference("Restore");
//        restorePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//
//            @Override
//            public boolean onPreferenceClick(Preference arg0) {
//                String mensaje="";
//                LogD("RestoreDatabase","entro");
//                if (TieneSD()) {
//                    try {
//                        switch (RestoreDatabase()) {
//                            case 1:
//                                mensaje = "Imposible, este es de backup";
//                                Toast.makeText(getBaseContext(), mensaje , Toast.LENGTH_SHORT).show();
//                                break;
//
//                            case 2:
//                                mensaje = getString(R.string.settings3);
//                                Toast.makeText(getBaseContext(), mensaje + "(/futbol/FutbolBackup)", Toast.LENGTH_SHORT).show();
//                                break;
//                            case 3:
//                                restauro = true;
//                                Toast.makeText(getBaseContext(), getString(R.string.settings4), Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                    } catch (IOException e) {
//                        LogD("RestoreDatabase",e.toString());
//                        e.printStackTrace();
//                    }
//                } else {
//                    Toast.makeText(getBaseContext(), getString(R.string.settings1), Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });

    }

    public Activity This() {
        return this;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class LoadingCargarBaseDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            ActionMateriasDB.CargarMaterias(db);

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPreExecute() {
            pCargarBaseDialog.show();
            MateriasDB.onDrop(db.getConection());
            AprobadaParaCursarDB.onDrop(db.getConection());
            AprobadaParaRendirDB.onDrop(db.getConection());
            CursadaParaCursarDB.onDrop(db.getConection());
            MateriasDB.onCreate(db.getConection());
            AprobadaParaCursarDB.onCreate(db.getConection());
            AprobadaParaRendirDB.onCreate(db.getConection());
            CursadaParaCursarDB.onCreate(db.getConection());
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(This(),"Base de datos restaurada",Toast.LENGTH_SHORT).show();
                pCargarBaseDialog.dismiss();
            }
        }
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("Preferencias", nombreMetodo + " " + mensaje);
    }
}
