/**
 * Classe EstadistiquesPartida que conté les estadístiques d'una partida d'un videojoc.
 */
package domini.classes;

public class EstadistiquesPartida {
    // Atributs
    private Jugador jugador;
    private Partida partida;
    private int puntuacio;
    private int temps;
    private Boolean acabada;
    private Boolean guanyada;

    /**
     * Mètode per obtenir el jugador de les estadístiques.
     * @return Retorna el jugador de les estadístiques.
     */
    public Jugador getJugador(){
        return this.jugador;
    }

    /**
     * Mètode per obtenir la partida de les estadístiques.
     * @return Retorna la partida de les estadístiques.
     */
    public Partida getPartida(){
        return this.partida;
    }

    /**
     * Mètode per obtenir la puntuació de les estadístiques.
     * @return Retorna la puntuació de les estadístiques.
     */
    public int getPuntuacio(){
        return this.puntuacio;
    }

    /**
     * Mètode per obtenir el temps de les estadístiques.
     * @return Retorna el temps de les estadístiques.
     */
    public int getTemps(){
        return this.temps;
    }

    /**
     * Mètode per obtenir si la partida ha acabat.
     * @return Retorna true si la partida ha acabat, false en cas contrari.
     */
    public Boolean getAcabada (){
        return this.acabada;
    }

    /**
     * Mètode per obtenir si la partida ha estat guanyada.
     * @return Retorna true si la partida ha estat guanyada, false en cas contrari.
     */
    public Boolean getGuanyada (){
        return this.guanyada;
    }
}