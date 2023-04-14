package drivers.driverPartida;

import drivers.driverConfiguracioPartida.ConfiguracioPartida;

public class Codemaker {
    public Codemaker() {
        super();
    }
    public Integer getId() {
        return 4;
    }

    public Integer rondesJugades() {
        return 5;
    }

    public Integer[] getSolutionCode() {
        return new Integer[]{1,2,3,4};
    }

    public void creaRonda() {

    }

    public Integer[] getCodiMaquina(Integer[] ultimIntent, String resposta) {
        return new Integer[] {-1,-1,-1,-1};
    }


}
