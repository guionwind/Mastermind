package domini.classes;

import java.util.HashMap;
/**
 * Classe codebreaker que gestiona les partides tipus Codebreaker.
 */
public class Codebreaker extends Partida {

    /**
     * Constructora de la classe Codebreaker
     *
     * @param idPartida Identificador de la partida 
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida creat automaticament en el controlador
     */
    public Codebreaker(int idPartida, ConfiguracioPartida configuracioPartida, Integer[] solutionCode) {
        super(idPartida, configuracioPartida, solutionCode);
    }

    /**
     * Constructora de la classe Codebreaker per a reconstruir-la des del disc.
     *
     * @param idPartida Identificador de la partida 
     * @param solutionCode Codi solucio de la partida creat automaticament en el controlador
     * @param rondes Conjunt de rondes que s'han jugat fins al moment
     */
    public Codebreaker(int idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes) {
        super(idPartida, solutionCode, rondes);
    }

}
