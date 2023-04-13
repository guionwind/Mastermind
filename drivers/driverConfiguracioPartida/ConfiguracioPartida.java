package drivers.driverConfiguracioPartida;

import drivers.driverConfiguracioPartida.Partida;

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
    private Partida partida;
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
        this.partida = null;
        this.tipusPartida = null;
        this.numeroIntents = 0;
        this.numeroColors = 0;
        this.longitudCombinacio = 0;
    }

    /**
     * Constructora 2
     * Crea una nova instància de la classe amb tots els atributs definits.s
     *
     * @param   partida             Identificador de la partida a la que pertanys la configuració.
     * @param   tipusPartida        Tipus de partida.
     * @param   numeroIntents       Número màxim d'intents permessos.
     * @param   numeroColors        Número de colors diferents possibles per crear el codi.
     * @param   longitudCombinacio  Llargada del codi a crear.
     */
    public ConfiguracioPartida(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        this.partida = null;
        this.tipusPartida = tipusPartida;

        if (numeroIntents < 1) numeroIntents = 1;
        else if (numeroIntents > 20) numeroIntents = 20;
        this.numeroIntents = numeroIntents;

        if (numeroColors < 4) numeroColors = 4;
        else if (numeroColors > 10) numeroColors = 10;
        this.numeroColors = numeroColors;

        if (longitudCombinacio < 4) longitudCombinacio = 4;
        else if (longitudCombinacio > 10) longitudCombinacio = 10;
        this.longitudCombinacio = longitudCombinacio;
    }

    /**
     * Retorna l'identificador de la partida a la que pertany.
     *
     * @return                      Partida a la que pertany.
     */
    public Partida getPartida() {
        return partida;
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
     * Assigna la partida a la que pertany.
     *
     * @param   partida             Partida a la que pertany.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setPartida(Partida partida) throws Exception {
        if (this.partida == null && partida != null) {
            // COMPROVAR QUE NO EXISTEIXI UNA ALTRA PARTIDA
            // Controlador domini demano, existeix partida?
            // Controlador domini demana a Partida,
            //      existeix partida ? (boolea) / retorna partida (amb Exception per al cas que no existeixi)
            this.partida = partida;
            return true;
        }
        return false;
    }

    /**
     * Assigna el tipus de partida.
     *
     * @param tipusPartida          Tipus de la partida
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setTipusPartida(TipusPartida tipusPartida) {
        if (tipusPartida != null) {
            // COMPROVAR QUE EXISTEIXI LA PARTIDA
            // Controlador domini demano, existeix partida?
            // Controlador domini demana a Partida,
            //      existeix partida ? (boolea) / retorna partida (amb Exception per al cas que no existeixi)
            this.tipusPartida = tipusPartida;
            return true;
        }
        return false;
    }

    /**
     * Assigna el número d'intents.
     *
     * @param numeroIntents         Número d'intents.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setNumeroIntents(int numeroIntents) {
        if (numeroIntents >= 1 && numeroIntents <= 20) {
            this.numeroIntents = numeroIntents;
            return true;
        }
        return false;
    }

    /**
     * Assigna número de colors.
     *
     * @param numeroColors          Número de colors.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setNumeroColors(int numeroColors) {
        if (numeroColors >= 4 && numeroColors <= 10) {
            this.numeroColors = numeroColors;
            return true;
        }
        return false;
    }

    /**
     * Assigna longitud codi.
     *
     * @param longitudCombinacio    Longitud del codi.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setLongitudCombinacio(int longitudCombinacio) {
        if (longitudCombinacio >= 4 && longitudCombinacio <= 10) {
            this.longitudCombinacio = longitudCombinacio;
            return true;
        }
        return false;
    }
}