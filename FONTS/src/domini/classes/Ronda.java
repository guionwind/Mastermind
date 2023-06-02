/**
 * Aquesta classe representa una ronda.
 * Cada ronda té un identificador únic i una combinació intentada.
 */
package domini.classes;
/**
 * Classe Ronda que gestiona les funcions pertinents a les rondes d'una partida.
 */
public class Ronda {
    // Atributs 
    /**
     * Numero de la ronda actual, s'assignen en ordre incremental dins de cada partida començant pel 0 
     */
    private final int id;
    /**
     * Combinacio intentada en la ronda
     */
    private Integer[] combinacioIntentada;
    /**
     * Correccio corresponent a l'intent
     */
    private String correccio;

    /**
     * Constructor de la classe Ronda.
     *
     * @param id Identificador únic de la ronda
     */
    public Ronda(int id) {
        this.id = id;
    }

    public Ronda(Integer idRonda, Integer[] combinacioIntentada, String correccio) {
        id = idRonda;
        this.combinacioIntentada = combinacioIntentada;
        this.correccio = correccio;
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
     * @return La combinació intentada en la ronda
     */
    public Integer[] getCombinacioIntentada() {
        return combinacioIntentada;
    }

    /**
     * Estableix la combinacio intentada en aquesta ronda.
     * @param combinacioIntentada Combinacio intentada per el Codebreaker en aquesta ronda
     */
    public void setCombinacioIntentada(Integer[] combinacioIntentada) {
        this.combinacioIntentada = combinacioIntentada.clone();
    }

    /**
     * Obte la correccio corresponent a l'intent
     * @return Correccio de l'intent de la ronda
     */
    public String getCorreccio() {
        return correccio;
    }

    /**
     * Estableix la correccio corresponent a l'intent
     * @param correccio Correccio donada per el Codemaker en aquesta ronda
     */
    public void setCorreccio(String correccio) {
        this.correccio = correccio;
    }
}