package com.utnfrlp.nicorz.utn_frlp_sistemas.dialogos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;


public abstract class  Dialogos {
	public static final int RESPUESTA_RESULTADO = 0;
	public static final int RESPUESTA_RESULTADO_OK = 1;
	public static final int RESPUESTA_RESULTADO_CANCEL = 2;
	public static final int RESPUESTA_RESULTADO_ERROR = 3;
	public static final String RESPUESTA_TEXTO = "TEXTO";

	public static String getTextoDialogo(Intent resultado) {
		return resultado.getExtras().getString(Dialogos.RESPUESTA_TEXTO);
	}
	@SuppressLint("InflateParams")
	public static  void showDialogoText(final Activity miActivity,String titulo,String hintText,final int resultCode,final iDialogoResult miDialog ) {

		try {

			LayoutInflater li = LayoutInflater.from(miActivity);
			View promptAgregarEquipo = li.inflate(R.layout.dialogo_texto, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(miActivity);
			alertDialogBuilder.setView(promptAgregarEquipo);

			final TextInputLayout textInputLayout = (TextInputLayout) promptAgregarEquipo.findViewById(R.id.textInputLayout);

			final EditText nombreEquipoNuevo = (EditText) promptAgregarEquipo.findViewById(R.id.txtNombreEquipo);
			nombreEquipoNuevo.setHint(hintText);

			alertDialogBuilder.setTitle(titulo);
			alertDialogBuilder.setCancelable(true);

			alertDialogBuilder.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					miDialog.onDialogoResult(Dialogos.RESPUESTA_RESULTADO_CANCEL, resultCode,null);
				}
			});

//			alertDialogBuilder.setPositiveButton(miActivity.getString(R.string.aceptar), new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog,int id) {
//					// Rescatamos el nombre del EditText y lo mostramos por pantalla
//					try {
//						if (nombreEquipoNuevo.getText().toString().length()!=0) {
//							Intent data = new Intent();
//							data.putExtra(Dialogos.RESPUESTA_TEXTO, nombreEquipoNuevo.getText().toString());
//							miDialog.onDialogoResult(Dialogos.RESPUESTA_RESULTADO_OK, resultCode,data );
//                            //miActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//						} else {
////							Intent data = new Intent();
////                            ToastS(miActivity.getBaseContext(),miActivity.getString(R.string.vacio));
////							miDialog.onDialogoResult(Dialogos.RESPUESTA_RESULTADO, resultCode,data );
//
//                            dialog.wait();
//						}
//					} catch (Exception e) {
//						LogD("aceptar",e.toString());
//					}
//				}
//			});

			alertDialogBuilder.setPositiveButton(miActivity.getString(R.string.aceptar), null);

			alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,int id) {
					// Cancelamos el cuadro de dialogo
					miDialog.onDialogoResult(Dialogos.RESPUESTA_RESULTADO_CANCEL, resultCode,null);
				}
			});

			// Creamos un AlertDialog y lo mostramos
			final AlertDialog alertDialog = alertDialogBuilder.create();

			nombreEquipoNuevo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

					if (nombreEquipoNuevo.getText().toString().length()!=0) {
						Intent data = new Intent();
						data.putExtra(Dialogos.RESPUESTA_TEXTO, nombreEquipoNuevo.getText().toString());
						miDialog.onDialogoResult(Dialogos.RESPUESTA_RESULTADO_OK, resultCode,data );
						alertDialog.dismiss();
					} else {
						textInputLayout.setError("No puede estar vacio");
					}
					return true;
				}
			});

			alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

			alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
				@Override
				public void onShow(DialogInterface dialog) {
					Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
					b.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							try {
								if (nombreEquipoNuevo.getText().toString().length()!=0) {
									Intent data = new Intent();
									data.putExtra(Dialogos.RESPUESTA_TEXTO, nombreEquipoNuevo.getText().toString());
									miDialog.onDialogoResult(Dialogos.RESPUESTA_RESULTADO_OK, resultCode,data );
									alertDialog.dismiss();
								} else {
									textInputLayout.setError("No puede estar vacio");
								}
							} catch (Exception e) {
								LogD("aceptar",e.toString());
							}
						}
					});
				}
			});

			alertDialog.show();

		} catch (Exception e) {
			LogD("showDialogoText", e.toString());
			miDialog.onDialogoResult(Dialogos.RESPUESTA_RESULTADO_ERROR,resultCode,null);
		}
	}

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("Dialogos", nombreMetodo + " " + mensaje);
    }
}