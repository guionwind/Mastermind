package domini.classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class estadisticasComparator implements Comparator<EstadistiquesPartida> {

    @Override
    public int compare(EstadistiquesPartida first, EstadistiquesPartida second) {
       return Integer.compare(first.getPuntuacio(), second.getPuntuacio());
    }

}

public class Ranquing {
    private int id;
    private ArrayList<EstadistiquesPartida> estadistiques = new ArrayList<EstadistiquesPartida>();

    //mètodes
    public int getId(){
        return this.id;
    }
    /*public void isOnRanquing(EstadistiquesPartida estadistica){
        int puntuacio = estadistica.getPuntuacio();
        for (int i = 0; i < 10; i++){
            EstadistiquesPartida estad = this.estadistiques.get(i);
            int tempPuntuacio = estad.getPuntuacio();
            if (puntuacio >= tempPuntuacio){
                this.estadistiques.add(i, estadistica);
                if (this.estadistiques.size() > 10){
                    this.estadistiques.remove(10);
                }
            }
        }
    }*/
    public void add(EstadistiquesPartida estadisitica){
        this.estadistiques.add(estadisitica);
        estadisticasComparator estatComparator = new estadisticasComparator();
        Collections.sort(this.estadistiques, estatComparator);
    }
    public ArrayList<EstadistiquesPartida> getTopN(int n){
        ArrayList<EstadistiquesPartida> tempEstadistiques = new ArrayList<EstadistiquesPartida>();
        for (int i = 0; i < n; i++){
            tempEstadistiques.add(this.estadistiques.get(i));
            //System.out.println(this.estadistiques.get(i).getJugador() + ": " + this.estadistiques.get(i).getPuntuacio());
        }
        return tempEstadistiques;
    }
}
