package domini.classes;

import java.util.*;

import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;
import domini.classes.actions.*;

public class FiveGuess implements Maquina {
    /**
     * Número de fitxes per al funcionament de l'algorisme FiveGuess.
     */
    private static final int NUM_PEG = 4;
    /**
     * Número de colors per al funcionament de l'algorisme FiveGuess.
     */
    private static final int NUM_COL = 6;
    /**
     * Número de codis diferents que es poden obtenir utilitzant
     * 4 fitxes i 6 colors.
     * Calcul: 6 * 6 * 6 * 6 = 1296
     */
    private static final int NUM_CODIS = 1296;
    /**
     * Número maxim d'intents permessos.
     */
    private static final int MAX_STEPS = 5;
    /**
     * Llista de totes les respostes que es poden obtenir en un intent.
     * B = Black; La fitxa del codi intentat es del color d'una de les
     * fitxes del codi solucio i esta a la mateixa posicio.
     * W = White; La fitxa del codi intentat es del color d'una de les
     * fitxes del codi solucio pero esta en una posicio erronia.
     * '-' = Nothing; La fitxa del codi intentar no es de cap dels
     * colors del codi solucio.
     */
    private static final String[] RESPOSTES = {
            "----",
            "W---",
            "WW--",
            "WWW-",
            "WWWW",
            "B---",
            "BW--",
            "BWW-",
            "BWWW",
            "BB--",
            "BBW-",
            "BBWW",
            "BBB-",
            "BBBB"
    };

    /**
     * Conjunt de codis que encara no s'han intentat
     */
    private ArrayList<Integer[]> codisDisponibles;
    /**
     * Conjunt de codis que encara tenen la possibilitat de ser el codi solucio.
     */
    private ArrayList<Integer[]> codisPossibles;

    /**
     * Constructora
     * Utilitzada per a la creacio d'un algorisme nou.
     */
    public FiveGuess() {
        codisDisponibles = new ArrayList<Integer[]>(NUM_CODIS);
        codisPossibles = new ArrayList<Integer[]>(NUM_CODIS);

        for (char i=1; i<=NUM_COL; ++i) {
            for (int j=1; j<=NUM_COL; ++j) {
                for (int k=1; k<=NUM_COL; ++k) {
                    for (int l=1; l<=NUM_COL; ++l) {
                        Integer[] codi = new Integer[] {
                                Integer.valueOf(i),
                                Integer.valueOf(j),
                                Integer.valueOf(k),
                                Integer.valueOf(l)
                        };
                        codisDisponibles.add(codi.clone());
                        codisPossibles.add(codi.clone());
                    }
                }
            }
        }
    }

    /**
     * Constructora.
     * Utilitzada per carregar algorismes.
     * 
     * @param codisDisponibles              Conjunt de codis que encara no s'han intentat
     * @param codisPossibles                Conjunt de codis que encara tenen la possibilitat de ser el codi solucio.
     */
    public FiveGuess(ArrayList<Integer[]> codisDisponibles, ArrayList<Integer[]> codisPossibles) {
        if (codisDisponibles == null)  throw new IllegalArgumentException("El conjunt d'intents no pot ser nul.");
        if (codisPossibles == null) throw new IllegalArgumentException("El conjunt de respostes dels codis intentats no pot ser nul.");
        
        this.codisDisponibles = codisDisponibles;
        this.codisPossibles = codisPossibles;
    }


