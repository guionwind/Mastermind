package domini.controllers;

import domini.classes.EstadistiquesPartida;

import java.util.HashMap;

/**
 * Controlador que gestiona les estadistiques de les partides jugades
 */
public class CtrlEstadistiquesPartida {

    private HashMap<Integer[], EstadistiquesPartida> estadistiquesPartida;


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
    public void creaEstadistiquesPartida(Integer idJugador, Integer idPartida, Integer puntuacio, Boolean guanyada) {
        EstadistiquesPartida eP = new EstadistiquesPartida(idJugador, idPartida, puntuacio, guanyada);

        Integer[] pair = new Integer[]{idJugador, idPartida};
        estadistiquesPartida.put(pair ,eP);
    }

    //? potser les dues funcions d'aqui a sota s'haurien de fer a CtrlDomini
    /**
     * Retorna la puntuacio obtinguda en una partida donada
     * @param idJugador Id del Jugador de la partida
     * @param idPartida Id de la partida jugada
     * @return Puntuacio obtinguda
     */
    public Integer getPuntuacio(Integer idJugador, Integer idPartida) {
        for (HashMap.Entry<Integer[], EstadistiquesPartida> entry : estadistiquesPartida.entrySet()) {
            Integer[] key = entry.getKey();
            EstadistiquesPartida ed = entry.getValue();
            System.out.println("ANALITZANT partida : "+key[1]+" jugador: "+key[0]);
            if (key[1] == idPartida && key[0] == idJugador){
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
    public EstadistiquesPartida getEstadistiquesPartida(Integer idJugador, Integer idPartida) {
        for (HashMap.Entry<Integer[], EstadistiquesPartida> entry : estadistiquesPartida.entrySet()) {
            Integer[] key = entry.getKey();
            EstadistiquesPartida ed = entry.getValue();
            if (key[1] == idPartida && key[0] == idJugador){
                return ed;
            }
        }
        return null;
    }
}
