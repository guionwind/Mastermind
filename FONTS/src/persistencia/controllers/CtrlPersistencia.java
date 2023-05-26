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
    
    public void afegirJugador(int idJugador, String username, String password) throws IOException, InstanciaJaExisteix {
        DAOJugador daoJ = new DAOJugador(idJugador, password);
        gestorJugador.afegirJugador(username, daoJ);
    }

    public void actualitzarJugador(int idJugador, String username, String password) throws IOException, InstanciaNoExisteix  {
        DAOJugador daoJ = new DAOJugador(idJugador, password);
        gestorJugador.actualitzarJugador(username, daoJ);
    }

    public Jugador obtenirJugador(String username) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOJugador daoJ = gestorJugador.obtenirJugador(username);
        Jugador j = null;
        try {
            j = new Jugador(
                daoJ.getId(),
                username,
                daoJ.getPassword()
            );
        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
        }
        return j;
    }

    public void eliminarJugador(String username) throws IOException, InstanciaNoExisteix {
        gestorJugador.eliminarJugador(username);
    }

    public boolean existeixJugador(String username) throws IOException {
        return gestorJugador.existeixJugador(username);
    }

    public int totalJugadors() throws IOException {
        return gestorJugador.totalJugadors();
    }

    public String obtenirPasswordJugador(String username) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        return gestorJugador.obtenirPassword(username);
    }

    public void afegirPartida(String idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes, TipusAlgorisme tipusAlgorisme) throws IOException, InstanciaJaExisteix {
        DAOPartida daoP = new DAOPartida(solutionCode, rondes, tipusAlgorisme);
        gestorPartida.afegirPartida(idPartida, daoP);
    }

    public void actualitzarPartida(String idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes, TipusAlgorisme tipusAlgorisme) throws IOException, InstanciaNoExisteix  {
        DAOPartida daoP = new DAOPartida(solutionCode, rondes, tipusAlgorisme);
        gestorPartida.actualitzarPartida(idPartida, daoP);
    }

    public Partida obtenirPartida(String idPartida, TipusPartida tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOPartida daoP = gestorPartida.obtenirPartida(idPartida);
        Partida p = null;
        try {
            if (tipusPartida == TipusPartida.CODEBREAKER) {
                p = new Codebreaker(Integer.valueOf(idPartida),
                        daoP.getSolutionCode(),
                        daoP.getRondes()
                );
            } else {
                p = new Codemaker(
                        Integer.valueOf(idPartida),
                        daoP.getSolutionCode(),
                        daoP.getRondes(),
                        daoP.getTipusAlgorisme()
                );
            }

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
    
    public int totalPartides() throws IOException {
        return gestorPartida.totalPartides();
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

    public TipusPartida obtenirTipusPartida(String strIdpartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        return gestorConfiguracioPartida.obtenirTipusPartida(strIdpartida);
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
        DAOEstadistiquesPartida daoEP = new DAOEstadistiquesPartida(puntuacio, guanyada);
        gestorEstadistiquesPartida.afegirEstadistiquesPartida(idJugador+" "+idPartida, daoEP);
    }

    public void actualitzarEstadistiquesPartida(String idJugador, String idPartida, Integer puntuacio, boolean guanyada) throws IOException, InstanciaNoExisteix  {
        DAOEstadistiquesPartida daoEP = new DAOEstadistiquesPartida(puntuacio, guanyada);
        gestorEstadistiquesPartida.actualitzarEstadistiquesPartida(idJugador+" "+idPartida, daoEP);
    }

    public EstadistiquesPartida obtenirEstadistiquesPartida(String idJugador, String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOEstadistiquesPartida daoEP = gestorEstadistiquesPartida.obtenirEstadistiquesPartida(idJugador+" "+idPartida);
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

    public void eliminarEstadistiquesPartida(String idJugador, String idPartida) throws IOException, InstanciaNoExisteix {        
        gestorEstadistiquesPartida.eliminarEstadistiquesPartida(idJugador+" "+idPartida);
    }

    public boolean existeixEstadistiquesPartida(String idJugador, String idPartida) throws IOException {
        return gestorEstadistiquesPartida.existeixEstadistiquesPartida(idJugador+" "+idPartida);
    }

    public ArrayList<String> obtenirIdJugadorsEstadistiquesPartida() throws IOException {
        return gestorEstadistiquesPartida.obtenirIdJugadors();
    }

    public ArrayList<String[]> obtenirEstadistiquesPartidesJugador(String username) throws ClassNotFoundException, IOException {
        ArrayList<DAOEstadistiquesPartida> estadistiquesPartidasDAO = gestorEstadistiquesPartida.obtenirEstadistiquesPartidesJugador(username);
        ArrayList<String[]> estadistiquesPartides = new ArrayList<>(estadistiquesPartidasDAO.size());

        for (int i=0; i<estadistiquesPartidasDAO.size(); ++i) {
            DAOEstadistiquesPartida daoEP = estadistiquesPartidasDAO.get(i);
            estadistiquesPartides.add(new String[] {String.valueOf(daoEP.getPuntuacio()), String.valueOf(daoEP.getGuanyada())});
        }

        return estadistiquesPartides;
    }
}