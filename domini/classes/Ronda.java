/**
 * Aquesta classe representa una ronda.
 * Cada ronda té un identificador únic i una combinació intentada.
 */
package domini.classes;

public class Ronda {
    // Atributs 
    private final int id;
    private Integer[] combinacioIntentada;

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
        return this.combinacioIntentada;
    }


    /**
     * Retorna la resposta que s'obtindria donat un dels codis encara no intentats i
     * el codi solució.
     *
     * @param   combinacioIntentada  Un codi del conjunt de codis que encara no s'han intentat com a solució
     * @param   combinacioCorrecta    Un codi del conjunt de codis que poden ser solució.
     * @return                  La resposta obtinguda per intentar un codi sobre un codi solució.
     */
    public String getResposta(Integer[] combinacioIntentada, Integer[] combinacioCorrecta) {
        String resposta = "";

        // Black
        for (int i=0; i<combinacioCorrecta.length; ++i) {
            if (combinacioIntentada[i] == combinacioCorrecta[i]) {
                resposta += "B";
                combinacioIntentada[i] = -1;
                combinacioCorrecta[i] = -1;
            }
        }

        // White
        for (int i=0; i<combinacioCorrecta.length; ++i) {
            if (combinacioIntentada[i] != -1) { // Encara no l'hem processat
                boolean trobat = false;
                for (int j=0; j<combinacioCorrecta.length && !trobat; ++j) {
                    if (combinacioIntentada[i] == combinacioCorrecta[j]) {
                        trobat = true;
                        resposta += "W";
                        combinacioCorrecta[j] = -1;
                    }
                }
            }
        }

        while (resposta.length() < combinacioCorrecta.length)
            resposta += "X";

        return resposta;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setCombinacioIntentada(Integer[] combinacioIntentada) {
        this.combinacioIntentada = combinacioIntentada;
    }
}