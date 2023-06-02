package domini.controllers;

import domini.classes.actions.ComparaRespostesAction;
import domini.classes.actions.CorregeixAction;
import domini.classes.*;
import domini.classes.exceptions.*;
import persistencia.controllers.CtrlPersistencia;
import presentacio.custom.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.String;
import java.io.*;

/**
 * Controlador principal de la capa de domini, dedicat a la comunicació amb les capes de presentació i persistència
 */
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
        ctrlPersistencia.afegirConfiguracioPartida(String.valueOf(ctrlPartida.getPartidaActual().getId()), String.valueOf(TipusPartida.CODEBREAKER), numeroIntents, numeroColors, longitudCombinacio);
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
    public void crearPartidaCodemaker(int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode, String tipusAlgorisme) throws Exception {
        System.out.println("tipusString: "+tipusAlgorisme);
        TipusAlgorisme tipusAlgorisme1 = TipusAlgorisme.valueOf(tipusAlgorisme);
        System.out.println(tipusAlgorisme1.toString());
        ctrlPartida.crearPartidaCodemaker(ctrlPersistencia.totalPartides(), numeroIntents, numeroColors, longitudCombinacio, solutionCode, tipusAlgorisme1);
        System.out.println("crear partida codemaker " +String.valueOf(TipusPartida.CODEMAKER));
        System.out.println("crear partida codemaker " +TipusPartida.CODEMAKER.toString());
        ctrlPersistencia.afegirConfiguracioPartida(String.valueOf(ctrlPartida.getPartidaActual().getId()), String.valueOf(TipusPartida.CODEMAKER), numeroIntents, numeroColors, longitudCombinacio);
    }

    /**
    * Estableix la correccio corresponent a aquesta ronda (la resposta a la combinacio intentada)
    * @param
    */
    public Boolean setCorreccioRonda(String respostaCombinacioUsuari) {
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        Integer[] ultimaCombinacio = ctrlPartida.getUltimaCombinacio();
        for (Integer integer : ultimaCombinacio) {
            System.out.print(integer);
        }
        System.out.println("ffffffffffffffffffffffffffffffff");
        System.out.println();
        Integer[] codiSolution = ctrlPartida.getSolutionCode();
        for (Integer integer : codiSolution) {
            System.out.print(integer);
        }
        System.out.println("ggggggggggggggggggggggggggggggggggg");
        System.out.println();
        System.out.println(ctrlPartida.getPartidaActual().getRondes().size());

        String respostaCombinacio = CorregeixAction.corregeix(ctrlPartida.getUltimaCombinacio(), ctrlPartida.getSolutionCode());
        System.out.println(respostaCombinacio);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        if (ComparaRespostesAction.comparaRespostes(respostaCombinacio, respostaCombinacioUsuari)){
            ctrlPartida.setCorreccioRonda(respostaCombinacio);
            return true;
        }
        return false;
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
        crearRonda();
        ctrlPartida.intentarCombinacio(combinacioIntentada);
        System.out.println(ctrlPartida.getUltimaCombinacio());
        String correccio = CorregeixAction.corregeix(combinacioIntentada, ctrlPartida.getSolutionCode());
        ctrlPartida.setCorreccioRonda(correccio);
        return correccio;
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
    public Integer[] jugarRondaCodeMaker() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes{
        Integer[] combinacioIntentada = ctrlPartida.getCodiMaquina();

        System.out.println("ctrlPartida.getCodiMaquina() " + combinacioIntentada);
        for (int i=0; i<combinacioIntentada.length; ++i)            
            System.out.print(" " + combinacioIntentada[i]);
        System.out.print("ctrlPartida.getCodiMaquina() ");


        crearRonda();
        ctrlPartida.intentarCombinacio(combinacioIntentada);
        // String combinacioIntentadaString = "";
        // for (int i = 0; i < combinacioIntentada.length; i++){
        //     combinacioIntentadaString += combinacioIntentada[i].toString();
        // }
        // return combinacioIntentadaString;
        return combinacioIntentada;
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

        ctrlPersistencia.afegirJugador(newId, username, password, j.getIdPartides());
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
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Pair<String, Integer>> getTop10() throws IOException, ClassNotFoundException {
        //obtenir totes les estadistiquespartides existents del disc i actualitzar ranquing
        ArrayList<String> ids = ctrlPersistencia.obtenirIdJugadorsEstadistiquesPartida();

        Iterator it = ids.iterator();
        
        while(it.hasNext()) {
            String username = (String) it.next();
            ArrayList<String[]> statsJugador = ctrlPersistencia.obtenirEstadistiquesPartidesJugador(username);

            Iterator it2 = statsJugador.iterator();
            while(it2.hasNext()) {
                String[] infoPartida = (String[]) it2.next();
                if (infoPartida[1] == "true") {
                    ctrlRanquing.setNewRecord(username, Integer.parseInt(infoPartida[0]));
                }
            }
        }

        //retornar el top10
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
        String username = ctrlJugador.getLoggedPlayerUsername();
        Integer numRondes = ctrlPartida.getNumeroRondes();
        System.out.println("idPartida "+String.valueOf(idPartida));
        System.out.println("idJugador "+String.valueOf(username));
        System.out.println("numRondes "+String.valueOf(numRondes));

        Integer puntuacio = 100 - numRondes;

        ctrlEstadistiquesPartida.creaEstadistiquesPartida(username, idPartida, puntuacio, guanyada);
        EstadistiquesPartida e = ctrlEstadistiquesPartida.getEstadistiquesPartida(username, idPartida);
        System.out.println(e.getPuntuacio());
        System.out.println(String.valueOf(e.getIdPartida()));
        System.out.println(String.valueOf(e.getGuanyada()));
        System.out.println(String.valueOf(e.getUsername()));

        //Afegim la estadistica creada a Jugador i a Partida
        ctrlPartida.addEstadistiquesPartida(e);
        ctrlJugador.addEstadistica(e);

        if (guanyada) ctrlRanquing.setNewRecord(username, puntuacio);
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
     * Retorna un pair de puntuacio de la partida i el numero d'intents
     *
     * @return retorna les estadistiques de la partida (actualment nomes la puntuacio i numero d'intents)
     */
    public Pair getEstadistiques() {
        System.out.println("Id jugador: "+ctrlJugador.getIdJugador());
        System.out.println("Id partida actual: "+ctrlPartida.getIdPartidaActual());
        System.out.println("nRondes: "+ctrlPartida.getNumeroRondes());
        System.out.println("Puntuacio: "+ctrlEstadistiquesPartida.getPuntuacio(ctrlJugador.getLoggedPlayerUsername(), ctrlPartida.getIdPartidaActual()));
        return new Pair(ctrlEstadistiquesPartida.getPuntuacio(ctrlJugador.getLoggedPlayerUsername(), ctrlPartida.getIdPartidaActual()), ctrlPartida.getNumeroRondes());
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

        String tipusPartida = ctrlPersistencia.obtenirTipusPartida(strIdpartida);
        Partida p = ctrlPersistencia.obtenirPartida(strIdpartida, tipusPartida);

        ConfiguracioPartida configuracioPartida = ctrlPersistencia.obtenirConfiguracioPartida(strIdpartida);
        System.out.println(configuracioPartida);
        p.setConfiguracioPartida(configuracioPartida);

//        EstadistiquesPartida estadistiquesPartida = ctrlPersistencia.obtenirEstadistiquesPartida(ctrlJugador.getLoggedPlayerUsername(), strIdpartida);
//        p.setEstadisticaPartida(estadistiquesPartida);

        if (tipusPartida.equals("CODEMAKER")) {
            TipusAlgorisme tipusAlgorisme = p.getTipusAlgorisme();

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
                ((Codemaker) p).setGenetic(genetic);
            }
        }
        ctrlPartida.setPartidaActual(p);
    }

    /**
     * Passa la informacio necessaria a CtrlPersistencia per guardar la partida actual
     * @throws InstanciaNoExisteix Es llança quan la partida que es vol guardar no existeix al sistema
     * @throws IOException Es llança quan persistencia no pot guardar l'arxiu
     */
    public void guardarPartidaActual() throws IOException, InstanciaJaExisteix, InstanciaNoExisteix {
        Partida p = ctrlPartida.getPartidaActual();
        HashMap<Integer, Ronda> rondes = p.getRondes();
        int midaRondes = rondes.size();

        ctrlJugador.getJugadorActual().afegirIdPartida(p.getId());
        Jugador j = ctrlJugador.getJugadorActual();
        ctrlPersistencia.actualitzarJugador(j.getID(), j.getUsername(), j.getPassword(), j.getIdPartides());
        System.out.println(j.getIdPartides().size());

        Integer[] idRondes = new Integer[midaRondes];
        Integer[][] combinacionsIntentades = new Integer[midaRondes][];
        String[] correccions = new String[midaRondes];

        if (p.getConfiguracioPartida().getTipusPartida() == TipusPartida.CODEBREAKER) {
            for (int i = 0; i < rondes.size(); ++i) {
                Ronda ronda = rondes.get(i);
                idRondes[i] = ronda.getId();
                combinacionsIntentades[i] = ronda.getCombinacioIntentada();
                correccions[i] = ronda.getCorreccio();
            }
        }
        else { // CODEMAKAER
            for (int i = 0; i < rondes.size(); ++i) {
                Ronda ronda = rondes.get(i);
                idRondes[i] = ronda.getId();
                combinacionsIntentades[i] = ronda.getCombinacioIntentada();
                if (i < rondes.size() - 1)
                    correccions[i] = ronda.getCorreccio();
            }
        }
        

        try {
            if (p.getTipusAlgorisme() == null) {
                ctrlPersistencia.actualitzarPartida(String.valueOf(p.getId()), p.getSolutionCode(), idRondes, combinacionsIntentades, correccions, "");
            }
            else {
                ctrlPersistencia.actualitzarPartida(String.valueOf(p.getId()), p.getSolutionCode(), idRondes, combinacionsIntentades, correccions, p.getTipusAlgorisme().toString());
                if (p.getTipusAlgorisme() == TipusAlgorisme.FIVEGUESS) {
                    ctrlPersistencia.actualitzarFiveGuess(String.valueOf(p.getId()), p.getFiveGuess().getCodisDisponibles(), p.getFiveGuess().getCodisPossibles());
                }
            }

        } catch (InstanciaNoExisteix e) {
            if (p.getTipusAlgorisme() == null) {
                ctrlPersistencia.afegirPartida(String.valueOf(p.getId()), p.getSolutionCode(), idRondes, combinacionsIntentades, correccions, "");
            }
            else {
                ctrlPersistencia.afegirPartida(String.valueOf(p.getId()), p.getSolutionCode(), idRondes, combinacionsIntentades, correccions, p.getTipusAlgorisme().toString());
                if (p.getTipusAlgorisme() == TipusAlgorisme.FIVEGUESS) {
                    ctrlPersistencia.afegirFiveGuess(String.valueOf(p.getId()), p.getFiveGuess().getCodisDisponibles(), p.getFiveGuess().getCodisPossibles());
                }
            }
        }
    }


    public ArrayList<String[]> getInfoPartidesGuardades() throws IOException, ClassNotFoundException, InstanciaNoExisteix {
        ArrayList<Integer> idPartides = ctrlJugador.getJugadorActual().getIdPartides();
        System.out.println(idPartides.size());
        for (int i = 0; i < idPartides.size(); ++i) {
            System.out.println(idPartides.get(i) + "David Barranquero");
        }

        ArrayList<String[]> infoPartidesNoAcabades = new ArrayList<>();

        for (Integer idPartida : idPartides) {
            if (! ctrlPersistencia.existeixEstadistiquesPartida(ctrlJugador.getLoggedPlayerUsername(), String.valueOf(idPartida))) {
                ConfiguracioPartida configuracioPartida = ctrlPersistencia.obtenirConfiguracioPartida(String.valueOf(idPartida));
                infoPartidesNoAcabades.add(new String[]{
                        String.valueOf(idPartida),
                        String.valueOf(configuracioPartida.getNumeroIntents()),
                        String.valueOf(configuracioPartida.getNumeroColors()),
                        String.valueOf(configuracioPartida.getLongitudCombinacio()),
                        String.valueOf(configuracioPartida.getTipusPartida())
                });
            }
        }
        return infoPartidesNoAcabades;
    }

    public void elimiarPartidaActual() throws IOException, InstanciaNoExisteix {
        String idPartida = String.valueOf(ctrlPartida.getIdPartidaActual());
        ctrlJugador.getJugadorActual().eliminarPartida(Integer.valueOf(idPartida));
        if (ctrlPersistencia.existeixPartida(idPartida)) {
            ctrlPersistencia.eliminarPartida(idPartida);
            if (ctrlPartida.getPartidaActual().getConfiguracioPartida().getTipusPartida() == TipusPartida.CODEMAKER && ctrlPartida.getPartidaActual().getTipusAlgorisme() == TipusAlgorisme.FIVEGUESS) 
                ctrlPersistencia.eliminarFiveGuess(idPartida);
            ctrlPersistencia.eliminarConfiguracioPartida(idPartida);
        }
    }

    public Integer[] getSolutionCodePartidaGuardada(String idPartida, String tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        Partida p = ctrlPersistencia.obtenirPartida(idPartida, tipusPartida);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(p);
        System.out.println(p.getId());

        Integer [] combinacioIntentada = p.getUltimaCombIntentada();
        for (int i = 0; i < combinacioIntentada.length; ++i)
            System.out.print(combinacioIntentada[i]);
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        String correcio = p.getUltimaCorreccio();
        if (correcio != null) {
            for (int i = 0; i < correcio.length(); ++i)
                System.out.print(correcio.charAt(i));
            System.out.println("cccccccccccccccccccccccccccccc");
        }

        Integer [] combinacioSolucio = p.getSolutionCode();
        for (int i = 0; i < combinacioSolucio.length; ++i)
            System.out.print(combinacioSolucio[i]);
        System.out.println("dddddddddddddddddddddddddddd");

        return p.getSolutionCode();
    }

    public Integer[][] getCombinacionsIntentades(String idPartida, String tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        Partida p = ctrlPersistencia.obtenirPartida(idPartida, tipusPartida);

        Integer[][] combinacionsIntentades= new Integer[p.rondesJugades()][];
        HashMap<Integer, Ronda> rondes = p.getRondes();
        for (int i = 0; i < p.rondesJugades(); ++i) {
            combinacionsIntentades[i] = rondes.get(i).getCombinacioIntentada();
        }
        return combinacionsIntentades;
    }

    public String[] getCorreccions(String idPartida, String tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        Partida p = ctrlPersistencia.obtenirPartida(idPartida, tipusPartida);
        
        System.out.println("Get correccions " + p);
        String[] correccions = new String[p.rondesJugades()];
        HashMap<Integer, Ronda> rondes = p.getRondes();
        System.out.println("Get correccions " + rondes);
        System.out.println("Get correccions " + rondes.size());
        System.out.println(p.rondesJugades());

        if (tipusPartida.equals("CODEBREAKER")) {
            System.out.println("Get correccions codebreaker");
            for (int i = 0; i < p.rondesJugades(); ++i) {
                correccions[i] = rondes.get(i).getCorreccio();
                System.out.println(correccions[i] + " ctrldomini");
            }
        }
        else { // CODEMAKAER
            for (int i = 0; i < p.rondesJugades()-1; ++i) {
                System.out.println("Get correccions codemaker");
                correccions[i] = rondes.get(i).getCorreccio();
                System.out.println(correccions[i] + " ctrldomini");
            }
        }
        return correccions;
    }
}