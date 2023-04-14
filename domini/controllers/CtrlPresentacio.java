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

    private void partidaCodebreaker() throws IOException{
        Boolean creada = false;
        Integer numeroIntents = 0;
        Integer longitudCombinacio = 0;
        while (!creada){
            System.out.println("Numero intents:");
            numeroIntents = Integer.parseInt(reader.readLine());
            System.out.println("Numero colors:");
            Integer numeroColors = Integer.parseInt(reader.readLine());
            System.out.println("Longitud combinacio:");
            longitudCombinacio = Integer.parseInt(reader.readLine());
            try {
                ctrlDomini.crearPartidaCodebreaker(numeroIntents, numeroColors, longitudCombinacio);
                creada = true;
            }
            catch (Exception exception){ //TODO Ficar la excepcio corresponent
                System.out.println("!!! Configuracio incorrecte !!!");
            }
        }
        codebrakerLoop(numeroIntents, longitudCombinacio);
    }

    private void codebrakerLoop(int numeroIntents, int longitudCombinacio) throws IOException{
        Integer intents = 0;
        Boolean guanyat = false;
        String respostaCorrecta = "";
        String respostaObtinguda = "";
        ArrayList<String> combinacions = new ArrayList<String>();
        ArrayList<String> respostes = new ArrayList<String>();
        for (int i = 0; i < longitudCombinacio; i++){
            respostaCorrecta += "B";
        }

        while (numeroIntents > intents && !guanyat){
            netejarConsola();
            System.out.println("Combinacio:");
            String combinacioIntentada = reader.readLine();
            combinacions.add(combinacioIntentada);

            char[] combinacioIntentadaChar = combinacioIntentada.toCharArray();
            Integer[] combinacioIntentadaInt = new Integer[longitudCombinacio];

            for (int i = 0; i < combinacioIntentada.length(); i++){
                combinacioIntentadaInt[i] = Character.getNumericValue(combinacioIntentadaChar[i]);
            }
            
            respostaObtinguda = ctrlDomini.jugarRondaCodebreaker(combinacioIntentadaInt);
            respostes.add(respostaObtinguda);

            System.out.println("////////////////////////////////////////////////////////");
            System.out.println("////////////////////// MASTERMIND //////////////////////");
            System.out.println("/////                                              /////");
            for (int i = 0; i < combinacions.size(); i++){
                System.out.println("///// " + combinacioIntentada + "     " + respostaObtinguda + "           /////");
            }
            System.out.println("////////////////////////////////////////////////////////");

            if (respostaCorrecta != respostaObtinguda) guanyat = true;
            intents++;
        }

        Integer puntuacio = ctrlDomini.partidaAcabada(guanyat);

        netejarConsola();
        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                                              /////");
        if (guanyat){
            System.out.println("/////              !! HAS GUANYAT !!               /////");
        }
        else{     
            System.out.println("/////               !! HAS PERDUT !!               /////");
        }
        System.out.println("/////                                              /////");
        System.out.println("/////                ESTADISTIQUES                 /////");
        System.out.println("///// Numero Intents: " + intents + "/" + numeroIntents + "                           /////");
        System.out.println("///// Puntuació: " + puntuacio + "                           /////");
        System.out.println("/////     Premi qualsevol tecla per continuar      /////");
        System.out.println("////////////////////////////////////////////////////////");
        reader.readLine();
        ranquing();
        menu();
    }

    private void jugar() throws IOException {
        netejarConsola();
        
        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                TIPUS PARTIDA                 /////");
        System.out.println("///// 0: CodeBreaker                               /////");
        System.out.println("///// 1: CodeMaker                                 /////");
        System.out.println("////////////////////////////////////////////////////////");

        String interaccioUsuari = reader.readLine();

        netejarConsola();
        
        switch (interaccioUsuari){
            case "0":
                partidaCodebreaker();
                break;
            case "1":
                break;
            default:
                jugar();
        }
        /*
         * Demanar tipus partida
         * Que em donin els parametres necessaris per fer la creacio de partida
         * Fer un while que mentre no sigui tot Black o no s'arribi al numero d'intents segueixi deixant crear rondes
         * Loop:
         *  CrearRonda
         *  jugarRondaCode----    <- Retorna un string amb la resposta
         *      ContadorIntents++
         *      Si es codebraker comprovem que la resposta sigui BBBB... o no
         *      Si es codemaker he de fer que lusuari em fiqui la resposta per consola i comprovar amb la resposta de la funcio jugarRondaCodemaker (si trolleja que torni a ficar la resposta, si la maquina fa tot BBBB... i lusuari corregeix be s'acaba loop)
         * Cridar funcio partidaAcabada(bool Guanyada)
         * Mostrar estadistiques   <- Crida a una funcio a part (Mostrar intents, si ha guanyat i puntuacio)
         * Mostrar ranquing   <- Crida a una funcio a part
         * Anar al menu
         */
    }

    private void ranquing() throws IOException{
        netejarConsola();
        ArrayList<Integer[]> top10 = ctrlDomini.getTop10();

        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                   RANQUING                   /////");
        for (int i = 0; i < top10.size(); i++){
            System.out.println("///// " + i+1 + ": " + top10.get(i)[0] + "( " + top10.get(i)[1] + ")" + "                 /////"); //TODO print el username en vez de la ID
        }
        System.out.println("/////                                              /////");
        System.out.println("/////     Premi qualsevol tecla per continuar      /////");
        System.out.println("////////////////////////////////////////////////////////");
        reader.readLine();
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
                jugar();
                break;
            case "1":
                ranquing();
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
