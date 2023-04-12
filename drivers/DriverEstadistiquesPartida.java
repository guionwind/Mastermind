package drivers;

import domini.classes.*;

public class DriverEstadistiquesPartida {
    public void testConstructora(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        System.out.println("testConstructora: Creat una estadistica test");
    }
    public void testGetJugador(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        Jugador jugadorTest = test.getJugador();
        System.out.println("testGetId: Agafem jugador creat Juan: "+jugadorTest.getUsername());
    }
    /*public void testGetPartida(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null);
        Jugador jugadorTest = test.getPartida();
        System.out.println("testGetId: Agafem jugador creat Juan: "+jugadorTest.getUsername());
    }*/
    public void testGetPuntuacio(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        int puntuacio = test.getPuntuacio();
        System.out.println("testGetId: Agafem puntuacio: "+puntuacio);
    }
    public void testSetPuntuacio(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        test.setPuntuacio(3000);
        System.out.println("testGetId: Modifiquem puntuacio de 3500 a 3000, Puntuacio: "+test.getPuntuacio());
    }
    public void testGetTemps(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        int temps = test.getTemps();
        System.out.println("testGetId: Agafem temps: "+temps);
    }
    public void testSetTemps(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        test.setTemps(700);
        System.out.println("testGetId: Modifiquem temps de 500 a 700, Temps: "+test.getTemps());
    }
    public void testGetGuanyada(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        Boolean guanyada = test.getGuanyada();
        System.out.println("testGetId: Mirem si la partida ha estat guanyada: "+guanyada.toString());
    }
    public void testSetGuanyada(){
        Jugador jugador = new Jugador("Juan", "1234");
        //CodeBreaker partida = new CodeBreaker(0, null, null, null); //TODO no han fet encara les constructores
        EstadistiquesPartida test = new EstadistiquesPartida(jugador, null, 3500, 500, true);
        test.setGuanyada(false);;
        System.out.println("testGetId: Modifiquem l'estat de la partida de guanyada a no guanyada, Guanyada: "+test.getGuanyada());
    }
    public static void main (String [] args){
        DriverEstadistiquesPartida test = new DriverEstadistiquesPartida();
        test.testConstructora();
        test.testGetJugador();
        //test.testGetPartida();
        test.testGetPuntuacio();
        test.testSetPuntuacio();
        test.testGetTemps();
        test.testSetTemps();
        test.testGetGuanyada();
        test.testSetGuanyada();
    }
}
