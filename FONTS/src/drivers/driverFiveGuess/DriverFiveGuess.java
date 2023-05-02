package drivers.driverFiveGuess;

import java.util.*;
import java.lang.Exception;

import drivers.driverFiveGuess.FiveGuess;
import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;

public class DriverFiveGuess {
    public void testCreadora() {
        FiveGuess fG = new FiveGuess(4);
        System.out.println("Instància amb creadora creada");
    }

    public void testGetId() {
        FiveGuess fG = new FiveGuess(5);
        Integer idPartida = fG.getId();
        System.out.println("Identificador de la partida a la que pertany = " + idPartida);
    }

    public void testEsbrina() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        FiveGuess fG = new FiveGuess(4);
        Integer[] codiSolucio = new Integer[] {6,4,3,5};
        Integer[] ultimCodi = null;
        String resposta = null;
        Integer[] guess = null;
        

        // Esbrina amb format longitud combinacion incorrecte, inferior
        try {
            fG.esbrina(new Integer[] {1,2,3}, new String("W-BW"));
        }
        catch (LongitudCombinacioIncorrecte e) {
            System.out.println(e.getMessage());
        }
        
        // Esbrina amb format longitud combinacion incorrecte, superior
        try {
            fG.esbrina(new Integer[] {1,2,3,4,5}, new String("W-BW"));
        }
        catch (LongitudCombinacioIncorrecte e) {
            System.out.println(e.getMessage());
        }

        // Esbrina amb format numero colors incorrecte
        try {
            fG.esbrina(new Integer[] {1,2,3,7}, new String("W-BW"));
        }
        catch (NumeroColorsIncorrecte e) {
            System.out.println(e.getMessage());
        }

        // Esbrina amb format longitud resposta incorrecte, inferior
        try {
            fG.esbrina(new Integer[] {1,2,3,4}, new String("W-B"));
        }
        catch (LongitudRespostaIncorrecte e) {
            System.out.println(e.getMessage());
        }

        // Esbrina amb format longitud resposta incorrecte, superior
        try {
            fG.esbrina(new Integer[] {1,2,3,4}, new String("W-BW-"));
        }
        catch (LongitudRespostaIncorrecte e) {
            System.out.println(e.getMessage());
        }

        // Esbrina amb format valors resposta incorrectes
        try {
            fG.esbrina(new Integer[] {1,2,3,4}, new String("W-DW"));  
        }
        catch (ValorsRespostaIncorrectes e) {
            System.out.println(e.getMessage());
        }


        // Esbrina amb formats correctes
        for (int i=0; i<5; ++i) {
            System.out.println("Fem una crida al algoritme per esbrinar el codi. Intent " + i + ":");
            
            try {
                guess = fG.esbrina(ultimCodi, resposta);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
                System.out.println(e.toString());
            }

            System.out.println("Resposta = " + resposta + ", guess = " + guess[0]+guess[1]+guess[2]+guess[3] + ", solucio = " + codiSolucio[0]+codiSolucio[1]+codiSolucio[2]+codiSolucio[3]);
            
            if (Arrays.equals(guess, codiSolucio)) {
                System.out.println("Codi esbrinat");
                return;
            }

            ultimCodi = guess;
            resposta = fG.generaResposta(guess, codiSolucio);
        }
    }

    public void testSolve() {
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
    }


    public void testSolveTots() {
        FiveGuess fG;
        List<Integer> solucio;

        for (char i=1; i<=6; ++i) {
            for (int j=1; j<=6; ++j) {
                for (int k=1; k<=6; ++k) {
                    for (int l=1; l<=6; ++l) {
                        solucio = Arrays.asList(new Integer[] {
                            Integer.valueOf(i),
                            Integer.valueOf(j),
                            Integer.valueOf(k),
                            Integer.valueOf(l)
                        });

                        fG = new FiveGuess(4);

                        System.out.print("Solucio = ");
                        for (int m=0; m<solucio.size(); ++m) {
                            System.out.print(solucio.get(m));
                        }

                        try {
                            List<List<Integer>> solucions = fG.solve(solucio);

                            for (int m=0; m<solucions.size(); ++m) {
                                List<Integer> codi = solucions.get(m);
                                System.out.print(" | Intent " + (m+1) + " : Codi = " + codi.get(0) + codi.get(1) + codi.get(2) + codi.get(3));
                            }
                            System.out.println();
                        }
                        catch (LongitudCombinacioIncorrecte e) {
                            System.out.println(" Codi solucio de entrada incorrecte: " + e.getMessage() + " " + e.toString());
                        }
                        catch (NumeroColorsIncorrecte e) {
                            System.out.println(" Codi solucio de entrada incorrecte: " + e.getMessage() + " " + e.toString());
                        }
                        catch (Exception e) {
                            System.out.println(" Codi solucio de entrada incorrecte: " + e.getMessage() + " " + e.toString() + " " + e.getCause());
                        }
                    }
                }
            }
        }
     }

    public static void main(String[] args) throws Exception {
        DriverFiveGuess testFiveGuess = new DriverFiveGuess();

        // System.out.println("\ntestCreadora");
        // testFiveGuess.testCreadora();

        // System.out.println("\ntestEsbrina");
        // testFiveGuess.testEsbrina();

        // System.out.println("\ntestSolve");
        // testFiveGuess.testSolve();

        System.out.println("\ntestSolveTots");
        testFiveGuess.testSolveTots();

    }
}