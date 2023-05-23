package presentacio.controllers;

import domini.controllers.CtrlDomini;
import presentacio.views.*;
public class CtrlPresentacio {
    private CtrlDomini cd;

    public CtrlPresentacio() {
        cd = new CtrlDomini();
        iniPresentacio();
    }

    public static void iniPresentacio() {
        vistaPrincipal vP = new vistaPrincipal();
    }

    public static void vistaTutorial() {
        vistaTutorial vT = new vistaTutorial();
    }

    public static void main (String [] args) {
        CtrlPresentacio cP = new CtrlPresentacio();

    }
}
