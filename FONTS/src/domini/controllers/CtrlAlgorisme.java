package domini.controllers;

import java.util.*;
import domini.classes.FiveGuess;
import domini.classes.Genetic;
import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;

public class CtrlAlgorisme {
    /**
     * Controlador de domini.
     */
    private final CtrlDomini ctrlDomini;

    public CtrlAlgorisme(CtrlDomini ctrlDomini) {
        this.ctrlDomini = ctrlDomini;
    }

    public FiveGuess crearFiveGuess() {
        return new FiveGuess();
    }

    public void guardarFiveGuess(String idPartida, FiveGuess fG) {
        if(ctrlDomini.existeixFiveGuess(idPartida))
            ctrlDomini.actualitzarFiveGuess(idPartida, fG);
        else
            ctrlDomini.afegirFiveGuess(idPartida, fG);
    }

    public FiveGuess carregarFiveGuess(String idPartida) {
        return ctrlDomini.obtenirFiveGuess(idPartida);
    }

    public void esborrarFiveGuess(String idPartida) {
        ctrlDomini.esborrarFiveGuess(idPartida);
    }

    public Genetic crearGenetic(Integer numPeg, Integer numCol) {
        return new Genetic(numPeg, numCol);
    }
    
    public Genetic crearGenetic(Integer numPeg, Integer numCol, ArrayList<Integer[]> codisIntentats, ArrayList<String> respostesCodisIntentats) {
        return new Genetic(numPeg, numCol, codisIntentats, respostesCodisIntentats);
    }
}