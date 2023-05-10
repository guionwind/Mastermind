package domini.classes;

import domini.classes.exceptions.NumeroIntentsIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.PartidaJaAssignada;
import domini.classes.exceptions.TipusPartidaInvalid;
import domini.classes.exceptions.PartidaInvalida;

public class ConfiguracioPartida {

    /**
     * Enumeration dels tipus de partida possibles.
     */
    public static enum TipusPartida {
        CODEBREAKER,
        CODEMAKER
    }

    /**
     * Identificador de la partida a la que pertany la configuració.
     */
    private Integer idPartida;
    /**
     * Tipus de partida.
     * Ha de ser un dels tipus admesos per l'Enumeration TipusPartida.
     */
    private TipusPartida tipusPartida;
    /**
     * Número d'intents permesos per intentar trencar el codi.
     * Equivalent al número de rondes màxim.
     */
    private Integer numeroIntents;
    /**
     * Número de colors diferents ademsos per crear el codi a trencar.
     */
    private Integer numeroColors;
    /**
     * Número de fitxes diferents admemses per crear el codi a trencar.
     * Equivalent a la longitud del codi.
     */
    private Integer longitudCombinacio;

    /**
     * Constructora 1
     * Crea una nova instància de la classe amb tots els atributs per defecte.
     */
    public ConfiguracioPartida() {
        this.idPartida = null;
        this.tipusPartida = null;
        this.numeroIntents = 0;
        this.numeroColors = 0;
        this.longitudCombinacio = 0;
    }

    /**
     * Constructora 2
     * Crea una nova instància de la classe amb tots els atributs definits.s
     *
     * @param   tipusPartida        Tipus de partida.
     * @param   numeroIntents       Número màxim d'intents permessos.
     * @param   numeroColors        Número de colors diferents possibles per crear el codi.
     * @param   longitudCombinacio  Llargada del codi a crear.
     */
    public ConfiguracioPartida(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio)  throws LongitudCombinacioIncorrecte, NumeroIntentsIncorrecte, NumeroColorsIncorrecte {
        this.idPartida = null;
        this.tipusPartida = tipusPartida;

        if (numeroIntents < 1 || numeroIntents > 20) throw new NumeroIntentsIncorrecte("Número d'intents està fora del rang permàs [1,20]");
        this.numeroIntents = numeroIntents;

        if (numeroColors < 4 || numeroColors > 10) throw new NumeroColorsIncorrecte("Numero de colors està fora del rang permàs [4,10]");
        this.numeroColors = numeroColors;

        if (longitudCombinacio < 4 || longitudCombinacio > 10) throw new LongitudCombinacioIncorrecte("Longitud de la combinació està fora del rang permàs [4,10]");
        this.longitudCombinacio = longitudCombinacio;
    }

    /**
     * Retorna l'identificador de la partida a la que pertany.
     *
     * @return                      Partida a la que pertany.
     */
    public Integer getPartida() {
        return idPartida;
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
    public Integer getNumeroIntents() {
        return numeroIntents;
    }

    /**
     * Retorna el número de colors diferents.
     *
     * @return                      Número de colors diferents.
     */
    public Integer getNumeroColors() {
        return numeroColors;
    }

    /**
     * Retorna la longitud del codi.
     *
     * @return                      Longitud del codi.
     */
    public Integer getLongitudCombinacio() {
        return longitudCombinacio;
    }

    /**
     * Assigna la partida a la que pertany.
     *
     * @param   partida             Partida a la que pertany.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public void setPartida(Integer partida) throws PartidaJaAssignada, PartidaInvalida {
        if (this.idPartida != null) throw new PartidaJaAssignada("La configuració de la partida ja està assignada a una partida");
        if (partida == null || partida < 0) throw new PartidaInvalida("L'identificador de la partida és null o un valor negatiu");
        this.idPartida = partida;
    }

    /**
     * Assigna el tipus de partida.
     *
     * @param tipusPartida          Tipus de la partida
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     * @throws TipusPartidaInvalid
     */
    public void setTipusPartida(TipusPartida tipusPartida) throws TipusPartidaInvalid {
        if (tipusPartida == null) throw new TipusPartidaInvalid("El tipus de la partida és null");
        this.tipusPartida = tipusPartida;
    }

    /**
     * Assigna el número d'intents.
     *
     * @param numeroIntents         Número d'intents.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public void setNumeroIntents(Integer numeroIntents) throws NumeroIntentsIncorrecte {
        if (numeroIntents < 1 || numeroIntents > 20) throw new NumeroIntentsIncorrecte("Numero de intents esta fora del rang permes [1,20]");
        this.numeroIntents = numeroIntents;
    }

    /**
     * Assigna número de colors.
     *
     * @param numeroColors          Número de colors.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public void setNumeroColors(Integer numeroColors) throws NumeroColorsIncorrecte {
        if (numeroColors < 4 || numeroColors > 10) throw new NumeroColorsIncorrecte("Numero de colors esta fora del rang permes [4,10]");
        this.numeroColors = numeroColors;
    }

    /**
     * Assigna longitud codi.
     *
     * @param longitudCombinacio    Longitud del codi.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public void setLongitudCombinacio(Integer longitudCombinacio) throws LongitudCombinacioIncorrecte {
        if (longitudCombinacio < 4 || longitudCombinacio > 10) throw new LongitudCombinacioIncorrecte("Longitud de la combinacio esta fora del rang permes [4,10]");
        this.longitudCombinacio = longitudCombinacio;
    }
}