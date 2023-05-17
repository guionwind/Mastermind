package persistencia.classes;

import domini.classes.TipusPartida;

public class DAOConfiguracioPartida {
    /**
     * Tipus de partida.
     * Ha de ser un dels tipus admesos per l'Enumeration TipusPartida.
     */
    private TipusPartida tipusPartida;
    /**
     * Número d'intents permesos per intentar trencar el cotipusPartidadi.
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
     * Contructora buida.
     * Utilitzada per la deserialitzacio de l'objecte.
     */
    public DAOConfiguracioPartida() {}

    /**
     * Contructora amb paràmetres.
     * 
     * @param tipusPartida          Tipus de la partida.
     * @param numeroIntents         Cuantitat d'intents màxim permesos, equivalent al número de rondes.
     * @param numeroColors          Cuantitat de colors diferents.
     * @param longitudCombinacio    Longitud del codi.
     */
    public DAOConfiguracioPartida(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        this.tipusPartida = tipusPartida;
        this.numeroIntents = numeroIntents;
        this.numeroColors = numeroColors;
        this.longitudCombinacio = longitudCombinacio;
    }

    public TipusPartida getTipusPartida() {
        return tipusPartida;
    }

    public int getNumeroIntents() {
        return numeroIntents;
    }

    public int getNumeroColors() {
        return numeroColors;
    }

    public int getLongitudCombinacio() {
        return longitudCombinacio;
    }
}
