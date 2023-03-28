public class Jugador {
    //atributs
    private final int id;
    private String username;
    private String password;
    private int partidesGuanyades;
    private int partidesJugades;
    // no esta al diagrama
    public static int nombreJugadors = 0;

    //associacions
    private ConfiguracioGlobal config;
    private Partida[] partides;
    
    public Jugador(String name, String pwd) {
        this.id = generaID();
        this.username = name;
        this.password = pwd;
        this.partidesGuanyades = 0;
        this.partidesJugades = 0;
    }

    //genera la ID de cada nou jugador de manera incremental
    private int generaID() {
        ++nombreJugadors;
        return nombreJugadors;
    }
    
    //incrementar comptadors de partides
    public incrementarPartidesGuanyades() {
        ++partidesGuanyades;
    }

    public incrementarPartidesJugades() {
        ++partidesJugades;
    }

    //getters
    public getID() {
        return id;
    }

    public getUsername() {
        return username;
    }

    public getPassword() {
        return password;
    }

    public getPartidesJugades() {
        return partidesJugades;
    }

    public getPartidesGuanyades() {
        return partidesGuanyades;
    }

    /*aixo aniria al ctrlDomini¿?
    //return true if login is successful
    public boolean login(String name, String pwd) {
        boolean loginSuccess = false;
        if (name == username and pwd == password) {
            loginSuccess = true;
        }
        return loginSuccess;
    }
    */
}