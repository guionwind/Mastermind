package presentacio.controllers;

import domini.classes.exceptions.*;
import domini.controllers.CtrlDomini;
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

    public static void vistaEstadistiquesPartida(Point location) {
        VistaEstadistiquesPartida vE = new VistaEstadistiquesPartida(location);
    }

    public static void vistaPartida(Point location) {
        VistaPartida vE = new VistaPartida(location);
    }

    public static void vistaCarregarPartida(Point location) {
        VistaPartidesGuardades vP = new VistaPartidesGuardades(location);
    }

    public static void vistaRanquing(Point location) {
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

    public static void crearPartidaCodebreaker(int intents, int colors, int longitud, Integer[] combinacio, String tipusAlgorisme) throws Exception {
        ctrlDomini.crearPartidaCodebreaker(intents, colors, longitud);
    }

    public static void jugarRondaCodebreaker(Integer[] combinacio) {
        ctrlDomini.jugarRondaCodebreaker(combinacio);
    }

    public static void jugarRondaCodemaker() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        ctrlDomini.jugarRondaCodeMaker();
    }

    public static int partidaAcabada(Boolean guanyada){
        return ctrlDomini.partidaAcabada(guanyada);
    }

    public static int partidaAcabadaCodemaker(){
        return ctrlDomini.partidaAcabadaCodemaker();
    }

    public static void setCorreccioRonda(String combinacio){
        ctrlDomini.setCorreccioRonda(combinacio);
    }

    public static ArrayList<Integer[]> getTop10(){
        return ctrlDomini.getTop10();
    }
    
    public static String demanarPista(){
        return ctrlDomini.demanarPista();
    }

    public static void guardarPartida() throws IOException, InstanciaNoExisteix {
        ctrlDomini.guardarPartidaActual();
    }

    public static void getEstadistiquesPartida() {
        ctrlDomini.getEstadistiques();
    }

    public static void tancarSessio() {
        ctrlDomini.logoff();
    }
}
