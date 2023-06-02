package domini.classes;

import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;

import java.util.HashMap;
/**
 * Classe codemaker que gestiona les partides tipus Codebreaker.
 */
public class Codemaker extends Partida {
    /**
     * Instància de FiveGuess associada, que implementa l'algorisme FiveGuess
     */
    private FiveGuess fiveGuess;

    /**
     * Instància de Genetic associada, que implementa l'algorisme FiveGuess
     */
    private Genetic genetic;

    /**
     * Algorisme que es farà servir en la partida (FIVEGUESS o GENETIC)
     */
    private TipusAlgorisme tipusAlgorisme;

    /**
     * Constructora de la classe Codemaker amb algorisme FiveGuess
     *
     * @param idPartida Identificador de la partida 
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     * @param fiveGuess Instància de FiveGuess associada
     */
    public Codemaker(int idPartida, ConfiguracioPartida configuracioPartida , Integer[] solutionCode, FiveGuess fiveGuess) {
        super(idPartida, configuracioPartida, solutionCode);

        this.fiveGuess = fiveGuess;
        this.tipusAlgorisme = TipusAlgorisme.FIVEGUESS;
    }

    /**
     * Constructora de la classe Codemaker amb algorisme Genetic
     *
     * @param idPartida Identificador de la partida 
     * @param configuracioPartida La configuracio de la partida
     * @param solutionCode Codi solucio de la partida indicat per l'usuari
     * @param genetic Instància de Genetic associada
     */
    public Codemaker(int idPartida, ConfiguracioPartida configuracioPartida , Integer[] solutionCode, Genetic genetic) {
        super(idPartida, configuracioPartida, solutionCode);

        this.genetic = genetic;
        this.tipusAlgorisme = TipusAlgorisme.GENETIC;
    }

    /**
     * Constructura de la classe Codemaker per a reconstruir-la des del disc.
     * 
     * @param idPartida Identificador de la partida
     * @param solutionCode Codi solució de la partida
     * @param rondes Conjunt de rondes que s'han jugat fins al moment
     * @param tipusAlgorisme Algorisme utilitzat en la partida (FiveGuess o Genetic)
     */
    public Codemaker(int idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes, TipusAlgorisme tipusAlgorisme) {
        super(idPartida, solutionCode, rondes);

        this.tipusAlgorisme = tipusAlgorisme;
    }

    /**
     * Constructura de la classe Codemaker per a reconstruir-la des del disc.
     *
     * @param idPartida Identificador de la partida
     * @param solutionCode Codi solució de la partida
     * @param rondes Conjunt de rondes que s'han jugat fins al moment
     * @param tipusAlgorisme Algorisme utilitzat en la partida (FiveGuess o Genetic)
     */
    public Codemaker(int idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes, String tipusAlgorisme) {
        super(idPartida, solutionCode, rondes);

        this.tipusAlgorisme = TipusAlgorisme.valueOf(tipusAlgorisme);
    }

    /**
     * Genera el següent intent a fer en l'algorisme FiveGuess
     * 
     * @param ultimIntent Intent anterior de l'algorisme. Si ha de fer el primer intent, aquest paràmetre és null.
     * @param resposta Correcció que ha fet el jugador del darrer intent.
     * 
     * @return La següent combinació de colors a intentar.
     * 
     * @throws LongitudCombinacioIncorrecte La longitud de l'ultim intent no es correspon amb la de la ConfiguracióPartida
     * @throws NumeroColorsIncorrecte S'ha intentat un color fora del rang definit a la ConfiguracióPartida
     * @throws LongitudRespostaIncorrecte La longitud de la resposta no es correspon amb la longitudCombinacio de la partida
     * @throws ValorsRespostaIncorrectes La correcció conté valors fora dels esperats
     */
    public Integer[] getCodiMaquinaFiveGuess(Integer[] ultimIntent, String resposta) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        Integer[] codi = fiveGuess.esbrina(ultimIntent, resposta);
        System.out.println("get codi maquina ");
        for (int i=0; i<codi.length; ++i) {
            System.out.print(codi[i]);

        }
        System.out.println();
        return codi;
    }

    /**
     * Genera el següent intent a fer en l'algorisme Genetic
     * 
     * @param resposta La correcio donada per l'usuari segons la ultima combinacio donada per la maquina
     * @return El seguent codi que la maquina jugara
     */
    public Integer[] getCodiMaquinaGenetic(String resposta)  {
        return genetic.esbrina(resposta);
    }

    /**
     * Setter que ens assigna la instància de FiveGuess rebuda.
     * 
     * @param fiveGuess Instància de fiveGuess a assignar
     */
    public void setFiveGuess(FiveGuess fiveGuess) {
        this.fiveGuess = fiveGuess;
    }

    /**
     * Setter que ens assigna la instància de Genetic rebuda.
     * 
     * @param genetic Instància de Genetic a assignar
     */
    public void setGenetic(Genetic genetic) {
        this.genetic = genetic;
    }

    /**
     * Retorna el tipus d'algorisme que s'utilitza.
     * 
     * @return Tipus d'algorisme utilitzat (GENETIC o FIVEGUESS)
     */
    public TipusAlgorisme getTipusAlgorisme() {
        return tipusAlgorisme;
    }

    public FiveGuess getFiveGuess() {
        return fiveGuess;
    }

}
