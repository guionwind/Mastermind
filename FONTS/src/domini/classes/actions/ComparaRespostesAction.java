package domini.classes.actions;

public class ComparaRespostesAction {

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
    public static boolean comparaRespostes(String resposta1, String resposta2) {
        char[] r1 = resposta1.toCharArray();
        char[] r2 = resposta2.toCharArray();
        int negres = 0;
        int blanques = 0;
        int buides = 0;

        for (int i=0; i<resposta1.length(); ++i) {
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
}
