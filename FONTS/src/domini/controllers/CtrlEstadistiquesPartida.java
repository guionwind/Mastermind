package domini.controllers;

import domini.classes.EstadistiquesPartida;
import presentacio.custom.Pair;

import java.awt.font.ImageGraphicAttribute;
import java.util.HashMap;

/**
 * Controlador que gestiona les estadistiques de les partides jugades
 */
public class CtrlEstadistiquesPartida {
    
    private EstadistiquesPartida estadisticaPartidaActual;

    public CtrlEstadistiquesPartida() {
        this.estadisticaPartidaActual = null;
    }

    public void setEstadisticaActual(EstadistiquesPartida estadisticaPartidaActual) {
        this.estadisticaPartidaActual = estadisticaPartidaActual;
    }

    public EstadistiquesPartida getEstadisticaActual() {
        return this.estadisticaPartidaActual;
    }
}
