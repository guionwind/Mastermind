package persistencia.classes;

import java.io.Serializable;
import java.util.*;

public class DAOFiveGuess implements Serializable {
    /**
     * Conjunt de codis que encara no s'han intentat
     */
    private ArrayList<Integer[]> codisDisponibles;
    /**
     * Conjunt de codis que encara tenen la possibilitat de ser el codi solució.
     */
    private ArrayList<Integer[]> codisPossibles;
    
    /**
     * Contructora buida.
     * Utilitzada per la deserialitzaciÓ de l'objecte.
     */
    public DAOFiveGuess() {}

    /**
     * Contructora amb paràmetres.
     * 
     * @param codisDisponibles          Conjunt de codis no intentats.
     * @param codisPossibles            Conjunt de codis com a possible solució.
     */
    public DAOFiveGuess(ArrayList<Integer[]> codisDisponibles, ArrayList<Integer[]> codisPossibles) {
        this.codisDisponibles = codisDisponibles;
        this.codisPossibles = codisPossibles;
        this.codisPossibles = codisPossibles;
    }

    /**
     * Retorna el conjunt de codis no intentats.
     * 
     * @return          Conjunt de codis no intentats.
     */
    public ArrayList<Integer[]> getCodisDisponibles() {
        return codisDisponibles;
    }
    
    /**
     * Retorna el conjunt de codis com a possible solució.
     * 
     * @return          Conjunt de codis com a possible solució.
     */
    public ArrayList<Integer[]> getCodisPossibles() {
        return codisPossibles;
    }
}