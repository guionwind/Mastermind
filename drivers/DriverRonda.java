package drivers;
import domini.classes.Ronda;
import java.util.Random;

public class DriverRonda {
    public void testConstructora(){
        Random randInt = new Random();
        int myRandomId = randInt.nextInt(20);
        Integer[] combinacioIntentada = {1,2,3,4};
        Ronda test = new Ronda(myRandomId, combinacioIntentada);
        System.out.println("testConstructora: Creada una ronda test amb ID random: "+myRandomId+" / CombinacioIntentada: 1234");
    }
    public void testGetId(){
        Integer[] combinacioIntentada = {1,2,3,4};
        Ronda test = new Ronda(6, combinacioIntentada);
        int id = test.getId();
        System.out.println("testGetId: Agafem Id: "+id);
    }
    public void testGetCombinacioIntentada(){
        Integer[] combinacioIntentada = {1,2,3,4};
        Ronda test = new Ronda(6, combinacioIntentada);
        Integer[] combinacioIntentada_1 = test.getCombinacioIntentada();
        System.out.print("testGetCombinacioIntentada: Agafem Combinacio Intentada: ");
        for (int i = 0; i < combinacioIntentada_1.length; i++) {
            System.out.print(combinacioIntentada_1[i]);
        }
        System.out.println();
    }
    public static void main (String [] args){
        DriverRonda test = new DriverRonda();
        test.testConstructora();
        test.testGetId();
        test.testGetCombinacioIntentada();
    }
}