    /**
     * Retorna un codi com a solucio probable donats el codi fet a l'intent anterior i
     * la resposta obtinguda respecte el codi intentat.
     * Si es el primer intent, per tant no s'ha intentat cap codi anteriorment, als
     * parametres ultimCodi i respoastaCodi s'han de passar una referencia null.
     *
     * @param   ultimultimCodiAuxCodi               Últim codi intentat, null si encara no s'ha intentat cap codi,
     *                                              es a dir, es la primera ronda).
     * @param   respostaCodi                        Respoasta obtinguda a l'haver intentat el codi ultimCodi.
     * @return                                      Un altre codi com a possible solucio.
     * @throws LongitudCombinacioIncorrecte         Retorna excepcio si la longitud del codi es incorrecta.
     * @throws NumeroColorsIncorrecte               Retorna excepcio si algun dels colors del codi es incorrecta.
     * @throws LongitudRespostaIncorrecte           Retorna excepcio si la longitud de la resposta es incorrecta.
     * @throws ValorsRespostaIncorrectes            Retorna excepcio si algun dels valors de la resposta es incorrecta.
     */
    public Integer[] esbrina(Integer[] ultimCodiAux, String respostaCodi) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        if (ultimCodiAux == null) {
            return new Integer[] {1,1,2,2};
        }
        else  {
            if (ultimCodiAux.length != NUM_PEG) throw new LongitudCombinacioIncorrecte("Mida incorrecte. La mida de l'últim codi ha de ser 4");
            for (Integer fitxa : ultimCodiAux)
                if (fitxa < 1 || fitxa > 6) throw new NumeroColorsIncorrecte("Colors incorrectes. Els colors de l'últim codi han de ser valors en rang [1,6]");

            if (respostaCodi.length() != NUM_PEG) throw new LongitudRespostaIncorrecte("Mida incorrecte. La mida de la resposta a l'últim codi ha de ser 4");
            for (char c : respostaCodi.toCharArray())
                if (c != 'B' && c != 'W' && c != '-') throw new ValorsRespostaIncorrectes("Resposta incorrecte. Els valors de la resposta a l'úlitm codi han de ser 'B', 'W', '-'");

            Integer[] ultimCodi = ultimCodiAux.clone();
            borrarCodisDisponibles(ultimCodi);
            reduirPossibilitats(ultimCodi, respostaCodi);

            return esbrinaCodi().clone();
        }
    }

    /**
     * Retorna un codi com a solucio probable.
     *
     * @return                  Un codi com a possible solucio.
     */
    private Integer[] esbrinaCodi() {
        Integer[] codiEsbrinar = new Integer[NUM_PEG];
        int minmax = 1300;

        Iterator<Integer[]> it = codisDisponibles.iterator();
        while(it.hasNext()) {
            Integer[] codiDisponible = it.next().clone();
            int max = 0;
            for (String resposta : RESPOSTES) {
                int numeroCodisPossibles = reduirPossibilitatsSimulacio(codiDisponible, resposta);
                if (numeroCodisPossibles > max)
                    max = numeroCodisPossibles;
            }

            if (max != 0 && max < minmax) {
                minmax = max;
                codiEsbrinar = codiDisponible.clone();
            }
            else if (max == minmax) { // Agafem amb preferencia un codi dels possibles.
                if (!estaCodisPossibles(codiEsbrinar) && estaCodisPossibles(codiDisponible))
                    codiEsbrinar = codiDisponible.clone();
            }
        }

        return codiEsbrinar;
    }

    /**
     * Donats un codi intentat al torn anterior i la resposta obtinguda per al mateix intent,
     * elimina del conjunt de codis possibles com a solucio, aquells codis que al simular la
     * resposta del codi intentat sobre el codi possible, dona una resposta diferent a la
     * obtinguda al torn.
     *
     * @param   codiIntentatAux     Codi intentat al torn anterior.
     * @param   resposta            Resposta obtinguda al realitzar l'intent de codiIntentat.
     */
    private void reduirPossibilitats(Integer[] codiIntentatAux, String resposta) {
        Integer[] codiIntentat = codiIntentatAux.clone();
        
        for (int i=0; i<codisPossibles.size(); ++i) {
            Integer[] codiPossible = codisPossibles.get(i);
            String respostaSimulada = CorregeixAction.corregeix(codiIntentat, codiPossible);
            if (!ComparaRespostesAction.comparaRespostes(resposta, respostaSimulada)) {
                codisPossibles.remove(codiPossible);
                --i;
            }
        }
    }

    /**
     * Reotorna el número de codis que es mantindrien, es a dir, no es podrien descartar
     * com a possibles codis solucio, per a la hipotetica situacio en que per l'intent d'un
     * dels codis del conjunt de codis encara no intentats es dones una de les respostes
     * possibles que es poden obtenir.
     *
     * @param   codiDisponibleAux   Un codi del conjunt de codis que encara no s'han intentat com a solucio.
     * @param   resposta            Una resposta del conjunt de respostes possibles que poden donar-se per un intent.
     * @return                      Número de codi que no es poden descartar com a solucio.
     */
    private Integer reduirPossibilitatsSimulacio(Integer[] codiDisponibleAux, String resposta) {
        Integer[] codiDisponible = codiDisponibleAux.clone();
        Integer numeroCodisPossibles = 0;

        Iterator<Integer[]> it = codisPossibles.iterator();
        while (it.hasNext()) {
            String respostaSimulada = CorregeixAction.corregeix(codiDisponible, it.next().clone());
            if (ComparaRespostesAction.comparaRespostes(resposta, respostaSimulada))
                ++numeroCodisPossibles;
        }

        return numeroCodisPossibles;
    }

    /**
     * Esborra del conjunt de codis disponibles el codi passat per parametre.
     * Si el codi no existeix, no fa res, ni dona excepcio.
     * 
     * @param   codi            Codi a esborrar.
     */
    private void borrarCodisDisponibles(Integer[] codi) {
        Iterator<Integer[]> it = codisDisponibles.iterator();
        while (it.hasNext()) {
            Integer[] codiDisponible = it.next();
            if (codi[0] == codiDisponible[0] && codi[1] == codiDisponible[1] && codi[2] == codiDisponible[2] && codi[3] == codiDisponible[3]) {
                codisDisponibles.remove(codiDisponible);
                return;
            }
        }
    }

    /**
     * Retora si el codi passat per parametre es troba al conjunt de codis possibles.
     * 
     * @param   codi            Codi a saber l'existencia.
     * @return                  Retorna cert si el codi es troba al conjunt de codis possibles,
     *                          i fals en cas contrari.
     */
    private boolean estaCodisPossibles(Integer[] codi) {
        Iterator<Integer[]> it = codisPossibles.iterator();
        while (it.hasNext()) {
            Integer[] codiPossible = it.next();
            if (codi[0] == codiPossible[0] && codi[1] == codiPossible[1] && codi[2] == codiPossible[2] && codi[3] == codiPossible[3]) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Given the solution code, the solve operation uses one of the proposed algorithm
     * (either five guess or the genetic one) to create the list of codes that will lead
     * to the solution. If the algorithm is unable to find the solution in less than
     * maxSteps steps, the returned list will contain a list composed of maxSteps codes.
     * The operation will throw an exception in case the secret code solution is not
     * consistent with the parameters of the current game.
     *
     * @param   solution        Solution code of the game to break.
     * @return                  List of the tried codes to break the solution code,
     *                          whether it's achieved or not.
     * @throws Exception        Gives exception if the solution code is not in the
     *                          correct format.
     */
    public List<List<Integer>> solve (List<Integer> solution) throws Exception {
        if (solution.size() != NUM_PEG) throw new LongitudCombinacioIncorrecte("Mida incorrecte. FiveGuess necessita mida 4");
        for (int i=0; i<solution.size(); ++i) {
            Integer fitxa = solution.get(i);
            if (fitxa < 1 || fitxa > 6) throw new NumeroColorsIncorrecte("Colors incorrectes. FiveGuess necessita colors en rang [1,6]");
        }

        Integer[] solutionArray = new Integer[NUM_PEG];
        solution.toArray(solutionArray);

        ArrayList<List<Integer>> codis = new ArrayList<List<Integer>>();
        ArrayList<String> respostes = new ArrayList<String>();
        int ronda = 0;
        boolean trobat = false;
        while (ronda < MAX_STEPS && !trobat) {
            Integer[] codi;
            if (ronda == 0) codi = esbrina(null, null).clone();
            else {
                Integer[] codiAnterior = new Integer[NUM_PEG];
            
                codis.get(ronda-1).toArray(codiAnterior);
                codi = esbrina(codiAnterior, respostes.get(ronda-1)).clone();
            }

            if (codi[0].equals(solution.get(0)) && codi[1].equals(solution.get(1)) && codi[2].equals(solution.get(2)) && codi[3].equals(solution.get(3)))
                trobat = true;

            codis.add(Arrays.asList(codi));
            if (!trobat) respostes.add(CorregeixAction.corregeix(codi, solutionArray));

            ++ronda;
        }

        return codis;
    }

    /*
    public static void main(String[] args) throws Exception {
        int p = 4;
        int c = 6;
        System.out.println("NumPeg: " + p + "   NumCol: " + c);
        
        Genetic g = new Genetic(p, c);

        List<Integer> codiSolucio = new ArrayList<>(p);
        for (int i=0; i<p; ++i) codiSolucio.add(ThreadLocalRandom.current().nextInt(1, c+1));
        System.out.println("Codi solucio: " + codiSolucio);

        long temps_ini = System.nanoTime();
        List<List<Integer>> solucions = g.solve(codiSolucio);
        long temps_fin = System.nanoTime();

        for (int i=0; i<solucions.size(); ++i) {
            System.out.println("Solucio " + (i+1) + ":    " + solucions.get(i));
        }

        System.out.println((temps_fin - temps_ini) / 1000000000);
    }
    */
}
