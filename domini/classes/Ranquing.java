/**
 * Aquesta classe representa un Rànquing.
 * Cada rànquing té un identificador únic i un conjunt d'estadistiques partida.
 */
package domini.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe que implementa un comparador d'estadístiques de partides per ordenar-les segons la puntuació.
 */
class estadisticasComparator implements Comparator<Integer[]> {
    @Override
    public int compare(Integer[] first, Integer[] second) {
        return Integer.compare(second[1], first[1]); 
    }
}

/**
 * Classe Ranquing que gestiona les estadístiques de partides.
 */
public class Ranquing {
    // Atributs
    private int id;
    private ArrayList<Integer[]> estadistiques;
    private static int nombreRanquings = 0;

    /**
     * Constructor de la classe Ranquing.
     *
     * Id s´assigna de manera incremental
     */
    public Ranquing() {
        this.id = nombreRanquings;
        estadistiques = new ArrayList<Integer[]>();
        ++nombreRanquings;
    }

    // Mètodes

    /**
     * Retorna l'ID del ranquing.
     *
     * @return ID del ranquing.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Afegeix una estadística al ranquing i l'ordena segons la puntuació.
     *
     * @param estadistica Estadística de partida a afegir al ranquing.
     */
    public void addEstadistica(Integer[] estadistica) {
        estadistiques.add(estadistica);
        estadisticasComparator estatComparator = new estadisticasComparator();
        Collections.sort(estadistiques, estatComparator);
    }

    /**
     * Retorna les N estadístiques de partides amb les puntuacions més altes del ranquing.
     *
     * @param n Nombre d'estadístiques a retornar.
     * @return Llista d'estadístiques de partides amb les puntuacions més altes.
     */
    public ArrayList<Integer[]> getTopN(int n) {
        ArrayList<Integer[]> tempEstadistiques = new ArrayList<Integer[]>();
        if (estadistiques.size() < n) n = estadistiques.size();
        for (int i = 0; i < n; i++) {
            tempEstadistiques.add(estadistiques.get(i));
        }
        return tempEstadistiques;
    }
}