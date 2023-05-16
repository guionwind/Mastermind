package persistencia.classes;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

import domini.classes.ConfiguracioPartida;
import domini.classes.exceptions.InstanciaJaExisteix;
import domini.classes.exceptions.InstanciaNoExisteix;

public class GestorConfiguracioPartida extends Gestor {
    
    public GestorConfiguracioPartida() {
        super("ConfiguracioPartida.dat");
    }

    public void afegirConfiguracioPartida(String id, ConfiguracioPartida cp) throws IOException, InstanciaJaExisteix {
        if (existeixObjecte(id))
            throw new InstanciaJaExisteix("Afegir ConfiguracioPartida: La instancia amb identificador (" + id + ") ja és a l'arxiu.");

        afegirObjecte(id, cp);
    }

    public void actualitzarConfiguracioPartida(String id, ConfiguracioPartida cp) throws IOException, InstanciaNoExisteix {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Actualitzar ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        actualitzarObjecte(id, cp);
    }

    public ConfiguracioPartida obtenirConfiguracioPartida(String id) throws IOException, InstanciaNoExisteix, ClassNotFoundException {
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Obtenir ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");
        
        return (ConfiguracioPartida) obtenirObjecte(id);
    }

    public void eliminarConfiguracioPartida(String id) throws IOException, InstanciaNoExisteix {        
        if (!existeixObjecte(id))
            throw new InstanciaNoExisteix("Eliminar ConfiguracioPartida: La instancia amb identificador (" + id + ") no és a l'arxiu.");

        eliminarObjecte(id);
    }

    public boolean existeixConfiguracioPartida(String id) throws IOException {
        return existeixObjecte(id);
    }

    public static void main(String[] args) throws Exception {
        GestorConfiguracioPartida gcp = new GestorConfiguracioPartida();
        
        // for (int i=0; i<10; ++i) {
        //     int idPartida = ThreadLocalRandom.current().nextInt(0, 1000);
        //     int numeroIntents = ThreadLocalRandom.current().nextInt(1,21);
        //     int numeroColors = ThreadLocalRandom.current().nextInt(4,11);
        //     int longitudCombinacio = ThreadLocalRandom.current().nextInt(4,11);
        //     ConfiguracioPartida cp = new ConfiguracioPartida(null, numeroIntents, numeroColors, longitudCombinacio);
        //     cp.setPartida(idPartida);

        //     System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(cp.getPartida())));
        //     gcp.afegirConfiguracioPartida(String.valueOf(cp.getPartida()), cp);
        //     System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(cp.getPartida())));
        // }

        ConfiguracioPartida cp1 = gcp.obtenirConfiguracioPartida(String.valueOf(707));
        System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(cp1.getPartida())));
        System.out.println(cp1.getPartida());
        System.out.println(cp1.getTipusPartida());
        System.out.println(cp1.getNumeroIntents());
        System.out.println(cp1.getNumeroColors());
        System.out.println(cp1.getLongitudCombinacio());
        System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(cp1.getPartida())));

        System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(610)));
        gcp.eliminarConfiguracioPartida(String.valueOf(610));
        System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(610)));
        
        System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(816)));
        ConfiguracioPartida cp2 = gcp.obtenirConfiguracioPartida(String.valueOf(816));
        System.out.println(gcp.existeixConfiguracioPartida(String.valueOf(cp2.getPartida())));
        System.out.println(cp2.getPartida());
        System.out.println(cp2.getTipusPartida());
        System.out.println(cp2.getNumeroIntents());
        System.out.println(cp2.getNumeroColors());
        System.out.println(cp2.getLongitudCombinacio());
        
        cp2.setNumeroIntents(7);
        cp2.setNumeroColors(8);
        cp2.setLongitudCombinacio(9);
        System.out.println(cp2.getPartida());
        System.out.println(cp2.getTipusPartida());
        System.out.println(cp2.getNumeroIntents());
        System.out.println(cp2.getNumeroColors());
        System.out.println(cp2.getLongitudCombinacio());

        gcp.actualitzarConfiguracioPartida(String.valueOf(cp2.getPartida()), cp2);
        cp2 = gcp.obtenirConfiguracioPartida(String.valueOf(816));
        System.out.println(cp2.getPartida());
        System.out.println(cp2.getTipusPartida());
        System.out.println(cp2.getNumeroIntents());
        System.out.println(cp2.getNumeroColors());
        System.out.println(cp2.getLongitudCombinacio());
    }
}