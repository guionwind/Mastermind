package persistencia.classes;

import java.io.*;

import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;

public class GestorConfiguracioPartida extends Gestor<DAOConfiguracioPartida> {
    
    public GestorConfiguracioPartida() {
        super("ConfiguracioPartida.dat");
    }

    public void afegirConfiguracioPartida(String id, DAOConfiguracioPartida cp) throws IOException, InstanciaJaExisteix {
        if (existeixObjecte(id))
            throw new InstanciaJaExisteix("Afegir ConfiguracioPartida: La instancia amb identificador (" + id + ") ja és a l'arxiu.");

        afegirObjecte(id, cp);
    }

    public void actualitzarConfiguracioPartida(String id, DAOConfiguracioPartida cp) throws IOException, InstanciaNoExisteix {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Actualitzar ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        actualitzarObjecte(id, cp);
    }

    public DAOConfiguracioPartida obtenirConfiguracioPartida(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        return obtenirObjecte(id);
    }

    public void eliminarConfiguracioPartida(String id) throws IOException, InstanciaNoExisteix {        
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Eliminar ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        eliminarObjecte(id);
    }

    public boolean existeixConfiguracioPartida(String id) throws IOException {
        return existeixObjecte(id);
    }
}