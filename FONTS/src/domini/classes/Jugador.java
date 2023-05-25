package domini.classes;

import java.util.ArrayList;


public class Jugador {
    //atributs
    private final int id;
    private String username;
    private String password;

    //associacions
    private ArrayList<EstadistiquesPartida> estadistiquesPartides;
    
    /**creadora per defecte. 
    * @pre name, pwd no son nulls 
    * crea un nou jugador amb ID incremental (el primer jugador tindra id = 1) 
    * despres assigna les credencials corresponents
    */
    public Jugador(int id, String username, String pwd)  {
        this.id = id;
        this.username = username;
        this.password = pwd;

        estadistiquesPartides = new ArrayList<EstadistiquesPartida>();
    }
    
    /**consultora de partides guanyades
     * @pre -
     * @return int nombre de partides guanyades
     */
    public int PartidesGuanyades() {
        int guanyades = 0;
        for (EstadistiquesPartida p : estadistiquesPartides) {
            if (p.getGuanyada()) ++guanyades;
        }
        return guanyades;
    }

    /**consultora de partides jugades
     * @pre -
     * @return int nombre de partides jugades
     */
    public int PartidesJugades() {
        return estadistiquesPartides.size();
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

    public void setEstadistica(EstadistiquesPartida estadistiquesPartida) {
        estadistiquesPartides.add(estadistiquesPartida);
    }

}