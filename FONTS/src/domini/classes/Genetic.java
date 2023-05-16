
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Genetic implements Maquina {

    /**
     * Número màxim d'intents permessos.
     */
    private static final int MAX_STEPS = 20;
    /*
     * Número de codis de la població
     */
    private static final int POPULATION_CAPACITY = 200;

    /**
     * Identificador de la partida a la que pertanyen la combinació dels conjunts
     * de codis encara no intentats i codis encara possibles com a solució.
     */
    private Integer idPartida;
    /**
     * Número de fitxes per al funcionament de l'algorisme FiveGuess.
     */
    private final int numPeg;
    /**
     * Número de colors per al funcionament de l'algorisme FiveGuess.
     */
    private final int numCol;
    /**
     * Mida màxima del conjunt codisIntentats
     */
    private final int maxMida;
    /**
     * Número màxim de generacions
     */
    private final int maxGen;
    /*
     * Històric de codis intentats
     */
    private ArrayList<Integer[]> codisIntentats;
    /*
     * Històric de respostes per cada codi del conjunt codisIntentats
     */
    private ArrayList<String> respostesCodisIntentats;

    public Genetic(Integer idPartida, Integer numPeg, Integer numCol) {
        this.idPartida = idPartida;
        this.numPeg = numPeg;
        this.numCol = numCol;
        this.maxMida = (numPeg+2)*10;
        this.maxGen = 25*numPeg;
        this.codisIntentats = new ArrayList<Integer[]>();
        this.respostesCodisIntentats = new ArrayList<String>();
    }

    public Integer getId(){
        return this.idPartida;
    }

    public Integer[] esbrina(String resposta) {
        if (codisIntentats.size() > 0 && resposta == null) throw new IllegalArgumentException("La resposta no pot ser un valor nul per a rondes posteriors a la primera.");

        if (codisIntentats.size() == 0) {
            Integer[] codi = new Integer[numPeg];
            int k = 0;
            int j = 1;
            while (j <= (numPeg/2)-1) {
                codi[k++] = j;
                codi[k++] = j++;
            }
            while (k < numPeg) {
                codi[k++] = j++;
            }

            codisIntentats.add(codi);
            return codi;
        }
        else {
            respostesCodisIntentats.add(resposta);

            // 
            System.out.println("##############");
            for (int i=0; i<codisIntentats.size(); ++i) {
                Integer[] codiIntentat = codisIntentats.get(i);
                System.out.print("Codi intentat " + i + " =");
                for (Integer fitxa : codiIntentat) {
                    System.out.print(" "+fitxa);
                }
                System.out.print("   ");

                String respostaCodiIntentat = respostesCodisIntentats.get(i);
                System.out.print("Resposta rebuda " + i + " =");
                for (char c : respostaCodiIntentat.toCharArray()) {
                    System.out.print(" "+c);
                }
                
                System.out.println();
            }
            System.out.println("##############\n");
            // 
            
            Integer[] codiIntentar = null;
            while (codiIntentar == null)
                codiIntentar = esbrinaCodi();
            codisIntentats.add(codiIntentar);
            return codiIntentar.clone();
        }
    }

    private Integer[] esbrinaCodi() {
        ArrayList<Integer[]> codisEscollibles = new ArrayList<Integer[]>(maxMida);
        int h = 1;

        ArrayList<Integer[]> poblacio = new ArrayList<Integer[]>(POPULATION_CAPACITY);
        inicialitzaPoblacio(poblacio);
        
        ArrayList<Integer> fitnessPoblacio = new ArrayList<Integer>(POPULATION_CAPACITY);
        for (int i=0; i<POPULATION_CAPACITY; ++i) fitnessPoblacio.add(0);
        calcularFitness(fitnessPoblacio, poblacio, codisEscollibles);

        while (h < maxGen && codisEscollibles.size() < maxMida) {
            ArrayList<Integer[]> generacio = new ArrayList<Integer[]>(POPULATION_CAPACITY);
            novaGeneracio(poblacio, fitnessPoblacio, generacio);
            poblacio = generacio;
            
            calcularFitness(fitnessPoblacio, poblacio, codisEscollibles);
            
            ++h;
        }
        
        return escollirIntent(codisEscollibles);
    }

    private void inicialitzaPoblacio(ArrayList<Integer[]> poblacio) {
        while (poblacio.size() < POPULATION_CAPACITY) {
            Integer[] codi = randomCodi();
            
            if (!estaPoblacio(poblacio, codi))
                poblacio.add(codi);
        }        
    }

    private Integer[] randomCodi() {
        Integer[] codi = new Integer[numPeg];
        for (int j=0; j<numPeg; ++j) {
            int randomColor = ThreadLocalRandom.current().nextInt(1, numCol + 1);
            codi[j] = Integer.valueOf(randomColor);
        }
        return codi;
    }

    private boolean estaPoblacio(ArrayList<Integer[]> poblacio, Integer[] codi) {
        for (int i=0; i<poblacio.size(); ++i) {
            Integer[] aux = poblacio.get(i);
            boolean trobat = true;
            for (int j=0; j<codi.length && trobat; ++j) {
                if (aux[j] != codi[j]) trobat = false;
            }
            if (trobat) return true;
        }
        return false;
    }

    private void calcularFitness(ArrayList<Integer> fitnessPoblacio, ArrayList<Integer[]> poblacio, ArrayList<Integer[]> codisEscollibles) {
        int i = codisIntentats.size() + 1;
        int a = 2;
        int b = 2;

        for (int j=0; j<poblacio.size(); ++j) {
            Integer[] cromosoma = poblacio.get(j);

            Integer[] puntuacio = fitness(cromosoma);
            fitnessPoblacio.set(j, a*puntuacio[0] + puntuacio[1] + b*numPeg*(i-1));

            if (puntuacio[0] == 0 && puntuacio[1] == 0)
                codisEscollibles.add(cromosoma);
        }
    }

    private Integer[] fitness(Integer[] cromosoma) {
        int puntuacioNegres = 0;
        int puntuacioBlanques = 0;
        for (int j=0; j<codisIntentats.size(); ++j) {
            Integer[] intentReal = codisIntentats.get(j);
            String respostaReal = respostesCodisIntentats.get(j);

            String respostaSim = generaResposta(cromosoma, intentReal);
            
            Integer[] puntuacioSim = puntuacio(respostaSim);
            Integer[] puntuacioReal = puntuacio(respostaReal);

            puntuacioNegres += Math.abs(puntuacioSim[0] - puntuacioReal[0]);
            puntuacioBlanques += Math.abs(puntuacioSim[1] - puntuacioReal[1]);
        }

        return new Integer[] {puntuacioNegres, puntuacioBlanques};
    }

    private String generaResposta(Integer[] codiIntentatAux, Integer[] codiSolucioAux) {
        Integer[] codiIntentat = codiIntentatAux.clone();
        Integer[] codiSolucio = codiSolucioAux.clone();
        String resposta = "";

        // Black
        for (int i=0; i<numPeg; ++i) {
            if (codiIntentat[i] == codiSolucio[i]) {
                resposta += "B";
                codiIntentat[i] = -1;
                codiSolucio[i] = -1;
            }
        }

        // White
        for (int i=0; i<numPeg; ++i) {
            if (codiIntentat[i] != -1) { // Encara no l'hem processat
                boolean trobat = false;
                for (int j=0; j<numPeg && !trobat; ++j) {
                    if (codiIntentat[i] == codiSolucio[j]) {
                        trobat = true;
                        resposta += "W";
                        codiSolucio[j] = -1;
                    }
                }
            }
        }

        while (resposta.length() < numPeg)
            resposta += "-";

        return resposta;
    }

    private Integer[] puntuacio(String resposta) {
        Integer[] puntuacio = new Integer[] {0, 0}; // {Número negres, Número blanque}
        boolean fi = false;
        for (int i=0; i<resposta.length() && !fi; ++i) {
            char c = resposta.charAt(i);
            if (c == 'B') ++puntuacio[0];
            else if (c == 'W') ++puntuacio[1];
            else fi = true;
        }
        return puntuacio;
    }

    private void novaGeneracio(ArrayList<Integer[]> poblacio, ArrayList<Integer> fitnessPoblacio, ArrayList<Integer[]> generacio) {
        while (generacio.size() < POPULATION_CAPACITY) {
            Integer[][] parents = randomParents(poblacio, fitnessPoblacio);

            Integer[][] childs = crossover(parents);
            Integer[] child1 = childs[0];
            Integer[] child2 = childs[1];
            
            float randomNum;
            randomNum = (float) ThreadLocalRandom.current().nextDouble();
            if (randomNum < 0.03) mutation(child1);
            randomNum = (float) ThreadLocalRandom.current().nextDouble();
            if (randomNum < 0.03) mutation(child2);

            randomNum = (float) ThreadLocalRandom.current().nextDouble();
            if (randomNum < 0.03) permutation(child1);
            randomNum = (float) ThreadLocalRandom.current().nextDouble();
            if (randomNum < 0.03) permutation(child2);

            randomNum = (float) ThreadLocalRandom.current().nextDouble();
            if (randomNum < 0.02) inversion(child1);
            randomNum = (float) ThreadLocalRandom.current().nextDouble();
            if (randomNum < 0.02) inversion(child2);

            if (!estaPoblacio(generacio, child1)) generacio.add(child1);
            else generacio.add(randomCodi());
            if (!estaPoblacio(generacio, child2)) generacio.add(child2);
            else generacio.add(randomCodi());
        }        
    }

    private Integer[][] randomParents(ArrayList<Integer[]> poblacio, ArrayList<Integer> fitnessPoblacio) {
        Integer[][] parents = new Integer[2][];

        int sumaFitness = 0;
        for (Integer f : fitnessPoblacio)
            sumaFitness += f;

        int sumaParcial = ThreadLocalRandom.current().nextInt(0, sumaFitness);
        int index = ThreadLocalRandom.current().nextInt(0, numPeg);
        while (sumaParcial < sumaFitness) {
            sumaParcial += fitnessPoblacio.get(index++);
            if (index == fitnessPoblacio.size())
                index = 0;
        }
        parents[0] = poblacio.get(index);
        
        
        sumaParcial = ThreadLocalRandom.current().nextInt(0, sumaFitness);
        index = ThreadLocalRandom.current().nextInt(0, numPeg);
        while (sumaParcial < sumaFitness) {
            sumaParcial += fitnessPoblacio.get(index++);
            if (index == fitnessPoblacio.size())
                index = 0;
        }
        parents[1] = poblacio.get(index);

        return parents;        
    }

    private Integer[][] crossover(Integer[][] parents) {
        float oneOrTwo = (float) ThreadLocalRandom.current().nextDouble();
        if (oneOrTwo < 0.5) {
            Integer inici = ThreadLocalRandom.current().nextInt(0, numPeg);
            return pointCrossover(parents, inici, numPeg);
        }
        else {
            Integer inici = ThreadLocalRandom.current().nextInt(0, numPeg);
            Integer fi = ThreadLocalRandom.current().nextInt(inici, numPeg);
            return pointCrossover(parents, inici, fi);
        }
    }

    private Integer[][] pointCrossover(Integer[][] parents, Integer inici, Integer fi) {
        Integer[][] childs = new Integer[2][];
        Integer[] child1 = new Integer[numPeg];
        Integer[] child2 = new Integer[numPeg];
        for (int i=0; i<inici; ++i) {
            child1[i] = parents[0][i];
            child2[i] = parents[1][i];
        }
        for (int i=inici; i<fi; ++i) {
            child1[i] = parents[1][i];
            child2[i] = parents[0][i];
        }
        for (int i=fi; i<numPeg; ++i) {
            child1[i] = parents[0][i];
            child2[i] = parents[1][i];
        }
        childs[0] = child1;
        childs[1] = child2;
        return childs;
    }

    private void mutation(Integer[] cromosoma) {
        Integer randomPosicio = ThreadLocalRandom.current().nextInt(0, numPeg);
        Integer randomColor = cromosoma[randomPosicio];
        while (cromosoma[randomPosicio] == randomColor)
            randomColor = ThreadLocalRandom.current().nextInt(1, numCol + 1);
        cromosoma[randomPosicio] = randomColor;
    }

    private void permutation(Integer[] cromosoma) {
        Integer randomPosicio1 = ThreadLocalRandom.current().nextInt(0, numPeg);
        Integer randomPosicio2 = ThreadLocalRandom.current().nextInt(0, numPeg);
        Integer aux = cromosoma[randomPosicio1];
        cromosoma[randomPosicio1] = cromosoma[randomPosicio2];
        cromosoma[randomPosicio2] = aux;
    }

    private void inversion(Integer[] cromosoma) {
        Integer randomPosicio1 = ThreadLocalRandom.current().nextInt(0, numPeg);
        Integer randomPosicio2 = ThreadLocalRandom.current().nextInt(0, numPeg);
        if (randomPosicio1 > randomPosicio2) {
            Integer aux = randomPosicio1;
            randomPosicio1 = randomPosicio2;
            randomPosicio2 = aux;
        }
        
        int rang = randomPosicio2 - randomPosicio1;
        Integer[] aux = new Integer[rang];
        for (int i=0; i<rang; ++i)
            aux[i] = cromosoma[randomPosicio1 + i];
        for (int i=0; i<rang; ++i)
            cromosoma[randomPosicio1 + i] = aux[rang-i-1];
    }

    private Integer[] escollirIntent(ArrayList<Integer[]> codisEscollibles) {
        Integer[] mitjanaRomanen = new Integer[codisEscollibles.size()];

        for (int i=0; i<codisEscollibles.size(); ++i) {
            Integer[] codiEscollible = codisEscollibles.get(i);
            int romanen = 0;
            for (Integer[] codiSolucio : codisEscollibles) {
                if (codiSolucio != codiEscollible) {
                    String solucioSolucio = generaResposta(codiEscollible, codiSolucio);
                    for (Integer[] codiRoman : codisEscollibles) {
                        if (codiRoman != codiEscollible && codiRoman != codiSolucio) {
                            String solucioRoman = generaResposta(codiEscollible, codiRoman);
                            if (comparaRespostes(solucioSolucio, solucioRoman))
                                ++romanen;
                        }
                    }
                }
            }
            romanen /= codisEscollibles.size();
            mitjanaRomanen[i] = romanen;
        }
        
        Integer[] intent = null;
        int minim = codisEscollibles.size();
        for (int i=0; i<mitjanaRomanen.length; ++i) {
            Integer romanen = mitjanaRomanen[i];
            if (romanen < minim) {
                minim = romanen;
                intent = codisEscollibles.get(i);
            }
        }
        return intent;
    }

    /**
     * Reotrna cert si les dues respostes donades són equivalents i fals en cas contrari.
     * Una de les respostes és la respota real obtinguda al torn i l'altre és una resposta
     * simulada per a una hipotètica situació.
     * Dues respostes són equivalents si, sense tenir en compte l'ordre, tenen el mateix
     * número de fitxes negres ('B'), fitxes blanques ('W'), i fitxes buides (' ').
     *
     * @param   resposta1       Primmera resposta.
     * @param   resposta2       Segona resposta.
     * @return                  Retorna cert si les respostes són equivalents i fals si no hi són.
     */
    private boolean comparaRespostes(String resposta1, String resposta2) {
        char[] r1 = resposta1.toCharArray();
        char[] r2 = resposta2.toCharArray();
        int negres = 0;
        int blanques = 0;
        int buides = 0;

        for (int i=0; i<numPeg; ++i) {
            if (r1[i] == 'B') ++negres;
            else if (r1[i] == 'W') ++blanques;
            else if (r1[i] == '-') ++buides;

            if (r2[i] == 'B') --negres;
            else if (r2[i] == 'W') --blanques;
            else if (r2[i] == '-') --buides;
        }


        if (negres == 0 && blanques == 0 && buides == 0) return true;
        return false;
    }

    public List<List<Integer>> solve(List<Integer> solution) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'solve'");
    }

    public static void main(String[] args) {
        Genetic genetic = new Genetic(1, 8, 9);
        
        Integer[] solucio = new Integer[] {6,3,5,2,1,8,1,9};

        Integer ronda = 0;
        boolean guanyat = false;
        Integer[] codi = genetic.esbrina(null);
        String resposta = genetic.generaResposta2(codi, solucio);
        guanyat = true;
        for (char c : resposta.toCharArray()) {
            if (c != 'B')
                guanyat = false;
        }
        System.out.print("Ronda = " + ++ronda);
        System.out.print("   Codi =");
        for (Integer fitxa : codi)
            System.out.print(" " + fitxa);
        System.out.print("   Resposta =");
        for (char car : resposta.toCharArray())
            System.out.print(" "+car);
        System.out.println();        
        
        while (!guanyat) {
            codi = genetic.esbrina(resposta);
            resposta = genetic.generaResposta2(codi, solucio);
            guanyat = true;
            for (char c : resposta.toCharArray()) {
                if (c != 'B')
                    guanyat = false;
            }
            
            System.out.print("Ronda = " + ++ronda);
            System.out.print("   Codi =");
            for (Integer fitxa : codi)
                System.out.print(" " + fitxa);
            System.out.print("   Resposta =");
            for (char car : resposta.toCharArray())
                System.out.print(" "+car);
            System.out.println();
        }
    }

    public String generaResposta2(Integer[] codiIntentatAux, Integer[] codiSolucioAux) {
        Integer[] codiIntentat = codiIntentatAux.clone();
        Integer[] codiSolucio = codiSolucioAux.clone();
        String resposta = "";

        // Black
        for (int i=0; i<numPeg; ++i) {
            if (codiIntentat[i] == codiSolucio[i]) {
                resposta += "B";
                codiIntentat[i] = -1;
                codiSolucio[i] = -1;
            }
        }

        // White
        for (int i=0; i<numPeg; ++i) {
            if (codiIntentat[i] != -1) { // Encara no l'hem processat
                boolean trobat = false;
                for (int j=0; j<numPeg && !trobat; ++j) {
                    if (codiIntentat[i] == codiSolucio[j]) {
                        trobat = true;
                        resposta += "W";
                        codiSolucio[j] = -1;
                    }
                }
            }
        }

        while (resposta.length() < numPeg)
            resposta += "-";

        return resposta;
    }


}

