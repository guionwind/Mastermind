package domini.classes;

import domini.classes.exceptions.NumeroIntentsIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.TipusPartidaInvalid;
/**
 * Classe ConfiguracioPartida que gestiona les funcionalitats pertinents a les configuracions de les partides.
 */
public class ConfiguracioPartida {
    /**
     * Tipus de partida.
     * Ha de ser un dels tipus admesos per l'Enumeration TipusPartida.
     */
    private TipusPartida tipusPartida;
    /**
     * Número d'intents permesos per intentar trencar el codi.
     * Equivalent al número de rondes màxim.
     */
    private int numeroIntents;
    /**
     * Número de colors diferents ademsos per crear el codi a trencar.
     */
    private int numeroColors;
    /**
     * Número de fitxes diferents admemses per crear el codi a trencar.
     * Equivalent a la longitud del codi.
     */
    private int longitudCombinacio;

    /**
     * Constructora 1
     * Crea una nova instància de la classe amb tots els atributs per defecte.
     */
    public ConfiguracioPartida() {
        this.tipusPartida = null;
        this.numeroIntents = 0;
        this.numeroColors = 0;
        this.longitudCombinacio = 0;
    }

    /**
     * Constructora 2
     * Crea una nova instància de la classe amb tots els atributs definits.s
     *
     * @param   tipusPartida                        Tipus de partida.
     * @param   numeroIntents                       Número màxim d'intents permessos.
     * @param   numeroColors                        Número de colors diferents possibles per crear el codi.
     * @param   longitudCombinacio                  Llargada del codi a crear.
     *  
     * @throws NumeroIntentsIncorrecte              Llença una excepció si el número d'intents no
     *                                              està dins del rang.
     * @throws NumeroColorsIncorrecte               Llença una excepció si el número de colors no
     *                                              està dins del rang.
     * @throws LongitudCombinacioIncorrecte         Llença una excepció si el la longitud de la
     *                                              combinacio no està dins del rang.
     */
    public ConfiguracioPartida(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio)  throws NumeroIntentsIncorrecte, NumeroColorsIncorrecte,LongitudCombinacioIncorrecte {
        this.tipusPartida = tipusPartida;

        if (numeroIntents < 1 || numeroIntents > 20) throw new NumeroIntentsIncorrecte("Número d'intents està fora del rang permàs [1,20]");
        this.numeroIntents = numeroIntents;

        if (numeroColors < 4 || numeroColors > 10) throw new NumeroColorsIncorrecte("Numero de colors està fora del rang permàs [4,10]");
        this.numeroColors = numeroColors;

        if (longitudCombinacio < 4 || longitudCombinacio > 10) throw new LongitudCombinacioIncorrecte("Longitud de la combinació està fora del rang permàs [4,10]");
        this.longitudCombinacio = longitudCombinacio;
    }

    /**
     * Retorna el tipus de partida
     *
     * @return                      Tipus de partida.
     */
    public TipusPartida getTipusPartida() {
        return tipusPartida;
    }

    /**
     * Retorna el número d'intents permessos.
     *
     * @return                      Número d'intents permessos.
     */
    public int getNumeroIntents() {
        return numeroIntents;
    }

    /**
     * Retorna el número de colors diferents.
     *
     * @return                      Número de colors diferents.
     */
    public int getNumeroColors() {
        return numeroColors;
    }

    /**
     * Retorna la longitud del codi.
     *
     * @return                      Longitud del codi.
     */
    public int getLongitudCombinacio() {
        return longitudCombinacio;
    }

    /**
     * Assigna el tipus de partida.
     *
     * @param tipusPartida Tipus de la partida
     * @throws TipusPartidaInvalid Llença una excepció si el tipus de la partida no és correcte.
     */
    public void setTipusPartida(TipusPartida tipusPartida) throws TipusPartidaInvalid {
        if (tipusPartida == null) throw new TipusPartidaInvalid("El tipus de la partida és null");
        this.tipusPartida = tipusPartida;
    }

    /**
     * Assigna el número d'intents.
     *
     * @param numeroIntents Número d'intents de la partida
     * @throws NumeroIntentsIncorrecte Llença una excepció si el número d'intents no està dins del rang permes.
     */
    public void setNumeroIntents(int numeroIntents) throws NumeroIntentsIncorrecte {
        if (numeroIntents < 1 || numeroIntents > 20) throw new NumeroIntentsIncorrecte("Numero de intents esta fora del rang permes [1,20]");
        this.numeroIntents = numeroIntents;
    }

    /**
     * Assigna número de colors.
     *
     * @param numeroColors Número de colors.
     * @throws NumeroColorsIncorrecte Llença una excepció si el número de colors no està dins del rang permes.
     */
    public void setNumeroColors(int numeroColors) throws NumeroColorsIncorrecte {
        if (numeroColors < 4 || numeroColors > 10) throw new NumeroColorsIncorrecte("Numero de colors esta fora del rang permes [4,10]");
        this.numeroColors = numeroColors;
    }

    /**
     * Assigna longitud codi.
     *
     * @param longitudCombinacio Longitud del codi.
     * @throws LongitudCombinacioIncorrecte Llença una excepció si el la longitud de la combinacio no està dins del rang permes.
     */
    public void setLongitudCombinacio(int longitudCombinacio) throws LongitudCombinacioIncorrecte {
        if (longitudCombinacio < 4 || longitudCombinacio > 10) throw new LongitudCombinacioIncorrecte("Longitud de la combinacio esta fora del rang permes [4,10]");
        this.longitudCombinacio = longitudCombinacio;
    }
}