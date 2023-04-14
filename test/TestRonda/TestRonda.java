/**
 * Aquesta classe representa una ronda.
 * Cada ronda té un identificador únic i una combinació intentada.
 */
package test.TestRonda;

import domini.classes.exceptions.*;
import domini.classes.Ronda;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestRonda {
    /**
     * Test del mètode Ronda(name, pwd) de la classe Ronda
     * Estratègia caixa gris
     * Tambe comprova getters
     */
    @Test
    public void testConstructora() {
        System.out.println("testConstructora");
        Ronda r1 = new Ronda(1);
        assertEquals(1, r1.getId());
        assertNull(r1.getCombinacioIntentada());
        assertNull(r1.getResposta());

        System.out.println("testConstructora exitos");
    }
    
    @Test
    public void testSetCombinacioIntentada() {
        Ronda r1 = new Ronda(1);
        Integer[] array1 = {1,2,3};
        r1.setCombinacioIntentada(array1);
        assertArrayEquals(array1, r1.getCombinacioIntentada());
        Integer[] array2 = {2,1,3};
        r1.setCombinacioIntentada(array2);
        assertArrayEquals(array2, r1.getCombinacioIntentada());
        Integer[] array3 = {};

        r1.setCombinacioIntentada(array3);
        assertArrayEquals(array3, r1.getCombinacioIntentada());

        r1.setCombinacioIntentada(null);
        assertArrayEquals(null, r1.getCombinacioIntentada());
    }

    @Test
    public void testSetResposta() {
        Ronda r1 = new Ronda(1);
        String res1 = "BBW-";
        r1.setResposta(res1);
        assertEquals(res1, r1.getResposta());
        String res2 = "----";
        r1.setResposta(res2);
        assertEquals(res2, r1.getResposta());

        String res3 = "";
        r1.setResposta(res3);
        assertEquals("", r1.getResposta());

        r1.setResposta(null);
        assertEquals(null, r1.getResposta());
    }
}