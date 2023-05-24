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

    /**
     * Constructora
     * 
     * @param ctrlDomini        Controlador de domini.
     */
    public CtrlAlgorisme(CtrlDomini ctrlDomini) {
        this.ctrlDomini = ctrlDomini;
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
     * Guarda a dades la instància de l'algorisme 
     * FiveGuess de la partida especificats.
     * 
     * @param idPartida             Identificador de la partida a la
     *                              que pertany l'algorisme.
     * @param fG                    Instancia de l'algorisme
     */
    public void guardarFiveGuess(String idPartida, FiveGuess fG) {
        if(ctrlDomini.existeixFiveGuess(idPartida))
            ctrlDomini.actualitzarFiveGuess(idPartida, fG);
        else
            ctrlDomini.afegirFiveGuess(idPartida, fG);
    }

    /**
     * Obté i retorna de dades la instància de l'algorisme
     * FiveGuess de la partida especificada.
     * 
     * @param idPartida             Identificador de la partida a la
     *                              que pertany l'algorisme.
     * @return                      L'algorisme de la partida.
     */
    public FiveGuess carregarFiveGuess(String idPartida) {
        return ctrlDomini.obtenirFiveGuess(idPartida);
    }

    /**
     * Esborra de dades l'algorisme FiveGuess
     * que pertany a la partida especificada.
     * Utilitzada quan na partida ha acabat.
     * 
     * @param idPartida             Identificador de la partida a la
     *                              que pertany l'algorisme.
     */
    public void esborrarFiveGuess(String idPartida) {
        ctrlDomini.esborrarFiveGuess(idPartida);
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