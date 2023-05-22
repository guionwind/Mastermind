package persistencia.controllers;

import java.io.IOException;

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
    GestorRanquing gestorRanquing;
    
    public CtrlPersistencia() throws IOException {
        gestorJugador = new GestorJugador();
        gestorPartida = new GestorPartida();
        gestorConfiguracioPartida = new GestorConfiguracioPartida();
        gestorFiveGuess = new GestorFiveGuess();
        gestorEstadistiquesPartida = new GestorEstadistiquesPartida();
        gestorRanquing = new GestorRanquing();
    }
    
    public void afegirJugador(String idJugador, ) throws IOException, InstanciaJaExisteix {
        DAOJugador daoJ = new DAOJugador();
        gestorJugador.afegirJugador(idJugador, daoJ);
    }

    public void actualitzarJugador(String idJugador, ) throws IOException, InstanciaNoExisteix  {
        DAOJugador daoJ = new DAOJugador();
        gestorJugador.actualitzarJugador(idJugador, daoJ);
    }

    public Jugador obtenirJugador(String idJugador) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOJugador daoJ = gestorJugador.obtenirJugador(idJugador);
        Jugador j = null;
        try {
            j = new Jugador(
                
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

    public void afegirPartida(String idPartida, ) throws IOException, InstanciaJaExisteix {
        DaoPartida daoP = new DaoPartida();
        gestorPartida.afegirPartida(idPartida, daoP);
    }

    public void actualitzarPartida(String idPartida, ) throws IOException, InstanciaNoExisteix  {
        DAOPartida daoP = new DAOPartida();
        gestorPartida.actualitzarPartida(idPartida, daoP);
    }

    public Partida obtenirPartida(String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOPartida daoP = gestorPartida.obtenirPartida(idPartida);
        Partida p = null;
        try {
            p = new Partida(
                
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

    public void afegirFiveGuess(String idFiveGuess, ) throws IOException, InstanciaJaExisteix {
        DaoFiveGuess daoFG = new DaoFiveGuess();
        gestorFiveGuess.afegirFiveGuess(idFiveGuess, daoFG);
    }

    public void actualitzarFiveGuess(String idFiveGuess, ) throws IOException, InstanciaNoExisteix  {
        DAOFiveGuess daoFG = new DAOFiveGuess();
        gestorFiveGuess.actualitzarFiveGuess(idFiveGuess, daoFG);
    }

    public FiveGuess obtenirFiveGuess(String idFiveGuess) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOFiveGuess daoFG = gestorFiveGuess.obtenirFiveGuess(idFiveGuess);
        FiveGuess fG = null;
        try {
            fG = new FiveGuess(
                
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return fG;
    }

    public void eliminarFiveGuess(String idFiveGuess) throws IOException, InstanciaNoExisteix {        
        gestorFiveGuess.eliminarFiveGuess(idFiveGuess);
    }

    public boolean existeixFiveGuess(String idFiveGuess) throws IOException {
        return gestorFiveGuess.existeixFiveGuess(idFiveGuess);
    }

    public void afegirEstadistiquesPartida(String idEstadistiquesPartida, ) throws IOException, InstanciaJaExisteix {
        DaoEstadistiquesPartida daoEP = new DaoEstadistiquesPartida();
        gestorEstadistiquesPartida.afegirEstadistiquesPartida(idEstadistiquesPartida, daoEP);
    }

    public void actualitzarEstadistiquesPartida(String idEstadistiquesPartida, ) throws IOException, InstanciaNoExisteix  {
        DAOEstadistiquesPartida daoEP = new DAOEstadistiquesPartida();
        gestorEstadistiquesPartida.actualitzarEstadistiquesPartida(idEstadistiquesPartida, daoEP);
    }

    public EstadistiquesPartida obtenirEstadistiquesPartida(String idEstadistiquesPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOEstadistiquesPartida daoEP = gestorEstadistiquesPartida.obtenirEstadistiquesPartida(idEstadistiquesPartida);
        EstadistiquesPartida eP = null;
        try {
            eP = new EstadistiquesPartida(
                
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

    public void afegirRanquing(String idRanquing, ) throws IOException, InstanciaJaExisteix {
        DaoRanquing daoR = new DaoRanquing();
        gestorRanquing.afegirRanquing(idRanquing, daoR);
    }

    public void actualitzarRanquing(String idRanquing, ) throws IOException, InstanciaNoExisteix  {
        DAORanquing daoR = new DAORanquing();
        gestorRanquing.actualitzarRanquing(idRanquing, daoR);
    }

    public Ranquing obtenirRanquing(String idRanquing) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAORanquing daoR = gestorRanquing.obtenirRanquing(idRanquing);
        Ranquing r = null;
        try {
            r = new Ranquing(
                
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return r;
    }

    public void eliminarRanquing(String idRanquing) throws IOException, InstanciaNoExisteix {        
        gestorRanquing.eliminarRanquing(idRanquing);
    }

    public boolean existeixRanquing(String idRanquing) throws IOException {
        return gestorRanquing.existeixRanquing(idRanquing);
    }
}