package domini.controllers;

import java.util.*;
import domini.classes.FiveGuess;
import domini.classes.Genetic;

public class CtrlAlgorisme {

    /**
     * Constructora
     *
     */
    public CtrlAlgorisme() {
    }

    /**
     * Crea i retorna un nou algorisme FiveGuess desde zero.
     * 
     * @return                      Nou algorisme FiveGuess.
     */
    public FiveGuess crearFiveGuess() {
        return new FiveGuess();
    }

    /**
     * Crea i retorna un nou algorisme Genetic desde zero.
     * 
     * @param numPeg                Número de fitxes que té el codi de la partida.
     * @param numCol                Número de colors que té la partiida.
     * @return                      Nou algorisme Genetic.
     */
    public Genetic crearGenetic(Integer numPeg, Integer numCol) {
        return new Genetic(numPeg, numCol);
    }
    
    /**
     * Crea i retorna la isntància de lalgorisme Genetic
     * de la partida amb número de fitxes, número de colors,
     * intents realitzats i respostes obtingudes especificats.
     * 
     * @param numPeg                        Número de fitxes que té el codi de la partida.
     * @param numCol                        Número de colors que té la partiida.
     * @param codisIntentats                Conjunt de codis intentats en la partida.
     * @param respostesCodisIntentats       Conjunt de respostes obtingudes a partir dels
     *                                      codis intentats.
     * @return                              L'algorisme de la partida de característiques
     *                                      especificades.
     */
    public Genetic crearGenetic(Integer numPeg, Integer numCol, ArrayList<Integer[]> codisIntentats, ArrayList<String> respostesCodisIntentats) {
        return new Genetic(numPeg, numCol, codisIntentats, respostesCodisIntentats);
    }
}