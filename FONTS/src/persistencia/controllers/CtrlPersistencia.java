package persistencia.controllers;

import java.io.IOException;
import java.util.*;

import domini.classes.*;
import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;
import persistencia.classes.*;

public class CtrlPersistencia {
    GestorJugador gestorJugador;
    GestorPartida gestorPartida;
    GestorConfiguracioPartida gestorConfiguracioPartida;
    GestorFiveGuess gestorFiveGuess;
    GestorEstadistiquesPartida gestorEstadistiquesPartida;
    
    public CtrlPersistencia() throws IOException {
        gestorJugador = new GestorJugador();
        gestorPartida = new GestorPartida();
        gestorConfiguracioPartida = new GestorConfiguracioPartida();
        gestorFiveGuess = new GestorFiveGuess();
        gestorEstadistiquesPartida = new GestorEstadistiquesPartida();
    }
    
    public void afegirJugador(String idJugador, String username, String password) throws IOException, InstanciaJaExisteix {
        DAOJugador daoJ = new DAOJugador(username, password);
        gestorJugador.afegirJugador(idJugador, daoJ);
    }

    public void actualitzarJugador(String idJugador, String username, String password) throws IOException, InstanciaNoExisteix  {
        DAOJugador daoJ = new DAOJugador(username, password);
        gestorJugador.actualitzarJugador(idJugador, daoJ);
    }

