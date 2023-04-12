package domini.classes;

public class Codemaker extends Partida {

    /**
     * Constructora de la classe CodeMaker
     *
     * @param id Identificador de la Partida
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     */
    public Codemaker(ConfiguracioPartida configuracioPartida , Integer[] solutionCode) {
        super(configuracioPartida, solutionCode);
    }

}
