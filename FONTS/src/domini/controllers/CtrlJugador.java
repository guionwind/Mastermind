package domini.controllers;

import domini.classes.EstadistiquesPartida;
import domini.classes.Jugador;

import domini.classes.exceptions.*;
import jdk.dynalink.linker.support.CompositeTypeBasedGuardingDynamicLinker;

import java.lang.String;


public class CtrlJugador {

    /**
     * Jugador loggejat actualment
     */
    private Jugador jugadorActual;


    /**
     * Constructora de CtrlJugador
     */
    public CtrlJugador() {

    }

    /**
     * Retorna la id del jugador actual loggejat
     * @return la id del jugadro acutal loggejat o -1 si no hi ha cap jugador loggejat
     */
    public int getIdJugador() {
        return jugadorActual.getID();
    }

    /**
     * Retorna el usuari del jugador actal loggejat
     * @return string username del jugador actual loggejat
     */
    public String getLoggedPlayerUsername() {
        return jugadorActual.getUsername();
    }

    /**
     * Crea un jugador amb els seus parametres corresponents
     * @param username username del usuari
     * @param password contrasenya del usuari
     * @throws JugadorInvalid En cas de que el nom d'usuari no sigui correcte
     */
    public Jugador crearJugador(int newId, String username, String password) throws JugadorInvalid {
        if (username == null || username.equals("")) throw new JugadorInvalid("Username invalid");
        else {
            jugadorActual = new Jugador(newId, username, password);

            return jugadorActual;
        }
    }

    /**
     * Actualitza la variable jugadorActual
     * @param username username del jugador actual
     */
    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    /**
     * Desloggeja al jugador actual, basicament posant -1 a la variable que identifica el jugador actual
     */
    public void logoff() {
        jugadorActual = null;
    }

    /**
     * Afegeix la estadistica d'una partida del jugadorActual
     * @param estadistiquesPartida Estadistiques de la partida jugada per el jugador actual
     */
    public void addEstadistica(EstadistiquesPartida estadistiquesPartida) {
        jugadorActual.setEstadistica(estadistiquesPartida);
    }
}
