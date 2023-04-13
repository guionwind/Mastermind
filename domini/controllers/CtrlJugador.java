package domini.controllers;

import domini.classes.Jugador;
//per retocar
import domini.classes.exceptions.*;

import java.lang.String;
import java.util.HashMap;



public class CtrlJugador {
    //id del jugador logejat actualment
    private int jugadorActual;

    //en crear un jugador, aqui guardarem les credencials
    private HashMap <Integer, Jugador> jugadors;
    
    //constructora
    //si no hi ha jugador, JugadorActual = -1
    public CtrlJugador() {
        jugadorActual = -1;
    }

    //retorna el jugador actual
    //sera -1 si no hi ha ningu logejat
    public int getIdJugador() {
        return jugadorActual;
    }

    public String getUsername() {
        return jugadors.get(jugadorActual).getUsername();
    }

    public String getUsernameFromID(int id) {
        return jugadors.get(id).getUsername();
    }


    //crea un nou jugador per al registre, afegeix les credencials a usuariContrasenya
    public void crearJugador(String username, String password) {
        if (jugadorActual == -1) {
            Jugador j = new Jugador(username, password);
            int newId = j.getID();
            jugadors.put(newId, j);
            jugadorActual = newId;
        }
    }

    //set jugador actual
    public void setJugadorActual(String username) {
        for (Jugador j : jugadors.values()) {
            if (j.getUsername() == username) jugadorActual = j.getID();
        }
    }

    //treu el JugadorActual
    //
    public void logoff() {
        jugadorActual = -1;
    }

    //obte la contrasenya a partir d'un username
    //busca entre els valors del map la instancia de Jugador que tingui la username indicada, i retorna la contrasenya
    public String getPassword(String username) throws UsuariNoExisteix{
        String pass = null;
        for (Jugador j : jugadors.values()) {
            if (j.getUsername() == username) {
                pass = j.getPassword();
            }
        }
        return pass;
    }


}
