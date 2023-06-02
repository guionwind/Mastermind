package presentacio.controllers;

import domini.classes.exceptions.*;
import domini.controllers.CtrlDomini;
import presentacio.custom.Pair;
import presentacio.views.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CtrlPresentacio {
    private static final CtrlDomini ctrlDomini;

    static {
        try {
            ctrlDomini = new CtrlDomini();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CtrlPresentacio() throws IOException {
        iniPresentacio();
    }

    public static void iniPresentacio() throws IOException {
        VistaPrincipal vP = new VistaPrincipal(null, -1);
        ctrlDomini.crearRanquing();
    }

    public static void vistaPrincipal(Point location, int state) throws IOException {
        VistaPrincipal vP = new VistaPrincipal(location, state);
    }

    public static void vistaTutorial(Point location, int state) throws IOException {
        VistaTutorial vT = new VistaTutorial(location, state);
    }

    public static void vistaLogin(Point location, int state) throws IOException {
        VistaLogin vL = new VistaLogin(location, state);
    }

    public static void vistaRegistre(Point location, int state) throws IOException {
        VistaRegister vL = new VistaRegister(location, state);
    }

    public static void vistaMenuInicial(Point location, int state) throws IOException {
        VistaMenuInicial vM = new VistaMenuInicial(location, state);
    }

    public static void vistaConfiguracioPartida(Point location, int state) throws IOException {
        VistaConfiguracioPartida vC = new VistaConfiguracioPartida(location, state);
    }

    public static void vistaEstadistiquesPartida(Point location, int state, String status) {
        Pair statics = getEstadistiquesPartida();
        int puntuacio = (Integer) statics.getFirst();
        int rondes = (Integer) statics.getSecond();
        System.out.println("puntuacio: "+puntuacio);
        System.out.println("rondes: "+rondes);
        VistaEstadistiquesPartida vE = new VistaEstadistiquesPartida(location, state, puntuacio, rondes, status);
    }

    public static void vistaPartida(Point location, int state, int intents, int colors, int longitud, Integer[] combinacio, String tipusPartida, Integer[][] combinacionsIntentades, String[] correccions) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        VistaPartida vE = new VistaPartida(location, state, intents, colors, longitud, combinacio, tipusPartida, combinacionsIntentades, correccions);
    }

    public static void vistaCarregarPartida(Point location, int state) throws IOException, ClassNotFoundException, InstanciaNoExisteix {
        VistaPartidesGuardades vP = new VistaPartidesGuardades(location, state);
    }

    public static void vistaRanquing(Point location, int state) throws IOException, ClassNotFoundException {
        VistaRanquing vR = new VistaRanquing(location, state);
    }

    public static void main (String [] args) throws IOException {
        iniPresentacio();
    }

    public static void login(String username, String password) throws IOException, InstanciaNoExisteix, ClassNotFoundException, ContrasenyaIncorrecte {
        ctrlDomini.loginAuthentication(username, password);
    }

    public static void register(String username, String password, String confirmPassword) throws InstanciaJaExisteix, JugadorJaExisteix, IOException, ContrasenyaNoCoincideix {
        ctrlDomini.registrarJugador(username, password, confirmPassword);
    }

    public static void carregarPartida(int id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        ctrlDomini.carregarPartida(id);
    }

    public static void crearPartidaCodemaker(int intents, int colors, int longitud, Integer[] combinacio, String tipusAlgorisme) throws Exception {
        ctrlDomini.crearPartidaCodemaker(intents, colors, longitud, combinacio, tipusAlgorisme); //FIXME COMPROVAR QUE FUNCIONE
    }

    public static void crearPartidaCodebreaker(int intents, int colors, int longitud) throws Exception {
        ctrlDomini.crearPartidaCodebreaker(intents, colors, longitud);
    }

    public static String jugarRondaCodebreaker(Integer[] combinacio) {
        return ctrlDomini.jugarRondaCodebreaker(combinacio);
    }

    public static String jugarRondaCodemaker() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        return ctrlDomini.jugarRondaCodeMaker();
    }

    public static int partidaAcabada(Boolean guanyada){
        return ctrlDomini.partidaAcabada(guanyada);
    }

    public static int partidaAcabadaCodemaker(){
        return ctrlDomini.partidaAcabadaCodemaker();
    }

    public static Boolean setCorreccioRonda(String combinacio){
        return ctrlDomini.setCorreccioRonda(combinacio);
    }

    public static ArrayList<Pair<String, Integer>> getTop10() throws IOException, ClassNotFoundException {
        return ctrlDomini.getTop10();
    }
    
    public static String demanarPista(){
        return ctrlDomini.demanarPista();
    }

    public static void guardarPartida() throws IOException, InstanciaNoExisteix, InstanciaJaExisteix {
        ctrlDomini.guardarPartidaActual();
    }

    public static Pair getEstadistiquesPartida() {
        return ctrlDomini.getEstadistiques();
    }

    public static void tancarSessio() {
        ctrlDomini.logoff();
    }

    public static ArrayList<String[]> getInfoPartidesGuardades() throws IOException, ClassNotFoundException, InstanciaNoExisteix {
        return ctrlDomini.getInfoPartidesGuardades();
    }

    public static void eliminarPartida() throws IOException, InstanciaNoExisteix {
        ctrlDomini.elimiarPartidaActual();
    }

    public static Integer[] getSolutionCode(String idPartida, String tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        return ctrlDomini.getSolutionCodePartidaGuardada(idPartida, tipusPartida);
    }

    public static Integer[][] getCombinacionsIntentades(String idPartida, String tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        return ctrlDomini.getCombinacionsIntentades(idPartida, tipusPartida);
    }

    public static String[] getCorrecions(String idPartida, String tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        return ctrlDomini.getCorreccions(idPartida, tipusPartida);
    }
}
