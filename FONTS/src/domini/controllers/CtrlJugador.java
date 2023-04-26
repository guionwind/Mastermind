package domini.controllers;

import domini.classes.EstadistiquesPartida;
import domini.classes.Jugador;
//per retocar
import domini.classes.exceptions.JugadorJaExisteix;
import domini.classes.exceptions.JugadorInvalid;

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
        jugadors = new HashMap<Integer, Jugador>();
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

    public boolean userExists(String username) {
        boolean aux = false;
        for (Jugador j : jugadors.values()) {
            if (j.getUsername().equals(username)) aux = true;
        }
        return aux;
    }

    //crea un nou jugador per al registre, afegeix les credencials a usuariContrasenya
    //* se'l logeja automaticament
    public void crearJugador(String username, String password) throws JugadorJaExisteix, JugadorInvalid {
        if (username == null || username.equals("")) throw new JugadorInvalid("Username invalid");
        else if (userExists(username)) {
            throw new JugadorJaExisteix("Ja hi ha un jugador amb aquest nom");
        }
        else {
            Jugador j = new Jugador(username, password);
            int newId = j.getID();
            jugadors.put(newId, j);
            jugadorActual = newId;
        }
    }

    //set jugador actual
    public void setJugadorActual(String username) throws JugadorNoExisteix{
        boolean found = false;
        for (Jugador j : jugadors.values()) {
            if (j.getUsername().equals(username)) {
                jugadorActual = j.getID();
                found = true;
            }
        }
        if (!found) throw new JugadorNoExisteix("El jugador no existeix");
    }

    //treu el JugadorActual
    public void logoff() {
        jugadorActual = -1;
    }

    //obte la contrasenya a partir d'un username
    //busca entre els valors del map la instancia de Jugador que tingui la username indicada, i retorna la contrasenya
    public String getPassword(String username) throws JugadorNoExisteix {
        String pass = new String();
        boolean found = false;
        for (Jugador j : jugadors.values()) {
            if (j.getUsername().equals(username)) {
                pass = j.getPassword();
                found = true;
            }
        }
        if (found) return pass;
        else throw new JugadorNoExisteix("El jugador no existeix");
    }

    public void addEstadistica(EstadistiquesPartida estadistiquesPartida) {
        Jugador j = jugadors.get(jugadorActual);

        j.setEstadistica(estadistiquesPartida);
    }


}
