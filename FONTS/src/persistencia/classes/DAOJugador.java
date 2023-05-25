package persistencia.classes;

public class DAOJugador {

    /**
     * Id del jugador.
     */
    private int idJugador;

    /**
     * Contrassenya que fa servir el jugador per autenticar-se.
     */
    private String password;

    /**
     * Constructora buida.
     * Utilitzada per la deserialització de l'objecte.
     */
    public DAOJugador() {}

    /**
     * Constructora amb paràmetres.
     * 
     *
     */
    public DAOJugador(int idJugador, String password) {
        this.idJugador = idJugador;
        this.password = password;
    }

    /**
     * Retorna el nom d'usuari del jugador.
     * 
     * @return username del jugador
     */
    public int getId() {
        return idJugador;
    }

    /**
     * Retorna la contrasenya del jugador.
     * 
     * @return password del jugador
     */
    public String getPassword() {
        return password;
    }
    
}
