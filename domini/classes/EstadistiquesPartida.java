/**
 * Classe EstadistiquesPartida que conté les estadístiques d'una partida.
 */
package domini.classes;

public class EstadistiquesPartida {
    // Atributs
    private Jugador jugador;
    private Partida partida;
    private int puntuacio;
    private int temps;
    private Boolean guanyada;
    
    /**
     * Constructor de la classe EstadistiquesPartida.
     *
     * @param jugador Jugador de la partida
     * @param partida Partida que ha jugat el jugador i de la qual es faran les estadístiques
     * @param puntuacio Puntuacio aconseguida pel jugador a la partida
     * @param temps Temps que ha tardat el jugador en finalitzar la partida
     * @param guanyada Determina si la partida ha estat guanyada o no
     */

    public EstadistiquesPartida(Jugador jugador, Partida partida, int puntuacio, int temps, Boolean guanyada) {
        this.jugador = jugador;
        this.partida = partida;
        this.puntuacio = puntuacio;
        this.temps = temps;
        this.guanyada = guanyada;
    }

    // Mètodes

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
     * Mètode per modificar la puntuació de les estadístiques.
     * 
     */
    public void setPuntuacio(int puntuacio){
        this.puntuacio = puntuacio;
    }

    /**
     * Mètode per obtenir el temps de les estadístiques.
     * @return Retorna el temps de les estadístiques.
     */
    public int getTemps(){
        return this.temps;
    }

    /**
     * Mètode per modificar el temps de les estadístiques.
     *
     */
    public void setTemps(int temps){
        this.temps = temps;
    }
    /**
     * Mètode per obtenir si la partida ha estat guanyada.
     * @return Retorna una booleana True si ha estat guanyada i False si no.
     */
    public Boolean getGuanyada(){
        return this.guanyada;
    }

    /**
     * Mètode per modificar si la partida ha estat guanyada.
     *
     */
    public void setGuanyada(Boolean flag){
        this.guanyada = flag;
    }
}