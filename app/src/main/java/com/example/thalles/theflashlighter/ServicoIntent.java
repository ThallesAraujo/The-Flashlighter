package com.example.thalles.theflashlighter;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

/**
 * Servi�o que recebe as chamadas dos Widgets da tela inicial
 * (IntentListener)
 */
public class ServicoIntent extends IntentService {

     /*
     As chamadas nos intents dos widgets s�o feitas atrav�s do setAction(), que
     recebe uma String de a��o. Para que os intents funcionem, � necess�rio
     que as a��es que os mesmos dever�o executar sejam declaradas em constantes
     numa classe de IntentService. Aqui, uma �nica a��o foi definida, j� que a altern�ncia
     se dar� entre dois estados. Poderia se fazer duas a��es (uma para ligar e outra para
     desligar a lanterna), por�m, considerou-se que seria mais pr�tica uma �nica a��o que
     aplicasse o estado oposto ao atual.
      */
    public static String ALTERNAR = "com.example.thalles.theflashlighter.ALTERNAR";

    private Lanterna lanterna = Lanterna.getInstance();

    public ServicoIntent(){
        super("ServicoIntent");
    }

    /*
    Define as a��es a serem executadas quando a classe for chamada pelos intents.
    intent: a partir dele, � poss�vel resgatar qual a��o foi chamada pelo intent
    e realizar a a��o desejada.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent.getAction().equals(ALTERNAR)) {
            if (!lanterna.getIsLigada()) {
                lanterna.ligarLanterna();
            } else {
                lanterna.desligarLanterna();
            }
        }

        atualizarWidgets();

    }

    /*
    M�todo de atualiza��o dos widgets da tela inicial pelo servi�o de intent
     */
    private void atualizarWidgets(){

        int[] ids = AppWidgetManager.getInstance(this).getAppWidgetIds(new ComponentName(this, ProvedorDoWidget.class));

        if(ids != null && ids.length > 0) {
            for (int id : ids) {
                ProvedorDoWidget.updateAppWidget(this, AppWidgetManager.getInstance(this), id);
            }
        }

    }
}
