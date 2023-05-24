package persistencia.classes;

import java.io.*;

import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;

public class GestorConfiguracioPartida extends Gestor<DAOConfiguracioPartida> {
    /**
     * Nom de l'arxiu a on es guarde les dades.
     */
    private static final String fileName = "ConfiguracioPartida.dat";
    
    /**
     * Constructora.
     * 
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public GestorConfiguracioPartida() throws IOException {
        super(fileName);
    }

    /**
     * Guarda una nova intància de configuració partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param cp                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaJaExisteix      Llença una excepció si la instància ja existeix.
     */
    public void afegirConfiguracioPartida(String id, DAOConfiguracioPartida cp) throws IOException, InstanciaJaExisteix {
        if (existeixObjecte(id))
            throw new InstanciaJaExisteix("Afegir ConfiguracioPartida: La instancia amb identificador (" + id + ") ja és a l'arxiu.");

        afegirObjecte(id, cp);
    }

    /**
     * Actualitza una intància de configuració partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param cp                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void actualitzarConfiguracioPartida(String id, DAOConfiguracioPartida cp) throws IOException, InstanciaNoExisteix {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Actualitzar ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        actualitzarObjecte(id, cp);
    }

    /**
     * Obté una intància de configuració partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param cp                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     * @throws ClassNotFoundException   Llença una excepció si la classe de la instància
     *                                  no s'ha localitzat.
     */
    public DAOConfiguracioPartida obtenirConfiguracioPartida(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        return obtenirObjecte(id);
    }

    /**
     * Esborra una intància de configuració partida
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void eliminarConfiguracioPartida(String id) throws IOException, InstanciaNoExisteix {        
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Eliminar ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        eliminarObjecte(id);
    }

    /**
     * Indica si una intància de configuració partida existeix
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @return                          Cert si la instància està guardada i
     *                                  fals en cas contrari.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public boolean existeixConfiguracioPartida(String id) throws IOException {
        return existeixObjecte(id);
    }
}