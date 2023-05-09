/**
 * Aquesta classe representa una ronda.
 * Cada ronda té un identificador únic i una combinació intentada.
 */
package domini.classes;

public class Ronda {
    // Atributs 
    private final int id;
    private Integer[] combinacioIntentada;

    private String correccio;

    /**
     * Constructor de la classe Ronda.
     *
     * @param id Identificador únic de la ronda
     */
    public Ronda(int id) {
        this.id = id;
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
    public Integer[] getCombinacioIntentada() {
        return combinacioIntentada;
    }

    public void setCombinacioIntentada(Integer[] combinacioIntentada) {
        this.combinacioIntentada = combinacioIntentada.clone();
    }

    public String getCorreccio() {
        return correccio;
    }

    public void setCorreccio(String correccio) {
        this.correccio = correccio;
    }
}