package presentacio.controllers;

import domini.controllers.CtrlDomini;
import presentacio.views.*;

import java.io.IOException;

public class CtrlPresentacio {
    private CtrlDomini cd;

    public CtrlPresentacio() throws IOException {
        cd = new CtrlDomini();
        iniPresentacio();
    }

    public static void iniPresentacio() {
        VistaPrincipal vP = new VistaPrincipal();
    }

    public static void vistaTutorial() {
        VistaTutorial vT = new VistaTutorial();
    }

    public static void vistaLogin() {
        VistaLogin vL = new VistaLogin();
    }

    public static void vistaRegistre() {
        VistaRegister vL = new VistaRegister();
    }

    public static void vistaMenuInicial() {
        VistaMenuInicial vM = new VistaMenuInicial();
    }

    public static void vistaConfiguracioPartida() {
        VistaConfiguracioPartida vC = new VistaConfiguracioPartida();
    }

    public static void vistaEstadistiquesPartida() {
        VistaEstadistiquesPartida vE = new VistaEstadistiquesPartida();
    }

    public static void vistaPartida() {

    }

    public static void main (String [] args) {
        CtrlPresentacio cP = new CtrlPresentacio();

    }
}
