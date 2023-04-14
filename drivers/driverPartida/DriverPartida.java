package drivers.driverPartida;


import drivers.driverPartida.ConfiguracioPartida;
import drivers.driverPartida.Partida;


public class DriverPartida {
    public void testConstructora() {
        ConfiguracioPartida c = new ConfiguracioPartida();
        CtrlPartida ctrlPartida = new CtrlPartida();
        Partida test = new Partida(c, new Integer[]{1,2,3,4}, ctrlPartida);
        Partida test2 = new Partida(c, new Integer[]{1,2,3,4}, ctrlPartida);

        System.out.println("testConstructora: Creada una partida amb Id: "+test.getId()+" i una altre amb Id: "+test2.getId());
    }

    public void testGetId() {
        ConfiguracioPartida c = new ConfiguracioPartida();
        CtrlPartida ctrlPartida = new CtrlPartida();
        Partida test = new Partida(c, new Integer[]{1,2,3,4}, ctrlPartida);

        int id = test.getId();
        System.out.println("testGetId: Agafem Id: "+id);
    }

    public void testRondesJugades() {
        ConfiguracioPartida c = new ConfiguracioPartida();
        CtrlPartida ctrlPartida = new CtrlPartida();
        Partida test = new Partida(c, new Integer[]{1,2,3,4}, ctrlPartida);

        int r = test.rondesJugades();
        System.out.println("testRondesJugades: Agafem el numero de rondes jugades: "+r);
    }

    public void testGetSolutionCode() {
        ConfiguracioPartida c = new ConfiguracioPartida();
        CtrlPartida ctrlPartida = new CtrlPartida();
        Partida test = new Partida(c, new Integer[]{1,2,3,4}, ctrlPartida);
    }
}
