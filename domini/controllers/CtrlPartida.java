package domini.controllers;

import domini.classes.*;
import domini.classes.ConfiguracioPartida.TipusPartida;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class CtrlPartida {

    /**
     * Identificador de la partida actual
     */
    private Integer idPartidaActual;

    private HashMap<Integer, Partida> partides;

    public CtrlPartida() {
        idPartidaActual = -1;
        partides = new HashMap<Integer, Partida>();
    }

    /**
     * sjfdksdkfjhs
     * @param tipusPartida
     * @param numeroIntents
     * @param numeroColors
     * @param longitudCombinacio
     */
    public void crearPartidaCodebreaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws Exception {
        ConfiguracioPartida c = creaConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);

        Integer[] solutionCode = generateSolutionCode(numeroColors, longitudCombinacio);

        Codebreaker cB = new Codebreaker(c, solutionCode);
        partidaActual = cB;
    }

    public void crearPartidaCodemaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) throws Exception {
        ConfiguracioPartida c = creaConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);

        Codemaker cM = new Codemaker(c, solutionCode);
        partidaActual = cM;
    }

    public void intentRonda(Integer[] combinacioIntentada) {
        rondaActual.setCombinacioIntentada(combinacioIntentada);
    }

    /**
     * Crea una ronda i la corresponent associacio amb Partida
     * @param partida de la ronda
     * @param rondaId Identificador de la ronda
     */
    public void creaRonda(Integer idPartidaActual) {
        Partida p = partides.get(idPartidaActual);

        p.addRonda();
    }

    public String corregeix(Integer[] combinacioIntentada) {
        Integer[] solutionCode = partidaActual.getSolutionCode();
        String[] resposta = {};

        for (int i = 0; i < combinacioIntentada.length; ++i) {
            //Black case: color i posicio correctes
            if (combinacioIntentada[i] == solutionCode[i]) {
                resposta += "B";
                solutionCode[i] = -1;
            }
            //White case: color correcte pero posicio no
            else {
                for (int j = 0; j < combinacioIntentada.length; ++j) {
                    if (combinacioIntentada[i] == solutionCode[j]) {

                    }
                }
            }
        }
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

}
