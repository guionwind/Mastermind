package persistencia.classes;

public class DAOJugador {

    /**
     * Nom d'usuari del jugador.
     */
    private String username;

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
     * @param username        Algorisme utilitzat en la partida
     * @param rondes                Conjunt de les rondes jugades
     */
    public DAOJugador(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    /**
     * Retorna el nom d'usuari del jugador.
     * 
     * @return username del jugador
     */
    public String getUsername() {
        return username;
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
