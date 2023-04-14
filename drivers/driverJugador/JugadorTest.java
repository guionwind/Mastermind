package drivers.driverJugador;

import java.util.ArrayList;
import domini.classes.exceptions.*;


public class JugadorTest {
    //atributs
    private final int id;
    private String username;
    private String password;
        //serveix per a generar la id
    private static int nombreJugadors = 0;

    //associacions
    private ArrayList<EstadistiquesPartidaTest> statsPartides;
    
    /**creadora per defecte. 
    * @pre name, pwd no son nulls 
    * crea un nou jugador amb ID incremental (el primer jugador tindra id = 1) 
    * despres assigna les credencials corresponents
    */

    public JugadorTest(String name, String pwd) throws JugadorJaExisteix,JugadorInvalid {
        ++nombreJugadors;
        this.id = nombreJugadors;

        this.username = name;
        this.password = pwd;
    }
    
    /**consultora de partides guanyades
     * @pre -
     * @return int nombre de partides guanyades
     */
    public int PartidesGuanyades() {
        int guanyades = 0;
        for (EstadistiquesPartidaTest p : this.statsPartides) {
            if (p.getGuanyada()) ++guanyades;
        }
        return guanyades;
    }

    /**consultora de partides jugades
     * @pre -
     * @return int nombre de partides jugades
     */
    public int PartidesJugades() {
        return this.statsPartides.size();
    }

    //getters
    public int getID() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEstadistica(EstadistiquesPartidaTest estadistiquesPartida) {
        statsPartides.add(estadistiquesPartida);
    }

}