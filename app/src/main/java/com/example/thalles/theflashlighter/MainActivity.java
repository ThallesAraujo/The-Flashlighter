package com.example.thalles.theflashlighter;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Activity principal do aplicativo
 */
public class MainActivity extends Activity {

    /**
     * Instância única da lanterna
     */
    private static Lanterna lanterna = Lanterna.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Inicialização de variáveis
         */
        setContentView(R.layout.activity_main);
        Switch switcher = (Switch) findViewById(R.id.switcher);
        TextView estado = (TextView) findViewById(R.id.textView_estado);

        /*
        Determinar o estado do switch de acordo com o estado
        atual da lanterna
         */
        switcher.setChecked(lanterna.getIsLigada());

        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /*
                Determinar o estado do TextView de estado de acordo com
                o estado atual da lanterna
                 */
                if (isChecked) {
                    ligar();
                    estado.setText("Lanterna ligada");
                } else {
                    desligar();
                    estado.setText("Lanterna desligada");
                }
            }
        });
        /*
        Atualiza os widgets da tela inicial
         */
        atualizarWidgets();
    }

    /**
     * Método para ligar a lanterna a partir da MainActivity
     */
    public static void ligar(){

        if(!lanterna.getIsLigada()){
            lanterna.ligarLanterna();
        }
    }

    /**
     * Método para desligar a lanterna a partir da MainActivity
     */
    public static void desligar(){
        if(lanterna.getIsLigada()){
            lanterna.desligarLanterna();
        }
    }

    //Métodos padrão do Android

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SobreActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void atualizarWidgets(){

        int[] ids = AppWidgetManager.getInstance(this).getAppWidgetIds(new ComponentName(this, ProvedorDoWidget.class));

        if(ids != null && ids.length > 0) {
            for (int id : ids) {
                ProvedorDoWidget.updateAppWidget(this, AppWidgetManager.getInstance(this), id);
            }
        }

    }

    /*
    A partir daqui, os métodos chamam o onCreate para que a MainActivity esteja sempre atualizada
     */

    @Override
    protected void onResume() {
        onCreate(new Bundle());
    }

    @Override
    protected void onPause() {
        onCreate(new Bundle());
    }

    @Override
    protected void onRestart() {
        onCreate(new Bundle());
    }

    @Override
    protected void onStart() {
        onCreate(new Bundle());
    }
}
