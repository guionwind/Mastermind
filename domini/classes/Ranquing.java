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
class estadisticasComparator implements Comparator<EstadistiquesPartida> {
    @Override
    public int compare(EstadistiquesPartida first, EstadistiquesPartida second) {
        return Integer.compare(first.getPuntuacio(), second.getPuntuacio());
    }
}

/**
 * Classe Ranquing que gestiona les estadístiques de partides.
 */
public class Ranquing {
    // Atributs
    private int id;
    private ArrayList<EstadistiquesPartida> estadistiques = new ArrayList<EstadistiquesPartida>();

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
     * @param estadisitica Estadística de partida a afegir al ranquing.
     */
    public void add(EstadistiquesPartida estadisitica) {
        this.estadistiques.add(estadisitica);
        estadisticasComparator estatComparator = new estadisticasComparator();
        Collections.sort(this.estadistiques, estatComparator);
    }

    /**
     * Retorna les N estadístiques de partides amb les puntuacions més altes del ranquing.
     *
     * @param n Nombre d'estadístiques a retornar.
     * @return Llista d'estadístiques de partides amb les puntuacions més altes.
     */
    public ArrayList<EstadistiquesPartida> getTopN(int n) {
        ArrayList<EstadistiquesPartida> tempEstadistiques = new ArrayList<EstadistiquesPartida>();
        for (int i = 0; i < n; i++) {
            tempEstadistiques.add(this.estadistiques.get(i));
        }
        return tempEstadistiques;
    }
}