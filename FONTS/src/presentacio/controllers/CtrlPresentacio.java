package presentacio.controllers;

import domini.controllers.CtrlDomini;
import presentacio.views.*;
public class CtrlPresentacio {
    private static CtrlDomini cd = new CtrlDomini();

    public static void iniPresentacio() {
        VistaPrincipal vP = new VistaPrincipal();
    }

    public static void vistaTutorial() {
        VistaTutorial vT = new VistaTutorial();
    }
}
