package domini.controllers;

import domini.classes.*;
import domini.classes.exceptions.*;
import java.io.IOException;

//import java.util.ArrayList;
import java.lang.String;
import domini.classes.ConfiguracioPartida.TipusPartida;
import domini.classes.exceptions.TipusPartidaIncorrecte;

public class CtrlDomini {

    // ATRIBUTS

    /**
     * Controlador de la partida
     */
    private final CtrlPartida ctrlPartida;
    /**
     * Controlador de Jugador
     */
    private final CtrlJugador ctrlJugador;
    /**
     * Controlador de Ranquing
     */
    private final CtrlRanquing ctrlRanquing;

    // METODES

    /**
     * Constructora del controlador domini
     */
    public CtrlDomini() {
        ctrlPartida = new CtrlPartida();
        ctrlJugador = new CtrlJugador();
        ctrlRanquing = new CtrlRanquing();
    }

    //CtrlPartida

    /**
     * Crea una partida Codebreaker
     *
     * @param tipusPartida Tipuspartida de la partida
     * @param numeroIntents Numero d'Intents permesos a la partida
     * @param numeroColors Numero de colors de la partida
     * @param longitudCombinacio Longitud maxima de la combinacio de colors
     * @throws Exception Llença excepcio en cas que l'usuari indiqui algun valor out of range
     */
    public void crearPartidaCodebreaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws Exception {
        ctrlPartida.crearPartidaCodebreaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
    }

    /**
     * Crea una partida Codemaker i una instancia de ctrlAlgorisme.
     *
     * @param tipusPartida Tipuspartida de la partida
     * @param numeroIntents Numero d'Intents permesos a la partida
     * @param numeroColors Numero de colors de la partida
     * @param longitudCombinacio Longitud maxima de la combinacio de colors
     * @param solutionCode Solucio de la partida indicada per l'usuari
     * @throws Exception Llença excepcio en cas que l'usuari indiqui algun valor out of range
     */
    public void crearPartidaCodemaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) throws Exception {
        CtrlAlgorisme ctrlAlgorisme = new CtrlAlgorisme();

        ctrlPartida.crearPartidaCodemaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio, solutionCode, ctrlAlgorisme);
    }

    /**
     * Crea una ronda
     */
    public void crearRonda() {
        ctrlPartida.crearRonda();
    }

    //Funcions Codebreaker

    /**
     * Es juga una ronda com a Codebreaker. Primer, es guarda la combinacio intentada a
     * la ronda actual de la partida, despres es corregeix la combinacio
     * i es retorna la resposta.
     *
     * @param combinacioIntentada Combinacio entrada per el Usuari.
     * @return Retorna la resposta de la correcio de la ronda.
     */
    public String jugarRondaCodebreaker(Integer[] combinacioIntentada) {
        ctrlPartida.intentarCombinacio(combinacioIntentada);
        return ctrlPartida.corregeix(combinacioIntentada);
    }


    //Funcions Codemaker

    /**
     * Es juga una ronda com a Codemaker. Es demana la combinacioIntentada per
     * l'algorisme que s'utilitzi i despres es corregeix la combinacio.
     *
     * @return Retorna la resposta de la correcio de la ronda.
     * @throws TipusPartidaIncorrecte Llença l'excepcio en cas que el
     * tipus de partida no sigui Codemaker.
     */
    public String jugarRondaCodeMaker() throws TipusPartidaIncorrecte {
        Integer[] combinacioIntentada = ctrlPartida.getCodiMaquina();
        return ctrlPartida.corregeix(combinacioIntentada);
    }

    //! del CtrlJugador
    public void crearJugador (String username, String password) {
        ctrlJugador.crearJugador(username, password);
    }

    public String getUsername() {
        return ctrlJugador.getUsername();
    }

    //sistema de login: comprova que coincideixin usuari i contrasenya
    //si coincideixen, isPasswordCorrect
    public boolean loginAuthentication (String username, String password) throws UsuariNoExisteix {
        boolean correctCredentials = false;
        String passwd = ctrlJugador.getPassword(username);
        if (password != null) {
            //throw exception
        }
        else if (passwd == password) {
            correctCredentials = true;
        }
        return correctCredentials;
    }

    //! del CtrlRanquing

    public Ranquing getRanquing() {
        return ctrlRanquing.getRanquingActual();
    }

    public void crearRanquing() {
        ctrlRanquing.crearRanquing();
    }

}