package persistencia.controllers;

import java.io.IOException;

import domini.classes.exceptions.*;
import persistencia.classes.*;

public class CtrlPersistencia {
    private final GestorJugador gestorJugador;
    private final GestorPartida gestorPartida;
    private final GestorConfiguracioPartida gestorConfiguracioPartida;
    private final GestorEstadistiquesPartida gestorEstadistiquesPartida;
    private final GestorRanquing gestorRanquing;
    private final GestorFiveGuess gestorFiveGuess;
    private final GestorGenetic gestorGenetic;

    public CtrlPersistencia() {
        gestorJugador = new GestorJugador();
        gestorPartida = new GestorPartida();
        gestorConfiguracioPartida = new GestorConfiguracioPartida();
        gestorEstadistiquesPartida = new GestorEstadistiquesPartida();
        gestorRanquing = new GestorRanquing();
        gestorFiveGuess = new GestorFiveGuess();
        gestorGenetic = new GestorGenetic();
    }

    public String[] obtenir(TipusClasse tipusClasse, String identificador1, String identificador2) throws IOException, InstanciaNoExisteix {
        switch (tipusClasse) {
            case CONFIGURACIOPARTIDA:
                return gestorConfiguracioPartida.obtenir(identificador1);
            case ESTADISTIQUESPARTIDA:
                return gestorEstadistiquesPartida.obtenir(identificador1, identificador2);
            case FIVEGUESS:
                return gestorFiveGuess.obtenir(identificador1);
            case GENETIC:
                return gestorGenetic.obtenir(identificador1);
            case JUGADOR:
                return gestorJugador.obtenir(identificador1);
            case PARTIDA:
                return gestorPartida.obtenir(identificador1);
            case RANQUING:
                return gestorRanquing.obtenir(identificador1);
            default:
                return null;
        }
    }

    public void afegir(TipusClasse tipusClasse, String[] instancia) throws IOException, InstanciaJaExisteix {
        switch (tipusClasse) {
            case CONFIGURACIOPARTIDA:
                gestorConfiguracioPartida.afegir(instancia);
            case ESTADISTIQUESPARTIDA:
                gestorEstadistiquesPartida.afegir(instancia);
            case FIVEGUESS:
                gestorFiveGuess.afegir(instancia);
            case GENETIC:
                gestorGenetic.afegir(instancia);
            case JUGADOR:
                gestorJugador.afegir(instancia);
            case PARTIDA:
                gestorPartida.afegir(instancia);
            case RANQUING:
                gestorRanquing.afegir(instancia);
            default:
                break;
        }        
    }

    public void actualitzar(TipusClasse tipusClasse, String[] instancia) throws IOException, InstanciaNoExisteix {
        switch (tipusClasse) {
            case CONFIGURACIOPARTIDA:
                gestorConfiguracioPartida.actualitzar(instancia);
            case ESTADISTIQUESPARTIDA:
                gestorEstadistiquesPartida.actualitzar(instancia);
            case FIVEGUESS:
                gestorFiveGuess.actualitzar(instancia);
            case GENETIC:
                gestorGenetic.actualitzar(instancia);
            case JUGADOR:
                gestorJugador.actualitzar(instancia);
            case PARTIDA:
                gestorPartida.actualitzar(instancia);
            case RANQUING:
                gestorRanquing.actualitzar(instancia);
            default:
                break;
        }
    }
    
    public void eliminar(TipusClasse tipusClasse, String identificador1, String identificador2) throws IOException, InstanciaNoExisteix {
        switch (tipusClasse) {
            case CONFIGURACIOPARTIDA:
                gestorConfiguracioPartida.eliminar(identificador1);
            case ESTADISTIQUESPARTIDA:
                gestorEstadistiquesPartida.eliminar(identificador1, identificador2);
            case FIVEGUESS:
                gestorFiveGuess.eliminar(identificador1);
            case GENETIC:
                gestorGenetic.eliminar(identificador1);
            case JUGADOR:
                gestorJugador.eliminar(identificador1);
            case PARTIDA:
                gestorPartida.eliminar(identificador1);
            case RANQUING:
                gestorRanquing.eliminar(identificador1);
            default:
                break;
        }        
    }

    public boolean existeix(TipusClasse tipusClasse, String identificador1, String identificador2) throws IOException {
        switch (tipusClasse) {
            case CONFIGURACIOPARTIDA:
                return gestorConfiguracioPartida.existeix(identificador1);
            case ESTADISTIQUESPARTIDA:
                return gestorEstadistiquesPartida.existeix(identificador1, identificador2);
            case FIVEGUESS:
                return gestorFiveGuess.existeix(identificador1);
            case GENETIC:
                return gestorGenetic.existeix(identificador1);
            case JUGADOR:
                return gestorJugador.existeix(identificador1);
            case PARTIDA:
                return gestorPartida.existeix(identificador1);
            case RANQUING:
                return gestorRanquing.existeix(identificador1);
            default:
                return false;
        }
    }

    public Integer obtenirNumeroTotal(TipusClasse tipusClasse) throws IOException {
        switch (tipusClasse) {
            case CONFIGURACIOPARTIDA:
                return gestorConfiguracioPartida.obtenirNumeroTotal();
            case ESTADISTIQUESPARTIDA:
                return gestorEstadistiquesPartida.obtenirNumeroTotal();
            case FIVEGUESS:
                return gestorFiveGuess.obtenirNumeroTotal();
            case GENETIC:
                return gestorGenetic.obtenirNumeroTotal();
            case JUGADOR:
                return gestorJugador.obtenirNumeroTotal();
            case PARTIDA:
                return gestorPartida.obtenirNumeroTotal();
            case RANQUING:
                return gestorRanquing.obtenirNumeroTotal();
            default:
                return null;
        }
    }
}
