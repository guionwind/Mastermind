package domini.classes;

import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;
import domini.controllers.CtrlAlgorisme;
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

    public Integer[] getCodiMaquina(Integer[] ultimIntent, String resposta) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        return ctrlAlgorisme.esbrinarCodiFiveguess(this.getId(),ultimIntent, resposta);
    }

}
