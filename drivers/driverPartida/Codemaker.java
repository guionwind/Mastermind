package drivers.driverPartida;

import domini.controllers.CtrlAlgorisme;
import drivers.driverConfiguracioPartida.ConfiguracioPartida;

public class Codemaker {
    private final CtrlAlgorisme ctrlAlgorisme;
    public Codemaker() {
        super();
        ctrlAlgorisme = new CtrlAlgorisme();
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
        return ctrlAlgorisme.esbrinarCodiFiveguess(this.getId(),ultimIntent, resposta);
    }


}
