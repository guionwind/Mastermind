package domini.actions;

public class CorregeixAction {

    /**
     * Donada una combinacio intentada, retorna la correccio d'aquesta
     * @param combinacioIntentada Combinacio intentada
     * @return retorna un string amb la correcio
     */
    public String corregeix(Integer[] combinacioIntentada, Integer[] solutionCode) {
        StringBuilder resposta = new StringBuilder();

        for (int i = 0; i < combinacioIntentada.length; ++i) {
            //Black case: color i posicio correctes
            if (combinacioIntentada[i] == solutionCode[i]) {
                resposta.append("B");
                solutionCode[i] = -1;
            }
            //White case: color correcte pero posicio no
            else {
                for (int j = 0; j < combinacioIntentada.length; ++j) {
                    if (combinacioIntentada[i] == solutionCode[j]) {
                        resposta.append("W");
                        solutionCode[j] = -1;
                    }
                }
            }
        }

        while (resposta.length() < combinacioIntentada.length) {
            resposta.append("-");
        }

        partidaActual.setCorrecioRonda(resposta.toString());
        return resposta.toString();
    }
}
