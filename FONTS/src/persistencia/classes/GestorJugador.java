package persistencia.classes;

import domini.classes.TipusPartida;
import domini.classes.exceptions.InstanciaNoExisteix;

import java.io.IOException;

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

    public String obtenirPassword(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir Passoword: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        return obtenirObjecte(id).getPassword();
    }
}
