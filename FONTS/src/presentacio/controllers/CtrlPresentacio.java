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

    public CtrlPresentacio() {
        iniPresentacio();
    }

    public static void iniPresentacio() {
        VistaPrincipal vP = new VistaPrincipal(null);
        ctrlDomini.crearRanquing();

    }
    public static void vistaPrincipal(Point location) {
        VistaPrincipal vP = new VistaPrincipal(location);
    }

    public static void vistaTutorial(Point location) {
        VistaTutorial vT = new VistaTutorial(location);
    }

    public static void vistaLogin(Point location) {
        VistaLogin vL = new VistaLogin(location);
    }

    public static void vistaRegistre(Point location) {
        VistaRegister vL = new VistaRegister(location);
    }

    public static void vistaMenuInicial(Point location) {
        VistaMenuInicial vM = new VistaMenuInicial(location);
    }

    public static void vistaConfiguracioPartida(Point location) {
        VistaConfiguracioPartida vC = new VistaConfiguracioPartida(location);
    }

    public static void vistaEstadistiquesPartida(Point location, String status) {
        Pair statics = getEstadistiquesPartida();
        int puntuacio = (Integer) statics.getFirst();
        int rondes = (Integer) statics.getSecond();
        System.out.println("puntuacio: "+puntuacio);
        System.out.println("rondes: "+rondes);
        VistaEstadistiquesPartida vE = new VistaEstadistiquesPartida(location, puntuacio, rondes, status);
    }

    public static void vistaPartida(Point location, int intents, int colors, int longitud, Integer[] combinacio, String tipusPartida) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        VistaPartida vE = new VistaPartida(location, intents, colors, longitud, combinacio, tipusPartida);
    }

    public static void vistaCarregarPartida(Point location) {
        VistaPartidesGuardades vP = new VistaPartidesGuardades(location);
    }

    public static void vistaRanquing(Point location) throws IOException, ClassNotFoundException {
        VistaRanquing vR = new VistaRanquing(location);
    }

    public static void main (String [] args)  {
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

    public static ArrayList<Integer[]> getTop10() throws IOException, ClassNotFoundException {
        return ctrlDomini.getTop10();
    }
    
    public static String demanarPista(){
        return ctrlDomini.demanarPista();
    }

    public static void guardarPartida() throws IOException, InstanciaNoExisteix {
        ctrlDomini.guardarPartidaActual();
    }

    public static Pair getEstadistiquesPartida() {
        return ctrlDomini.getEstadistiques();
    }

    public static void tancarSessio() {
        ctrlDomini.logoff();
    }
}
