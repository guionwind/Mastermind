package persistencia.classes;

import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;

import java.io.IOException;
import java.io.Serializable;

public class GestorJugador extends Gestor<DAOJugador> {

    /**
     * Nom de l'arxiu a on es guarde les dades.
     */
    private static final String fileName = "Jugador.dat";

    /**
     * Constructora.
     *
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public GestorJugador() throws IOException {
        super(fileName);
    }

    /**
     * Guarda una nova intància de jugador
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param j                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaJaExisteix      Llença una excepció si la instància ja existeix.
     */
    public void afegirJugador(String id, DAOJugador j) throws IOException, InstanciaJaExisteix {
        if (existeixObjecte(id))
            throw new InstanciaJaExisteix("Afegir Jugador: La instancia amb identificador (" + id + ") ja és a l'arxiu.");

        afegirObjecte(id, j);
    }

    /**
     * Actualitza una intància de jugador
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param j                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void actualitzarJugador(String id, DAOJugador j) throws IOException, InstanciaNoExisteix {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Actualitzar Jugador: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        actualitzarObjecte(id, j);
    }

    /**
     * Obté una intància de jugador
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param j                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     * @throws ClassNotFoundException   Llença una excepció si la classe de la instància
     *                                  no s'ha localitzat.
     */
    public DAOJugador obtenirJugador(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir Jugador: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        return obtenirObjecte(id);
    }

    /**
     * Esborra una intància de jugador
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void eliminarJugador(String id) throws IOException, InstanciaNoExisteix {        
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Eliminar Jugador: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        eliminarObjecte(id);
    }

    /**
     * Indica si una intància de jugador existeix
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @return                          Cert si la instància està guardada i
     *                                  fals en cas contrari.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public boolean existeixJugador(String id) throws IOException {
        return existeixObjecte(id);
    }

    /**
     * Indica el total de partides que hi ha desades en el disc.
     * 
     * @return Nombre de partides guardades al disc
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida
     *                                  de dades.
     */
    public int totalJugadors() throws IOException {
        return nombreObjectes();
    }

    /**
     * Indica la contrasenya del jugador.
     * 
     * @param id
     * @return Contrasenya del jugador
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida
     *                                  de dades.   
     * @throws InstanciaNoExisteix      Llença una excepció si la instancia no  
     *                                  existeix.
     * @throws ClassNotFoundException   Llença una excepció si la classe
     *                                  de la instància no s'ha localitzat.
     */
    public String obtenirPassword(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir Password: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        return obtenirObjecte(id).getPassword();
    }
}
