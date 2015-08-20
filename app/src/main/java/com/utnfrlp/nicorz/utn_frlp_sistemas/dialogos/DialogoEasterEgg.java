package com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;


public class DialogoEasterEgg {
	
	@SuppressLint("InflateParams") 
	public static  void showDialogoEasterEgg(final Activity miActivity) {
		try {
			LayoutInflater li = LayoutInflater.from(miActivity);
			View easterEgg = li.inflate(R.layout.preferences_easter_egg, null);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(miActivity);							
			alertDialogBuilder.setView(easterEgg);
			alertDialogBuilder.setCancelable(true);
			
			
			final AlertDialog alertDialog = alertDialogBuilder.create();
			
			alertDialog.show();
			
		} catch (Exception e) {
			LogD("showDialogoTamEquipo",e.toString());
		}
	}

	public static void LogD(String nombreMetodo,String mensaje) {
		L.LogD("EasterEgg", nombreMetodo + " " + mensaje);
	}
}
