package domini.controllers;

import domini.classes.*;
import java.util.ArrayList;
import java.lang.String;
import domini.classes.ConfiguracioPartida.TipusPartida;

public class CtrlDomini {
    private CtrlPartida ctrlPartida;
    private CtrlJugador ctrlJugador;
    private CtrlRanquing ctrlRanquing;


    public CtrlDomini() {
        ctrlPartida = new CtrlPartida();
        ctrlJugador = new CtrlJugador();
    }

    //! del CtrlPartida
    public void crearPartidaCodebreaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        ctrlPartida.crearPartidaCodebreaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
    }

    public void crearPartidaCodemaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) {
        ctrlPartida.crearPartidaCodemaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio, solutionCode);
    }

    public void creaRonda(Partida partida, int rondaId) {
    }

    //! del CtrlJugador
    public void crearJugador (String username, String password) {
        ctrlJugador.crearJugador(username, password);
    }

    //sistema de login: comprova que coincideixin usuari i contrasenya
    //si coincideixen, isPasswordCorrect
    public boolean loginAuthentication (String username, String password) {
        boolean correctCredentials = false;
        String passwd = ctrlJugador.getPassword(username);
        if (password != null) {
            //throw exception
        }
        else if (passwd == password) {
            correctCredentials = true;
        }
        return correctCredentials;
    }

    //! del CtrlRanquing

    public Ranquing getRanquing() {
        return ctrlRanquing.getRanquingActual();
    }

    public void crearRanquing() {
        ctrlRanquing.crearRanquing();
    }
    

}