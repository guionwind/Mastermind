package domini.controllers;

import java.util.ArrayList;

import domini.classes.Ranquing;
//import java.util.ArrayList;


public class CtrlRanquing {

    /**
     * Ranquing sobre el qual cridarem les funcions corresponents.
     */
    private Ranquing ranquing;

    /**
     * Constructora
     */
    public CtrlRanquing() {
        ranquing = null;
    }

    /**
     * Crea una nova instància de Ranquing dins el controlador.
     */
    public void crearRanquing() {
        ranquing = new Ranquing();
    }

    /**
     * Retorna el ranquing actual. Pot ser null si no n'hi ha cap assignat.
     * @return Retorna la instancia de ranquing
     */
    public Ranquing getRanquing() {
        return ranquing;
    }

    /**
     * Retorna les 10 puntuacions mes altes
     * @return Les 10 millors puntuacions amb el jugador corresponent, en el format {idJugador, puntuacio}
     */
    public ArrayList<Integer[]> getTop10(){
        return ranquing.getTopN(10);
    }

    /**
     * Afegeix una nova puntuacio al ranquing
     * @param idJugador id del jugador que ha jugat la partida
     * @param puntacio Puntuacio obtinguda a la partida
     */
    public void setNewRecord(Integer idJugador, Integer puntacio) {
        ranquing.addEstadistica(new Integer[] {idJugador, puntacio});
    }
}
