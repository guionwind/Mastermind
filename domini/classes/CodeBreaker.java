package domini.classes;

public class CodeBreaker extends Partida {

    /**
     * Constructora de la classe codeBreaker
     *
     * @param id Identificador de la partida
     * @param estadistiquesPartida EstadistiquesPartida de la Partida
     * @param solutionCode Codi solucio de la partida creat automaticament en el controlador
     */
    public CodeBreaker(int id, EstadistiquesPartida estadistiquesPartida, Integer[] solutionCode) {
        super(id, estadistiquesPartida, solutionCode);
    }
}
