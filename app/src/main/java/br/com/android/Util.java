package br.com.android;

import android.app.AlertDialog;
import android.content.Context;

public class Util {

    public static final String AVISO_CAMPO_OBRIGATORIO = "Campo obrigatório";

    public static void showAviso(Context ctx, int msg) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(ctx);
        dialogo.setMessage(msg);
        dialogo.setNeutralButton("Ok", null);
        dialogo.show();
    }
}