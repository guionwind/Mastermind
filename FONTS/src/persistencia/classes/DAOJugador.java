package persistencia.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class DAOJugador implements Serializable {

    /**
     * Id del jugador.
     */
    private int idJugador;

    /**
     * Contrassenya que fa servir el jugador per autenticar-se.
     */
    private String password;

    private ArrayList<Integer> idPartides;

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
    public DAOJugador(int idJugador, String password, ArrayList<Integer> idPartides) {
        this.idJugador = idJugador;
        this.password = password;
        this.idPartides = idPartides;
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

    public ArrayList<Integer> getIdPartides() {
        return idPartides;
    }
}
