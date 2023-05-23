package persistencia.classes;

public class DAOEstadistiquesPartida {

    /**
     * Identificador del jugador que ha jugat la partida.
     * Part de la clau primària juntament amb idPartida.
     */
    private int idJugador;

    /**
     * Identificador de la partida de la qual s'agafen les estadistiques.
     * Part de la clau primària juntament amb idJugador.
     * La partida és de tipus Codebreaker.
     */
    private int idPartida;

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
     * @param idJugador         Identificador del jugador
     * @param idPartida         Identificador de la partida
     * @param puntuacio         Puntuacio obtinguda a la partida
     * @param guanyada          Si la partida ha estat guanyada, guanyada==true
     */

    public DAOEstadistiquesPartida(int idJugador, int idPartida, int puntuacio, Boolean guanyada) {
        this.idJugador = idJugador;
        this.idPartida = idPartida;
        this.puntuacio = puntuacio;
        this.guanyada = guanyada;
    }

    /**
     * Retorna la id del jugador.
     * @return id del jugador
     */
    public int getIdJugador() {
        return idJugador;
    }

    /**
     * Retorna la id de la partida.
     * @return id de la partida
     */
    public int getIdPartida() {
        return idPartida;
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
    public Boolean guanyada() {
        return guanyada;
    }
}
