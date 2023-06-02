/**
 * Aquesta classe representa un Rànquing.
 * Cada rànquing té un identificador únic i un conjunt d'estadistiques partida.
 */
package domini.classes;

import presentacio.custom.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe que implementa un comparador d'estadístiques de partides per ordenar-les segons la puntuació.
 */
class estadisticasComparator implements Comparator<Pair<String, Integer>> {
    @Override
    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        if (o2.getSecond() > o1.getSecond()) {
            return 0;
        } else if (o2.getSecond() == o1.getSecond()) {
            if (o1.getFirst().compareTo(o2.getFirst()) <= 0) {
                return 1;
            } else return 0;
        } else {
            return 1;
        }
    }
}

/**
 * Classe Ranquing que gestiona les estadístiques de partides.
 */
public class Ranquing {
    // Atributs
    /**
     * Identificador del ranquing
     */
    //! NO ES FA SERVIR
    //private int id;

    /**
     * Conjunt d'estadistiques que conte el ranquing
     * Cada Integer[] conté {idJugador, puntuacio}
     */
    private ArrayList<Pair<String, Integer>> estadistiques;

    //! NO ES FA SERVIR
    //private static int nombreRanquings = 0;

    /**
     * Constructor de la classe Ranquing.
     *
     * Id s´assigna de manera incremental
     */
    public Ranquing() {
        //this.id = nombreRanquings;
        estadistiques = new ArrayList<>();
        //++nombreRanquings;
    }

    // Mètodes

    /**
     * Afegeix una estadística al ranquing i l'ordena segons la puntuació.
     *
     * @param estadistica Estadística de partida a afegir al ranquing.
     */
    public void addEstadistica(Pair<String, Integer> estadistica) {
        estadistiques.add(estadistica);
        estadisticasComparator estatComparator = new estadisticasComparator();
        estadistiques.sort(estatComparator);
    }

    /**
     * Retorna les N estadístiques de partides amb les puntuacions més altes del ranquing.
     *
     * @param n Nombre d'estadístiques a retornar.
     * @return Llista d'estadístiques de partides amb les puntuacions més altes.
     */
    public ArrayList<Pair<String, Integer>> getTopN(int n) {
        ArrayList<Pair<String, Integer>> tempEstadistiques = new ArrayList<>();
        if (estadistiques.size() < n) n = estadistiques.size();
        for (int i = 0; i < n; i++) {
            tempEstadistiques.add(estadistiques.get(i));
        }
        return tempEstadistiques;
    }
}