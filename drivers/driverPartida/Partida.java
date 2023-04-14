package drivers.driverPartida;


import domini.classes.EstadistiquesPartida;
import domini.classes.Ronda;

import java.util.HashMap;

public class Partida {


    public Partida() {

    }

    public int getId() {
        return 4;
    }

    public int rondesJugades() {
        return 3;
    }

    public Integer[] getSolutionCode() {
        return new Integer[]{1,2,3,4};
    }

    public int getNombrePartides() {
        return 5;
    }

    public void creaRonda() {

    }

    public void setEstadisticaPartida(EstadistiquesPartida estadisticaPartida) throws Exception{
        if (estadisticaPartida == null) {
            this.estadisticaPartida = estadisticaPartida;
        }
        //else throw excepcio propia nostre. A fer
    }

    public ConfiguracioPartida getConfiguracioPartida() {
        return configuracioPartida;
    }

    public void intentarCombinacio(Integer[] combinacioIntentada) {
        rondes.get(rondes.size()-1).setCombinacioIntentada(combinacioIntentada);
    }

    public abstract boolean esCodeMaker();

    public abstract Integer[] getCodiMaquina(Integer[] ultimIntent, String resposta);

    public Integer[] getUltimCodi() {
        Ronda r = rondes.get(rondes.size() - 1);

        return r.getCombinacioIntentada();
    }

    public String getUltimaResposta() {
        Ronda r = rondes.get(rondes.size() - 1);

        return r.getResposta();
    }

    public void setRespostaRonda(String respostaRonda) {
        Ronda r = rondes.get(rondes.size() - 1);

        r.setResposta(respostaRonda);
    }
}
