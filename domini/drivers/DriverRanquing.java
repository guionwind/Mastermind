package domini.drivers;
import domini.classes.*;

import java.util.ArrayList;

public class DriverRanquing {
    public void testConstructora(){
        Ranquing test = new Ranquing();
        Ranquing test2 = new Ranquing();
        System.out.println("testConstructora: Creat una ranquing test amb Id: "+test.getId()+" i un altre amb Id: "+test2.getId());
    }
    public void testGetId(){
        Ranquing test = new Ranquing();
        int id = test.getId();
        System.out.println("testGetId: Agafem Id: "+id);
    }
    public void testAdd(){
        Ranquing test = new Ranquing();
        Jugador jugador = new Jugador("Pepe", "1234");
        EstadistiquesPartida estadistica = new EstadistiquesPartida(jugador, null, 2300, 300, true);
        test.add(estadistica);
        System.out.println("testAdd: Afegim estadistica amb jugador Pepe, 2300 de puntuacio, partida nula i temps 300");
    }
    public void testGetTopN(){
        Ranquing test = new Ranquing();
        Jugador jugador = new Jugador("Pepe", "1234");
        EstadistiquesPartida estadistica = new EstadistiquesPartida(jugador, null, 5000, 300, true);
        test.add(estadistica);
        Jugador jugador_2 = new Jugador("Maria", "1234");
        EstadistiquesPartida estadistica_2 = new EstadistiquesPartida(jugador_2, null, 1000, 500, true);
        test.add(estadistica_2);
        Jugador jugador_3 = new Jugador("Antonio", "1234");
        EstadistiquesPartida estadistica_3 = new EstadistiquesPartida(jugador_3, null, 2300, 400, true);
        test.add(estadistica_3);
        System.out.println("testGetTopN: Dels 3 Jugadors (Pepe: 5000, Maria: 1000, Antonio: 2300) llistem el TOP 2: ");
        ArrayList<EstadistiquesPartida> top_2 = test.getTopN(2);
        for (int i = 0; i < top_2.size(); i++){
            System.out.println((i+1)+": "+ top_2.get(i).getJugador().getUsername() + "("+ top_2.get(i).getPuntuacio()+" punts)");
        }
    }
    public static void main (String [] args){
        DriverRanquing test = new DriverRanquing();
        test.testConstructora();
        test.testGetId();
        test.testAdd();
        test.testGetTopN();
    }
}