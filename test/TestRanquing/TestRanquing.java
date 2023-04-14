/**
 * Aquesta classe representa un Rànquing.
 * Cada rànquing té un identificador únic i un conjunt d'estadistiques partida.
 */
package test.TestRanquing;

import domini.classes.exceptions.*;
import domini.classes.Ranquing;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class TestRanquing {

    /**
     * Testea la constructora y el getter getId()
     */
    @Test
    public void testConstructora() {
        System.out.println("testConstructora");
        Ranquing r1 = new Ranquing();
        assertEquals(0, r1.getId());

        Ranquing r2 = new Ranquing();
        assertEquals(1, r2.getId());
        System.out.println("testConstructora exitos");
    }

    /**
     * Testea addEstadistica() y getTopN(int)
     */
    @Test
    public void testGetTopN() {
        System.out.println("testEstadistiques");
        Ranquing r1 = new Ranquing();
        Integer[] est1 = {1,2};
        r1.addEstadistica(est1);
        Integer[] est2 = {1,3};
        r1.addEstadistica(est2);
        Integer[] est3 = {2,4};
        r1.addEstadistica(est3);
        System.out.println("testEstadistiques exitos");

        System.out.println("testGetTopN");
        
        //n > size
        ArrayList<Integer[]> res1 = new ArrayList<Integer[]>();
        res1.add(est3);
        res1.add(est2);
        res1.add(est1);
        assertEquals(res1, r1.getTopN(777));
        System.out.println("testGetTopN n>size exitos");

        //n = size
        assertEquals(res1, r1.getTopN(3));
        System.out.println("testGetTopN n=size exitos");

        //n < size
        ArrayList<Integer[]> res2 = new ArrayList<Integer[]>();
        res2.add(est3);
        res2.add(est2);
        assertEquals(res2, r1.getTopN(2));
        System.out.println("testGetTopN n<size exitos");

        //n = 0
        ArrayList<Integer[]> res3 = new ArrayList<Integer[]>();
        assertEquals(res3, r1.getTopN(0));
        System.out.println("testGetTopN n=0 exitos");
    }
}