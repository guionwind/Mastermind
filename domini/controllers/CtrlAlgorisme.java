package domini.controllers;

import java.util.*;
import domini.classes.FiveGuess;

public class CtrlAlgorisme {

    /**
     * HashMap contenidor de algorismes FiveGuess amb clau de la partida a la que pertanyen
     */
    private HashMap<Integer, FiveGuess> fiveGuess;

    public CtrlAlgorisme() {
        fiveGuess = new HashMap<Integer, FiveGuess>();
    }

    public void creaFiveGuess(Integer idPartida) {
        FiveGuess fG = new FiveGuess(idPartida);
        fiveGuess.put(idPartida, fG);
    }

    public void esborraFiveGuess(Integer idPartida) {
        fiveGuess.remove(idPartida);
    }

    public Integer[] esbrinarCodiFiveguess(Integer idPartida, Integer[] ultimIntent, String resposta) {
        return fiveGuess.get(idPartida).esbrina(ultimIntent, resposta);
    }
}