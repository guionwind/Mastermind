package drivers.driverFiveGuess;
import java.util.*;
import java.lang.*;

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
     * Càlcul: 6 * 6 * 6 * 6 = 1296
     */
    private static final int NUM_CODIS = 1296;
    /**
     * Número màxim d'intents permessos.
     */
    private static final int MAX_STEPS = 5;
    /**
     * Llista de totes les respostes que es poden obtenir en un intent.
     * B = Black; La fitxa del codi intentat és del color d'una de les
     * fitxes del codi solució i està a la mateixa posició.
     * W = White; La fitxa del codi intentat és del color d'una de les
     * fitxes del codi solució però està en una posició errònia.
     * ' ' = Nothing; La fitxa del codi intentar no és de cap dels
     * colors del codi solució.
     */
    private static final String[] RESPOSTES = {
            "    ",
            "W   ",
            "WW  ",
            "WWW ",
            "WWWW",
            "B   ",
            "BW  ",
            "BWW ",
            "BWWW",
            "BB  ",
            "BBW ",
            "BBWW",
            "BBB ",
            "BBBB"
    };

    /**
     * Identificador de la partida a la que pertanyen la combinació dels conjunts
     * de codis encara no intentats i codis encara possibles com a solució.
     */
    private final int idPartida;
    /**
     * Conjunt de codis que encara no s'han intentat
     */
    private ArrayList<Integer[]> codisDisponibles;
    /**
     * Conjunt de codis que encara tenen la possibilitat de ser el codi solució.
     */
    private ArrayList<Integer[]> codisPossibles;

        /**
         * Constructora
         * S'especifica a la partida que pertany.
         *
         * @param   idPartida       Identificador de la partida a la que pertany.
         */
    public FiveGuess(int idPartida) {
        this.idPartida = idPartida;
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
         * Retorna un codi com a solució probable donats el codi fet a l'intent anterior i
         * la resposta obtinguda respecte el codi intentat.
         * Si es el primer intent, per tant no s'ha intentat cap codi anteriorment, als
         * paràmetres ultimCodi i respoastaCodi s'han de passar una referència null.
         *
         * @param   ultimCodi       Últim codi intentat, null si encara no s'ha intentat cap codi,
         *                          és a dir, és la primera ronda).
         * @param   respostaCodi    Respoasta obtinguda a l'haver intentat el codi ultimCodi.
         * @return                  Un altre codi com a possible solució.
         */
    public Integer[] esbrina(Integer[] ultimCodiAux, String respostaCodi) {

        Integer[] codi;

        if (ultimCodiAux == null) codi = new Integer[]{1,1,2,2};
        else {
            Integer[] ultimCodi = ultimCodiAux.clone();
            borrarCodisDisponibles(ultimCodi);
            reduirPossibilitats(ultimCodi, respostaCodi);
            codi = esbrinaCodi().clone();
        }

        return codi;
    }

        /**
         * Retorna un codi com a solució probable.
         *
         * @return                  Un codi com a possible solució.
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
            else if (max == minmax) { // Agafem amb preferència un codi dels possibles.
                if (!estaCodisPossibles(codiEsbrinar) && estaCodisPossibles(codiDisponible))
                    codiEsbrinar = codiDisponible.clone();
            }
        }

        return codiEsbrinar;
    }

        /**
         * Donats un codi intentat al torn anterior i la resposta obtinguda per al mateix intent,
         * elimina del conjunt de codis possibles com a solució, aquells codis que al simular la
         * resposta del codi intentat sobre el codi possible, dóna una resposta diferent a la
         * obtinguda al torn.
         *
         * @param   codiIntentat    Codi intentat al torn anterior.
         * @param   resposta        Resposta obtinguda al realitzar l'intent de codiIntentat.
         */
    private void reduirPossibilitats(Integer[] codiIntentatAux, String resposta) {
        Integer[] codiIntentat = codiIntentatAux.clone();

        for (int i = 0; i < codisPossibles.size(); i++) {
            Integer[] codiPossible = codisPossibles.get(i);
            String respostaSimulada = generaResposta(codiIntentat, codiPossible);
            if (!comparaRespostes(resposta, respostaSimulada)) {
                codisPossibles.remove(codiPossible);
                i--;
            }
        }
    }

        /**
         * Reotorna el número de codis que es mantindrien, és a dir, no es podrien descartar
         * com a possibles codis solució, per a la hipotètica situació en que per l'intent d'un
         * dels codis del conjunt de codis encara no intentats es donés una de les respostes
         * possibles que es poden obtenir.
         *
         * @param   codiDisponible  Un codi del conjunt de codis que encara no s'han intentat com a solució.
         * @param   resposta        Una resposta del conjunt de respostes possibles que poden donar-se per un intent.
         * @return                  Número de codi que no es poden descartar com a solució.
         */
    private Integer reduirPossibilitatsSimulacio(Integer[] codiDisponibleAux, String resposta) {
        Integer[] codiDisponible = codiDisponibleAux.clone();
        Integer numeroCodisPossibles = 0;

        Iterator<Integer[]> it = codisPossibles.iterator();
        while (it.hasNext()) {
            String respostaSimulada = generaResposta(codiDisponible, it.next().clone());
            if (comparaRespostes(resposta, respostaSimulada))
                ++numeroCodisPossibles;
        }

        return numeroCodisPossibles;
    }

        /**
         * Retorna la resposta que s'obtindria donat un dels codis encara no intentats i
         * el codi solució.
         *
         * @param   codiDisponible  Un codi del conjunt de codis que encara no s'han intentat com a solució
         * @param   codiPossible    Un codi del conjunt de codis que poden ser solució.
         * @return                  La resposta obtinguda per intentar un codi sobre un codi solució.
         */
    protected String generaResposta(Integer[] codiDisponibleAux, Integer[] codiPossibleAux) {
        Integer[] codiDisponible = codiDisponibleAux.clone();
        Integer[] codiPossible = codiPossibleAux.clone();
        String resposta = "";

        // Black
        for (int i=0; i<NUM_PEG; ++i) {
            if (codiDisponible[i] == codiPossible[i]) {
                resposta += "B";
                codiDisponible[i] = -1;
                codiPossible[i] = -1;
            }
        }

        // White
        for (int i=0; i<NUM_PEG; ++i) {
            if (codiDisponible[i] != -1) { // Encara no l'hem processat
                boolean trobat = false;
                for (int j=0; j<NUM_PEG && !trobat; ++j) {
                    if (codiDisponible[i] == codiPossible[j]) {
                        trobat = true;
                        resposta += "W";
                        codiPossible[j] = -1;
                    }
                }
            }
        }

        while (resposta.length() < NUM_PEG)
            resposta += " ";

        return resposta;
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
    boolean comparaRespostes(String resposta1, String resposta2) {
        char[] r1 = resposta1.toCharArray();
        char[] r2 = resposta2.toCharArray();
        int negres = 0;
        int blanques = 0;
        int buides = 0;

        for (int i=0; i<NUM_PEG; ++i) {
            if (r1[i] == 'B') negres = negres + 1;
            else if (r1[i] == 'W') blanques = blanques + 1;
            else if (r1[i] == ' ') buides = buides + 1;

            if (r2[i] == 'B') --negres;
            else if (r2[i] == 'W') --blanques;
            else if (r2[i] == ' ') --buides;
        }


        if (negres == 0 && blanques == 0 && buides == 0) return true;
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
     */
    public List<List<Integer>> solve (List<Integer> solution) throws Exception {
        Integer[] solutionArray = new Integer[NUM_PEG];
        solution.toArray(solutionArray);

        ArrayList<List<Integer>> codis = new ArrayList<List<Integer>>(MAX_STEPS);
        ArrayList<String> respostes = new ArrayList<String>(MAX_STEPS);
        int ronda = 0;
        boolean trobat = false;
        while (ronda < MAX_STEPS && !trobat) {
            Integer[] codi;
            if (ronda == 0) codi = esbrina(null, null);
            else {
                Integer[] codiAnterior = new Integer[NUM_PEG];
                codis.get(ronda-1).toArray(codiAnterior);
                codi = esbrina(codiAnterior, respostes.get(ronda-1));
            }

            if (Arrays.asList(codi).equals(solution))
                trobat = true;

            codis.set(ronda, Arrays.asList(codi));
            if (!trobat) respostes.set(ronda, generaResposta(codi, solutionArray));

            ++ronda;
        }

        while (++ronda < MAX_STEPS) codis.remove(ronda);
        return codis;
    }

    private void borrarCodisDisponibles(Integer[] codi) {
        Iterator<Integer[]> it = codisDisponibles.iterator();
        boolean trobat = false;
        while (it.hasNext() && !trobat) {
            Integer[] codiDisponible = it.next();
            if (codi[0] == codiDisponible[0] && codi[1] == codiDisponible[1] && codi[2] == codiDisponible[2] && codi[3] == codiDisponible[3]) {
                trobat = true;
                codisDisponibles.remove(codiDisponible);
            }
        }
    }

    private void borrarCodisPossibles(Integer[] codi) {
        Iterator<Integer[]> it = codisPossibles.iterator();
        boolean trobat = false;
        while (it.hasNext() && !trobat) {
            Integer[] codiPossible = it.next();
            if (codi[0] == codiPossible[0] && codi[1] == codiPossible[1] && codi[2] == codiPossible[2] && codi[3] == codiPossible[3]) {
                trobat = true;
                codisDisponibles.remove(codiPossible);
            }
        }
    }

    private boolean estaCodisPossibles(Integer[] codi) {
        Iterator<Integer[]> it = codisPossibles.iterator();
        boolean trobat = false;
        while (it.hasNext() && !trobat) {
            Integer[] codiPossible = it.next();
            if (codi[0] == codiPossible[0] && codi[1] == codiPossible[1] && codi[2] == codiPossible[2] && codi[3] == codiPossible[3]) {
                trobat = true;
            }
        }
        return trobat;
    }
}
