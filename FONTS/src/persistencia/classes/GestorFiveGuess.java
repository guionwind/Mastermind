package persistencia.classes;

import java.io.*;

import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;

public class GestorFiveGuess extends Gestor<DAOFiveGuess> {
    /**
     * Nom de l'arxiu a on es guarde les dades.
     */
    private static final String fileName = "FiveGuess.dat";
    
    /**
     * Constructora.
     * 
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public GestorFiveGuess() throws IOException {
        super(fileName);
    }

    /**
     * Guarda una nova intància de l'algorisme FiveGuess
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param fG                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaJaExisteix      Llença una excepció si la instància ja existeix.
     */
    public void afegirFiveGuess(String id, DAOFiveGuess fG) throws IOException, InstanciaJaExisteix {
        if (existeixObjecte(id))
            throw new InstanciaJaExisteix("Afegir FiveGuess: La instancia amb identificador (" + id + ") ja és a l'arxiu.");

        afegirObjecte(id, fG);
    }

    /**
     * Actualitza una intància de l'algorisme FiveGuess
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param fG                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void actualitzarFiveGuess(String id, DAOFiveGuess fG) throws IOException, InstanciaNoExisteix {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Actualitzar FiveGuess: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        actualitzarObjecte(id, fG);
    }

    /**
     * Obté una intància de l'algorisme FiveGuess
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param fG                        Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     * @throws ClassNotFoundException   Llença una excepció si la classe de la instància
     *                                  no s'ha localitzat.
     */
    public DAOFiveGuess obtenirFiveGuess(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir FiveGuess: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        return obtenirObjecte(id);
    }

    /**
     * Esborra una intància de l'algorisme FiveGuess
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws InstanciaNoExisteix      Llença una excepció si la instància no existeix.
     */
    public void eliminarFiveGuess(String id) throws IOException, InstanciaNoExisteix {        
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Eliminar FiveGuess: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        eliminarObjecte(id);
    }

    /**
     * Indica si una intància de l'algorisme FiveGuess existeix
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @return                          Cert si la instància està guardada i
     *                                  fals en cas contrari.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public boolean existeixFiveGuess(String id) throws IOException {
        return existeixObjecte(id);
    }
}
