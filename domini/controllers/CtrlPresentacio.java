package domini.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import domini.classes.exceptions.TipusPartidaIncorrecte;

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

    private void codemakerLoop(Integer numeroIntents, Integer longitudCombinacio, Integer[] combinacioCorrecteInt) throws IOException, InterruptedException, TipusPartidaIncorrecte{ 
        Integer intents = 0;
        Boolean guanyat = false;
        String correccio;
        ArrayList<String> combinacions = new ArrayList<String>();
        ArrayList<String> respostes = new ArrayList<String>();
        String respostaCorrecta = "";

        for (int i = 0; i < longitudCombinacio; i++){
            respostaCorrecta += "B";
        }
        
        Boolean benCorretgit = false;
        while (numeroIntents > intents && !guanyat){
            netejarConsola();
            ctrlDomini.crearRonda();
            String[] resposta = ctrlDomini.jugarRondaCodeMaker();
            combinacions.add(resposta[0]);

            System.out.println("////////////////////////////////////////////////////////");
            System.out.println("////////////////////// MASTERMIND //////////////////////");
            System.out.println("/////                                              /////");
            for (int i = 0; i < respostes.size(); i++){
                System.out.println("///// " + combinacions.get(i) + "     " + respostes.get(i) + "           /////");
            }
            System.out.println("///// " + resposta[0] + "                      /////");
            System.out.println("/////                                              /////");
            if (benCorretgit) System.out.println("/////                Ben corretgit!                /////");
            System.out.println("/////             Envia una correccio              /////");
            System.out.println("/////                                              /////");
            System.out.println("////////////////////////////////////////////////////////");

            correccio = reader.readLine();
            netejarConsola();
            if (correccio == resposta[1]){
                benCorretgit = true;
            }
            else{
                while (correccio.equals(resposta[1])){
                    System.out.println("////////////////////////////////////////////////////////");
                    System.out.println("////////////////////// MASTERMIND //////////////////////");
                    System.out.println("/////                                              /////");
                    for (int i = 0; i < respostes.size(); i++){
                        System.out.println("///// " + combinacions.get(i) + "     " + respostes.get(i) + "           /////");
                    }
                    System.out.println("///// " + resposta[0] + "                      /////");
                    System.out.println("/////                                              /////");
                    System.out.println("/////        Mal corretgit! Torna a provar         /////");
                    System.out.println("/////                 Torna a provar               /////");
                    System.out.println("/////                                              /////");
                    System.out.println("////////////////////////////////////////////////////////");

                    correccio = reader.readLine();
                    netejarConsola();
                }

                
            }
            respostes.add(correccio);
            if (respostaCorrecta == correccio) guanyat = true;

            intents++;
        }

        Integer puntuacio = ctrlDomini.partidaAcabadaCodemaker();

        acabarPartida(guanyat, intents, numeroIntents, puntuacio, true);
    }

    private void partidaCodemaker() throws IOException, InterruptedException, TipusPartidaIncorrecte{
        Boolean creada = false;
        Integer numeroIntents = 0;
        Integer longitudCombinacio = 0;
        Integer[] combinacioCorrecteIntParam = new Integer[]{};
        while (!creada){
            System.out.println("Numero intents:");
            numeroIntents = Integer.parseInt(reader.readLine());

            System.out.println("Numero colors:");
            Integer numeroColors = Integer.parseInt(reader.readLine());

            System.out.println("Longitud combinacio:");
            longitudCombinacio = Integer.parseInt(reader.readLine());

            System.out.println("Combinacio correcte:");
            String combinacioCorrecte = reader.readLine();

            char[] combinacioCorrecteChar = combinacioCorrecte.toCharArray();
            Integer[] combinacioCorrecteInt = new Integer[longitudCombinacio];

            for (int i = 0; i < combinacioCorrecte.length(); i++){
                combinacioCorrecteInt[i] = Character.getNumericValue(combinacioCorrecteChar[i]);
            }
            try {
                ctrlDomini.crearPartidaCodemaker(numeroIntents, numeroColors, longitudCombinacio, combinacioCorrecteInt);
                creada = true;
            }
            catch (Exception exception){ //TODO Ficar la excepcio corresponent
                System.out.println("!!! Configuracio incorrecte !!!");
                Thread.sleep(2000); //S'espera 2 segons
            }
        }
        codemakerLoop(numeroIntents, longitudCombinacio, combinacioCorrecteIntParam);
    }

    private void partidaCodebreaker() throws IOException, InterruptedException, TipusPartidaIncorrecte{
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
                Thread.sleep(2000); //S'espera 2 segons
            }
        }
        codebrakerLoop(numeroIntents, longitudCombinacio);
    }

    private void pista(int longitudCombinacio) throws IOException{
        String pista = ctrlDomini.demanarPista();
        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                                              /////");
        System.out.println("/////               La teva pista es:              /////");
        System.out.println("/////               " + pista + "              /////");
        System.out.println("/////                                              /////");
        System.out.println("/////     Premi qualsevol tecla per continuar      /////");
        System.out.println("////////////////////////////////////////////////////////");
        reader.readLine();
    }

    private void acabarPartida(Boolean guanyat, Integer intents, int numeroIntents, Integer puntuacio, Boolean codemaker) throws IOException, InterruptedException, TipusPartidaIncorrecte{
        netejarConsola();
        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                                              /////");
        if (guanyat){
            if (codemaker) System.out.println("/////              !! LA MAQUINA HA GUANYAT !!               /////");
            else System.out.println("/////              !! HAS GUANYAT !!               /////");
        }
        else{
            if (codemaker) System.out.println("/////            !! LA MAQUINA HA PERDUT !!               /////");
            else System.out.println("/////               !! HAS PERDUT !!               /////");
        }
        System.out.println("/////                                              /////");
        System.out.println("/////                ESTADISTIQUES                 /////");
        System.out.println("///// Numero Intents: " + intents + "/" + numeroIntents + "                       /////");
        System.out.println("///// Puntuació: " + puntuacio + "                           /////");
        System.out.println("/////     Premi qualsevol tecla per continuar      /////");
        System.out.println("////////////////////////////////////////////////////////");
        reader.readLine();
        if (!codemaker) ranquing();
        menu();
    }

    private void codebrakerLoop(int numeroIntents, int longitudCombinacio) throws IOException, InterruptedException, TipusPartidaIncorrecte{
        Integer intents = 0;
        Boolean guanyat = false;
        String respostaCorrecta = "";
        String respostaObtinguda = "";
        ArrayList<String> combinacions = new ArrayList<String>();
        ArrayList<String> respostes = new ArrayList<String>();
        for (int i = 0; i < longitudCombinacio; i++){
            respostaCorrecta += "B";
        }
        ctrlDomini.crearRonda();
        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                                              /////");
        System.out.println("/////                                              /////");
        System.out.println("/////            Envia una combinacio o            /////");
        System.out.println("/////    Envia H per obtenir una pista aleatoria   /////");
        System.out.println("////////////////////////////////////////////////////////");
        while (numeroIntents > intents && !guanyat){
            
            String combinacioIntentada = reader.readLine();
            netejarConsola();
            
            if (combinacioIntentada.equals("H")){
                pista(longitudCombinacio);
            }
            else{
                if (intents != 0) ctrlDomini.crearRonda();
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
                    System.out.println("///// " + combinacions.get(i) + "     " + respostes.get(i) + "           /////");
                }
                System.out.println("/////                                              /////");
                System.out.println("/////            Envia una combinacio o            /////");
                System.out.println("/////         Envia H per obtenir una pista        /////");
                System.out.println("////////////////////////////////////////////////////////");

                if (respostaCorrecta == respostaObtinguda) guanyat = true;
                intents++;
            }
        }

        Integer puntuacio = ctrlDomini.partidaAcabada(guanyat);

        acabarPartida(guanyat, intents, numeroIntents, puntuacio, false);
    }

    private void jugar() throws IOException, InterruptedException, TipusPartidaIncorrecte {
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
                partidaCodemaker();
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
        for (int i = 0; i < top10.size(); i++){ //FIXME
            System.out.println("///// " + i+1 + ": " + top10.get(i)[0] + "( " + top10.get(i)[1] + ")" + "                 /////"); //TODO print el username en vez de la ID
        }
        System.out.println("/////                                              /////");
        System.out.println("/////     Premi qualsevol tecla per continuar      /////");
        System.out.println("////////////////////////////////////////////////////////");
        reader.readLine();
    }

    private void login() throws IOException, InterruptedException, TipusPartidaIncorrecte {
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
                    if (!contrasenya.equals(confirmaContrasenya)){
                        System.out.println("!!! Les contrasenyes no coincideixen !!!");
                        Thread.sleep(2000); //S'espera 2 segons
                        netejarConsola();
                    }
                    else {
                        try{
                            ctrlDomini.crearJugador(usuari, contrasenya);
                            registered = true;
                        }
                        catch(Exception JugadorJaExisteix){
                            System.out.println("!!! Usuari ja existeix !!!");
                            Thread.sleep(2000); //S'espera 2 segons
                        }
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
                    try{
                        loged = ctrlDomini.loginAuthentication(usuari, contrasenya);
                        if (!loged) System.out.println("!!! Usuari o contrasenya incorrectes !!!");
                    }
                    catch (Exception JugadorNoExisteix){
                        System.out.println("!!! Usuari no existeix !!!");
                    }

                    Thread.sleep(2000); //S'espera 2 segons
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

    private void menu () throws IOException, InterruptedException, TipusPartidaIncorrecte {
        netejarConsola();

        System.out.println("////////////////////////////////////////////////////////");
        System.out.println("////////////////////// MASTERMIND //////////////////////");
        System.out.println("/////                     MENU                     /////");
        System.out.println("/////  Benvingut " + ctrlDomini.getUsername() + "                      /////");
        System.out.println("///// 0: Jugar                                     /////");
        System.out.println("///// 1: Rànquing                                  /////");
        System.out.println("///// 2: Tancar Sessio                             /////");
        System.out.println("///// 3: Sortir                                    /////");
        System.out.println("////////////////////////////////////////////////////////");

        String interaccioUsuari = reader.readLine();
        
        switch (interaccioUsuari){
            case "0":
                jugar();
                break;
            case "1":
                ranquing();
                menu();
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

    public static void main (String [] args) throws IOException, InterruptedException, TipusPartidaIncorrecte{
        CtrlPresentacio ctrlPresentacio = new CtrlPresentacio();
        ctrlPresentacio.ctrlDomini.crearRanquing();
        
        //Mostrem la pantalla de login
        ctrlPresentacio.login();
    }
}