/*
^Cdavid@david-VivoBook-ASUSLaptop-X421EA-S433EA:~/Documents/genetic$  cd /home/david/Documents/genetic ; /usr/bin/env /usr/lib/jvm/java-11-openjdk-amd64/bin/java -cp /home/david/.config/Code/User/workspaceStorage/9c7e86901698910d9b4ef296b0ddc1f8/redhat.java/jdt_ws/genetic_cb303c86/bin Genetic 
Ronda = 1   Codi = 1 1 2 2 3 3 4 5   Resposta = B W W W W - - -
##############
Codi intentat 0 = 1 1 2 2 3 3 4 5   Resposta rebuda 0 = B W W W W - - -
##############

Ronda = 2   Codi = 1 4 7 3 2 7 3 3   Resposta = W W W - - - - -
##############
Codi intentat 0 = 1 1 2 2 3 3 4 5   Resposta rebuda 0 = B W W W W - - -
Codi intentat 1 = 1 4 7 3 2 7 3 3   Resposta rebuda 1 = W W W - - - - -
##############

Ronda = 3   Codi = 4 8 9 2 5 1 2 4   Resposta = B W W W W - - -
##############
Codi intentat 0 = 1 1 2 2 3 3 4 5   Resposta rebuda 0 = B W W W W - - -
Codi intentat 1 = 1 4 7 3 2 7 3 3   Resposta rebuda 1 = W W W - - - - -
Codi intentat 2 = 4 8 9 2 5 1 2 4   Resposta rebuda 2 = B W W W W - - -
##############

Ronda = 4   Codi = 5 3 2 6 9 2 2 1   Resposta = B W W W W W - -
##############
Codi intentat 0 = 1 1 2 2 3 3 4 5   Resposta rebuda 0 = B W W W W - - -
Codi intentat 1 = 1 4 7 3 2 7 3 3   Resposta rebuda 1 = W W W - - - - -
Codi intentat 2 = 4 8 9 2 5 1 2 4   Resposta rebuda 2 = B W W W W - - -
Codi intentat 3 = 5 3 2 6 9 2 2 1   Resposta rebuda 3 = B W W W W W - -
##############

Ronda = 5   Codi = 2 2 3 5 3 9 2 8   Resposta = W W W W W - - -
##############
Codi intentat 0 = 1 1 2 2 3 3 4 5   Resposta rebuda 0 = B W W W W - - -
Codi intentat 1 = 1 4 7 3 2 7 3 3   Resposta rebuda 1 = W W W - - - - -
Codi intentat 2 = 4 8 9 2 5 1 2 4   Resposta rebuda 2 = B W W W W - - -
Codi intentat 3 = 5 3 2 6 9 2 2 1   Resposta rebuda 3 = B W W W W W - -
Codi intentat 4 = 2 2 3 5 3 9 2 8   Resposta rebuda 4 = W W W W W - - -
##############

Ronda = 6   Codi = 8 3 5 2 1 6 6 2   Resposta = B B B B W W - -
##############
Codi intentat 0 = 1 1 2 2 3 3 4 5   Resposta rebuda 0 = B W W W W - - -
Codi intentat 1 = 1 4 7 3 2 7 3 3   Resposta rebuda 1 = W W W - - - - -
Codi intentat 2 = 4 8 9 2 5 1 2 4   Resposta rebuda 2 = B W W W W - - -
Codi intentat 3 = 5 3 2 6 9 2 2 1   Resposta rebuda 3 = B W W W W W - -
Codi intentat 4 = 2 2 3 5 3 9 2 8   Resposta rebuda 4 = W W W W W - - -
Codi intentat 5 = 8 3 5 2 1 6 6 2   Resposta rebuda 5 = B B B B W W - -
##############

Ronda = 7   Codi = 9 3 5 2 1 8 1 6   Resposta = B B B B B B W W
##############
Codi intentat 0 = 1 1 2 2 3 3 4 5   Resposta rebuda 0 = B W W W W - - -
Codi intentat 1 = 1 4 7 3 2 7 3 3   Resposta rebuda 1 = W W W - - - - -
Codi intentat 2 = 4 8 9 2 5 1 2 4   Resposta rebuda 2 = B W W W W - - -
Codi intentat 3 = 5 3 2 6 9 2 2 1   Resposta rebuda 3 = B W W W W W - -
Codi intentat 4 = 2 2 3 5 3 9 2 8   Resposta rebuda 4 = W W W W W - - -
Codi intentat 5 = 8 3 5 2 1 6 6 2   Resposta rebuda 5 = B B B B W W - -
Codi intentat 6 = 9 3 5 2 1 8 1 6   Resposta rebuda 6 = B B B B B B W W
##############

Ronda = 8   Codi = 6 3 5 2 1 8 1 9   Resposta = B B B B B B B B
 */