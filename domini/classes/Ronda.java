package domini.classes;

public class Ronda {
    private int id;
    private String combinacioIntentada;
    /*creadora per defecte. 
    * crea una nova Ronda amb ID incremental (partida s´encarrega de que les IDs siguien incrementals) 
    * despres assigna la combinacions intentades
    * 
    */
    public Ronda(int id, String combinacioIntentada) {
        this.id = id;
        this.combinacioIntentada = combinacioIntentada;
    }

    //metodes
    public int getId(){
        return this.id;
    }
    public String getCombinacioIntentada(){
        return this.combinacioIntentada;
    }
}