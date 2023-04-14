package test.TestJugador;

import domini.classes.exceptions.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestJugador {
    /**
     * Test del mètode Jugador(name, pwd) de la classe Jugador
     * Estratègia caixa gris
     * Tambe comprova getters
     * @throws JugadorInvalid
     * @throws JugadorJaExisteix
     */

    @Test
    public void testConstructora () throws JugadorJaExisteix, JugadorInvalid {
        System.out.println("testConstructora");
        Jugador jugador1 = new Jugador("Jose", "Sheng");
        assertEquals("Jose", jugador1.getUsername());
        assertEquals("Sheng", jugador1.getPassword());
        assertEquals(1, jugador1.getID());

        Jugador jugador2 = new Jugador("Marc", "cordinador");
        assertEquals("Marc", jugador2.getUsername());
        assertEquals("cordinador", jugador2.getPassword());
        assertEquals(2, jugador2.getID());
        System.out.println("testConstructora exitos");
    }

    @Test
    //es comprova que salta l'excepcio "JugadorJaExisteix" de la creadora
    public void testConstructoraInvalid () throws JugadorJaExisteix, JugadorInvalid {
        System.out.println("testConstructora (exception JugadorInvalid)");
        Jugador jugador1 = new Jugador("", "Sheng");
    }

    @Test
    //es comprova que salta l'excepcio "JugadorJaExisteix" de la creadora
    public void testConstructoraJaExisteix () throws JugadorJaExisteix, JugadorInvalid {
        System.out.println("testConstructora (exception JugadorJaExisteix)");
        Jugador jugador1 = new Jugador("Jose", "Sheng");
        Jugador jugador2 = new Jugador("Jose", "cordinador");
    }


    @Test
    public void testSetEstadistica() throws JugadorJaExisteix, JugadorInvalid {
        System.out.println("testPartidesJugades");
        Jugador jug = new Jugador("generic", "111");
        jug.setEstadistica(new EstadistiquesPartida(false));
        System.out.println("testPartidesJugades exitos");
    }

    @Test
    public void testPartidesGuanyades() throws JugadorJaExisteix, JugadorInvalid {
        System.out.println("testPartidesGuanyades");
        Jugador jug = new Jugador("generic", "111");

        jug.setEstadistica(new EstadistiquesPartida(false));
        jug.setEstadistica(new EstadistiquesPartida(true));
        jug.setEstadistica(new EstadistiquesPartida(true));
        jug.setEstadistica(null);

        assertEquals(2, jug.PartidesGuanyades());
        System.out.println("testPartidesGuanyades exitos");
    }


    @Test
    public void testPartidesJugades() throws JugadorJaExisteix, JugadorInvalid {
        System.out.println("testPartidesJugades");

        Jugador jug = new Jugador("generic", "111");

        jug.setEstadistica(new EstadistiquesPartida(false));
        jug.setEstadistica(new EstadistiquesPartida(true));
        jug.setEstadistica(new EstadistiquesPartida(true));
        jug.setEstadistica(null);

        assertEquals(3, jug.PartidesJugades());
        System.out.println("testPartidesJugades exitos");
    }


    //la resta de getters es testejen en comprovar la creadora
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