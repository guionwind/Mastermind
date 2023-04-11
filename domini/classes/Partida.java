package domini.classes;

import java.util.HashSet;

public abstract class Partida {
    private final int id;
    private final Integer[] solutionCode;

    private static int nombrePartides;
    private HashSet<Ronda> rondes; //No la he posat a la creadora ja que la relacioó és 0...*
    private final EstadistiquesPartida estadisticaPartida;

    private final ConfiguracioPartida configuracioPartida;

    /**
     * Constructora de la classe Partida
     *
     * @param id Identificador de la Partida
     * @param estadisticaPartida Estadistiques de la Partida
     * @param solutionCode La solucio de la partida
     */

    public Partida(int id, EstadistiquesPartida estadisticaPartida, ConfiguracioPartida configuracioPartida, Integer[] solutionCode) {
        this.id = id;
        this.estadisticaPartida = estadisticaPartida;
        this.configuracioPartida = configuracioPartida;
        this.solutionCode = solutionCode;
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

    public void incNombrePartides() {
        nombrePartides =+ 1;
    }

    public void decNombrePartides() {
        nombrePartides -= 1;
    }

    /**
     * Retorna les rondes de la partida
     *
     * @return rondes de la partida
     */
    public HashSet<Ronda> getRondes() {
        return rondes;
    }

    /**
     * Afegeix una ronda a la partida
     *
     * @param r ronda ha afegir
     */
    public void addRonda(Ronda r) {
        rondes.add(r);
    }

}
