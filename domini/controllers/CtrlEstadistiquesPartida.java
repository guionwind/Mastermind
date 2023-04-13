package domini.controllers;

import domini.classes.EstadistiquesPartida;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class CtrlEstadistiquesPartida {

    private HashMap<Integer[], EstadistiquesPartida> estadistiquesPartida;

    public CtrlEstadistiquesPartida() {
        estadistiquesPartida = new HashMap<Integer[], EstadistiquesPartida>();
    }

    public void creaEstadistiquesPartida(Integer idJugador, Integer idPartida, Integer puntuacio, Boolean guanyada) {
        EstadistiquesPartida eP = new EstadistiquesPartida(idJugador, idPartida, puntuacio, guanyada);

        Integer[] pair = new Integer[]{idJugador, idPartida};
        estadistiquesPartida.put(pair ,eP);
    }



}
