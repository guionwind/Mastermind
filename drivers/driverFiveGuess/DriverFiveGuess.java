package drivers.driverFiveGuess;

import java.util.*;
import java.lang.*;
import drivers.driverFiveGuess.FiveGuess;

public class DriverFiveGuess {
    public void testCreadora() {
        FiveGuess fG = new FiveGuess(4);
        System.out.println("Instància amb creadora creada");
    }

    public void testEsbrina() {
        FiveGuess fG = new FiveGuess(4);
        Integer[] codiSolucio = new Integer[] {6,4,3,5};
        Integer[] ultimCodi = null;
        String resposta = null;
        Integer[] guess;

        guess = fG.esbrina(ultimCodi, resposta);
        System.out.println("Fem una crida al algoritme per esbrinar el codi. Intent 1: ");
        System.out.println("Rresposta = " + resposta + ", guess = " + guess[0]+guess[1]+guess[2]+guess[3] + ", solucio = " + codiSolucio[0]+codiSolucio[1]+codiSolucio[2]+codiSolucio[3]);
        if (Arrays.equals(guess, codiSolucio)) {
            System.out.println("Codi esbrinat");
            return;
        }

        ultimCodi = guess;
        resposta = fG.generaResposta(guess, codiSolucio);
        guess = fG.esbrina(ultimCodi, resposta);
        System.out.println("Fem una crida al algoritme per esbrinar el codi. Intent 2: ");
        System.out.println("ultimCodi = " + ultimCodi[0]+ultimCodi[1]+ultimCodi[2]+ultimCodi[3] + ", resposta = " + resposta + ", guess = " + guess[0]+guess[1]+guess[2]+guess[3] + ", solucio = " + codiSolucio[0]+codiSolucio[1]+codiSolucio[2]+codiSolucio[3]);
        if (Arrays.equals(guess, codiSolucio)) {
            System.out.println("Codi esbrinat");
            return;
        }

        ultimCodi = guess;
        resposta = fG.generaResposta(guess, codiSolucio);
        guess = fG.esbrina(ultimCodi, resposta);
        System.out.println("Fem una crida al algoritme per esbrinar el codi. Intent 3: ");
        System.out.println("ultimCodi = " + ultimCodi[0]+ultimCodi[1]+ultimCodi[2]+ultimCodi[3] + ", resposta = " + resposta + ", guess = " + guess[0]+guess[1]+guess[2]+guess[3] + ", solucio = " + codiSolucio[0]+codiSolucio[1]+codiSolucio[2]+codiSolucio[3]);
        if (Arrays.equals(guess, codiSolucio)) {
            System.out.println("Codi esbrinat");
            return;
        }

        ultimCodi = guess;
        resposta = fG.generaResposta(guess, codiSolucio);
        guess = fG.esbrina(ultimCodi, resposta);
        System.out.println("Fem una crida al algoritme per esbrinar el codi. Intent 4: ");
        System.out.println("ultimCodi = " + ultimCodi[0]+ultimCodi[1]+ultimCodi[2]+ultimCodi[3] + ", resposta = " + resposta + ", guess = " + guess[0]+guess[1]+guess[2]+guess[3] + ", solucio = " + codiSolucio[0]+codiSolucio[1]+codiSolucio[2]+codiSolucio[3]);
        if (Arrays.equals(guess, codiSolucio)) {
            System.out.println("Codi esbrinat");
            return;
        }

        ultimCodi = guess;
        resposta = fG.generaResposta(guess, codiSolucio);
        guess = fG.esbrina(ultimCodi, resposta);
        System.out.println("Fem una crida al algoritme per esbrinar el codi. Intent 5: ");
        System.out.println("ultimCodi = " + ultimCodi[0]+ultimCodi[1]+ultimCodi[2]+ultimCodi[3] + ", resposta = " + resposta + ", guess = " + guess[0]+guess[1]+guess[2]+guess[3] + ", solucio = " + codiSolucio[0]+codiSolucio[1]+codiSolucio[2]+codiSolucio[3]);
        if (Arrays.equals(guess, codiSolucio)) {
            System.out.println("Codi esbrinat");
            return;
        }

        ultimCodi = guess;
        resposta = fG.generaResposta(guess, codiSolucio);
        guess = fG.esbrina(ultimCodi, resposta);
        System.out.println("Fem una crida al algoritme per esbrinar el codi. Intent 6: ");
        System.out.println("ultimCodi = " + ultimCodi[0]+ultimCodi[1]+ultimCodi[2]+ultimCodi[3] + ", resposta = " + resposta + ", guess = " + guess[0]+guess[1]+guess[2]+guess[3] + ", solucio = " + codiSolucio[0]+codiSolucio[1]+codiSolucio[2]+codiSolucio[3]);
        if (Arrays.equals(guess, codiSolucio)) {
            System.out.println("Codi esbrinat");
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        DriverFiveGuess testFiveGuess = new DriverFiveGuess();

        System.out.println("\ntestCreadora");
        testFiveGuess.testCreadora();

        System.out.println("\ntestEsbrina");
        testFiveGuess.testEsbrina();
    }
}