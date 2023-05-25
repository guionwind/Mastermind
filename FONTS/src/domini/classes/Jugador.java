package domini.classes;

import java.util.ArrayList;


public class Jugador {
    /**
     * Identificador del jugador.
     */
    private final int id;

    /**
     * Nom d'usuari del jugador.
     */
    private String username;

    /**
     * Contrasenya del jugador.
     */
    private String password;

    /**
     * Conjunt de les estadístiques de les partides que ha jugat el jugador.
     */
    private ArrayList<EstadistiquesPartida> estadistiquesPartides;
    
    /**
     * Constructora de Jugador.
     * 
     * @param id
     * @param username
     * @param pwd
     */
    public Jugador(int id, String username, String pwd)  {
        this.id = id;
        this.username = username;
        this.password = pwd;

        estadistiquesPartides = new ArrayList<EstadistiquesPartida>();
    }
    
    //! AQUEST METODE NO ES FA SERVIR
    /** 
     * Consultora de partides guanyades
     * @return int nombre de partides guanyades
     */
    /*
    public int PartidesGuanyades() {
        int guanyades = 0;
        for (EstadistiquesPartida p : estadistiquesPartides) {
            if (p.getGuanyada()) ++guanyades;
        }
        return guanyades;
    }
    */

    //! AQUEST METODE NO ES FA SERVIR
    /**
     * Consultora del total de partides jugades.
     * 
     * @return Nombre de partides jugades pel Jugador.
     */
    /*
    public int PartidesJugades() {
        return estadistiquesPartides.size();
    }
    */

    /**
     * Consultora que retorna la ID del jugador.
     * @return ID del jugador
     */
    public int getID() {
        return id;
    }

    /**
     * Consultora que retorna el nom del jugador.
     * @return Nom del jugador
     */
    public String getUsername() {
        return username;
    }

    /**
     * Consultora que retorna la contrasenya del jugador.
     * @return Contrasenya del jugador
     */
    public String getPassword() {
        return password;
    }

    /**
     * Afegeix una instància de estadistiquesPartida al conjunt d'estadistiques partida
     * @param estadistiquesPartida Estadistiques de la partida a afegir
     */
    public void setEstadistica(EstadistiquesPartida estadistiquesPartida) {
        estadistiquesPartides.add(estadistiquesPartida);
    }

}