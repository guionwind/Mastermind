package domini.controllers;

import domini.classes.*;
import domini.classes.exceptions.*;

import java.util.Random;


/**
 * Controlador que implementa totes les funcionalitats de jugabilitat de partides i gestiona les rondes
 */
public class CtrlPartida {

    /**
     * Partida actual
     */
    private Partida partidaActual;

    /**
     * CtrlAlgorisme de la partida
     */
    private final CtrlAlgorisme ctrlAlgorisme;

    /**
     * Constructora de la classe CtrlPartida
     * @param ctrlAlgorisme Controlador que crearà i retornarà instàncies de l'algorisme corresponent
     */

    public CtrlPartida(CtrlAlgorisme ctrlAlgorisme) {
        this.ctrlAlgorisme = ctrlAlgorisme;
    }

    /**
     * Crea una partida en mode Codebreaker.
     * S'especifica el tipus de la partida
     * Es crea la configuració de la partida
     * Es genera el codi solucio de la partida
     *
     * @param idPartida          Id de la partida a crear
     * @param numeroIntents      Numero d'intents permesos de la partida
     * @param numeroColors       Numero de colors permesos de la partida
     * @param longitudCombinacio Longitud combinacio de la partida
     * @throws LongitudCombinacioIncorrecte Salta l'excepcio en cas que la longitud sigui massa gran o massa petita
     * @throws NumeroColorsIncorrecte       Salta l'excepcio en cas que el numero de colors sigui incorrecte
     * @throws NumeroIntentsIncorrecte      Salta l'excepcio en cas que el numero d'intents sigui incorrecte
     */
    public void crearPartidaCodebreaker(int idPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws NumeroIntentsIncorrecte, NumeroColorsIncorrecte, LongitudCombinacioIncorrecte {
        TipusPartida t = TipusPartida.CODEBREAKER;
        ConfiguracioPartida configPartida = creaConfiguracioPartida(t ,numeroIntents, numeroColors, longitudCombinacio);

        Integer[] solutionCode = generateSolutionCode(numeroColors, longitudCombinacio);
        
        partidaActual = new Codebreaker(idPartida, configPartida, solutionCode);

    }

    /**
     * Crea una partida en mode Codebreaker.
     * S'especifica el tipus de la partida
     * Es crea la configuració de la partida
     * Es genera el codi solucio de la partida
     *
     * @param idPartida          Id de la partida a crear
     * @param numeroIntents      Numero d'intents permesos de la partida
     * @param numeroColors       Numero de colors permesos de la partida
     * @param longitudCombinacio Longitud combinacio de la partida
     * @param solutionCode       Solucio de la partida
     * @param tipusAlgorisme     Tipus del algorisme triat
     * @throws NumeroIntentsIncorrecte      Salta l'excepcio en cas que el numero d'intents sigui incorrecte
     * @throws NumeroColorsIncorrecte       Salta l'excepcio en cas que el numero de colors sigui incorrecte
     * @throws LongitudCombinacioIncorrecte Salta l'excepcio en cas que la longitud sigui massa gran o massa petita
     */

    public void crearPartidaCodemaker(int idPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode, TipusAlgorisme tipusAlgorisme) throws NumeroIntentsIncorrecte, NumeroColorsIncorrecte, LongitudCombinacioIncorrecte {
        TipusPartida t = TipusPartida.CODEMAKER;
        ConfiguracioPartida configPartida = creaConfiguracioPartida(t, numeroIntents, numeroColors, longitudCombinacio);

        if (tipusAlgorisme == TipusAlgorisme.FIVEGUESS) {
            FiveGuess fiveGuess = ctrlAlgorisme.crearFiveGuess();
            partidaActual = new Codemaker(idPartida, configPartida, solutionCode, fiveGuess);
        } else {
            Genetic genetic = ctrlAlgorisme.crearGenetic(longitudCombinacio, numeroColors);
            partidaActual = new Codemaker(idPartida, configPartida, solutionCode, genetic);
        }

    }

    /**
     * Assigna la partida actual.
     * @param partidaActual Partida a assignar
     */
    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }

    /**
     * Afegeix la combinacio intentada a la ronda actual de la partida.
     * @param combinacioIntentada Combinacio intentada
     */

    public void intentarCombinacio(Integer[] combinacioIntentada) {
        partidaActual.guardarCombinacio(combinacioIntentada);
    }

    /**
     * Crea una ronda i la corresponent associacio amb Partida
     */
    public void crearRonda() {
        partidaActual.creaRonda();
    }

