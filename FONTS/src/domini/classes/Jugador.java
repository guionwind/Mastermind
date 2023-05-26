package domini.classes;

import java.util.ArrayList;

/**
 * Classe Jugador que gestiona les funcionalitats pertinents d'un jugador.
 */
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
     * ArrayList de les ids de les partides del jugador
     */
    private ArrayList<Integer> idPartides;

    /**
     * Conjunt de les estadístiques de les partides que ha jugat el jugador.
     */
    private ArrayList<EstadistiquesPartida> estadistiquesPartides;
    
    /**
     * Constructora de Jugador.
     *
     * @param id Id del jugador
     * @param username Username del jugador
     * @param pwd Contrasenya del jugador
     */
    public Jugador(int id, String username, String pwd)  {
        this.id = id;
        this.username = username;
        this.password = pwd;
    }

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