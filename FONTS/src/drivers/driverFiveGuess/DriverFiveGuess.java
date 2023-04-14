package drivers.driverFiveGuess;

import java.util.*;
import java.lang.*;
import java.lang.Exception;
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

    public void testSolve() {
        try {
            FiveGuess fG = new FiveGuess(4);
            List<Integer> solucio = Arrays.asList(new Integer[]{3,2,6,4});

            System.out.print("Solucio: ");
            for (int i=0; i<solucio.size(); ++i) {
                System.out.print(solucio.get(i));
            }
            System.out.println();

            List<List<Integer>> solucions = fG.solve(solucio);

            for (int i=0; i<solucions.size(); ++i) {
                List<Integer> codi = solucions.get(i);
                System.out.println("Intent: " + (i+1) + "   Codi: " + codi.get(0) + codi.get(1) + codi.get(2) + codi.get(3));
            }
        }
        catch (Exception e) {
            System.out.println("Codi solucio de entrada incorrecte: " + e.getMessage());
        }


        try {
            FiveGuess fG = new FiveGuess(4);
            List<Integer> solucio = Arrays.asList(new Integer[]{3,2,6,4,2});

            System.out.print("Solucio: ");
            for (int i=0; i<solucio.size(); ++i) {
                System.out.print(solucio.get(i));
            }
            System.out.println();

            List<List<Integer>> solucions = fG.solve(solucio);

            for (int i=0; i<solucions.size(); ++i) {
                List<Integer> codi = solucions.get(i);
                System.out.println("Intent: " + (i+1) + "   Codi: " + codi.get(0) + codi.get(1) + codi.get(2) + codi.get(3));
            }
        }
        catch (Exception e) {
            System.out.println("Codi solucio de entrada incorrecte: " + e.getMessage());
        }


        try {
            FiveGuess fG = new FiveGuess(4);
            List<Integer> solucio = Arrays.asList(new Integer[]{3,2,8,4});

            System.out.print("Solucio: ");
            for (int i=0; i<solucio.size(); ++i) {
                System.out.print(solucio.get(i));
            }
            System.out.println();

            List<List<Integer>> solucions = fG.solve(solucio);

            for (int i=0; i<solucions.size(); ++i) {
                List<Integer> codi = solucions.get(i);
                System.out.println("Intent: " + (i+1) + "   Codi: " + codi.get(0) + codi.get(1) + codi.get(2) + codi.get(3));
            }
        }
        catch (Exception e) {
            System.out.println("Codi solucio de entrada incorrecte: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        DriverFiveGuess testFiveGuess = new DriverFiveGuess();

        System.out.println("\ntestCreadora");
        testFiveGuess.testCreadora();

        System.out.println("\ntestEsbrina");
        testFiveGuess.testEsbrina();

        System.out.println("\ntestSolve");
        testFiveGuess.testSolve();

    }
}