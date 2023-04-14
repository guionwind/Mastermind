package test.TestConfiguracioPartida;

import domini.classes.ConfiguracioPartida;
import domini.classes.ConfiguracioPartida.TipusPartida;
import domini.classes.exceptions.*;


import static org.junit.Assert.*;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;

import org.junit.Test;


public class TestConfiguracioPartida {

    /**
     * Test de les constructores de ConfiguracioPartida
     * Estratègia caixa gris
     * Tambe comprova getters
     */

    @Test
    public void testConstructora() {
        System.out.println("testConstructora()");
        Integer i = 0;  //necessari per als assert
        ConfiguracioPartida cp1 = new ConfiguracioPartida();
        assertEquals(null, cp1.getPartida());
        assertEquals(null, cp1.getTipusPartida());
        assertEquals(i, cp1.getNumeroIntents());
        assertEquals(i, cp1.getNumeroColors());
        assertEquals(i, cp1.getLongitudCombinacio());
        System.out.println("testConstructora() pass");
    }

    @Test
    public void testConstructoraParams()  throws LongitudCombinacioIncorrecte, NumeroIntentsIncorrecte,NumeroColorsIncorrecte {
        System.out.println("testConstructora CODEBREAKER amb tots els params");
        Integer i1 = 2, i2 = 5, i3 = 5;
        ConfiguracioPartida cp2 = new ConfiguracioPartida(TipusPartida.CODEBREAKER, i1, i2, i3);
        assertEquals(null, cp2.getPartida());
        assertEquals(TipusPartida.CODEBREAKER, cp2.getTipusPartida());
        assertEquals(i1, cp2.getNumeroIntents());
        assertEquals(i2, cp2.getNumeroColors());
        assertEquals(i3, cp2.getLongitudCombinacio());
        System.out.println("testConstructora CODEBREAKER amb tots els params pass");
    }



    @Test
    public void testSetPartida() throws LongitudCombinacioIncorrecte, NumeroIntentsIncorrecte,NumeroColorsIncorrecte  {
        System.out.println("test setPartida");

        ConfiguracioPartida cp2 = new ConfiguracioPartida(TipusPartida.CODEBREAKER, 5, 5, 5);
        Integer i = 10;
        cp2.setPartida(i);
        assertEquals(i, cp2.getPartida());
        System.out.println("test setPartida pass");
    }

    @Test
    public void testSetTipusPartida()  throws LongitudCombinacioIncorrecte, NumeroIntentsIncorrecte,NumeroColorsIncorrecte  {
        System.out.println("test setTipusPartida");

        ConfiguracioPartida cp2 = new ConfiguracioPartida(TipusPartida.CODEBREAKER, 5, 5, 5);
        
        boolean b = cp2.setTipusPartida(TipusPartida.CODEMAKER);
        assertEquals(true, b);
        assertEquals(TipusPartida.CODEMAKER, cp2.getTipusPartida());
        System.out.println("test setTipusPartida pass");
    }

    @Test
    public void testSetNumeroIntents()  throws LongitudCombinacioIncorrecte, NumeroIntentsIncorrecte,NumeroColorsIncorrecte  {
        System.out.println("test setNumeroIntents");

        ConfiguracioPartida cp2 = new ConfiguracioPartida(TipusPartida.CODEBREAKER, 5, 5, 5);
        
        Integer i = 6;
        boolean b = cp2.setNumeroIntents(i);
        assertEquals(true, b);
        assertEquals(i, cp2.getNumeroIntents());
        System.out.println("test setNumeroIntents pass");
    }

    @Test
    public void testSetNumeroColors()  throws LongitudCombinacioIncorrecte, NumeroIntentsIncorrecte,NumeroColorsIncorrecte {
        System.out.println("test setNumeroIntents");

        ConfiguracioPartida cp2 = new ConfiguracioPartida(TipusPartida.CODEBREAKER, 5, 5, 5);
        
        Integer i = 6;
        boolean b = cp2.setNumeroColors(i);
        assertEquals(true, b);

        assertEquals(i, cp2.getNumeroColors());
        System.out.println("test setNumeroIntents pass");
    }

    @Test
    public void testSetLongitudCombinacio() throws LongitudCombinacioIncorrecte, NumeroIntentsIncorrecte,NumeroColorsIncorrecte {
        System.out.println("test setNumeroIntents");

        ConfiguracioPartida cp2 = new ConfiguracioPartida(TipusPartida.CODEBREAKER, 5, 5, 5);
        
        Integer i = 6;
        boolean b = cp2.setLongitudCombinacio(i);
        assertEquals(true, b);

        assertEquals(i, cp2.getLongitudCombinacio());
        System.out.println("test setNumeroIntents pass");
    }
}
