package domini.controllers;

import domini.classes.Ranquing;
//import java.util.ArrayList;


public class CtrlRanquing {
    private Ranquing RanquingActual;

    public CtrlRanquing() {
        RanquingActual = null;
    }

    public void crearRanquing() {
        RanquingActual = new Ranquing();
    }

    public Ranquing getRanquingActual() {
        return RanquingActual;
    }

}