    public Jugador obtenirJugador(String idJugador) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOJugador daoJ = gestorJugador.obtenirJugador(idJugador);
        Jugador j = null;
        try {
            j = new Jugador(
                Integer.valueOf(idJugador),
                daoJ.getUsername(),
                daoJ.getPassword()
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return j;
    }

    public void eliminarJugador(String idJugador) throws IOException, InstanciaNoExisteix {        
        gestorJugador.eliminarJugador(idJugador);
    }

    public boolean existeixJugador(String idJugador) throws IOException {
        return gestorJugador.existeixJugador(idJugador);
    }

    public void afegirPartida(String idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes, String tipusAlgorisme) throws IOException, InstanciaJaExisteix {
        DaoPartida daoP = new DAOPartida(idPartida, solutionCode, rondes, tipusAlgorisme);
        gestorPartida.afegirPartida(idPartida, daoP);
    }

    public void actualitzarPartida(String idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes, String tipusAlgorisme) throws IOException, InstanciaNoExisteix  {
        DAOPartida daoP = new DAOPartida(idPartida, solutionCode, rondes, tipusAlgorisme);
        gestorPartida.actualitzarPartida(idPartida, daoP);
    }

    public Partida obtenirPartida(String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOPartida daoP = gestorPartida.obtenirPartida(idPartida);
        Partida p = null;
        try {
            p = new Partida(
                Integer.valueOf(idPartida),
                daoP.getSolutionCode(),
                daoP.getRondes(),
                daoP.getTipusAlgorisme()
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return p;
    }

    public void eliminarPartida(String idPartida) throws IOException, InstanciaNoExisteix {        
        gestorPartida.eliminarPartida(idPartida);
    }

    public boolean existeixPartida(String idPartida) throws IOException {
        return gestorPartida.existeixPartida(idPartida);
    }
    
    public void afegirConfiguracioPartida(String idPartida, TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException, InstanciaJaExisteix {
        DAOConfiguracioPartida daoCP = new DAOConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
        gestorConfiguracioPartida.afegirConfiguracioPartida(idPartida, daoCP);
    }

    public void actualitzarConfiguracioPartida(String idPartida, TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException, InstanciaNoExisteix {
        DAOConfiguracioPartida daoCP = new DAOConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
        gestorConfiguracioPartida.actualitzarConfiguracioPartida(idPartida, daoCP);
    }

    public ConfiguracioPartida obtenirConfiguracioPartida(String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOConfiguracioPartida daoCP = gestorConfiguracioPartida.obtenirConfiguracioPartida(idPartida);
        ConfiguracioPartida cp = null;
        // ########### PREGUNTAR ###########
        // cp = new ConfiguracioPartida(
        //         daoCP.getTipusPartida(),
        //         daoCP.getNumeroIntents(),
        //         daoCP.getNumeroColors(),
        //         daoCP.getLongitudCombinacio()
        //     );
        try {
            cp = new ConfiguracioPartida(
                daoCP.getTipusPartida(),
                daoCP.getNumeroIntents(),
                daoCP.getNumeroColors(),
                daoCP.getLongitudCombinacio()
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return cp;
    }

    public void eliminarConfiguracioPartida(String idPartida) throws IOException, InstanciaNoExisteix {        
        gestorConfiguracioPartida.eliminarConfiguracioPartida(idPartida);
    }

    public boolean existeixConfiguracioPartida(String idPartida) throws IOException {
        return gestorConfiguracioPartida.existeixConfiguracioPartida(idPartida);
    }

    public void afegirFiveGuess(String idPartida, ArrayList<Integer[]> codisDisponibles, ArrayList<Integer[]> codisPossibles) throws IOException, InstanciaJaExisteix {
        DAOFiveGuess daoFG = new DAOFiveGuess(codisDisponibles, codisPossibles);
        gestorFiveGuess.afegirFiveGuess(idPartida, daoFG);
    }

    public void actualitzarFiveGuess(String idPartida, ArrayList<Integer[]> codisDisponibles, ArrayList<Integer[]> codisPossibles) throws IOException, InstanciaNoExisteix  {
        DAOFiveGuess daoFG = new DAOFiveGuess();
        gestorFiveGuess.actualitzarFiveGuess(idPartida, daoFG);
    }

    public FiveGuess obtenirFiveGuess(String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOFiveGuess daoFG = gestorFiveGuess.obtenirFiveGuess(idPartida);
        FiveGuess fG = null;
        try {
            fG = new FiveGuess(
                daoFG.getCodisDisponibles(),
                daoFG.getCodisPossibles()
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return fG;
    }

    public void eliminarFiveGuess(String idPartida) throws IOException, InstanciaNoExisteix {        
        gestorFiveGuess.eliminarFiveGuess(idPartida);
    }

    public boolean existeixFiveGuess(String idPartida) throws IOException {
        return gestorFiveGuess.existeixFiveGuess(idPartida);
    }

    public void afegirEstadistiquesPartida(String idJugador, String idPartida, Integer puntuacio, boolean guanyada) throws IOException, InstanciaJaExisteix {
        DAOEstadistiquesPartida daoEP = new DAOEstadistiquesPartida(idJugador, idPartida, puntuacio, guanyada);
        gestorEstadistiquesPartida.afegirEstadistiquesPartida(idJugador, idPartida, daoEP);
    }

    public void actualitzarEstadistiquesPartida(String idJugador, String idPartida, Integer puntuacio, boolean guanyada) throws IOException, InstanciaNoExisteix  {
        DAOEstadistiquesPartida daoEP = new DAOEstadistiquesPartida(idJugador, idPartida, puntuacio, guanyada);
        gestorEstadistiquesPartida.actualitzarEstadistiquesPartida(idJugador, idPartida, daoEP);
    }

    public EstadistiquesPartida obtenirEstadistiquesPartida(String idJugador, String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOEstadistiquesPartida daoEP = gestorEstadistiquesPartida.obtenirEstadistiquesPartida(idJugador, idPartida);
        EstadistiquesPartida eP = null;
        try {
            eP = new EstadistiquesPartida(
                Integer.valueOf(idJugador),
                Integer.valueOf(idPartida),
                daoEP.getPuntuacio(),
                daoEP.getGuanyada()
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return eP;
    }

    public void eliminarEstadistiquesPartida(String idEstadistiquesPartida) throws IOException, InstanciaNoExisteix {        
        gestorEstadistiquesPartida.eliminarEstadistiquesPartida(idEstadistiquesPartida);
    }

    public boolean existeixEstadistiquesPartida(String idEstadistiquesPartida) throws IOException {
        return gestorEstadistiquesPartida.existeixEstadistiquesPartida(idEstadistiquesPartida);
    }
}