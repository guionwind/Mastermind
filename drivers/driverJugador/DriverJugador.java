package drivers;

import domini.classes.Jugador;

import java.io.*;

import static org.junit.Assert.*;
import org.junit.Test;


public class DriverJugador {
    
    private static Jugador jug;

    /**
     * setup d'un jugador generic
     */

    @Before
    public void setup() {
        System.out.println("setup amb constructora");
        jug = new Jugador("generic", "111");
        assertEquals("generic", jug.getUsername());
        assertEquals("111", jug.getPassword());
        assertEquals(1, jug.getID());

    }

    
    /**
     * Test del mètode Jugador(name, pwd) de la classe Jugador
     * Estratègia caixa gris
     */

    @Test
    public void testConstructora () {
        System.out.println("testConstructora");
        Jugador jugador1 = new Jugador("Jose", "Sheng");
        assertEquals("Jose", jugador1.getUsername());
        assertEquals("Sheng", jugador1.getPassword());
        assertEquals(2, jugador1.getID());

        Jugador jugador2 = new Jugador("Marc", "cordinador");
        assertEquals("Marc", jugador2.getUsername());
        assertEquals("cordinador", jugador2.getPassword());
        assertEquals(3, jugador2.getID());
        System.out.println("testConstructora exitos");
    }

    //* necessitare un stub aqui??
    // vull 2 partides jugades i 1 guanyada

    @Test
    public void testPartidesGuanyades() {
        System.out.println("testPartidesGuanyades");
        assertEquals(1, jug.PartidesGuanyades());
        System.out.println("testPartidesGuanyades exitos");
    }

    @Test
    public void testPartidesJugades() {
        System.out.println("testPartidesJugades");
        assertEquals(2, jug.PartidesJugades());
        System.out.println("testPartidesJugades exitos");
    }

    //necessito un stub pero no se com ferho
    @Test
    public void testSetEstadistica() {
        System.out.println("testPartidesJugades");
        jug.setEstadistica(null);
        System.out.println("testPartidesJugades exitos");
    }

    //la resta de getters es fan en comprovar la creadora
    /*
    public int testGetID() {
        return this.id;
    }

    public String testGetUsername() {
        return this.username;
    }

    public String testGetPassword() {
        return this.password;
    }
    */
}