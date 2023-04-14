package drivers;
import domini.classes.Ronda;
import java.util.Random;

public class DriverRonda {
    public void testConstructora(){
        Random randInt = new Random();
        int myRandomId = randInt.nextInt(20);
        Integer[] combinacioIntentada = {1,2,3,4};
        Integer[] combinacioCorrecta = {2,2,3,4};
        Ronda test = new Ronda(myRandomId, combinacioIntentada, combinacioCorrecta);
        System.out.println("testConstructora: Creada una ronda test amb ID random: "+myRandomId+" / CombinacioIntentada: 1234 / CombinacioCorrecta: 2234");
    }
    public void testGetId(){
        Integer[] combinacioIntentada = {1,2,3,4};
        Integer[] combinacioCorrecta = {2,2,3,4};
        Ronda test = new Ronda(6, combinacioIntentada, combinacioCorrecta);
        int id = test.getId();
        System.out.println("testGetId: Agafem Id: "+id);
    }
    public void testGetCombinacioIntentada(){
        Integer[] combinacioIntentada = {1,2,3,4};
        Integer[] combinacioCorrecta = {2,2,3,4};
        Ronda test = new Ronda(6, combinacioIntentada, combinacioCorrecta);
        Integer[] combinacioIntentada_1 = test.getCombinacioIntentada();
        System.out.print("testGetCombinacioIntentada: Agafem Combinacio Intentada: ");
        for (int i = 0; i < combinacioIntentada_1.length; i++) {
            System.out.print(combinacioIntentada_1[i]);
        }
        System.out.println();
    }
    public void testGetCombinacioCorrecta(){
        Integer[] combinacioIntentada = {1,2,3,4};
        Integer[] combinacioCorrecta = {2,2,3,4};
        Ronda test = new Ronda(6, combinacioIntentada, combinacioCorrecta);
        Integer[] combinacioCorrecta_1 = test.getCombinacioCorrecta();
        System.out.print("testGetCombinacioCorrecta: Agafem Combinacio Correcta: ");
        for (int i = 0; i < combinacioCorrecta_1.length; i++) {
            System.out.print(combinacioCorrecta_1[i]);
        }
        System.out.println();
    }
    public void testGetResposta(){
        Integer[] combinacioIntentada = {1,2,3,4};
        Integer[] combinacioCorrecta = {2,2,3,4};
        Ronda test = new Ronda(6, combinacioIntentada, combinacioCorrecta);
        String correccio = test.getResposta(combinacioIntentada, combinacioCorrecta);
        System.out.println("testGetResposta: Calculem resposta: "+correccio);
    }

    public static void main (String [] args){
        DriverRonda test = new DriverRonda();
        test.testConstructora();
        test.testGetId();
        test.testGetCombinacioIntentada();
        test.testGetCombinacioCorrecta();
        test.testGetResposta();
    }
}