    /**
     * Assigna la correcció corresponent a la ronda actual de la partida actual.
     * @param correcio Correccio de la ronda, de la combinacio intentada per la maquina
     */
    public void setCorreccioRonda(String correcio) {
        partidaActual.setCorrecioRonda(correcio);
    }

    /**
     * Metode per aconseguir la combinacio intentada de la maquina amb FiveGuess
     * @return retorna un integer[] amb la combinacio intentada per la maquina amb FiveGuess
     * @throws LongitudRespostaIncorrecte La longitud de la resposta no coincideix amb la indicada per l'usuari
     * @throws NumeroColorsIncorrecte El numero de colors no coincideix amb la indicada per l'usuari
     * @throws LongitudCombinacioIncorrecte La longitud de la combinacio no coincideix amb la indicada per l'usuari
     * @throws ValorsRespostaIncorrectes Els valors de la resposta no son correctes
     */
    public Integer[] getCodiMaquina() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes{
        Codemaker codemaker = (Codemaker) partidaActual;
        
        if (codemaker.getTipusAlgorisme() == TipusAlgorisme.FIVEGUESS) {
            if (partidaActual.rondesJugades() == 0) return codemaker.getCodiMaquinaFiveGuess(null, null);
            return codemaker.getCodiMaquinaFiveGuess(codemaker.getUltimaCombIntentada(), codemaker.getUltimaCorreccio());
        } else {
            if (partidaActual.rondesJugades() == 0) return codemaker.getCodiMaquinaGenetic(null);
            return codemaker.getCodiMaquinaGenetic(codemaker.getUltimaCorreccio());
        }
    }

    /**
     * Metode per aconseguir la id de la partida actual
     * @return retorna el id de la partida actual
     */
    public Integer getIdPartidaActual() {
        return partidaActual.getId();
    }

    /**
     * Metode per aconseguir la ultima combinacio intentada de la partida actual
     * @return retorna la ultima combinacio de la partida actual
     */
    public Integer[] getUltimaCombinacio() {
        return partidaActual.getUltimaCombIntentada();
    }

    /**
     * Metode per aconseguir el numero de rondes de la partida actual
     * @return retorna el numero de rondes
     */
    public Integer getNumeroRondes() {
        return partidaActual.rondesJugades();
    }

    /**
     * Afegeix estadistiques de la partida a una partida
     * @param estadistiquesPartida Estadistiques de la partida
     */
    public void addEstadistiquesPartida(EstadistiquesPartida estadistiquesPartida) {
        partidaActual.setEstadisticaPartida(estadistiquesPartida);
    }

    /**
     * Retorna una pista segons el codi solucio de la partida
     * @return retorna un string amb la pista
     */
    public String getPista() {
        Random r = new Random();

        Integer[] solution = partidaActual.getSolutionCode();
        StringBuilder pista = new StringBuilder();

        int posicio = r.nextInt(solution.length);
        for (int i = 0; i < solution.length; ++i) {
            if (i != posicio) pista.append("?");
            else pista.append(solution[i]);
        }

        return pista.toString();
    }

    /**
     * Crea una configuracio de la Partida
     * @param tipusPartida tipus de la partida que volem crear la configuracio
     * @param numeroIntents numero d'intents que tindra la partida
     * @param numeroColors numero de colors que tindra la partida
     * @param longitudCombinacio longitud de la combinacio que tindra la partida
     * @return retorna una nova instancia de configuracio partida
     * @throws NumeroIntentsIncorrecte Salta si el numero d'intents supera limits
     * @throws NumeroColorsIncorrecte Salta si el numero de colors supera limits
     * @throws LongitudCombinacioIncorrecte Salta si la longitud de combinacio supera limits
     */
    private ConfiguracioPartida creaConfiguracioPartida(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws NumeroIntentsIncorrecte, NumeroColorsIncorrecte, LongitudCombinacioIncorrecte {
        return new ConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
    }

    /**
     * Donada una llargada l i un numero de colors crea un codi solucio random i el retorna
     * @param n Numero de colors del codi
     * @param l Longitud del codi
     * @return retorna un codi solucio random segons els parametres especificats
     */
    private Integer[] generateSolutionCode(int n, int l) {
        Random r = new Random();

        Integer[] code = new Integer[l];
        for (int i = 0; i < l; ++i) {
            code[i] = r.nextInt(n) + 1;
        }

        return code;
    }
    
    /**
     * Retorna el codi solucio de la partida actual
     * @return codi solucio de la partida actual
     */
    public Integer[] getSolutionCode() {
        return partidaActual.getSolutionCode();
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }
}
