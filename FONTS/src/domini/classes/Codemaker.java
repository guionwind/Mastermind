package domini.classes;

import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;

import java.util.HashMap;

public class Codemaker extends Partida {

    private FiveGuess fiveGuess;
    private Genetic genetic;

    private TipusAlgorisme tipusAlgorisme;

    /**
     * Constructora de la classe CodeMaker
     *
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     */
    public Codemaker(int idPartida, ConfiguracioPartida configuracioPartida , Integer[] solutionCode, FiveGuess fiveGuess) {
        super(idPartida, configuracioPartida, solutionCode);

        this.fiveGuess = fiveGuess;
    }

    public Codemaker(int idPartida, ConfiguracioPartida configuracioPartida , Integer[] solutionCode, Genetic genetic) {
        super(idPartida, configuracioPartida, solutionCode);

        this.genetic = genetic;
    }

    public Codemaker(int idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes, TipusAlgorisme tipusAlgorisme) {
        super(idPartida, solutionCode, rondes);

        this.tipusAlgorisme = tipusAlgorisme;
    }

    public Integer[] getCodiMaquinaFiveGuess(Integer[] ultimIntent, String resposta) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        return fiveGuess.esbrina(ultimIntent, resposta);
    }

    public Integer[] getCodiMaquinaGenetic(String resposta) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        return genetic.esbrina(resposta);
    }

    public void setFiveGuess(FiveGuess fiveGuess) {
        this.fiveGuess = fiveGuess;
    }

    public void setGenetic(Genetic genetic) {
        this.genetic = genetic;
    }

    public TipusAlgorisme getTipusAlgorisme() {
        return tipusAlgorisme;
    }

}
