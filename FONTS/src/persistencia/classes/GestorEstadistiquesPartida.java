package persistencia.classes;

import java.io.IOException;

import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;

public class GestorEstadistiquesPartida extends Gestor<DAOEstadistiquesPartida> {

    /**
     * Nom de l'arxiu a on es guarde les dades.
     */
    private static final String fileName = "EstadistiquesPartida.dat";

    /**
     * Constructora.
     *
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public GestorEstadistiquesPartida() throws IOException {
        super(fileName);
    }

    /**
     * Guarda una nova intància de jugador
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param eP                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaJaExisteix      Llença una excepció si la instància ja existeix.
     */
    public void afegirEstadistiquesPartida(String id, DAOEstadistiquesPartida eP) throws IOException, InstanciaJaExisteix {
        if (existeixObjecte(id))
            throw new InstanciaJaExisteix("Afegir EstadistiquesPartida: La instancia amb identificador (" + id + ") ja és a l'arxiu.");

        afegirObjecte(id, eP);
    }

    /**
     * Actualitza una intància de EstadistiquesPartida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param eP                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void actualitzarEstadistiquesPartida(String id, DAOEstadistiquesPartida eP) throws IOException, InstanciaNoExisteix {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Actualitzar EstadistiquesPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        actualitzarObjecte(id, eP);
    }

    /**
     * Obté una intància de EstadistiquesPartida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param eP                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     * @throws ClassNotFoundException   Llença una excepció si la classe de la instància
     *                                  no s'ha localitzat.
     */
    public DAOEstadistiquesPartida obtenirEstadistiquesPartida(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir EstadistiquesPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        return obtenirObjecte(id);
    }

    /**
     * Esborra una intància de EstadistiquesPartida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void eliminarEstadistiquesPartida(String id) throws IOException, InstanciaNoExisteix {        
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Eliminar EstadistiquesPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        eliminarObjecte(id);
    }

    /**
     * Indica si una intància de EstadistiquesPartida existeix
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @return                          Cert si la instància està guardada i
     *                                  fals en cas contrari.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public boolean existeixEstadistiquesPartida(String id) throws IOException {
        return existeixObjecte(id);
    }
}
