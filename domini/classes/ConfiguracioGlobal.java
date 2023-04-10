/*hi ha molta probabilitat de que
aquesta classe sigui inutil
de cara al domain model, ja que
aquesta configuracio serà de
la interfaç*/

public class ConfiguracioGlobal {
    //atributs
    private boolean modeDaltonic;

    //associacions
    private int id;
    private Jugador[] jugadorsAmbAquestaConfig;

    //
    public static int nombreConfigsGlobals = 0;

    public ConfiguracioGlobal() {
        id = generaID();
        modeDaltonic = false;
    }

    

    private int generaID() {
        ++nombreConfigsGlobals;
        return nombreConfigsGlobals;
    }
}