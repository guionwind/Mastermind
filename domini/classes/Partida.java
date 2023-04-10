package domini.classes;

import java.util.HashSet;

public abstract class Partida {
    private final int id;
    private final int solutionCode;
    private HashSet<Ronda> rondes; //No la he posat a la creadora ja que la relacioó és 0...*
    private final EstadistiquesPartida estadisticaPartida;

    public Partida(int id, EstadistiquesPartida estadisticaPartida, int solutionCode) {
        this.id = id;
        this.estadisticaPartida = estadisticaPartida;
        this.solutionCode = solutionCode;
    }

    public int getId() {
        return id;
    }

    public int RondesJugades() {

        return rondes.size();
    }

    public int getSolutionCode() {
        return solutionCode;
    }

    public HashSet<Ronda> getRondes() {
        return rondes;
    }

    public HashSet<Ronda> addRonda(Ronda r) {
        rondes.add(r);
    }

}
