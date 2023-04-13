package domini.controllers;
import domini.controllers.CtrlDomini;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CtrlPresentacio {
    // ATRIBUTS

    /**
     * Controlador domini
     */
    private CtrlDomini ctrlDomini;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Constructora del controlador presentació
     */
    public CtrlPresentacio() {
        ctrlDomini = new CtrlDomini();
    }

    private void netejarConsola(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private void jugar() throws IOException {

    }

    private void login() throws IOException {
        netejarConsola();
        
        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                    LOGIN                     /////");
        System.out.println("///// 0: Registrar-se                              /////");
        System.out.println("///// 1: Login                                     /////");
        System.out.println("////////////////////////////////////////////////////////");

        String interaccioUsuari = reader.readLine();

        netejarConsola();
        
        switch (interaccioUsuari){
            case "0":
                Boolean registered = false;
                while (!registered){
                    System.out.println("Usuari:");
                    String usuari = reader.readLine();
                    System.out.println("Contrasenya:");
                    String contrasenya = reader.readLine();
                    System.out.println("Confirma contrasenya:");
                    String confirmaContrasenya = reader.readLine();
                    if (contrasenya != confirmaContrasenya){
                        System.out.println("!!! Les contrasenyes no coincideixen !!!");
                        netejarConsola();
                    }
                    else {
                        ctrlDomini.crearJugador(usuari, contrasenya); //TODO Falta ficar el try catch per veure que no existeixi el jugador
                    }
                }
                //Mostrem el menú principal
                menu();
                break;
            case "1":
                Boolean loged = false;
                while (!loged){
                    System.out.println("Usuari:");
                    String usuari = reader.readLine();
                    System.out.println("Contrasenya:");
                    String contrasenya = reader.readLine();
                    loged = ctrlDomini.loginAuthentication(usuari, contrasenya); // try catch?? TODO
                    netejarConsola();
                }
                //Mostrem el menú principal
                menu();
                break;
            case "2":
                System.out.println("////////////////////////////////////////////////////////");
                System.out.println("////////////////////// MASTERMIND //////////////////////");
                System.out.println("/////                 Estas segur?                 /////");
                System.out.println("///// 0: Sí                                        /////");
                System.out.println("///// 1: No                                        /////");
                System.out.println("////////////////////////////////////////////////////////");

                interaccioUsuari = reader.readLine();

                if (interaccioUsuari == "0") break;
            default:
                login();
        }

        /*if (interaccioUsuari == "0"){
            
        }
        else if (interaccioUsuari == "1"){
            
        }*/
    }

    private void menu () throws IOException {
        netejarConsola();

        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                     MENU                     /////");
        System.out.println("/////  Benvingut " + ctrlDomini.getUsername() + "  /////");
        System.out.println("///// 0: Jugar                                     /////");
        System.out.println("///// 1: Rànquing                                  /////");
        System.out.println("///// 2: Canviar usuari                            /////");
        System.out.println("///// 3: Sortir                                    /////");
        System.out.println("////////////////////////////////////////////////////////");

        String interaccioUsuari = reader.readLine();
        
        switch (interaccioUsuari){
            case "0":
                break;
            case "1":
                netejarConsola();
                ArrayList<Integer[]> top10 = ctrlDomini.getTop10();

                System.out.println("////////////////////////////////////////////////////////");
                System.out.println("////////////////////// MASTERMIND //////////////////////");
                System.out.println("/////                   RANQUING                   /////");
                for (int i = 0; i < top10.size(); i++){
                    System.out.println("///// " + i+1 + ": " + top10.get(i)[0] + "( " + top10.get(i)[1] + ")" + "                 /////"); //TODO print el username en vez de la ID
                }
                System.out.println("////////////////////////////////////////////////////////");
                break;
            case "2":
                ctrlDomini.logoff();
                login();
                break;
            case "3":
                System.out.println("////////////////////////////////////////////////////////");
                System.out.println("////////////////////// MASTERMIND //////////////////////");
                System.out.println("/////                 Estas segur?                 /////");
                System.out.println("///// 0: Sí                                        /////");
                System.out.println("///// 1: No                                        /////");
                System.out.println("////////////////////////////////////////////////////////");

                interaccioUsuari = reader.readLine();

                if (interaccioUsuari == "0") break;
            default:
                menu();
        }
    }

    public void main (String [] args) throws IOException{
        //CtrlDomini ctrlDomini = new CtrlDomini();
        ctrlDomini.crearRanquing();
        
        //Mostrem la pantalla de login
        login();

        


    }
}
