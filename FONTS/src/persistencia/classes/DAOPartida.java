package persistencia.classes;

import domini.classes.TipusAlgorisme;
import domini.classes.Ronda;
import java.util.*;

public class DAOPartida {

    /**
     * Identificador de la partida.
     */
    private int id;

    /**
     * Codi solució de la partida.
     */
    private Integer[] solutionCode;

    /**
     * Tipus d'algorisme.
     * És null si la partida és de tipus Codebreaker.
     * Ha de ser un dels tipus admesos per l'Enumeration TipusAlgorisme (FIVEGUESS o GENETIC) si la partida és de tipus Codemaker.
     */
    private TipusAlgorisme tipusAlgorisme;

    /**
     * Rondes de la partida.
     * Cada ronda conté:
     *  - Integer id de la ronda, que és incremental des del 0:
     *    la primera ronda té ID 0, la segona té ID 1, etc.
     *  - Integer[] combinacioIntentada en la ronda donada
     *  - String correcció de l'intent fet
     */
    private HashMap<Integer, Ronda> rondes;


    /**
     * Constructora buida.
     * Utilitzada per la deserialitzaciÓ de l'objecte.
     */
    public DAOPartida() {}

    /**
     * Constructora amb paràmetres.
     * 
     * @param id                    Identificador de la partida
     * @param solutionCode          Codi solució de la partida
     * @param tipusAlgorisme        Algorisme utilitzat en la partida
     * @param rondes                Conjunt de les rondes jugades
     */

    public DAOPartida(int id, Integer[] solutionCode, TipusAlgorisme tipusAlgorisme, HashMap<Integer, Ronda> rondes) {
        this.id = id;
        this.solutionCode = solutionCode;
        this.tipusAlgorisme = tipusAlgorisme;
        this.rondes = rondes;
    }

    public int getId() {
        return id;
    }

    public Integer[] getSolutionCode() {
        return solutionCode;
    }

    public TipusAlgorisme getTipusAlgorisme() {
        return tipusAlgorisme;
    }

    public HashMap<Integer, Ronda> getRondes() {
        return rondes;
    }

}
