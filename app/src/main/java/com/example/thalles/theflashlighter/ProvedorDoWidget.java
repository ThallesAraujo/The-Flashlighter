package com.example.thalles.theflashlighter;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Classe de gerenciamento e representa��o dos widgets da tela inicial.
 */
public class ProvedorDoWidget extends AppWidgetProvider {


    /**
     * M�todo de atualiza��o dos widgets da tela inicial
     * @param context Contexto atual da aplica�ao
     * @param appWidgetManager Gerente de widgets
     * @param widgetId identificador do widget
     */
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int widgetId) {

        Lanterna  lanterna = Lanterna.getInstance();
        /*
        Resgata o layout do widget
         */
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.app_widget);

        /*
        Configura o intent com a classe que cont�m a a��o a ser realizada
        quando o usu�rio toca no widget
         */
        Intent intent = new Intent(context,ServicoIntent.class);
        /*
        Aqui, se configura qual das a��es da classe deve ser realizada.
        As a��es devem estar definidas em constantes da classe
        ver: ServicoIntent
         */
        intent.setAction(ServicoIntent.ALTERNAR);

        /*
        Determina o texto da TextView do widget e o resgata
        a partir da classe R
         */
        String estado = (lanterna.getIsLigada())?"Ligada":"Desligada";
        remoteViews.setTextViewText(R.id.tv_Widget,estado);
        /*
        Intent para atualiza��o
         */
        PendingIntent it = PendingIntent.getService(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*
        Determina qual bot�o ou ImageButton acionar� a a��o a ser
        realizada pelo Intent
         */
        remoteViews.setOnClickPendingIntent(R.id.ib_widget,it);
        appWidgetManager.updateAppWidget(widgetId,remoteViews);

    }

    /**
     * @see this.updateAppWidget
     * ver updateAppWidget
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
        @Override
        public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            // There may be multiple widgets active, so update all of them
            for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }
        }


}
