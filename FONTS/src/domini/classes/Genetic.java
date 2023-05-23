package domini.classes;

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
     * Número de fitxes del codi.
     * Equivalent a la longitud del codi.
     */
    private final int numPeg;
    /**
     * Número de colors possibles.
     */
    private final int numCol;
    /**
     * Mida màxima del conjunt de codis marcats com a possible solució.
     */
    private final int maxMida;
    /**
     * Número màxim de generacions.
     */
    private final int maxGen;
    /*
     * Històric de codis intentats
     */
    private ArrayList<Integer[]> codisIntentats;
    /*
     * Històric de respostes per als codis intentats.
     */
    private ArrayList<String> respostesCodisIntentats;

    /**
     * Construcctora amb 2 paràmetres.
     * Utilitzada per a la creació d'un algorisme nou.
     * 
     * @param numPeg        Número de fitxes del codi.
     * @param numCol        Número de colors possibles.
     */
    public Genetic(int numPeg, int numCol) {
        this.numPeg = numPeg;
        this.numCol = numCol;
        this.maxMida = (numPeg+2)*10;
        this.maxGen = 25*numPeg;
        this.codisIntentats = new ArrayList<Integer[]>();
        this.respostesCodisIntentats = new ArrayList<String>();
    }

    /**
     * Constructora amb 4 paràmetres.
     * Utilitzada per carregar algorismes.
     * 
     * @param numPeg
     * @param numCol
     * @param codisIntentats
     * @param respostesCodisIntentats
     */
    public Genetic(int numPeg, int numCol, ArrayList<Integer[]> codisIntentats, ArrayList<String> respostesCodisIntentats) {
        if (codisIntentats == null)  throw new IllegalArgumentException("El conjunt d'intents no pot ser nul.");
        if (respostesCodisIntentats == null) throw new IllegalArgumentException("El conjunt de respostes dels codis intentats no pot ser nul.");
        
        this.numPeg = numPeg;
        this.numCol = numCol;
        this.maxMida = (numPeg+2)*10;
        this.maxGen = 25*numPeg;
        this.codisIntentats = codisIntentats;
        this.respostesCodisIntentats = respostesCodisIntentats;
    }

    /**
     * Retorna un codi com a possible solució.
     * 
     * @param resposta      La resposta rebuda de l'útlim codi intentat.
     * @return              Un altre codi com a possible solució.
     */
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
            
            Integer[] codiIntentar = null;
            while (codiIntentar == null)
                codiIntentar = esbrinaCodi();
            codisIntentats.add(codiIntentar);
            return codiIntentar.clone();
        }
    }

    /**
     * Retorna un codi com a solució probable.
     *
     * @return              Un codi com a possible solució.
     */
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

    /**
     * Genera un conjunt de codis aleatoris en el contenidor especificat.
     * 
     * @param poblacio      Contenidor on es guarden els codis generats.
     */
    private void inicialitzaPoblacio(ArrayList<Integer[]> poblacio) {
        while (poblacio.size() < POPULATION_CAPACITY) {
            Integer[] codi = randomCodi();
            
            if (!estaPoblacio(poblacio, codi))
                poblacio.add(codi);
        }        
    }

    /**
     * Retorna un codi generat de manera aleatòria.
     * 
     * @return              Un codi aleatori.
     */
    private Integer[] randomCodi() {
        Integer[] codi = new Integer[numPeg];
        for (int j=0; j<numPeg; ++j) {
            int randomColor = ThreadLocalRandom.current().nextInt(1, numCol + 1);
            codi[j] = Integer.valueOf(randomColor);
        }
        return codi;
    }

    /**
     * Indica si un codi hi és al conjunt especificat.
     * 
     * @param poblacio      Conjunt de codis.
     * @param codi          Codi a buscar.
     * @return              Retorna cert si el codi pertany al conjunt i
     *                      fals en cas contrari.
     */
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

    /**
     * Calcula el valor fitness de cadascún dels codis de la població.
     * Si els codis no generen cap diferència amb els codis intentats
     * anteriorment, s'afegeix al conjunt de codis com a possible solució.
     * 
     * @param fitnessPoblacio       Conjunt a on es guarden els valors
     *                              de fitness de cada codi.
     * @param poblacio              Conjunt de codis dels que es calcula el fitness.
     * @param codisEscollibles      Conjunt de codis comm a possible solució.
     */
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

    /**
     * Retorna la diferencia entre els codis intentats anteriorment
     * i el codi donat.
     * 
     * @param cromosoma         Codi a commparar.
     * @return                  Diferències de número de fitxes begres i blanques.
     */
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

    /**
     * Retorna la resposta que s'obtindria donat un dels codis encara no intentats i
     * el codi solució.
     *
     * @param   codiIntentatAux     Un codi del conjunt de codis que encara no s'han intentat com a solució
     * @param   codiSolucioAux      Un codi del conjunt de codis que poden ser solució.
     * @return                      La resposta obtinguda per intentar un codi sobre un codi solució.
     */
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

    /**
     * Calcula el número de fitxes negres i fitxes blanques
     * que conté la resposta donada.
     * Les fitxes negres es guarden al primer element de l'array
     * i les fitxen blanques al segon element.
     * 
     * @param resposta      Resposta donada al realitzar un intent.
     * @return              Número de fitxes negres i blanques
     */
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

    /**
     * Genera una nova població a partir de una població donada i
     * del fitness de cada codi de la població.
     * La nova població generada es guarda en un altre conjunt.
     * 
     * @param poblacio              Conjunt de la població.
     * @param fitnessPoblacio       Fitness de la població donada.
     * @param generacio             Conjunt de la nova població.
     */
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

    /**
     * Selecciona dos codis de manera aleatòria del conjunt donat.
     * 
     * @param poblacio              Conjuntde de la població.
     * @param fitnessPoblacio       Fitness de la població donada.
     * @return                      Dos codis escollits aleatòriament.
     */
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

    /**
     * Genera dos nous codis a partir de la combinació
     * dels dos codis donats, utilitzant 1-point crossover
     * o 2-point crossover de manera aleatòria.
     * 
     * @param parents           Dos codis dels que es generaran els altres.
     * @return                  Dos codis nous.
     */
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

    /**
     * Genera dos codis nous intercanviant la part
     * continguda entre els punts especificats.
     * 
     * @param parents       Codis dels quals es generen els altres codis.
     * @param inici         Punt d'inici de l'intercanvi.
     * @param fi            Punt de fi de l'intercanvi.
     * @return              Dos codis nous.
     */
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

    /**
     * Realitza un canvi de valor aleatori a una
     * posició aleatòria sobre el codi donat.
     * 
     * @param cromosoma         Codi on es realitza el canvi.
     */
    private void mutation(Integer[] cromosoma) {
        Integer randomPosicio = ThreadLocalRandom.current().nextInt(0, numPeg);
        Integer randomColor = cromosoma[randomPosicio];
        while (cromosoma[randomPosicio] == randomColor)
            randomColor = ThreadLocalRandom.current().nextInt(1, numCol + 1);
        cromosoma[randomPosicio] = randomColor;
    }

    /**
     * Intercanvia de posició dos valors a partir
     * de posicions aleatòries.
     * 
     * @param cromosoma         Codi on es realitza el canvi.
     */
    private void permutation(Integer[] cromosoma) {
        Integer randomPosicio1 = ThreadLocalRandom.current().nextInt(0, numPeg);
        Integer randomPosicio2 = ThreadLocalRandom.current().nextInt(0, numPeg);
        Integer aux = cromosoma[randomPosicio1];
        cromosoma[randomPosicio1] = cromosoma[randomPosicio2];
        cromosoma[randomPosicio2] = aux;
    }

    /**
     * Inverteix una part del codi donat entre
     * dues posicions aleatòries.
     * 
     * @param cromosoma         Codi on es realitza el canvi.
     */
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

    /**
     * Es retorna el codi més adecuat com a possible
     * solució, d'entre els codis possibles.
     * 
     * @param codisEscollibles          Codis que poden ser solució.
     * @return                          Codi com a possible solució.
     */
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
}