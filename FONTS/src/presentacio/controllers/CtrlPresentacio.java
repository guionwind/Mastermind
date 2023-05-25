package presentacio.controllers;

import domini.controllers.CtrlDomini;
import presentacio.views.*;

import java.awt.*;
import java.io.IOException;

public class CtrlPresentacio {
    private CtrlDomini cd;

    public CtrlPresentacio() throws IOException {
        cd = new CtrlDomini();
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

    }

    public static void main (String [] args)  {
        iniPresentacio();
    }
}
