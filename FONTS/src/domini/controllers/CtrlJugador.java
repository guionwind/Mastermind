package domini.controllers;

import domini.classes.EstadistiquesPartida;
import domini.classes.Jugador;

import domini.classes.exceptions.*;
import persistencia.controllers.CtrlPersistencia;

import java.lang.String;
import java.util.HashMap;


public class CtrlJugador {

    /**
     * Id del jugador loggejat actualment
     */
    private int jugadorActual;

    /**
     * HashMap que emmagatzema els jugadors
     * Apunt: no es final ja que es modifica fora de la constructora
     */
    private HashMap <Integer, Jugador> jugadors;

    /**
     * Constructora de CtrlJugador
     */
    public CtrlJugador() {
        jugadorActual = -1;
        jugadors = new HashMap<>();
    }

    /**
     * Retorna la id del jugador actual loggejat
     * @return la id del jugadro acutal loggejat o -1 si no hi ha cap jugador loggejat
     */
    public int getIdJugador() {
        return jugadorActual;
    }

    /**
     * Retorna el usuari del jugador actal loggejat
     * @return string username del jugador actual loggejat
     */
    public String getUsername() {
        return jugadors.get(jugadorActual).getUsername();
    }

    /**
     * Retorna un username a partir de la id d'un jugador
     * @param id d'un jugador
     * @return el username del jugador amb id, id
     */
    public String getUsernameFromID(int id) {
        return jugadors.get(id).getUsername();
    }

    /**
     * Crea un jugador amb els seus parametres corresponents
     * @param username username del usuari
     * @param password contrasenya del usuari
     * @throws JugadorJaExisteix En cas que el jugador ja existeixi
     * @throws JugadorInvalid En cas de que el nom d'usuari no sigui correcte
     */
    public Jugador crearJugador(int newId, String username, String password) throws JugadorJaExisteix, JugadorInvalid {
        if (username == null || username.equals("")) throw new JugadorInvalid("Username invalid");
        else if (getJugador(username) != null) {
            throw new JugadorJaExisteix("Ja hi ha un jugador amb aquest nom");
        }
        else {
            Jugador j = new Jugador(newId, username, password);
            jugadorActual = newId;
            return j;
        }
    }

    /**
     * Actualitza la variable jugadorActual
     * @param username username del jugador actual
     * @throws JugadorNoExisteix en cas que el jugador al que es vol canviar no existeixi
     */
    public void setJugadorActual(String username) throws JugadorNoExisteix{
        Jugador j = getJugador(username);
        if (j != null) {
            jugadorActual = j.getID();
        }
        else throw new JugadorNoExisteix("El jugador no existeix");
    }

    /**
     * Desloggeja al jugador actual, basicament posant -1 a la variable que identifica el jugador actual
     */
    public void logoff() {
        jugadorActual = -1;
    }

    /**
     * Retorna la contrasenya del usuari indicat per el parametre username
     * @param username username del usuari
     * @return retorna la contrasenya del usuari indicat per el username
     * @throws JugadorNoExisteix en cas que el jugador no existeixi es llança la excepcio
     */
    public String getPassword(String username) throws JugadorNoExisteix {
        Jugador j = getJugador(username);
        if (j != null) {
            return j.getPassword();
        }
        else throw new JugadorNoExisteix("El jugador no existeix");
    }

    /**
     * Afegeix la estadistica d'una partida del jugadorActual
     * @param estadistiquesPartida Estadistiques de la partida jugada per el jugador actual
     */
    public void addEstadistica(EstadistiquesPartida estadistiquesPartida) {
        Jugador j= jugadors.get(jugadorActual);

        j.setEstadistica(estadistiquesPartida);
    }

    /**
     * Retorna el jugador donat un username
     * @param username Username del usuari
     * @return el jugador identificat per el username
     */
    private Jugador getJugador(String username) {
        for (Jugador j : jugadors.values()) {
            if (j.getUsername().equals(username)) return j;
        }
        return null;
    }
}
