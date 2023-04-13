package domini.classes;

import domini.controllers.CtrlPartida;

import java.util.HashMap;

public abstract class Partida {
    private final int id;
    private final Integer[] solutionCode;

    private static int nombrePartides = 0;
    private HashMap<Integer, Ronda> rondes; //No la he posat a la creadora ja que la relacioó és 0...*

    private EstadistiquesPartida estadisticaPartida;

    private final ConfiguracioPartida configuracioPartida;

    private final CtrlPartida ctrlPartida;

    /**
     * Constructora de la classe partida
     * @param configuracioPartida de la partida en questio
     * @param solutionCode de la partida
     */

    public Partida(ConfiguracioPartida configuracioPartida, Integer[] solutionCode, CtrlPartida ctrlPartida) {
        this.id = nombrePartides;
        nombrePartides++;
        this.configuracioPartida = configuracioPartida;
        this.solutionCode = solutionCode;
        this.ctrlPartida = ctrlPartida;
    }

    /**
     * Retorna la id de la partida
     *
     * @return l'identificador de la partida
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el numero de rondes jugades de la partida
     *
     * @return numero de rondes jugades
     */
    public int rondesJugades() {
        return rondes.size();
    }

    /**
     * Retorna la solucio de la partida
     *
     * @return la solucio de la partida
     */
    public Integer[] getSolutionCode() {
        return solutionCode;
    }

    public int getNombrePartides() {
        return nombrePartides;
    }

    /**
     * Afegeix una ronda a la partida
     *
     * @param r ronda ha afegir
     */
    public void addRonda() {
        Integer id = Integer.valueOf(rondes.size());
        Ronda r = new Ronda(id);
        rondes.put(id, r);
    }

    /**
     * Associa una EstadisticaPartida amb Partida
     *
     * @param estadisticaPartida a associar amb Partida
     */

    public void setEstadisticaPartida(EstadistiquesPartida estadisticaPartida) {
        if (estadisticaPartida == null) {
            this.estadisticaPartida = estadisticaPartida;
        }
    }

    public ConfiguracioPartida getConfiguracioPartida() {
        return configuracioPartida;
    }

}
