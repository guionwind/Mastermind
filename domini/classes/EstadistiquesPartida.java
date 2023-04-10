package domini.classes;
import java.util.Comparator;

public class EstadistiquesPartida {
    private Jugador jugador;
    private Partida partida;
    private int puntuacio;
    private int temps;
    private Boolean acabada;
    private Boolean guanyada;

    public Jugador getJugador(){
        return this.jugador;
    }
    public Partida getPartida(){
        return this.partida;
    }
    public int getPuntuacio(){
        return this.puntuacio;
    }
    public int getTemps(){
        return this.temps;
    }
    public Boolean getAcabada (){
        return this.acabada;
    }
    public Boolean getGuanyada (){
        return this.guanyada;
    }
}