package domini.controllers;

import domini.classes.*;
import domini.classes.exceptions.*;

import java.util.ArrayList;
import java.lang.String;
import domini.classes.ConfiguracioPartida.TipusPartida;

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


    /**
     * Controlador de EstadistiquesPartida
     */
    private final CtrlEstadistiquesPartida ctrlEstadistiquesPartida;

    // METODES

    /**
     * Constructora del controlador domini
     */
    public CtrlDomini() {
        ctrlPartida = new CtrlPartida();
        ctrlJugador = new CtrlJugador();
        ctrlRanquing = new CtrlRanquing();
        ctrlEstadistiquesPartida = new CtrlEstadistiquesPartida();
    }

    //!CtrlPartida

    /**
     * Crea una partida Codebreaker
     *
     * @param tipusPartida Tipuspartida de la partida
     * @param numeroIntents Numero d'Intents permesos a la partida
     * @param numeroColors Numero de colors de la partida
     * @param longitudCombinacio Longitud maxima de la combinacio de colors
     * @throws Exception Llença excepcio en cas que l'usuari indiqui algun valor out of range
     */
    public void crearPartidaCodebreaker(int numeroIntents, int numeroColors, int longitudCombinacio) throws Exception {
        ctrlPartida.crearPartidaCodebreaker(numeroIntents, numeroColors, longitudCombinacio);
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
    public void crearPartidaCodemaker(int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) throws Exception {
        ctrlPartida.crearPartidaCodemaker(numeroIntents, numeroColors, longitudCombinacio, solutionCode);
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

    public String demanarPista() {
        return ctrlPartida.getPista();
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
    public String[] jugarRondaCodeMaker() throws TipusPartidaIncorrecte {
        Integer[] combinacioIntentada = ctrlPartida.getCodiMaquina();
        ctrlPartida.intentarCombinacio(combinacioIntentada);
        String respostaCombinacio = ctrlPartida.corregeix(combinacioIntentada);
        String combinacioIntentadaString = "";
        for (int i = 0; i < respostaCombinacio.length(); i++){
            combinacioIntentadaString += combinacioIntentada[i].toString();
        }
        String[] resposta = new String[]{combinacioIntentadaString, respostaCombinacio};
        return resposta;
    }

    //! del CtrlJugador
    public void crearJugador (String username, String password) throws JugadorJaExisteix, JugadorInvalid {
        ctrlJugador.crearJugador(username, password);
    }

    public String getUsername() {
        return ctrlJugador.getUsername();
    }

    public String getUsernameFromID(int id) {
        return ctrlJugador.getUsernameFromID(id);
    }

    //sistema de login: comprova que coincideixin usuari i contrasenya
    //si coincideixen, retorna true

    //? diria que es pot fer mes eficient si:
    /*  1. setJugadorActual(String username)
        2. li demanem contrasenya (sense atributs)
        3. la comprovem
        4. si es incorrecta, logout()
        ens estalviem un recorregut sobre el map
    */

    public boolean loginAuthentication (String username, String password) throws JugadorNoExisteix {
        boolean correctCredentials = false;
        String passwd = ctrlJugador.getPassword(username);
        if (password.isEmpty()) {
            throw new JugadorNoExisteix("El jugador es incorrecte");
        }
        else if (passwd.equals(password)) {
            correctCredentials = true;
            ctrlJugador.setJugadorActual(username);
        }
        return correctCredentials;
    }

    /**
     * Logoff: posa el JugadorActual del ctrlJugador a -1
     */
    public void logoff() {
        ctrlJugador.logoff();
    }

    //! del CtrlRanquing

    public Ranquing getRanquing() {
        return ctrlRanquing.getRanquing();
    }

    public void crearRanquing() {
        ctrlRanquing.crearRanquing();
    }

    public ArrayList<Integer[]> getTop10(){
        return ctrlRanquing.getTop10();
    }

    //CtrlEstadistiquesPartida

    /**
     * La partida ha acabat, per tant es crea la seva estadistica pertinent. Despres afegim una nova
     * entrada al ranquing
     *
     * @param guanyada Guanyada indica true si la partida s'ha guanyat.
     */
    public Integer partidaAcabada(Boolean guanyada) {
        Integer idPartida = ctrlPartida.getIdPartidaActual();
        Integer idJugador = ctrlJugador.getIdJugador();
        Integer numRondes = ctrlPartida.getNumeroRondes();

        Integer puntuacio = 100 - numRondes;

        ctrlEstadistiquesPartida.creaEstadistiquesPartida(idJugador, idPartida, puntuacio, guanyada);
        EstadistiquesPartida e = ctrlEstadistiquesPartida.getEstadistiquesPartida(idJugador, idPartida);

        //Afegim la estadistica creada a Jugador i a Partida
        ctrlPartida.addEstadistiquesPartida(e);
        ctrlJugador.addEstadistica(e);

        if (guanyada) ctrlRanquing.setNewRecord(idJugador, puntuacio);
        return puntuacio;
    }

    public Integer partidaAcabadaCodemaker() {
        Integer numRondes = ctrlPartida.getNumeroRondes();

        Integer puntuacio = 100 - numRondes;

        return puntuacio;
    }

    /**
     * Agafem les estadistiques que volem. Actualment nomes s'agafa la puntuacio de la partida
     *  
     * @param idJugador Identificador del jugador
     * @param idPartida Identificador de la partida
     * @return retorna les estadistiques de la partida (actualment nomes la puntuacio)
     */
    public int getEstadistiques(Integer idJugador, Integer idPartida) {
        return ctrlEstadistiquesPartida.getPuntuacio(idJugador, idPartida);
    }


}