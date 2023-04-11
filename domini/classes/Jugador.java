package domini.classes;

import java.util.ArrayList;

public class Jugador {
    //atributs
    private final int id;
    private String username;
    private String password;
        //serveix per a generar la id
    private static int nombreJugadors = 0;

    //associacions
    private ArrayList<EstadistiquesPartida> statsPartides;
    
    /*creadora per defecte. 
    * crea un nou jugador amb ID incremental (el primer jugador tindra id = 1) 
    * despres assigna les credencials corresponents
    */
    public Jugador(String name, String pwd) {
        ++nombreJugadors;
        this.id = nombreJugadors;
        this.username = name;
        this.password = pwd;
    }
    
    //atributs derivats calculats
    public int PartidesGuanyades() {
        int guanyades;
        for (EstadistiquesPartida p : this.statsPartides) {
            if (p.esGuanyada()) ++guanyades;
        }
        return guanyades;
    }

    public int PartidesJugades() {
        return statsPartides.size();
    }

    //getters
    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /*aixo aniria al ctrlDomini¿?
    //return true if login is successful
    public boolean login(String name, String pwd) {
        boolean loginSuccess = false;
        if (name == username and pwd == password) {
            loginSuccess = true;
        }
        return loginSuccess;
    }
    */
}