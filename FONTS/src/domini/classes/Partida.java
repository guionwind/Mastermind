package domini.classes;

import domini.controllers.CtrlPartida;

import domini.classes.exceptions.*;

import java.util.HashMap;

public abstract class Partida {
    /**
     * Identificador de la partida
     */
    private final int id;

    /**
     * Solucio de la partida
     */
    private final Integer[] solutionCode;

    /**
     * Numero de partides utilitzat per saber la id de la partida creada
      */
    private static int nombrePartides = 0;

    /**
     * Rondes jugades de la partida
     * NO POSAR FINAL JA QUE ES MODIFICA FORA DE LA CREADORA
     */
    private HashMap<Integer, Ronda> rondes;

    /**
     * Estadistiques de la partida
     */
    private EstadistiquesPartida estadisticaPartida;

    /**
     * Configuracio de la partida
     */
    private final ConfiguracioPartida configuracioPartida;

    /**
     * Controlador de la partida
     */
    private final CtrlPartida ctrlPartida;

    /**
     * Constructora de la classe partida
     * La id es extreta apartir d'incrementar l'atribut nombre partides
     * @param configuracioPartida de la partida en questio
     * @param solutionCode de la partida
     * @param ctrlPartida Controlador de la partida
     */
    public Partida(ConfiguracioPartida configuracioPartida, Integer[] solutionCode, CtrlPartida ctrlPartida) {
        this.id = nombrePartides;
        nombrePartides++;
        this.configuracioPartida = configuracioPartida;
        this.solutionCode = solutionCode;
        this.ctrlPartida = ctrlPartida;
        rondes = new HashMap<Integer, Ronda>();
    }

    /**
     * Retorna la id de la partida
     *
     * @return l'identificador de la partida
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el numero de rondes jugades de la partida
     *
     * @return numero de rondes jugades
     */
    public int rondesJugades() {
        return rondes.size();
    }

    /**
     * Retorna la solucio de la partida
     *
     * @return la solucio de la partida
     */
    public Integer[] getSolutionCode() {
        return solutionCode;
    }

    /**
     * Crea una ronda de la partida
     *
     */
    public void creaRonda() {
        Integer id = Integer.valueOf(rondes.size());
        Ronda r = new Ronda(id);
        rondes.put(id, r);
    }

    /**
     * Associa una EstadisticaPartida amb Partida
     *
     * @param estadisticaPartida a associar amb Partida
     */

    public void setEstadisticaPartida(EstadistiquesPartida estadisticaPartida) {
        if (estadisticaPartida == null) {
            this.estadisticaPartida = estadisticaPartida;
        }
        //else throw excepcio propia nostre. A fer
    }

    /**
     * Metdode per afegir una combinacio intentada a la ronda de la partida
     * @param combinacioIntentada Combinacio intentada per l'ususari
     */
    public void intentarCombinacio(Integer[] combinacioIntentada) {
        rondes.get(rondes.size()-1).setCombinacioIntentada(combinacioIntentada.clone());
    }

    /**
     * Funcio per obtenir el codi de la maquina.
     * @param ultimIntent Ultim intent fet per la maquina
     * @param resposta Correcio donada per l'usuari
     * @return Retorna un integer ple de -1 ja que esta reimplementada a codeMaker
     */
    public Integer[] getCodiMaquina(Integer[] ultimIntent, String resposta) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        return new Integer[]{-1, -1, -1, -1};
    }

    /**
     * Retorna l'ultima combinacio intentada
     * @return Retorna l'ultima combinacio intentada
     */
    public Integer[] getUltimaCombIntentada() {
        Ronda r = rondes.get(rondes.size() - 2);
    
        return r.getCombinacioIntentada();
    }


    /**
     * Retorna la ultima correccio
     * @return retorna la ultima correccio
     */
    public String getUltimaCorreccio() {
        Ronda r = rondes.get(rondes.size() - 2);
        return r.getCorreccio();
    }

    /**
     * Afegeix la correcio de la ronda actual
     * @param correcioRonda correccio de la ronda
     */
    public void setCorrecioRonda(String correcioRonda) {
        Ronda r = rondes.get(rondes.size() - 1);

        r.setCorreccio(correcioRonda);
    }
}
