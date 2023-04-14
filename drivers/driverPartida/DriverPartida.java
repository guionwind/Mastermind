package drivers.driverPartida;


import drivers.driverPartida.Codebreaker;
import drivers.driverPartida.ConfiguracioPartida;
import drivers.driverPartida.Partida;


public class DriverPartida {
    public void testConstructora() {
        Codebreaker test = new Codebreaker();
        Codemaker test2 = new Codemaker();

        System.out.println("testConstructora: Creada una partida amb Id: "+test.getId()+" i una altre amb Id: "+test2.getId());
    }

    public void testGetId() {
        Codebreaker test = new Codebreaker();
        Codemaker test2 = new Codemaker();

        int id1 = test.getId();
        int id2 = test2.getId();
        System.out.println("testGetId: Agafem Id: "+id1+" i una altra Id: "+id2);
    }

    public void testRondesJugades() {
        Codebreaker test = new Codebreaker();
        Codemaker test2 = new Codemaker();

        int r = test.rondesJugades();
        System.out.println("testRondesJugades: Agafem el numero de rondes jugades: "+r+" i l'altre nummero de rondes "+test2);
    }

    public void testGetSolutionCode() {
        Codebreaker test = new Codebreaker();
        Codemaker test2 = new Codemaker();

        Integer[] s = test.getSolutionCode();
        Integer[] s1 = test2.getSolutionCode();

        System.out.println("testGetSolutionCode: Agafem la solucio "+s+" i l'altre solucio "+s1);
    }

    public void testCreaRonda() {
        Codebreaker test = new Codebreaker();
        Codemaker test2 = new Codemaker();

        test.creaRonda();
        test2.creaRonda();
        System.out.println("S'ha afegit creat dues ronda i s'ha afegit a la estructura de dades");
    }

    public void testSetEstadistiquesPartida() {
        Partida test = new Partida();

        test.setEstadisticaPartida();
    }
}
