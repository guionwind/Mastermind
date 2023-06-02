package domini.controllers;

import java.util.*;

import domini.classes.EstadistiquesPartida;
import presentacio.custom.Pair;
/*
 * Controlador que gestiona les estadistiques de les partides jugades
 */
public class CtrlEstadistiquesPartida {

    private HashMap<Pair<String, Integer>, EstadistiquesPartida> estadistiquesPartida;

    public CtrlEstadistiquesPartida() {
        estadistiquesPartida = new HashMap<>();
    }

    /**
     * Crea una nova instància d'EstadistiquesPartida i la retorna.
     * 
     * @param idJugador id del Jugador que ha jugat la partida
     * @param idPartida id de la Partida corresponent
     * @param puntuacio puntuacio obtinguda en la partida
     * @param guanyada true si el jugador ha guanyat la partida, false si no
     */
    public void creaEstadistiquesPartida(String username, Integer idPartida, Integer puntuacio, Boolean guanyada) {
        EstadistiquesPartida eP = new EstadistiquesPartida(username, idPartida, puntuacio, guanyada);

        Pair<String, Integer> identificadors = new Pair<String, Integer>(username, idPartida);
        estadistiquesPartida.put(identificadors ,eP);
    }

    //? potser les dues funcions d'aqui a sota s'haurien de fer a CtrlDomini
    /**
     * Retorna la puntuacio obtinguda en una partida donada
     * @param idJugador Id del Jugador de la partida
     * @param idPartida Id de la partida jugada
     * @return Puntuacio obtinguda
     */
    public Integer getPuntuacio(String username, Integer idPartida) {
        for (HashMap.Entry<Pair<String, Integer>, EstadistiquesPartida> estadisticaPartida : estadistiquesPartida.entrySet()) {
            Pair<String, Integer> key = estadisticaPartida.getKey(); //Key = {idJugador, idPartida}

            System.out.println("ANALITZANT partida : "+key.getSecond()+" jugador: "+key.getFirst());
            if (key.getFirst() == username && key.getSecond() == idPartida){
                EstadistiquesPartida ed = estadisticaPartida.getValue();
                System.out.println("ESTIC A CTRLESTATICS: "+ed.getPuntuacio());
                return ed.getPuntuacio();
            }
        }
        return null;
    }


    /**
     * Obte una instancia d'estadistiquesPartida
     * @param idJugador Id del Jugador de la partida
     * @param idPartida Id de la partida jugada
     * @return La instancia corresponent
     */
    public EstadistiquesPartida getEstadistiquesPartida(String username, Integer idPartida) {
        for (HashMap.Entry<Pair<String, Integer>, EstadistiquesPartida> entry : estadistiquesPartida.entrySet()) {
            Pair<String, Integer> key = entry.getKey();
            EstadistiquesPartida ed = entry.getValue();
            if (key.getSecond() == idPartida && key.getFirst() == username){
                return ed;
            }
        }
        return null;
    }
}