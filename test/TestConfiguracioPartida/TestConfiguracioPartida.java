package test.TestConfiguracioPartida;

import domini.classes.ConfiguracioPartida;
import domini.classes.ConfiguracioPartida.TipusPartida;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestConfiguracioPartida {

    private Integer idPartida;
    private TipusPartida tipusPartida;
    private Integer numeroIntents;
    private Integer numeroColors;
    private Integer longitudCombinacio;
    /**
     * Test de les constructores de ConfiguracioPartida
     * Estratègia caixa gris
     * Tambe comprova getters
     * @throws JugadorInvalid
     * @throws JugadorJaExisteix
     */

    @Test
    public void testConstructora() {
        System.out.println("testConstructora()");
        Integer i = 0;  //necessari per als assert
        ConfiguracioPartida cp1 = new ConfiguracioPartida();
        assertEquals(null, cp1.getPartida());
        assertEquals(null, cp1.getTipusPartida());
        assertEquals(i, cp1.getNumeroIntents());
        assertEquals(i, cp1.getNumeroColors());
        assertEquals(i, cp1.getLongitudCombinacio());
        System.out.println("testConstructora() pass");

    }

    @Test
    public void testConstructoraParams() {
        System.out.println("testConstructora CODEBREAKER amb tots els params");
        Integer i1 = 1, i2 = 2, i3 = 3;
        ConfiguracioPartida cp2 = new ConfiguracioPartida(TipusPartida.CODEBREAKER, i1, i2, i3);
        assertEquals(null, cp2.getPartida());
        assertEquals(TipusPartida.CODEBREAKER, cp2.getTipusPartida());
        assertEquals(i1, cp2.getNumeroIntents());
        assertEquals(i2, cp2.getNumeroColors());
        assertEquals(i3, cp2.getLongitudCombinacio());
        System.out.println("testConstructora CODEBREAKER amb tots els params pass");

        System.out.println("testConstructora CODEMAKER amb valors per sota del rang");
        i1 = -1; i2 = -3; i3 = -4;
        ConfiguracioPartida cp3 = new ConfiguracioPartida(TipusPartida.CODEMAKER, i1, i2, i3);
        assertEquals(null, cp3.getPartida());
        assertEquals(TipusPartida.CODEMAKER, cp3.getTipusPartida());
        assertEquals(1, cp3.getNumeroIntents());
        assertEquals(4, cp3.getNumeroColors());
        assertEquals(4, cp3.getLongitudCombinacio());
        System.out.println("testConstructora CODEMAKER amb valors per sota del rang pass");

        
        System.out.println("testConstructora CODEMAKER amb valors per sobre del rang");
        i1 = 21; i2 = 21; i3 = 21;
        ConfiguracioPartida cp4 = new ConfiguracioPartida(TipusPartida.CODEMAKER, i1, i2, i3);
        assertEquals(TipusPartida.CODEMAKER, cp4.getTipusPartida());
        assertEquals(null, cp4.getPartida());
        assertEquals(TipusPartida.CODEMAKER, cp4.getTipusPartida());
        assertEquals(20, cp4.getNumeroIntents());
        assertEquals(10, cp4.getNumeroColors());
        assertEquals(10, cp4.getLongitudCombinacio());
        System.out.println("testConstructora CODEMAKER amb valors per sobre del rang pass");
    }

    @Test
    public void testSetPartida() {

    }
    /**
     * Assigna la partida a la que pertany.
     *
     * @param   partida             Partida a la que pertany.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setPartida(Integer partida) {
        if (this.idPartida == null && partida != null) {
            this.idPartida = partida;
            return true;
        }
        return false;
    }

    /**
     * Assigna el tipus de partida.
     *
     * @param tipusPartida          Tipus de la partida
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setTipusPartida(TipusPartida tipusPartida) {
        if (tipusPartida != null) {
            this.tipusPartida = tipusPartida;
            return true;
        }
        return false;
    }

    /**
     * Assigna el número d'intents.
     *
     * @param numeroIntents         Número d'intents.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setNumeroIntents(Integer numeroIntents) {
        if (numeroIntents >= 1 && numeroIntents <= 20) {
            this.numeroIntents = numeroIntents;
            return true;
        }
        return false;
    }

    /**
     * Assigna número de colors.
     *
     * @param numeroColors          Número de colors.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setNumeroColors(Integer numeroColors) {
        if (numeroColors >= 4 && numeroColors <= 10) {
            this.numeroColors = numeroColors;
            return true;
        }
        return false;
    }

    /**
     * Assigna longitud codi.
     *
     * @param longitudCombinacio    Longitud del codi.
     * @return                      Cert si s'ha pogut assignar correctament,
     *                              Fals en cas contrari.
     */
    public boolean setLongitudCombinacio(Integer longitudCombinacio) {
        if (longitudCombinacio >= 4 && longitudCombinacio <= 10) {
            this.longitudCombinacio = longitudCombinacio;
            return true;
        }
        return false;
    }
}