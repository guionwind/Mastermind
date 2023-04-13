package domini.controllers;

import domini.classes.*;
import domini.classes.ConfiguracioPartida.TipusPartida;

import java.util.Random;
import java.util.stream.IntStream;

public class CtrlPartida {

    /**
     * Ronda actual
     */
    private Ronda ronda;

    /**
     * Partida actual
     */
    private Partida partida;



    public void crearPartidaCodebreaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        ConfiguracioPartida c = creaConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);

        Integer[] solutionCode = generateSolutionCode(numeroColors, longitudCombinacio);

        Codebreaker cB = new Codebreaker(c, solutionCode);
        partida = cB;

        creaRonda(0, cB);
    }

    public void crearPartidaCodemaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) {
        ConfiguracioPartida c = creaConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);

        Codemaker cM = new Codemaker(c, solutionCode);

        creaRonda(0, cM);
    }

    public void intentRonda(Integer[] combinacioIntentada) {
        ronda.setCombinacioIntentada(combinacioIntentada);
    }

    private ConfiguracioPartida creaConfiguracioPartida(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        return new ConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
    }

    private Integer[] generateSolutionCode(int n, int l) {
        Random r = new Random();

        Integer[] code = new Integer[l];
        for (int i = 0; i < l; ++i) {
            code[i] = r.nextInt(n) + 1;
        }

        return code;
    }

    /**
     * Crea una ronda i la corresponent associacio amb Partida
     * @param partida de la ronda
     * @param rondaId Identificador de la ronda
     */
    private void creaRonda(int rondaId, Partida partida) {
        Ronda r = new Ronda(rondaId, partida);
        this.ronda = r;

        partida.addRonda(r);
    }

}
