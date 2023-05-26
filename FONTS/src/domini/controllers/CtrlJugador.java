package domini.controllers;

import domini.classes.EstadistiquesPartida;
import domini.classes.Jugador;

//import jdk.dynalink.linker.support.CompositeTypeBasedGuardingDynamicLinker;

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
     * @return la id del jugador acutal loggejat o -1 si no hi ha cap jugador loggejat
     */
    public int getIdJugador() {
        if (jugadorActual != null) return jugadorActual.getID();
        else return -1;
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
     * @param newId La id del nou jugador
     * @param username username del usuari
     * @param password contrasenya del usuari
     * @return Retorna la instancia del jugador creat
     */
    public Jugador crearJugador(int newId, String username, String password) {
        jugadorActual = new Jugador(newId, username, password);

        return jugadorActual;
    }

    /**
     * Assigna el jugadorActual
     * @param jugadorActual instància a assignar
     */
    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    /**
     * Desloggeja al jugador actual, establint el jugadorActual a null.
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
