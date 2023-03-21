package domini.classes;

public class Ranquing {
    private int id;
    private EstadistiquesPartida estadistiques[]; //TODO això hauria d'estar ordenat sempre, de millor a pitjor

    //mètodes
    public int getId(){
        return this.id;
    }
    public void isOnRanquing(EstadistiquesPartida estadistica){
        int puntuacio = estadistica.getPuntuacio();
        for (int i = 0; i < this.estadistiques.length; i++){
            EstadistiquesPartida estad = estadistiques[i];
            int tempPuntuacio = estad.getPuntuacio();
            if (puntuacio >= tempPuntuacio){
                //TODO afegir puntuacio a la llista
            }
        }
    }
}
