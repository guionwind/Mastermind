package domini.controllers;

import domini.classes.Codebreaker;
import domini.classes.Codemaker;
import domini.classes.ConfiguracioPartida;
import domini.classes.ConfiguracioPartida.TipusPartida;

import java.util.Random;
import java.util.stream.IntStream;

public class CtrlPartida {



    public void crearPartidaCodebreaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        ConfiguracioPartida c = creaConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);

        Integer[] solutionCode = generateSolutionCode(numeroColors, longitudCombinacio);

        Codebreaker cB = new Codebreaker(c, solutionCode);
    }

    public void crearPartidaCodemaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) {
        ConfiguracioPartida c = creaConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);

        Codemaker cB = new Codemaker(c, solutionCode);
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
