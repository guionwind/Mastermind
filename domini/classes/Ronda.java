/**
 * Aquesta classe representa una ronda.
 * Cada ronda té un identificador únic i una combinació intentada.
 */
package domini.classes;

public class Ronda {
    // Atributs 
    private int id;
    private String combinacioIntentada;
    
    /**
     * Constructor de la classe Ronda.
     *
     * @param id Identificador únic de la ronda
     * @param combinacioIntentada Combinació intentada en aquesta ronda
     */
    public Ronda(int id, String combinacioIntentada) {
        this.id = id;
        this.combinacioIntentada = combinacioIntentada;
    }

    // Mètodes

    /**
     * Retorna el identificador únic de la ronda.
     *
     * @return El identificador de la ronda
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna la combinació intentada en aquesta ronda.
     *
     * @return La combinació intentada
     */
    public String getCombinacioIntentada() {
        return this.combinacioIntentada;
    }
}