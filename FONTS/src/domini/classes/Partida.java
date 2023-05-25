package domini.classes;

import domini.controllers.CtrlPartida;

import java.util.ArrayList;
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
    private ConfiguracioPartida configuracioPartida;

    /**
     * Constructora de la classe partida
     * La id es extreta apartir d'incrementar l'atribut nombre partides
     * @param configuracioPartida Configuracio de la partida en questio
     * @param solutionCode Codi solucio de la partida
     * @param ctrlPartida Controlador de la partida
     */
    public Partida(int idPartida, ConfiguracioPartida configuracioPartida, Integer[] solutionCode) {
        this.id = idPartida;
        this.configuracioPartida = configuracioPartida;
        this.solutionCode = solutionCode;
        rondes = new HashMap<Integer, Ronda>();
    }

    /**
     * Constructora utilitzada per a reconstruir una partida en curs des del disc.
     * @param idPartida id de la partida
     * @param solutionCode codi solució de la partida
     * @param rondes conjunt de rondes jugades a la partida
     */
    public Partida(int idPartida, Integer[] solutionCode, HashMap<Integer, Ronda> rondes) {
        this.id = idPartida;
        this.solutionCode = solutionCode;
        this.rondes = rondes;
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
        if (this.estadisticaPartida == null) {
            this.estadisticaPartida = estadisticaPartida;
        }
        //else throw excepcio propia nostre. A fer
    }

    /**
     * Metdode per afegir una combinacio intentada a la ronda de la partida
     * @param combinacioIntentada Combinacio intentada per l'ususari
     */
    public void guardarCombinacio(Integer[] combinacioIntentada) {
        rondes.get(rondes.size()-1).setCombinacioIntentada(combinacioIntentada.clone());
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

    /**
     * Retorna el conjunt de rondes jugades a la partida.
     * @return Rondes jugades
     */
    public HashMap<Integer, Ronda> getRondes() {
        return rondes;
    }

    /**
     * Estableix la configuracio de la partida
     * @param configuracioPartida Configuracio a establir a la partida
     */
    public void setConfiguracioPartida(ConfiguracioPartida configuracioPartida) {
        this.configuracioPartida = configuracioPartida;
    }

    /**
     * Retorna la configuracio de la partida
     * @param configuracioPartida Configuracio a establir a la partida
     */
    public ConfiguracioPartida getConfiguracioPartida() {
        return configuracioPartida;
    }


    /**
     * Retorna el conjunt de codis que s'han intentat en les rondes
     * @return Els codisIntentats a totes les rondes jugades en la partida
     */
    public ArrayList<Integer[]> getCodisIntentats() {
        ArrayList<Integer[]> codisIntentats = new ArrayList<Integer[]>(rondes.size());

        for (Ronda ronda : rondes.values()) {
            codisIntentats.add(ronda.getCombinacioIntentada());
        }

        return codisIntentats;
    }

    /**
     * Retorna el conjunt de respostes (correccions) que s'han intentat en les rondes
     * @return Les correccions de totes les rondes jugades en la partida
     */
    public ArrayList<String> getRespostes() {
        ArrayList<String> respostes = new ArrayList<String>(rondes.size());

        for (Ronda ronda : rondes.values()) {
            respostes.add(ronda.getCorreccio());
        }

        return respostes;
    }
}
