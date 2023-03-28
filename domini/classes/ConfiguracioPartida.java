package domini.classes;
import domini.Partida;

public class ConfiguracioPartida {
    public enum TipusPartida {
        Codebreaker,
        Codemaker
    }

    private artida partida;
    private TipusPartida tipusPartida;
    private int numeroIntents;
    private int numeroColors;
    private int longitudCombinacio;

    public ConfiguracioPartida() {
        this.partida = null;
        this.tipusPartida = null;
        this.numeroIntents = 0;
        this.numeroColors = 0;
        this.longitudCombinacio = 0;
    }

    public ConfiguracioPartida(Partida partida, TipusPartida tipusPartida, int numeroIntents, int numeroColors, int numeroIntents, int longitudCombinacio) {
        this.partida = partida;
        this.tipusPartida = tipusPartida;
        this.numeroIntents = numeroIntents;
        this.numeroColors = numeroColors;
        this.longitudCombinacio = longitudCombinacio;
    }

    public Partida getPartida() {
        return Partida;
    }

    public TipusPartida getTipusPartida() {
        return tipusPartida;
    }

    public int getNumeroIntents() {
        return numeroIntents;
    }

    public int getNumeroColors() {
        return numeroColors;
    }

    public int getLongitudCombinacio() {
        return longitudCombinacio;
    }

    public boolean setPartida(Partida partida) {
        if (partida != null) {
            this.partida = partida;
            return true;
        }
        return false;
    }

    public boolean setTipusPartida(TipusPartida tipusPartida) {
        if (tipusPartida != null) {
            this.tipusPartida = tipusPartida;
            return true;
        }
        return false;
    }

    public boolean setNumeroIntents(int numeroIntents) {
        if (numeroIntents >= 1 && numeroIntents <= 20) { // CUANTAS RONDAS DE LIMITE ??
            this.numeroIntents = numeroIntents;
            return true;
        }
        return false;
    }

    public boolean setNumeroColors(int numeroColors) {
        if (numeroColors >= 2 && numeroColors <= 10) { // CUANTOS COLORES DE LIMITE ??
            this.numeroColors = numeroColors;
            return true;
        }
        return false;
    }

    public boolean setLongitudCombinacio(int longitudCombinacio) {
        if (longitudCombinacio <= 2 && longitudCombinacio <= 10) { // CUANTA LONGITUD DE LIMITE ??
            this.longitudCombinacio = longitudCombinacio;
            return true;
        }
        return false;
    }
}