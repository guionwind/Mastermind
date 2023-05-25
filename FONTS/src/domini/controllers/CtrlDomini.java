package domini.controllers;

import domini.actions.CorregeixAction;
import domini.classes.*;
import domini.classes.exceptions.*;
import persistencia.controllers.CtrlPersistencia;

import java.util.ArrayList;
import java.lang.String;
import java.io.*;

public class CtrlDomini {

    // Altres controladors de domini
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


    // Controlador de persistencia
    private final CtrlPersistencia ctrlPersistencia;

    private final CtrlAlgorisme ctrlAlgorisme;


    //! METODES

    /**
     * Constructora del controlador domini
     */
    public CtrlDomini() throws IOException {
        this.ctrlJugador = new CtrlJugador();
        this.ctrlRanquing = new CtrlRanquing();
        this.ctrlEstadistiquesPartida = new CtrlEstadistiquesPartida();
        this.ctrlPersistencia = new CtrlPersistencia();
        this.ctrlAlgorisme = new CtrlAlgorisme();
        this.ctrlPartida = new CtrlPartida(ctrlAlgorisme);
    }

    // CtrlPartida

    /**
     * Crea una partida Codebreaker
     *
     * @param numeroIntents Numero d'Intents permesos a la partida
     * @param numeroColors Numero de colors de la partida
     * @param longitudCombinacio Longitud maxima de la combinacio de colors
     * @throws Exception Llença excepcio en cas que l'usuari indiqui algun valor out of range
     */
    public void crearPartidaCodebreaker(int numeroIntents, int numeroColors, int longitudCombinacio) throws Exception {
        ctrlPartida.crearPartidaCodebreaker(ctrlPersistencia.totalPartides(), numeroIntents, numeroColors, longitudCombinacio);
    }

    /**
     * Crea una partida Codemaker i una instancia de ctrlAlgorisme.
     *
     * @param numeroIntents Numero d'Intents permesos a la partida
     * @param numeroColors Numero de colors de la partida
     * @param longitudCombinacio Longitud maxima de la combinacio de colors
     * @param solutionCode Solucio de la partida indicada per l'usuari
     * @throws Exception Llença excepcio en cas que l'usuari indiqui algun valor out of range
     */
    public void crearPartidaCodemaker(int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode, TipusAlgorisme tipusAlgorisme) throws Exception {
        ctrlPartida.crearPartidaCodemaker(ctrlPersistencia.totalPartides(), numeroIntents, numeroColors, longitudCombinacio, solutionCode, tipusAlgorisme);
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
        return CorregeixAction.corregeix(combinacioIntentada, ctrlPartida.getSolutionCode());
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
     * @throws PartidaInvalida Llença l'excepcio en cas que el
     * tipus de partida no sigui Codemaker.
     */
    public String[] jugarRondaCodeMaker() throws PartidaInvalida, LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes{
        Integer[] combinacioIntentada = ctrlPartida.getCodiMaquina().clone();
        ctrlPartida.intentarCombinacio(combinacioIntentada);
        String respostaCombinacio = CorregeixAction.corregeix(combinacioIntentada, ctrlPartida.getSolutionCode());
        ctrlPartida.setCorreccioRonda(respostaCombinacio);
        String combinacioIntentadaString = "";
        for (int i = 0; i < respostaCombinacio.length(); i++){
            combinacioIntentadaString += combinacioIntentada[i].toString();
        }
        String[] resposta = new String[]{combinacioIntentadaString, respostaCombinacio};
        return resposta;
    }

    //! del CtrlJugador
    public void registrarJugador(String username, String password, String confirmPassowrd) throws JugadorJaExisteix, IOException, InstanciaJaExisteix, ContrasenyaNoCoincideix {
        int newId = ctrlPersistencia.totalJugadors();

        if (ctrlPersistencia.existeixJugador(username)) {
            throw new JugadorJaExisteix("Ja hi ha un jugador amb aquest nom");
        } else if (!password.equals(confirmPassowrd)) {
            throw new ContrasenyaNoCoincideix("Les contrasenyes no coincideixen");
        }
        Jugador j = ctrlJugador.crearJugador(newId, username, password);
        ctrlPersistencia.afegirJugador(newId, username, password);
    }

    public String getUsername() {
        return ctrlJugador.getLoggedPlayerUsername();
    }

    /**
     * Retorna la contrasenya del usuari indicat per el parametre username
     * @param username username del usuari
     * @return retorna la contrasenya del usuari indicat per el username
     * @throws JugadorNoExisteix en cas que el jugador no existeixi es llança la excepcio
     */
    public String getPassword(String username) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        return ctrlPersistencia.obtenirPasswordJugador(username);
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

    public void loginAuthentication (String username, String password) throws IOException, InstanciaNoExisteix, ClassNotFoundException, ContrasenyaIncorrecte {
        String passwd = ctrlPersistencia.obtenirPasswordJugador(username);

        if (passwd.equals(password)) {
            ctrlJugador.setJugadorActual(ctrlPersistencia.obtenirJugador(username));
        } else {
            throw new ContrasenyaIncorrecte("La contrasenya es incorrecte");
        }
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

    public void carregarPartida(int idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        String strIdpartida = String.valueOf(idPartida);

        TipusPartida tipusPartida = ctrlPersistencia.obtenirTipusPartida(strIdpartida);
        Partida p = ctrlPersistencia.obtenirPartida(strIdpartida, tipusPartida);

        ConfiguracioPartida configuracioPartida = ctrlPersistencia.obtenirConfiguracioPartida(strIdpartida);
        p.setConfiguracioPartida(configuracioPartida);

        EstadistiquesPartida estadistiquesPartida = ctrlPersistencia.obtenirEstadistiquesPartida(ctrlJugador.getLoggedPlayerUsername(), strIdpartida);
        p.setEstadisticaPartida(estadistiquesPartida);

        if (tipusPartida == TipusPartida.CODEMAKER) {
            TipusAlgorisme tipusAlgorisme = ((Codemaker) p).getTipusAlgorisme();

            if (tipusAlgorisme == TipusAlgorisme.FIVEGUESS) {
                FiveGuess fiveGuess = ctrlPersistencia.obtenirFiveGuess(strIdpartida);
                ((Codemaker) p).setFiveGuess(fiveGuess);
            } else {
                Genetic genetic = ctrlAlgorisme.crearGenetic(
                        configuracioPartida.getLongitudCombinacio(),
                        configuracioPartida.getNumeroColors(),
                        p.getCodisIntentats(),
                        p.getRespostes()
                );
            }
        }
        ctrlPartida.setPartidaActual(p);
    }


}