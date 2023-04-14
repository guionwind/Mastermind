package drivers.driverConfiguracioPartida;

import drivers.driverConfiguracioPartida.ConfiguracioPartida;
import drivers.driverConfiguracioPartida.ConfiguracioPartida.TipusPartida;

public class DriverConfiguracioPartida {
    public void testCreadora1() {
        ConfiguracioPartida cp = new ConfiguracioPartida();
        System.out.println("Instància amb creadora 1 creada");
    }

    public void testCreadora2() {
        ConfiguracioPartida cP;
        TipusPartida tP;
        int nI, nC, lC;

        tP = TipusPartida.CODEMAKER;
        nI = 1;
        nC = 4;
        lC = 4;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER,  nI, nC, lC);
        System.out.println("Instància amb creadora 2 creada. Valors minims permesos:");
        System.out.println("Parametres constructora = (" + tP + ", " + nI + ", " + nC + ", " + lC + ")");
        System.out.println("Parametres constructora = (" + cP.getTipusPartida() + ", " + cP.getNumeroIntents() + ", " + cP.getNumeroColors() + ", " + cP.getLongitudCombinacio() + ")");

        tP = TipusPartida.CODEMAKER;
        nI = 20;
        nC = 10;
        lC = 10;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER,  nI, nC, lC);
        System.out.println("Instància amb creadora 2 creada. Valors maxims permesos");
        System.out.println("Parametres constructora = (" + tP + ", " + nI + ", " + nC + ", " + lC + ")");
        System.out.println("Parametres constructora = (" + cP.getTipusPartida() + ", " + cP.getNumeroIntents() + ", " + cP.getNumeroColors() + ", " + cP.getLongitudCombinacio() + ")");

        tP = TipusPartida.CODEMAKER;
        nI = 12;
        nC = 7;
        lC = 8;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER,  nI, nC, lC);
        System.out.println("Instància amb creadora 2 creada. Valors dins dels rangs permesos");
        System.out.println("Parametres constructora = (" + tP + ", " + nI + ", " + nC + ", " + lC + ")");
        System.out.println("Parametres constructora = (" + cP.getTipusPartida() + ", " + cP.getNumeroIntents() + ", " + cP.getNumeroColors() + ", " + cP.getLongitudCombinacio() + ")");

        tP = TipusPartida.CODEMAKER;
        nI = 0;
        nC = 3;
        lC = 3;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER,  nI, nC, lC);
        System.out.println("Instància amb creadora 2 creada. Valors just per sota dels minims permesos");
        System.out.println("Parametres constructora = (" + tP + ", " + nI + ", " + nC + ", " + lC + ")");
        System.out.println("Parametres constructora = (" + cP.getTipusPartida() + ", " + cP.getNumeroIntents() + ", " + cP.getNumeroColors() + ", " + cP.getLongitudCombinacio() + ")");

        tP = TipusPartida.CODEMAKER;
        nI = 21;
        nC = 11;
        lC = 11;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER,  nI, nC, lC);
        System.out.println("Instància amb creadora 2 creada. Valors just per sobre dels minims permesos");
        System.out.println("Parametres constructora = (" + tP + ", " + nI + ", " + nC + ", " + lC + ")");
        System.out.println("Parametres constructora = (" + cP.getTipusPartida() + ", " + cP.getNumeroIntents() + ", " + cP.getNumeroColors() + ", " + cP.getLongitudCombinacio() + ")");

        tP = TipusPartida.CODEMAKER;
        nI = -1;
        nC = -2;
        lC = -3;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER,  nI, nC, lC);
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, -1, -2, -3);
        System.out.println("Instància amb creadora 2 creada. Valors negatius per sota dels minims permesos");
        System.out.println("Parametres constructora = (" + tP + ", " + nI + ", " + nC + ", " + lC + ")");
        System.out.println("Parametres constructora = (" + cP.getTipusPartida() + ", " + cP.getNumeroIntents() + ", " + cP.getNumeroColors() + ", " + cP.getLongitudCombinacio() + ")");

        tP = TipusPartida.CODEMAKER;
        nI = 30;
        nC = 98;
        lC = 76;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER,  nI, nC, lC);
        System.out.println("Instància amb creadora 2 creada. Valors positius per sobre dels maxims permesos");
        System.out.println("Parametres constructora = (" + tP + ", " + nI + ", " + nC + ", " + lC + ")");
        System.out.println("Parametres constructora = (" + cP.getTipusPartida() + ", " + cP.getNumeroIntents() + ", " + cP.getNumeroColors() + ", " + cP.getLongitudCombinacio() + ")");
    }

    public void testGetPartida() {
        ConfiguracioPartida cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, 8);
        Integer p = new Integer(8);
        System.out.println("Obtenim partida");
    }

    public void testGetTipusPartida() {
        ConfiguracioPartida cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, 8);
        TipusPartida tP = cP.getTipusPartida();
        System.out.println("Obtenim tipus de la partida: " + tP);
    }

    public void testGetNumeroIntents() {
        ConfiguracioPartida cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, 8);
        int nI = cP.getNumeroIntents();
        System.out.println("Obtenim numero de intents: " + nI);
    }

    public void testGetNumeroColors() {
        ConfiguracioPartida cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, 8);
        int nC = cP.getNumeroColors();
        System.out.println("Obtenim numero de colors: " + nC);
    }

    public void testGetLongitudCombinacio() {
        ConfiguracioPartida cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, 8);
        int lC = cP.getLongitudCombinacio();
        System.out.println("Obtenim longitud de la combinacio: " + lC);
    }

    public void testSetPartida() throws Exception {
        boolean retorn;
        Integer p;
        ConfiguracioPartida cP1, cP2;

        cP1 = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, 8);
        p = new Integer(5);
        retorn = cP1.setPartida(p);
        System.out.println("Assignem la partida que conte la propia configuracio partida: " + retorn);

        p = new Integer(7);
        retorn = cP1.setPartida(p);
        System.out.println("Assignem la partida quan ja hi ha una partida assignada: " + retorn);

    }

    public void testSetTipusPartida() {
        boolean retorn;

        TipusPartida tP1 = TipusPartida.CODEMAKER;
        ConfiguracioPartida cP = new ConfiguracioPartida(tP1, 12, 7, 8);
        TipusPartida tP2 = TipusPartida.CODEBREAKER;
        retorn = cP.setTipusPartida(tP2);
        System.out.println("Assignem el tipus de partida: " + retorn + " Antic = " + tP1 + ", Nou = " + tP2);
    }

    public void testSetNumeroIntents() {
        boolean retorn;
        ConfiguracioPartida cP;
        int nI1, nI2;

        nI1 = 12;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, nI1, 7, 8);
        nI2 = -1;
        retorn = cP.setNumeroIntents(nI2);
        System.out.println("Assignem el numero de intents: " + retorn + " Antic = " + nI1 + ", Nou = " + nI2);

        nI1 = 12;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, nI1, 7, 8);
        nI2 = 0;
        retorn = cP.setNumeroIntents(nI2);
        System.out.println("Assignem el numero de intents: " + retorn + " Antic = " + nI1 + ", Nou = " + nI2);

        nI1 = 12;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, nI1, 7, 8);
        nI2 = 1;
        retorn = cP.setNumeroIntents(nI2);
        System.out.println("Assignem el numero de intents: " + retorn + " Antic = " + nI1 + ", Nou = " + nI2);

        nI1 = 12;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, nI1, 7, 8);
        nI2 = 20;
        retorn = cP.setNumeroIntents(nI2);
        System.out.println("Assignem el numero de intents: " + retorn + " Antic = " + nI1 + ", Nou = " + nI2);

        nI1 = 12;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, nI1, 7, 8);
        nI2 = 21;
        retorn = cP.setNumeroIntents(nI2);
        System.out.println("Assignem el numero de intents: " + retorn + " Antic = " + nI1 + ", Nou = " + nI2);

        nI1 = 12;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, nI1, 7, 8);
        nI2 = 22;
        retorn = cP.setNumeroIntents(nI2);
        System.out.println("Assignem el numero de intents: " + retorn + " Antic = " + nI1 + ", Nou = " + nI2);
    }

    public void testSetNumeroColors() {
        boolean retorn;
        ConfiguracioPartida cP;
        int nC1, nC2;

        nC1 = 7;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, nC1, 8);
        nC2 = -1;
        retorn = cP.setNumeroColors(nC2);
        System.out.println("Assignem el numero de colors: " + retorn + " Antic = " + nC1 + ", Nou = " + nC2);

        nC1 = 7;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, nC1, 8);
        nC2 = 3;
        retorn = cP.setNumeroColors(nC2);
        System.out.println("Assignem el numero de colors: " + retorn + " Antic = " + nC1 + ", Nou = " + nC2);

        nC1 = 7;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, nC1, 8);
        nC2 = 4;
        retorn = cP.setNumeroColors(nC2);
        System.out.println("Assignem el numero de colors: " + retorn + " Antic = " + nC1 + ", Nou = " + nC2);

        nC1 = 7;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, nC1, 8);
        nC2 = 10;
        retorn = cP.setNumeroColors(nC2);
        System.out.println("Assignem el numero de colors: " + retorn + " Antic = " + nC1 + ", Nou = " + nC2);

        nC1 = 7;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, nC1, 8);
        nC2 = 11;
        retorn = cP.setNumeroColors(nC2);
        System.out.println("Assignem el numero de colors: " + retorn + " Antic = " + nC1 + ", Nou = " + nC2);

        nC1 = 7;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, nC1, 8);
        nC2 = 12;
        retorn = cP.setNumeroColors(nC2);
        System.out.println("Assignem el numero de colors: " + retorn + " Antic = " + nC1 + ", Nou = " + nC2);
    }

    public void testSetLongitudCombinacio() {
        boolean retorn;
        ConfiguracioPartida cP;
        int lC1, lC2;

        lC1 = 8;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, lC1);
        lC2 = -1;
        retorn = cP.setLongitudCombinacio(lC2);
        System.out.println("Assignem la longitud de la combinacio: " + retorn + " Antic = " + lC1 + ", Nou = " + lC2);

        lC1 = 8;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, lC1);
        lC2 = 3;
        retorn = cP.setLongitudCombinacio(lC2);
        System.out.println("Assignem la longitud de la combinacio: " + retorn + " Antic = " + lC1 + ", Nou = " + lC2);

        lC1 = 8;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, lC1);
        lC2 = 4;
        retorn = cP.setLongitudCombinacio(lC2);
        System.out.println("Assignem la longitud de la combinacio: " + retorn + " Antic = " + lC1 + ", Nou = " + lC2);

        lC1 = 8;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, lC1);
        lC2 = 10;
        retorn = cP.setLongitudCombinacio(lC2);
        System.out.println("Assignem la longitud de la combinacio: " + retorn + " Antic = " + lC1 + ", Nou = " + lC2);

        lC1 = 8;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, lC1);
        lC2 = 11;
        retorn = cP.setLongitudCombinacio(lC2);
        System.out.println("Assignem la longitud de la combinacio: " + retorn + " Antic = " + lC1 + ", Nou = " + lC2);

        lC1 = 8;
        cP = new ConfiguracioPartida(TipusPartida.CODEMAKER, 12, 7, lC1);
        lC2 = 12;
        retorn = cP.setLongitudCombinacio(lC2);
        System.out.println("Assignem la longitud de la combinacio: " + retorn + " Antic = " + lC1 + ", Nou = " + lC2);
    }

    public static void main (String [] args) throws Exception {
        DriverConfiguracioPartida testConfiguracioPartida = new DriverConfiguracioPartida();

        System.out.println("\ntestCreadora1");
        testConfiguracioPartida.testCreadora1();

        System.out.println("\ntestCreadora2");
        testConfiguracioPartida.testCreadora2();

        System.out.println("\ntestGetPartida");
        testConfiguracioPartida.testGetPartida();

        System.out.println("\ntestGetTipusPartida");
        testConfiguracioPartida.testGetTipusPartida();

        System.out.println("\ntestGetNumeroIntents");
        testConfiguracioPartida.testGetNumeroIntents();

        System.out.println("\ntestGetNumeroColors");
        testConfiguracioPartida.testGetNumeroColors();

        System.out.println("\ntestGetLongitudCombinacio");
        testConfiguracioPartida.testGetLongitudCombinacio();

        System.out.println("\ntestSetPartida");
        testConfiguracioPartida.testSetPartida();

        System.out.println("\ntestSetTipusPartida");
        testConfiguracioPartida.testSetTipusPartida();

        System.out.println("\ntestSetNumeroIntents");
        testConfiguracioPartida.testSetNumeroIntents();

        System.out.println("\ntestSetNumeroColors");
        testConfiguracioPartida.testSetNumeroColors();

        System.out.println("\ntestSetLongitudCombinacio");
        testConfiguracioPartida.testSetLongitudCombinacio();
    }
}
