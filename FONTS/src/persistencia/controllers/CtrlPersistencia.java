package persistencia.controllers;

import java.io.IOException;

import domini.classes.TipusPartida;
import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;
import persistencia.classes.*;

public class CtrlPersistencia {
    GestorJugador gestorJugador;
    GestorPartida gestorPartida;
    GestorConfiguracioPartida gestorConfiguracioPartida;
    GestorFiveGuess gestorFiveGuess;
    GestorEstadistiquesPartida gestorEstadistiquesPartida;
    GestorRanquing gestorRanquing;
    
    public CtrlPersistencia() throws IOException {
        gestorJugador = new GestorJugador();
        gestorPartida = new GestorPartida();
        gestorConfiguracioPartida = new GestorConfiguracioPartida();
        gestorFiveGuess = new GestorFiveGuess();
        gestorEstadistiquesPartida = new GestorEstadistiquesPartida();
        gestorRanquing = new GestorRanquing();
    }

    public void afegirConfiguracioPartida(String idPartida, TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException, InstanciaJaExisteix {
        DAOConfiguracioPartida cp = new DAOConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
        gestorConfiguracioPartida.afegirConfiguracioPartida(idPartida, cp);
    }

    public void actualitzarConfiguracioPartida(String idPartida, TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException, InstanciaNoExisteix {
        DAOConfiguracioPartida cp = new DAOConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
        gestorConfiguracioPartida.actualitzarConfiguracioPartida(idPartida, cp);
    }

    public DAOConfiguracioPartida obtenirConfiguracioPartida(String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOConfiguracioPartida cp = obtenirConfiguracioPartida(idPartida);
        return cp;
    }

    public void eliminarConfiguracioPartida(String idPartida) throws IOException, InstanciaNoExisteix {        
        gestorConfiguracioPartida.eliminarConfiguracioPartida(idPartida);
    }

    public boolean existeixConfiguracioPartida(String idPartida) throws IOException {
        return gestorConfiguracioPartida.existeixConfiguracioPartida(idPartida);
    }
}