package domini.controllers;

import domini.classes.*;
//import java.util.ArrayList;
import java.lang.String;
import domini.classes.ConfiguracioPartida.TipusPartida;

public class CtrlDomini {
    private CtrlPartida ctrlPartida;
    private CtrlJugador ctrlJugador;
    private CtrlRanquing ctrlRanquing;


    public CtrlDomini() {
        ctrlPartida = new CtrlPartida();
        ctrlJugador = new CtrlJugador();
    }

    //! del CtrlPartida
    public void crearPartidaCodebreaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio) {
        ctrlPartida.crearPartidaCodebreaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio);
    }

    public void crearPartidaCodemaker(TipusPartida tipusPartida, int numeroIntents, int numeroColors, int longitudCombinacio, Integer[] solutionCode) {
        ctrlPartida.crearPartidaCodemaker(tipusPartida, numeroIntents, numeroColors, longitudCombinacio, solutionCode);
    }

    //? funcions codebreaker
    /**
     * 1. es crea una ronda i s'estableix com a rondaActual dins CtrlPartida
     * 2. el jugador ha fet un intent (input)
     * 3. corregim l'intent i retornem el resultat
    */ 

    public String corregeix(Integer[] combinacioIntentada) {
        return CtrlPartida.corregeix(combinacioIntentada);
    }

    public String jugaRondaCodebreaker(int rondaId, Integer[] combinacioIntentada) {
        CtrlPartida.creaRonda(rondaId, combinacioIntentada);
        String correccio = CtrlPartida.corregeix(combinacioIntentada);
        return correccio;
    }

    

    //? funcions codemaker
    /** (ronda inicial: l'usuari entra el solutionCode)
     * funcionament de cada ronda:
     * 1. es crea una ronda
     * 2. es genera un intent
     * 3. l'usuari fa un input (intentCorreccio)
     * 4. comprovem si l'intent esta be
     * 5. en cas que no, el jugador haura de repetir
     */

    //donats el codi intentat la ronda anterior i el feedback,
    //genera el codi mes probable a intentar
    //ultimCodi i respostaCodi son null si es la primera ronda
    public void setSolutionCode(Integer[] solution) {
        CtrlPartida.setSolutionCode(solution);
    }

    public String generaIntent(Integer[] ultimCodi, String respostaCodi) {
        return CtrlPartida.esbrina(ultimCodi, respostaCodi);
    }

    //comprova si un intent de correccio es correcte
    public boolean comprovaIntentCorreccio(String intentCorreccio, Integer[] combinacioIntentada) {
        String correcioCorrecta = corregeix(combinacioIntentada);
        return correccioCorrecta.equals(intentCorreccio);
    }


    //! del CtrlJugador
    public void crearJugador (String username, String password) {
        ctrlJugador.crearJugador(username, password);
    }

    //sistema de login: comprova que coincideixin usuari i contrasenya
    //si coincideixen, isPasswordCorrect
    public boolean loginAuthentication (String username, String password) {
        boolean correctCredentials = false;
        String passwd = ctrlJugador.getPassword(username);
        if (password != null) {
            //throw exception
        }
        else if (passwd == password) {
            correctCredentials = true;
        }
        return correctCredentials;
    }

    //! del CtrlRanquing

    public Ranquing getRanquing() {
        return ctrlRanquing.getRanquingActual();
    }

    public void crearRanquing() {
        ctrlRanquing.crearRanquing();
    }

}

/* 
coses a comentar-li a l'arnau
lo de afegir classes actuals a CtrlJugador
-> inclou refactoritzar les creadores de Codemaker, Codebreaker i Ronda

De la creadora de Ronda, no necessitaras la Partida. et passo: rondaId, combinacioIntentada

vull getters desde CtrlPartida per als atributs de PartidaActual
(per la Ronda idk)
*/