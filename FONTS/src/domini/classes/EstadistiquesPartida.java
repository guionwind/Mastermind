/**
 * Classe EstadistiquesPartida que conté les estadístiques d'una partida.
 */
package domini.classes;

public class EstadistiquesPartida {
    /**
     * Identificador del jugador. Part de la clau primària amb idPartida
     */
    private Integer idJugador;
    /**
     * Identificador de la partida. Part de la clau primària amb idJugador
     */
    private Integer idPartida;
    /**
     * Puntuacio obtinguda a la partida
     */
    private Integer puntuacio;
    /**
     * Resultat de la partida: true si s'ha guanyat, false si s'ha perdut
     */
    private Boolean guanyada;
    
    /**
     * Constructor de la classe EstadistiquesPartida.
     *
     * @param idJugador Jugador de la partida
     * @param idPartida Partida que ha jugat el jugador i de la qual es faran les estadístiques
     * @param puntuacio Puntuacio aconseguida pel jugador a la partida
     * @param guanyada Determina si la partida ha estat guanyada o no
     */

    public EstadistiquesPartida(Integer idJugador, Integer idPartida, int puntuacio, Boolean guanyada) {
        this.idJugador = idJugador;
        this.idPartida = idPartida;
        this.puntuacio = puntuacio;
        this.guanyada = guanyada;
    }

    // Mètodes

    /**
     * Mètode per obtenir el jugador de les estadístiques.
     * @return Retorna el jugador de les estadístiques.
     */
    public Integer getIdJugador(){
        return this.idJugador;
    }

    /**
     * Mètode per obtenir la partida de les estadístiques.
     * @return Retorna la partida de les estadístiques.
     */
    public Integer getIdPartida(){
        return this.idPartida;
    }

    /**
     * Mètode per obtenir la puntuació de les estadístiques.
     * @return Retorna la puntuació de les estadístiques.
     */
    public Integer getPuntuacio(){
        return this.puntuacio;
    }

    /**
     * Mètode per modificar la puntuació de les estadístiques.
     * 
     * @param puntuacio Puntuacio de la partida
     */
    public void setPuntuacio(int puntuacio){
        this.puntuacio = puntuacio;
    }


    /**
     * Retorna el resultat de la partida.
     * @return Cert si la partida és guanyada, fals si no.
     */
    public Boolean getGuanyada(){
        return this.guanyada;
    }

    /**
     * Mètode per modificar el resultat de la partida.
     * @param flag Cert si la partida és guanyada, fals si no.
     */
    public void setGuanyada(Boolean flag){
        this.guanyada = flag;
    }
}