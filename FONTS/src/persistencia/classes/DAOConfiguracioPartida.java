package persistencia.classes;

import java.io.Serializable;

public class DAOConfiguracioPartida implements Serializable {
    /**
     * Tipus de partida.
     * Ha de ser un dels tipus admesos per l'Enumeration TipusPartida.
     */
    private String tipusPartida;
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
     * Utilitzada per la deserialitzaciÓ de l'objecte.
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
    public DAOConfiguracioPartida(String tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        this.tipusPartida = tipusPartida;
        this.numeroIntents = numeroIntents;
        this.numeroColors = numeroColors;
        this.longitudCombinacio = longitudCombinacio;
    }

    /**
     * Retorna el tipus de partida.
     * 
     * @return      Tipus de partida.
     */
    public String getTipusPartida() {
        return tipusPartida;
    }

    /**
     * Retorna el número d'intents permessos.
     *
     * @return      Número d'intents permessos.
     */
    public int getNumeroIntents() {
        return numeroIntents;
    }

    /**
     * Retorna el número de colors diferents.
     *
     * @return      Número de colors diferents.
     */
    public int getNumeroColors() {
        return numeroColors;
    }

    /**
     * Retorna la longitud del codi.
     *
     * @return      Longitud del codi.
     */
    public int getLongitudCombinacio() {
        return longitudCombinacio;
    }
}
