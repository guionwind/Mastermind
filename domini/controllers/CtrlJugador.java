package domini.controllers;

import domini.classes.Jugador;
//per retocar
import domini.classes.exceptions.*;

import java.lang.String;
import java.util.HashMap;



public class CtrlJugador {
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


    //crea un nou jugador per al registre, afegeix les credencials a usuariContrasenya
    public void crearJugador(String username, String password) {
        if (jugadorActual == -1) {
            Jugador j = new Jugador(username, password);
            int newId = j.getID();
            jugadors.put(newId, j);
            jugadorActual = newId;
        }
    }

    //treu el JugadorActual
    //
    public void logoff() {
        jugadorActual = -1;
    }

    //obte la contrasenya de l'usuari a partir del seu username
    public String getPassword(String username) {
        if (jugadors.containsKey(username)) {
            String pass = jugadors.get(jugadorActual).getPassword(username);
            return pass;
        }
        //else throw Exception
    }


}
