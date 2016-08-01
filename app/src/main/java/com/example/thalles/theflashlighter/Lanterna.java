package com.example.thalles.theflashlighter;

import android.hardware.Camera;
import android.util.Log;
import android.widget.Toast;

/**
 * Classe de controle da lanterna (flash) do
 * aparelho
 *
 * Padrão de projeto Singleton aplicado.
 * O mesmo garante que somente uma instância de
 * um objeto existirá no aplicativo inteiro
 */
public class Lanterna {

    private static Lanterna lanterna;
    private boolean isLigada = false;
    private Camera camera;

    /**
     * Por ser um Singleton, o construtor
     * só pode ser acessível na própria classe
     */
    private Lanterna(){}

    /**
     * A própria classe retornará sua própria instância
     * @return Instância única da classe
     */
    public static synchronized Lanterna getInstance(){
        String logText = null;
        if(lanterna == null){
            lanterna = new Lanterna();
            Log.i("Lanterna - Singleton","Primeira instância retornada");
            return lanterna;
        }else{
            Log.i("Lanterna - Singleton","Instância já existente retornada");
            return lanterna;
        }
    }


    /**
     * Método para ligar a lanterna do aparelho
     */
    public void ligarLanterna(){
        camera = Camera.open();
        if(camera!=null && !isLigada){
            Camera.Parameters params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isLigada = true;
        }
    }

    /**
     * Método para desligar a lanterna do aparelho
     */
    public void desligarLanterna() {

        camera = Camera.open();
        if (camera != null && isLigada) {
            Camera.Parameters params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            camera.release();
            camera = null;
            isLigada = false;
        }
    }

    /**
     * Indicador de estado da lanterna
     * @return true caso a lanterna esteja ligada, false caso contrário
     */
    public boolean getIsLigada(){
        return this.isLigada;
    }


}
