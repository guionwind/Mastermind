package presentacio.controllers;

import domini.classes.TipusAlgorisme;
import domini.classes.exceptions.*;
import domini.controllers.CtrlDomini;
import presentacio.views.*;

import java.awt.*;
import java.io.IOException;

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

    public static void vistaPartida() {
        VistaPartida vE = new VistaPartida();
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
        ctrlDomini.crearPartidaCodemaker(intents, colors, longitud, combinacio, domini.classes.TipusAlgorisme.valueOf(tipusAlgorisme)); //FIXME COMPROVAR QUE FUNCIONE
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

    public static void xd(){
        ctrlDomini.
    }
    
    public static String demanarPista(){
        return ctrlDomini.demanarPista();
    }

    public static void tancarSessio() {
        ctrlDomini.logoff();
    }
}
