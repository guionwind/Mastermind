package persistencia.classes;

public class DAOEstadistiquesPartida {

    /**
     * Puntuació obtinguda a la partida.
     */
    private int puntuacio;

    /**
     * Si és cert, indica que el jugador ha guanyat aquesta partida.
     */
    private Boolean guanyada;

    /**
     * Constructora buida.
     * Utilitzada per la deserialització de l'objecte.
     */
    public DAOEstadistiquesPartida() {};

    /**
     * Constructora amb paràmetres.
     * 
     * @param puntuacio         Puntuacio obtinguda a la partida
     * @param guanyada          Si la partida ha estat guanyada, guanyada==true
     */

    public DAOEstadistiquesPartida(int puntuacio, Boolean guanyada) {
        this.puntuacio = puntuacio;
        this.guanyada = guanyada;
    }

    /**
     * Retorna la puntuació obtinguda a la partida.
     * @return puntuacio final de la partida
     */
    public int getPuntuacio() {
        return puntuacio;
    }

    /**
     * Retorna si la partida ha estat guanyada.
     * @return Cert si ha estat guanyada, fals si no.
     */
    public Boolean getGuanyada() {
        return guanyada;
    }
}
