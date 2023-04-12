package domini.controllers;

import domini.classes.*;
import java.lang.String;
import java.util.Map;

public class CtrlJugador {
    private Jugador JugadorActual;
    //en crear un jugador, aqui guardarem les credencials
    private Map <String, String> usuariContrasenya;
    
    //constructora
    public CtrlJugador() {
        JugadorActual = null;
    }

    //retorna el jugador actual
    //sera null si no hi ha ningu logejat
    public Jugador getJugadorActual() {
        return JugadorActual;
    }

    //crea un nou jugador per al registre, afegeix les credencials a usuariContrasenya
    public void crearJugador(String username, String password) {
        if (JugadorActual == null) {
            new Jugador(username, password);
            usuariContrasenya.put(username, password);
        }
    }

    //treu el JugadorActual, nomes si n'hi ha un actualment
    public void logoff() {
        if (JugadorActual != null) JugadorActual = null;
    }

    //obte la contrasenya de l'usuari a partir del seu username
    public String getPassword(String username) {
        if (usuariContrasenya.containsKey(username)) {
            String pass = usuariContrasenya.get(username);
            return pass;
        //else throw Exception
    }

}
