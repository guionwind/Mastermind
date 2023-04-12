package domini.classes;

public class CodeMaker extends Partida {

    /**
     * Constructora de la classe CodeMaker
     *
     * @param id Identificador de la Partida
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     */
    public CodeMaker(int id,  ConfiguracioPartida configuracioPartida ,Integer[] solutionCode) {
        super(id, configuracioPartida, solutionCode);
    }

}
