package persistencia.controllers;

import java.io.IOException;
import java.lang.reflect.Array;
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
    
    public void afegirJugador(int idJugador, String username, String password, ArrayList<Integer> idPartides) throws IOException, InstanciaJaExisteix {
        DAOJugador daoJ = new DAOJugador(idJugador, password, idPartides);
        gestorJugador.afegirJugador(username, daoJ);
    }

    public void actualitzarJugador(int idJugador, String username, String password, ArrayList<Integer> idPartides) throws IOException, InstanciaNoExisteix  {
        DAOJugador daoJ = new DAOJugador(idJugador, password, idPartides);
        gestorJugador.actualitzarJugador(username, daoJ);
    }

    public Jugador obtenirJugador(String username) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOJugador daoJ = gestorJugador.obtenirJugador(username);
        Jugador j = null;
        
        try {
            j = new Jugador(
                daoJ.getId(),
                username,
                daoJ.getPassword(),
                daoJ.getIdPartides()
            );
        }
        catch (Exception e) {
            System.out.println(e.toString());
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

    public void afegirPartida(String idPartida, Integer[] solutionCode, Integer[] idRondes, Integer[][] combinacionsIntentades, String[] correccions, String tipusAlgorisme) throws IOException, InstanciaJaExisteix {
        DAOPartida daoP = new DAOPartida(solutionCode, idRondes, combinacionsIntentades, correccions, tipusAlgorisme);
        gestorPartida.afegirPartida(idPartida, daoP);
    }

    public void actualitzarPartida(String idPartida, Integer[] solutionCode, Integer[] idRondes, Integer[][] combinacionsIntentades, String[] correccions, String tipusAlgorisme) throws IOException, InstanciaNoExisteix  {
        DAOPartida daoP = new DAOPartida(solutionCode, idRondes, combinacionsIntentades, correccions, tipusAlgorisme);
        gestorPartida.actualitzarPartida(idPartida, daoP);
    }

    public Partida obtenirPartida(String idPartida, String tipusPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOPartida daoP = gestorPartida.obtenirPartida(idPartida);
        Partida p = null;
        try {
            HashMap<Integer, Ronda> rondes = new HashMap<>();
            int midaRondes = daoP.getIdRondes().length;

            Integer[] idRondes = daoP.getIdRondes();
            Integer[][] combinacionsIntentades = daoP.getCombinacionsIntentades();
            String[] correccions = daoP.getCorreccions();
            System.out.println(midaRondes + "midarondes");
            for (int i = 0; i < midaRondes; i++) {
                Ronda ronda = new Ronda(idRondes[i], combinacionsIntentades[i], correccions[i]);
                System.out.println(i + "la i");
                rondes.put(idRondes[i], ronda);
            }
            
            if (tipusPartida.equals("CODEBREAKER")) {
                p = new Codebreaker(Integer.valueOf(idPartida),
                        daoP.getSolutionCode(),
                        rondes
                );
            } else {
                p = new Codemaker(
                        Integer.valueOf(idPartida),
                        daoP.getSolutionCode(),
                        rondes,
                        daoP.getTipusAlgorisme()
                );
            }

        }
        catch (Exception e) {
            System.out.println("Aquest error no hauria de passar.");
            System.out.println(e.toString());
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

    public void afegirConfiguracioPartida(String idPartida, String tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException, InstanciaJaExisteix {
        DAOConfiguracioPartida daoCP = new DAOConfiguracioPartida(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
        gestorConfiguracioPartida.afegirConfiguracioPartida(idPartida, daoCP);
    }

    public void actualitzarConfiguracioPartida(String idPartida, String tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) throws IOException, InstanciaNoExisteix {
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

    public String obtenirTipusPartida(String strIdpartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
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

    public void afegirEstadistiquesPartida(String username, String idPartida, Integer puntuacio, boolean guanyada) throws IOException, InstanciaJaExisteix {
        DAOEstadistiquesPartida daoEP = new DAOEstadistiquesPartida(puntuacio, guanyada);
        gestorEstadistiquesPartida.afegirEstadistiquesPartida(username+" "+idPartida, daoEP);
    }

    public void actualitzarEstadistiquesPartida(String username, String idPartida, Integer puntuacio, boolean guanyada) throws IOException, InstanciaNoExisteix  {
        DAOEstadistiquesPartida daoEP = new DAOEstadistiquesPartida(puntuacio, guanyada);
        gestorEstadistiquesPartida.actualitzarEstadistiquesPartida(username+" "+idPartida, daoEP);
    }

    public EstadistiquesPartida obtenirEstadistiquesPartida(String username, String idPartida) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        DAOEstadistiquesPartida daoEP = gestorEstadistiquesPartida.obtenirEstadistiquesPartida(username+" "+idPartida);
        EstadistiquesPartida eP = null;
        try {
            eP = new EstadistiquesPartida(
                username,
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

    public void eliminarEstadistiquesPartida(String username, String idPartida) throws IOException, InstanciaNoExisteix {
        gestorEstadistiquesPartida.eliminarEstadistiquesPartida(username+" "+idPartida);
    }

    public boolean existeixEstadistiquesPartida(String username, String idPartida) throws IOException {
        return gestorEstadistiquesPartida.existeixEstadistiquesPartida(username+" "+idPartida);
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