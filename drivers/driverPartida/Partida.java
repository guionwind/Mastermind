package drivers.driverPartida;


import domini.classes.Ronda;

import java.util.HashMap;

public class Partida {
    private static int nombrePartides = 0;

    private final int id;
    private final Integer[] solutionCode;

    private HashMap<Integer, Ronda> rondes;

    private final CtrlPartida ctrlPartida;
    private final drivers.driverPartida.ConfiguracioPartida configuracioPartida;

    public Partida(ConfiguracioPartida configuracioPartida, Integer[] solutionCode, CtrlPartida ctrlPartida) {
        this.id = nombrePartides++;
        this.configuracioPartida = configuracioPartida;
        this.solutionCode = solutionCode;
        this.ctrlPartida = ctrlPartida;
    }

    public int getId() {
        return id;
    }

    public int rondesJugades() {
        return rondes.size();
    }
}
