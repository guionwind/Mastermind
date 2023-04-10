public class Jugador {
    //atributs
    private final int id;
    private String username;
    private String password;
        //serveix per a generar la id
    private static int nombreJugadors = 0;

    //associacions
    private ArrayList<EstadistiquesPartida> statsPartides;
    
    /*creadora per defecte. 
    * crea un nou jugador amb ID incremental (el primer jugador tindra id = 1) 
    * despres assigna les credencials corresponents
    * 
    */
    public Jugador(String name, String pwd) {
        ++nombreJugadors;
        this.id = nombreJugadors;
        this.username = name;
        this.password = pwd;
    }
    
    //atributs derivats calculats
    public PartidesGuanyades() {
        int guanyades;
        for (EstadistiquesPartida p : this.statsPartides) {
            if (p.guanyada) ++guanyades;
        }
        return guanyades;
    }

    public PartidesJugades() {
        return statsPartides.length();
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