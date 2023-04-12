package domini.classes;

public class CodeBreaker extends Partida {

    /**
     * Constructora de la classe codeBreaker
     *
     * @param id Identificador de la partida
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida creat automaticament en el controlador
     */
    public CodeBreaker(int id, ConfiguracioPartida configuracioPartida, Integer[] solutionCode) {
        super(id, configuracioPartida, solutionCode);
    }
}
