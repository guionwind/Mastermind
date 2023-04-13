package drivers;
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
    public void testAddEstadistica(){
        Ranquing test = new Ranquing();
        Integer[] estadistica = {1, 2300};
        test.addEstadistica(estadistica);
        System.out.println("testAdd: Afegim estadistica amb jugador Pepe, 2300 de puntuacio, partida nula i temps 300");
    }
    public void testGetTopN(){
        Ranquing test = new Ranquing();
        Integer[] estadistica = {0, 5000};
        test.addEstadistica(estadistica);
        Integer[] estadistica_2 = {1, 1000};
        test.addEstadistica(estadistica_2);
        Integer[] estadistica_3 = {2, 2300};
        test.addEstadistica(estadistica_3);
        System.out.println("testGetTopN: Dels 3 Jugadors (Pepe: 5000, Maria: 1000, Antonio: 2300) llistem el TOP 2: ");
        ArrayList<Integer[]> top_2 = test.getTopN(2);
        for (int i = 0; i < top_2.size(); i++){
            System.out.println((i+1)+": "+ top_2.get(i)[0] + " ("+ top_2.get(i)[1]+" punts)");
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