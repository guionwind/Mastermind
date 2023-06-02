package persistencia.classes;

import domini.classes.Ronda;

import java.io.Serializable;
import java.util.*;

public class DAOPartida implements Serializable {

    /**
     * Codi solució de la partida.
     */
    private Integer[] solutionCode;

    /**
     * Tipus d'algorisme.
     * És null si la partida és de tipus Codebreaker.
     * Ha de ser un dels tipus admesos per l'Enumeration TipusAlgorisme (FIVEGUESS o GENETIC) si la partida és de tipus Codemaker.
     */
    private String tipusAlgorisme;

    /**
     * Rondes de la partida.
     * Cada ronda conté:
     *  - Integer id de la ronda, que és incremental des del 0:
     *    la primera ronda té ID 0, la segona té ID 1, etc.
     *  - Integer[] combinacioIntentada en la ronda donada
     *  - String correcció de l'intent fet
     */
    private Integer[] idRondes;
    private Integer[][] combinacionsIntentades;
    private String[] correccions;


    /**
     * Constructora buida.
     * Utilitzada per la deserialitzaciÓ de l'objecte.
     */
    public DAOPartida() {}

    /**
     * Constructora amb paràmetres.
     * 
     * @param solutionCode          Codi solució de la partida
     * @param tipusAlgorisme        Algorisme utilitzat en la partida
     * @param rondes                Conjunt de les rondes jugades
     */

    public DAOPartida(Integer[] solutionCode, Integer[] idRondes, Integer[][] combinacionsIntentades, String[] correccions, String tipusAlgorisme) {
        this.solutionCode = solutionCode;
        this.tipusAlgorisme = tipusAlgorisme;
        this.idRondes = idRondes;
        this.combinacionsIntentades = combinacionsIntentades;
        this.correccions = correccions;
    }
    /**
     * Retorna el codi solució de la partida.
     * 
     * @return Codi solució de la partida
     */
    public Integer[] getSolutionCode() {
        return solutionCode;
    }
    /**
     * Retorna l'algorisme que s'està utilitzant en aquesta partida.
     * És null si la partida és de tipus Codebreaker.
     * 
     * @return El tipus d'algorisme utilitzat (FiveGuess)
     */
    public String getTipusAlgorisme() {
        return tipusAlgorisme;
    }

    public Integer[] getIdRondes() {
        return idRondes;
    }

    public Integer[][] getCombinacionsIntentades() {
        return combinacionsIntentades;
    }

    public String[] getCorreccions() {
        return correccions;
    }

}
