package domini.controllers;

import java.util.ArrayList;

import domini.classes.EstadistiquesPartida;
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

    public ArrayList<EstadistiquesPartida> getTop10(){
        return RanquingActual.getTopN(10);
    }

}
