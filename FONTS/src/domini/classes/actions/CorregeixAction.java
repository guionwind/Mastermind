package domini.classes.actions;

/**
 * Action encarregat de donar una correccio donat una combinacioIntentada i el solutionCode
 */
public class CorregeixAction {

    /**
     * Donada una combinacio intentada, retorna la correccio d'aquesta
     * @param combinacioIntentada Combinacio intentada
     * @param solutionCode Codi Solucio
     * @return retorna un string amb la correcio
     */
    public static String corregeix(Integer[] combinacioIntentada, Integer[] solutionCode) {
        combinacioIntentada = combinacioIntentada.clone();
        solutionCode = solutionCode.clone();

        StringBuilder resposta = new StringBuilder();

        // Black
        for (int i=0; i<combinacioIntentada.length; ++i) {
            if (combinacioIntentada[i] == solutionCode[i]) {
                resposta.append("B");
                combinacioIntentada[i] = -1;
                solutionCode[i] = -1;
            }
        }

        // White
        for (Integer integer : combinacioIntentada) {
            if (integer != -1) { // Encara no l'hem processat
                boolean trobat = false;
                for (int j = 0; j < combinacioIntentada.length && !trobat; ++j) {
                    if (integer == solutionCode[j]) {
                        trobat = true;
                        resposta.append("W");
                        solutionCode[j] = -1;
                    }
                }
            }
        }

        while (resposta.length() < combinacioIntentada.length) {
            resposta.append("-");
        }

        return resposta.toString();
    }
}
