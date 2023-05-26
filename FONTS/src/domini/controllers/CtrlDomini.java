package domini.controllers;

import domini.classes.actions.CorregeixAction;
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

    /**
     * Controlador de persistencia
     */
    private final CtrlPersistencia ctrlPersistencia;

    /**
     * Controlador dels algorismes que fa servir la màquina
     */
    private final CtrlAlgorisme ctrlAlgorisme;


    //! METODES

    /**
     * Constructora del controlador domini
     * @throws IOException Es llança la excepcio en cas que persistencia no hagi pogut obrir, escriure, llegir o crear un arxiu.
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
     * @param tipusAlgorisme Tipus del algorisme utilitzat
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
     * Es juga una ronda com a Codebreaker.
     * Primer, es guarda la combinacio intentada a la ronda actual de la partida, despres es corregeix la combinacio i es retorna la resposta.
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
     * Es juga una ronda com a Codemaker. Es demana la combinacioIntentada per l'algorisme que s'utilitzi i despres es corregeix la combinacio.
     *
     * @return Retorna la resposta de la correcio de la ronda.
     * @throws LongitudCombinacioIncorrecte La longitud de la combinacio no coincideix amb la indicada per l'usuari
     * @throws NumeroColorsIncorrecte El numero de colors no coincideix amb l'indicada per l'usuari
     * @throws LongitudRespostaIncorrecte La longitud de la resposta no coincideix amb la indicada per l'usuari
     * @throws ValorsRespostaIncorrectes Els valors indicats a la resposta no son correctes
     */
    public String[] jugarRondaCodeMaker() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes{
        Integer[] combinacioIntentada = ctrlPartida.getCodiMaquina().clone();
        ctrlPartida.intentarCombinacio(combinacioIntentada);
        String respostaCombinacio = CorregeixAction.corregeix(combinacioIntentada, ctrlPartida.getSolutionCode());
        ctrlPartida.setCorreccioRonda(respostaCombinacio);
        String combinacioIntentadaString = "";
        for (int i = 0; i < respostaCombinacio.length(); i++){
            combinacioIntentadaString += combinacioIntentada[i].toString();
        }
        return new String[]{combinacioIntentadaString, respostaCombinacio};
    }

    //! del CtrlJugador
    /**
     * Enregistra un jugador nou al sistema i el desa al disc.
     * Com a mesura d'usabilitat, l'usuari introdueix dos cops la seva contrasenya i només es fa el registre en cas que coincideixin ambdues.
     * 
     * @param username  Nom del jugador a enregistrar-se
     * @param password  Contrasenya que usara el jugador en loguejar-se
     * @param confirmPassword   Confirmacio de la contrasenya del jugador
     * @throws JugadorJaExisteix    El nom pertany a un jugador existent
     * @throws IOException          Error d'entrada i sortida
     * @throws InstanciaJaExisteix  Ja s'ha creat aquest jugador
     * @throws ContrasenyaNoCoincideix  password i confirmPassword no coincideixen
     */
    public void registrarJugador(String username, String password, String confirmPassword) throws JugadorJaExisteix, IOException, InstanciaJaExisteix, ContrasenyaNoCoincideix {
        int newId = ctrlPersistencia.totalJugadors();

        if (ctrlPersistencia.existeixJugador(username)) {
            throw new JugadorJaExisteix("Ja hi ha un jugador amb aquest nom");
        } else if (!password.equals(confirmPassword)) {
            throw new ContrasenyaNoCoincideix("Les contrasenyes no coincideixen");
        }
        Jugador j = ctrlJugador.crearJugador(newId, username, password);
        ctrlPersistencia.afegirJugador(newId, username, password);
    }

    /**
     * Retorna el username del usuari loggejat actualment
     * @return Retorna el username del usuari loggejat actualment
     */
    public String getUsername() {
        return ctrlJugador.getLoggedPlayerUsername();
    }

    /**
     * Retorna la contrasenya del usuari indicat per el parametre username
     * @param username username del usuari
     * @return retorna la contrasenya del usuari indicat per el username
     * @throws IOException Throws IOException en cas que no es pugui accedir al fitxer on es troba la contrasenya
     * @throws InstanciaNoExisteix Throws IOException en cas que no existeixi la contrasenya
     * @throws ClassNotFoundException Throws en cas que no es trobes la classe
     */
    public String getPassword(String username) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        return ctrlPersistencia.obtenirPasswordJugador(username);
    }

    //

    /**
     * Comprova que coincideixin usuari i contrasenya.
     * Si coincideixen, retorna true. Si no, false.
     * 
     * @param username  Nom d'usuari introduit
     * @param password  Contrasenya introduida
     * @throws IOException              Error d'entrada i sortida
     * @throws InstanciaNoExisteix      El nom d'usuari es incorrecte
     * @throws ClassNotFoundException   No s'ha trobat el Jugador amb el nom
     * @throws ContrasenyaIncorrecte    La contrasenya es incorrecta
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
     * Logoff: posa el JugadorActual del ctrlJugador a null
     */
    public void logoff() {
        ctrlJugador.logoff();
    }

    //! del CtrlRanquing
    /**
     * Obte les dades del ranquing actual.
     * @return Retorna el ranquing actual
     */
    public Ranquing getRanquing() {
        return ctrlRanquing.getRanquing();
    }

    /**
     * Crea una instància de ranquing dins el controlador del ranquing
     */
    public void crearRanquing() {
        ctrlRanquing.crearRanquing();
    }

    /**
     * Retorna les 10 puntuacions mes altes
     * @return Les 10 millors puntuacions amb el jugador corresponent, en el format {idJugador, puntuacio}
     */
    public ArrayList<Integer[]> getTop10(){
        return ctrlRanquing.getTop10();
    }

    //CtrlEstadistiquesPartida

    /**
     * La partida actual de tipus Codebreaker s'ha acabat.
     * 
     * Es crea la seva estadisticaPartida pertinent.
     * La puntuacio es calcula com a (100 - intents), on intents es correspon al nombre de rondes jugades.
     * Despres afegim una nova entrada al ranquing, en cas que haguem guanyat la partida. 
     * 
     * @param guanyada Guanyada indica true si la partida s'ha guanyat.
     * @return puntuacio final de la partida
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

    /**
     * La partida actual de tipus Codemaker s'ha acabat.
     * Es retorna la puntuacio final.
     * La puntuacio es calcula com a (100 - intents), on intents es correspon al nombre de rondes jugades.
     * @return puntuacio final de la partida
     */
    public Integer partidaAcabadaCodemaker() {
        Integer numRondes = ctrlPartida.getNumeroRondes();

        return 100 - numRondes;
    }

    /**
     * Agafem les estadistiques que volem.
     * Nomes ens interessa la puntuacio, però es deixa obert a la extensió.
     *  
     * @param idJugador Identificador del jugador
     * @param idPartida Identificador de la partida
     * @return retorna les estadistiques de la partida (actualment nomes la puntuacio)
     */
    public int getEstadistiques(Integer idJugador, Integer idPartida) {
        return ctrlEstadistiquesPartida.getPuntuacio(idJugador, idPartida);
    }

    /**
     * Carrega una partida des del disc.
     * 
     * @param idPartida Identificador de la partida
     * @throws IOException  Error d'entrada i sortida
     * @throws InstanciaNoExisteix  No existeix la partida indicada
     * @throws ClassNotFoundException   No s'ha aconseguit obtenir la instancia a partir de la id
     */
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