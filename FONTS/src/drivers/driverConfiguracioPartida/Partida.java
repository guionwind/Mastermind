package drivers.driverConfiguracioPartida;

public class Partida {

    private static int nombrePartides = 0;

    private final int id;
    private final Integer[] solutionCode;
    private final ConfiguracioPartida configuracioPartida;

    public Partida(ConfiguracioPartida configuracioPartida, Integer[] solutionCode) {
        this.id = nombrePartides++;
        this.configuracioPartida = configuracioPartida;
        this.solutionCode = solutionCode;
    }

}