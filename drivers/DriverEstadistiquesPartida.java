package drivers;

import domini.classes.EstadistiquesPartida;

public class DriverEstadistiquesPartida {
    public void testConstructora(){
        EstadistiquesPartida test = new EstadistiquesPartida(0, 0, 3500, true);
        System.out.println("testConstructora: Creat una estadistica test");
    }
    public void testGetIdJugador(){
        EstadistiquesPartida test = new EstadistiquesPartida(0, 0, 3500, true);
        Integer jugadorTest = test.getIdJugador();
        System.out.println("testGetId: Agafem jugador creat amb id 0: " + jugadorTest);
    }
    public void testGetIdPartida(){
        EstadistiquesPartida test = new EstadistiquesPartida(0, 0, 3500, true);
        Integer partidaTest = test.getIdPartida();
        System.out.println("testGetId: Agafem partida creada amb id 0: " + partidaTest);
    }
    public void testGetPuntuacio(){
        EstadistiquesPartida test = new EstadistiquesPartida(0, 0, 3500, true);
        int puntuacio = test.getPuntuacio();
        System.out.println("testGetId: Agafem puntuacio: " + puntuacio);
    }
    public void testSetPuntuacio(){
        EstadistiquesPartida test = new EstadistiquesPartida(0, 0, 3500, true);
        test.setPuntuacio(3000);
        System.out.println("testGetId: Modifiquem puntuacio de 3500 a 3000, Puntuacio: " + test.getPuntuacio());
    }
    public void testGetGuanyada(){
        EstadistiquesPartida test = new EstadistiquesPartida(0, 0, 3500, true);
        Boolean guanyada = test.getGuanyada();
        System.out.println("testGetId: Mirem si la partida ha estat guanyada: " + guanyada.toString());
    }
    public void testSetGuanyada(){
        EstadistiquesPartida test = new EstadistiquesPartida(0, 0, 3500, true);
        test.setGuanyada(false);;
        System.out.println("testGetId: Modifiquem l'estat de la partida de guanyada a no guanyada, Guanyada: " + test.getGuanyada());
    }
    public static void main (String [] args){
        DriverEstadistiquesPartida test = new DriverEstadistiquesPartida();
        test.testConstructora();
        test.testGetIdJugador();
        test.testGetIdPartida();
        test.testGetPuntuacio();
        test.testSetPuntuacio();
        test.testGetGuanyada();
        test.testSetGuanyada();
    }
}
