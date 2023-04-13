package domini.classes;

import domini.controllers.CtrlPartida;

public class Codemaker extends Partida {

    private final CtrlAlgorisme ctrlAlgorisme;

    /**
     * Constructora de la classe CodeMaker
     *
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     */
    public Codemaker(ConfiguracioPartida configuracioPartida , Integer[] solutionCode, CtrlPartida ctrlPartida, CtrlAlgorisme ctrlAlgorisme) {
        super(configuracioPartida, solutionCode, ctrlPartida);

        this.ctrlAlgorisme = ctrlAlgorisme;
    }

    public Integer[] getCodiMaquina(Integer[] ultimIntent, String resposta) {
        return ctrlAlgorisme.esbrinarCodiFiveguess(ultimIntent, resposta);
    }

    public boolean esCodeMaker() {return true; }

}
