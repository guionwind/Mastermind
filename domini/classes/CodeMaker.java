package domini.classes;

public class CodeMaker extends Partida {

    /**
     * Constructora de la classe CodeMaker
     *
     * @param id Identificador de la Partida
     * @param estadistiquesPartida EstadistiquesPartida de la Partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     */
    public CodeMaker(int id, EstadistiquesPartida estadistiquesPartida, Integer[] solutionCode) {
        super(id, estadistiquesPartida, solutionCode);
    }

}
