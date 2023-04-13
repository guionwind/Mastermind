package domini.controllers;

import domini.classes.Jugador;
//per retocar
import domini.classes.exceptions.*;

import java.io.IOException;
import java.lang.String;
import java.util.HashMap;



public class CtrlJugador {
    private int JugadorActual;
    //en crear un jugador, aqui guardarem les credencials
    private HashMap <Integer, Jugador> jugadors;
    
    //constructora
    //si no hi ha jugador, JugadorActual = -1
    public CtrlJugador() {
        JugadorActual = -1;
    }

    //retorna el jugador actual
    //sera -1 si no hi ha ningu logejat
    public int getIdJugador() throws {
        if (JugadorActual != -1) return JugadorActual;
        //else throw 
    }

    //crea un nou jugador per al registre, afegeix les credencials a usuariContrasenya
    public void crearJugador(String username, String password) throws JugadorJaExisteix {
        if (JugadorActual == null) {
            new Jugador(username, password);
            usuariContrasenya.put(username, password);
        }
    }

    //treu el JugadorActual
    //
    public void logoff() {
        if (JugadorActual != -1) JugadorActual = -1;
    }

    //obte la contrasenya de l'usuari a partir del seu username
    public String getPassword(String username) {
        if (usuariContrasenya.containsKey(username)) {
            String pass = usuariContrasenya.get(username);
            return pass;
        }
        //else throw Exception
    }


}
