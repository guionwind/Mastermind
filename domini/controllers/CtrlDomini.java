package domini.controllers;

import domini.classes.*;
import java.util.ArrayList;
import java.lang.String;
import domini.classes.ConfiguracioPartida.TipusPartida;

public class CtrlDomini {
    private CtrlPartida ctrlPartida;
    private CtrlJugador ctrlJugador;


    public CtrlDomini() {
        ctrlPartida = new CtrlPartida();
        ctrlJugador = new CtrlJugador();
    }

    public void crearPartidaCodebreaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        ctrlPartida.crearPartidaCodebreaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
    }

    public void crearPartidaCodemaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) {
        ctrlPartida.crearPartidaCodemaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio, solutionCode);
    }


    public void crearJugador (String username, String password) {

    }

}