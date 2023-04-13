package domini.controllers;

import java.util.ArrayList;

import domini.classes.Ranquing;
//import java.util.ArrayList;


public class CtrlRanquing {
    private Ranquing ranquing;

    public CtrlRanquing() {
        ranquing = null;
    }

    public void crearRanquing() {
        ranquing = new Ranquing();
    }

    public Ranquing getRanquing() {
        return ranquing;
    }

    public ArrayList<Integer[]> getTop10(){
        return ranquing.getTopN(10);
    }

    public void setNewRecord(Integer idJugador, Integer puntacio) {
        ranquing.addEstadistica(new Integer[] {idJugador, puntacio});
    }
}
