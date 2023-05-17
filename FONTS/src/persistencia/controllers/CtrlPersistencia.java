package persistencia.controllers;

import persistencia.classes.*;

public class CtrlPersistencia {
    GestorJugador gestorJugador;
    GestorPartida gestorPartida;
    GestorConfiguracioPartida gestorConfiguracioPartida;
    GestorFiveGuess gestorFiveGuess;
    GestorEstadistiquesPartida gestorEstadistiquesPartida;
    GestorRanquing gestorRanquing;
    
    public CtrlPersistencia() {
        gestorJugador = new GestorJugador();
        gestorPartida = new GestorPartida();
        gestorConfiguracioPartida = new GestorConfiguracioPartida();
        gestorFiveGuess = new GestorFiveGuess();
        gestorEstadistiquesPartida = new GestorEstadistiquesPartida();
        gestorRanquing = new GestorRanquing();
    }

    
}