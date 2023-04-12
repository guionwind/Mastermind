package domini.drivers;
import domini.classes.Ronda;
import java.util.Random;

public class DriverRonda {
    public void testConstructora(){
        Random randInt = new Random();
        int myRandomId = randInt.nextInt(20);
        Ronda test = new Ronda(myRandomId, "1234");
        System.out.println("testConstructora: Creada una ronda test amb ID random: "+myRandomId+" / CombinacioIntentada: 1234");
    }
    public void testGetId(){
        Ronda test = new Ronda(6, "2341");
        int id = test.getId();
        System.out.println("testGetId: Agafem Id: "+id);
    }
    public void testGetCombinacioIntentada(){
        Ronda test = new Ronda(6, "2341");
        String combinacioIntentada = test.getCombinacioIntentada();
        System.out.println("testGetCombinacioIntentada: Agafem Combinacio Intentada: "+combinacioIntentada);
    }
    public static void main (String [] args){
        DriverRonda test = new DriverRonda();
        test.testConstructora();
        test.testGetId();
        test.testGetCombinacioIntentada();
    }
}
