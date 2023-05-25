package domini.classes;

import domini.controllers.CtrlPartida;

import java.util.HashMap;

public class Codebreaker extends Partida {

    /**
     * Constructora de la classe codeBreaker
     *
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida creat automaticament en el controlador
     * @param ctrlPartida Controlador de la partida
     */
    public Codebreaker(int idPartida, ConfiguracioPartida configuracioPartida, Integer[] solutionCode) {
        super(idPartida, configuracioPartida, solutionCode);
    }

    public Codebreaker(int idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes) {
        super(idPartida, solutionCode, rondes);
    }

}
