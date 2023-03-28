package domini.classes;

public abstract class Partida {
    private int id;
    private int solutionCode;


    public Partida(int id, int solutionCode) {
        this.id = id;
        this.solutionCode = solutionCode;
    }

    public int getId() {
        return id;
    }

    public int getSolutionCode() {
        return solutionCode;
    }

}
