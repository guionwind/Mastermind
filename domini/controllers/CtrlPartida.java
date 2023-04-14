package domini.controllers;

import domini.classes.*;
import domini.classes.ConfiguracioPartida.TipusPartida;
import domini.classes.exceptions.TipusPartidaIncorrecte;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;


public class CtrlPartida {

    /**
     * Identificador de la partida actual
     */
    private Integer idPartidaActual;

    /**
     * HashMap que conte les id's i partides
     */
    private HashMap<Integer, Partida> partides;

    /**
     * Constructora de la classe CtrlPartida
     * Inicialitza idPartidaActual a -1 per que no s'inicialtzi automaticament a 0
     * Inicialitza el HashMap
     */

    public CtrlPartida() {
        idPartidaActual = -1;
        partides = new HashMap<Integer, Partida>();
    }

    /**
     * Crea una partida en mode Codebreaker.
     * Es genera el codi solucio de la partida.
     *
     * @param numeroIntents
     * @param numeroColors
     * @param longitudCombinacio
     * @throws IOException
     */
    public void crearPartidaCodebreaker(int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException {
        TipusPartida t = TipusPartida.CODEBREAKER;
        ConfiguracioPartida c = creaConfiguracioPartida(t ,numeroIntents, numeroColors, longitudCombinacio);

        Integer[] solutionCode = generateSolutionCode(numeroColors, longitudCombinacio);


        Codebreaker cB = new Codebreaker(c, solutionCode, this);

        idPartidaActual = cB.getId();
        partides.put(idPartidaActual, cB);
    }

    public void crearPartidaCodemaker(int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode, CtrlAlgorisme ctrlAlgorisme) throws IOException {
        TipusPartida t = TipusPartida.CODEMAKER;
        ConfiguracioPartida c = creaConfiguracioPartida(t, numeroIntents, numeroColors, longitudCombinacio);

        Codemaker cM = new Codemaker(c, solutionCode, this, ctrlAlgorisme);

        idPartidaActual = cM.getId();
        partides.put(idPartidaActual, cM);
    }

    public void intentarCombinacio(Integer[] combinacioIntentada) {
        partides.get(idPartidaActual).intentarCombinacio(combinacioIntentada);
    }

    /**
     * Crea una ronda i la corresponent associacio amb Partida
     */
    public void crearRonda() {
        Partida p = partides.get(idPartidaActual);

        p.creaRonda();
    }

    public String corregeix(Integer[] combinacioIntentada) {
        Partida p = partides.get(idPartidaActual);

        Integer[] solutionCode = p.getSolutionCode().clone();
        String resposta = "";

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
                        resposta += "W";
                        solutionCode[j] = -1;
                    }
                }
            }
        }

        while (resposta.length() < combinacioIntentada.length) {
            resposta += "-";
        }

        p.setRespostaRonda(resposta);
        return resposta;
    }

    public Integer[] getCodiMaquina() {
        Partida p = partides.get(idPartidaActual);
        if(p.rondesJugades() == 1) return p.getCodiMaquina(null, null);
        return p.getCodiMaquina(p.getUltimCodi(), p.getUltimaResposta());
    }

    public Integer getIdPartidaActual() {
        return idPartidaActual;
    }

    public Integer getNumeroRondes() {
        Partida p = partides.get(idPartidaActual);

        return p.rondesJugades();
    }

    public void addEstadistiquesPartida(EstadistiquesPartida estadistiquesPartida) {
        Partida p = partides.get(idPartidaActual);

        p.setEstadisticaPartida(estadistiquesPartida);
    }

    public String getPista() {
        Partida p = partides.get(idPartidaActual);

        Random r = new Random();

        Integer[] solution = p.getSolutionCode();
        String pista = "";

        int posicio = r.nextInt(solution.length);
        for (int i = 0; i < solution.length; ++i) {
            if (i != posicio) pista += "?";
            else pista += solution[i];
        }

        return pista;
    }

    private ConfiguracioPartida creaConfiguracioPartida(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException {
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
