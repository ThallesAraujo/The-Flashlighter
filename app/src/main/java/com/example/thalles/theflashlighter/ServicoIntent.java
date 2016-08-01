package com.example.thalles.theflashlighter;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

/**
 * Serviço que recebe as chamadas dos Widgets da tela inicial
 * (IntentListener)
 */
public class ServicoIntent extends IntentService {

     /*
     As chamadas nos intents dos widgets são feitas através do setAction(), que
     recebe uma String de ação. Para que os intents funcionem, é necessário
     que as ações que os mesmos deverão executar sejam declaradas em constantes
     numa classe de IntentService. Aqui, uma única ação foi definida, já que a alternãncia
     se dará entre dois estados. Poderia se fazer duas ações (uma para ligar e outra para
     desligar a lanterna), porém, considerou-se que seria mais prática uma única ação que
     aplicasse o estado oposto ao atual.
      */
    public static String ALTERNAR = "com.example.thalles.theflashlighter.ALTERNAR";

    private Lanterna lanterna = Lanterna.getInstance();

    public ServicoIntent(){
        super("ServicoIntent");
    }

    /*
    Define as ações a serem executadas quando a classe for chamada pelos intents.
    intent: a partir dele, é possível resgatar qual ação foi chamada pelo intent
    e realizar a ação desejada.
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
    Método de atualização dos widgets da tela inicial pelo serviço de intent
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
