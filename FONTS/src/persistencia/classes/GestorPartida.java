package persistencia.classes;

import java.io.IOException;

import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;

public class GestorPartida extends Gestor<DAOPartida> {
    
    /**
     * Nom de l'arxiu a on es guarde les dades.
     */
    private static final String fileName = "Partida.dat";

    /**
     * Constructora.
     *
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public GestorPartida() throws IOException {
        super(fileName);
    }

    /**
     * Guarda una nova intància de Partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param p                         Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaJaExisteix      Llença una excepció si la instància ja existeix.
     */
    public void afegirPartida(String id, DAOPartida p) throws IOException, InstanciaJaExisteix {
        if (existeixObjecte(id))
            throw new InstanciaJaExisteix("Afegir Partida: La instancia amb identificador (" + id + ") ja és a l'arxiu.");

        afegirObjecte(id, p);
    }

    /**
     * Actualitza una intància de Partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param p                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void actualitzarPartida(String id, DAOPartida p) throws IOException, InstanciaNoExisteix {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Actualitzar Partida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        actualitzarObjecte(id, p);
    }

    /**
     * Obté una intància de Partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param p                         Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     * @throws ClassNotFoundException   Llença una excepció si la classe de la instància
     *                                  no s'ha localitzat.
     */
    public DAOPartida obtenirPartida(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir Partida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        return obtenirObjecte(id);
    }

    /**
     * Esborra una intància de Partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void eliminarPartida(String id) throws IOException, InstanciaNoExisteix {        
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Eliminar Partida: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        eliminarObjecte(id);
    }

    /**
     * Indica si una intància de Partida existeix
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @return                          Cert si la instància està guardada i
     *                                  fals en cas contrari.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public boolean existeixPartida(String id) throws IOException {
        return existeixObjecte(id);
    }

    /**
     * Indica el total de partides que hi ha desades en el disc.
     * 
     * @return Nombre de partides guardades al disc
     * @throws IOException
     */
    public int totalPartides() throws IOException {
        return nombreObjectes();
    }
}
