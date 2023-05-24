package domini.classes;

import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;
import domini.controllers.CtrlAlgorisme;
import domini.controllers.CtrlPartida;

public class Codemaker extends Partida {

    private final FiveGuess fiveGuess;
    private final Genetic genetic;

    /**
     * Constructora de la classe CodeMaker
     *
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     */
    public Codemaker(int idPartida, ConfiguracioPartida configuracioPartida , Integer[] solutionCode, FiveGuess fiveGuess, CtrlPartida ctrlPartida) {
        super(idPartida, configuracioPartida, solutionCode, ctrlPartida);

        this.fiveGuess = fiveGuess;
        this.genetic = null;
    }

    public Codemaker(int idPartida, ConfiguracioPartida configuracioPartida , Integer[] solutionCode, Genetic genetic, CtrlPartida ctrlPartida) {
        super(idPartida, configuracioPartida, solutionCode, ctrlPartida);

        this.genetic = genetic;
        this.fiveGuess = null;
    }

    public Integer[] getCodiMaquinaFiveGuess(Integer[] ultimIntent, String resposta) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        return fiveGuess.esbrina(ultimIntent, resposta);
    }

    public Integer[] getCodiMaquinaGenetic(String resposta) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        return genetic.esbrina(resposta);
    }

}
