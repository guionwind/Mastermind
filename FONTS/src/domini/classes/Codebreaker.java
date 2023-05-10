package domini.classes;

import domini.controllers.CtrlPartida;

public class Codebreaker extends Partida {

    /**
     * Constructora de la classe codeBreaker
     *
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida creat automaticament en el controlador
     * @param ctrlPartida Controlador de la partida
     */
    public Codebreaker(ConfiguracioPartida configuracioPartida, Integer[] solutionCode, CtrlPartida ctrlPartida) {
        super(configuracioPartida, solutionCode, ctrlPartida);
    }

}
