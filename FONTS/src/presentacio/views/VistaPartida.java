package presentacio.views;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domini.classes.exceptions.*;
import presentacio.controllers.CtrlPresentacio;
import presentacio.custom.RoundButton;

public class VistaPartida extends JFrame {
    private JPanel contentPane;
    private JButton bSortir;
    private JPanel pCombinacions;
    private JPanel pColors;
    private JButton bPista;
    private JPanel pCorreccio;
    private JButton bCorretgir;
    private JLabel lInvalidCombinacio;
    private int longitud = 4;
    private int colors = 6;
    private int intents = 5;
    private int current_color = 0;
    private String tipus_partida = "CODEBREAKER";
    private int current_round = 0;
    private Integer[] combinacio_intentada = new Integer[]{};
    private ArrayList<Integer> combinacio = new ArrayList<>();
    private ArrayList<Color> colorList = new ArrayList<>();
    private ArrayList<ArrayList<RoundButton>> buttonMatrixIntentat = new ArrayList<>();
    private ArrayList<ArrayList<RoundButton>> buttonMatrixCorreccio = new ArrayList<>();
    private ArrayList<JPanel> panelList = new ArrayList<>();
    private ArrayList<JPanel> panelListCorreccio = new ArrayList<>();


    public VistaPartida(Point location, int state, int init_intents, int init_colors, int init_longitud, Integer[] solution_code, String init_tipusPartida, Integer[][] combinacionsIntentades, String[] correccions) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        setLocation(location);

        setUndecorated(false);
        setContentPane(contentPane);
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(state);

        getRootPane().setDefaultButton(bSortir);

        intents = init_intents;
        colors = init_colors;
        longitud = init_longitud;
        if (combinacionsIntentades != null) {
            combinacio_intentada = combinacionsIntentades[combinacionsIntentades.length - 1];
        }
        tipus_partida = init_tipusPartida;
        combinacio.addAll(Arrays.asList(solution_code));

        System.out.println("Starting game with:");
        System.out.println(intents + " intents");
        System.out.println(colors + " colors");
        System.out.println(longitud + " longitud");
        System.out.println(tipus_partida + " tipus partida");
        if (tipus_partida.equals("CODEMAKER")) {
            bPista.setVisible(false);
            System.out.println("Solution code: ");

            for (int i = 0; i < combinacio.size(); i++) {
                System.out.print(solution_code[i]);
            }
            System.out.println();
        }

        colorList.add(Color.GRAY);
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.ORANGE);
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.PINK);
        colorList.add(Color.BLACK);
        colorList.add(Color.WHITE);

        if (combinacionsIntentades == null && correccions == null) { // Creen nova partida codebreaker i codemaker
            if (tipus_partida.equals("CODEMAKER")) {
                combinacio_intentada = CtrlPresentacio.jugarRondaCodemaker();
                System.out.println("Primera comb: " + combinacio_intentada);
                for (int i = 0; i < combinacio_intentada.length; ++i) {
                    System.out.print(combinacio_intentada[i]);
                }
                System.out.println();
            }
        }

        initButtonsPanel();

        //Si carreguem la partida
        if (combinacionsIntentades != null && correccions != null) { // Carreguem partida codebreaker i codemaker

            System.out.println();
            System.out.println("Vista partida contructora ");
            for (int i = 0; i < combinacionsIntentades.length; ++i) {
                for (int j = 0; j < combinacionsIntentades[i].length; ++j) {
                    System.out.print(combinacionsIntentades[i][j]);
                }
                System.out.println();
            }
            System.out.println("Vista partida contructora ");
            for (int i = 0; i < correccions.length; ++i) {
                System.out.println(correccions[i]);
            }
            System.out.println(combinacionsIntentades);
            System.out.println(correccions);


            System.out.println("Vista partida bucle inicial carregar");
            for (int i = 0; i < combinacionsIntentades.length; ++i) {
                Integer[] combinacioIntentada = combinacionsIntentades[i];
                String correccio = correccions[i];
                System.out.println("Vista partida bucle inicial carregar combinacioIntentada ");
                for (int j = 0; j < combinacionsIntentades.length; ++j) {
                    System.out.println(combinacionsIntentades[i]);
                }
                System.out.println("Vista partida bucle inicial carregar correcio ");
                System.out.println(correccio);

                for (int j = 0; j < combinacioIntentada.length; ++j) {
                    if (tipus_partida.equals("CODEMAKER")) {
                        //Deshabilitem els botons de correccio ja pintats per l'usuari
                        RoundButton buttonCorreccio = buttonMatrixCorreccio.get(intents - current_round - 1).get(j);
                        buttonCorreccio.setEnabled(false);

                        //Pintem la correccio
                        int color;
                        System.out.println(correccio);
                        System.out.println(correccions + "correccions");
                        if (correccio.charAt(j) == 'B') {
                            color = 9;
                        } else if (correccio.charAt(j) == 'W') {
                            color = 10;
                        } else {
                            color = 0;
                        }
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).setCurrentColor(colorList.get(color), color);
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).setEnabled(false);
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).revalidate();
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).repaint();
                    } else {
                        // //Deshabilitem els botons de correccio ja pintats per l'usuari
                        // RoundButton buttonCorreccio = buttonMatrixCorreccio.get(intents - current_round - 1).get(j);
                        // buttonCorreccio.setEnabled(false);

                        //Pintem la correccio
                        int color;
                        System.out.println(correccio);
                        System.out.println(correccions + "correccions");
                        if (correccio.charAt(j) == 'B') {
                            color = 9;
                        } else if (correccio.charAt(j) == 'W') {
                            color = 10;
                        } else {
                            color = 0;
                        }
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).setCurrentColor(colorList.get(color), color);
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).setEnabled(false);
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).revalidate();
                        buttonMatrixCorreccio.get(intents - current_round - 1).get(j).repaint();
                    }

                    // //Deshabilitem els botons de correccio ja pintats per l'usuari
                    // RoundButton buttonIntentat = buttonMatrixIntentat.get(intents - current_round - 1).get(j);
                    // buttonIntentat.setEnabled(false);

                    //Pinta la combinacio triada per l'usuari
                    buttonMatrixIntentat.get(intents - current_round - 1).get(j).setCurrentColor(colorList.get(combinacioIntentada[j]), combinacioIntentada[j]);
                    buttonMatrixIntentat.get(intents - current_round - 1).get(j).setEnabled(false);
                    buttonMatrixIntentat.get(intents - current_round - 1).get(j).revalidate();
                    buttonMatrixIntentat.get(intents - current_round - 1).get(j).repaint();
                }
                if (tipus_partida.equals("CODEBREAKER"))
                    current_round++;
                else if (i < combinacionsIntentades.length - 1)
                    current_round++;

            }

            //Habilita els seguents botons de intent
            if (tipus_partida.equals("CODEBREAKER")) {
                for (int j = 0; j < combinacionsIntentades[0].length; ++j) {
                    buttonMatrixIntentat.get(intents - current_round - 1).get(j).setEnabled(true);
                }
            } else {
                for (int j = 0; j < combinacionsIntentades[0].length; ++j) {
                    buttonMatrixCorreccio.get(intents - current_round - 1).get(j).setEnabled(true);
                }
            }
        }

        bPista.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarPista();

                super.mousePressed(e);
            }
        });

        bCorretgir.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    onCorregir();
                } catch (LongitudCombinacioIncorrecte | ValorsRespostaIncorrectes | IOException |
                         LongitudRespostaIncorrecte | NumeroColorsIncorrecte ex) {
                    throw new RuntimeException(ex);
                }
                super.mousePressed(e);
            }
        });

        bSortir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onSortir();
                } catch (IOException | InstanciaNoExisteix | InstanciaJaExisteix ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    onSortir();
                } catch (IOException | InstanciaNoExisteix | InstanciaJaExisteix ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onSortir();
                } catch (IOException | InstanciaNoExisteix | InstanciaJaExisteix ex) {
                    throw new RuntimeException(ex);
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void mostrarPista() {
        String pista = CtrlPresentacio.demanarPista();
        String final_pista = "";
        for (int i = 0; i < pista.length(); i++) {
            if (pista.charAt(i) != '?') {
                String temp_color;
                switch (i) {
                    case 0:
                        temp_color = "VERMELL";
                        break;
                    case 1:
                        temp_color = "VERD";
                        break;
                    case 2:
                        temp_color = "BLAU";
                        break;
                    case 3:
                        temp_color = "GROC";
                        break;
                    case 4:
                        temp_color = "TARONJA";
                        break;
                    case 5:
                        temp_color = "CYAN";
                        break;
                    case 6:
                        temp_color = "MAGENTA";
                        break;
                    case 7:
                        temp_color = "ROSA";
                        break;
                    default:
                        temp_color = "INVALID";
                        break;
                }

                final_pista += " " + temp_color;
            } else {
                final_pista += " ?";
            }
        }
        JOptionPane.showMessageDialog(pCombinacions, "La teva pista es: " + final_pista, "Pista", JOptionPane.PLAIN_MESSAGE);

    }

    private void initButtonsPanel() {
        pCombinacions.setLayout(new BoxLayout(pCombinacions, BoxLayout.Y_AXIS));
        pCorreccio.setLayout(new BoxLayout(pCorreccio, BoxLayout.Y_AXIS));

        // Código para agregar los botones al panel pCombinacio
        for (int i = 0; i < intents; i++) {
            JPanel panel = new JPanel();
            JPanel panelCorreccio = new JPanel();

            panel.setLayout(new FlowLayout());
            panelCorreccio.setLayout(new GridLayout(2, 0));

            ArrayList<RoundButton> buttonList = new ArrayList<>();
            ArrayList<RoundButton> buttonListCorreccio = new ArrayList<>();
            for (int j = 0; j < longitud; j++) {
                RoundButton button = new RoundButton("");
                RoundButton buttonCorreccio = new RoundButton("");
                button.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (button.isEnabled()) {
                            button.setCurrentColor(colorList.get(current_color), current_color);
                        }
                        super.mousePressed(e);
                    }
                });
                buttonCorreccio.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (buttonCorreccio.isEnabled()) {
                            buttonCorreccio.setCurrentColor(colorList.get(current_color), current_color);

//                            buttonCorreccio.revalidate();
//                            buttonCorreccio.repaint();
                        }
                        super.mousePressed(e);
                    }
                });
                System.out.println(tipus_partida);
                if (tipus_partida.equals("CODEBREAKER")) {
                    buttonCorreccio.setEnabled(false);
                    if (i != intents - 1) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                } else {
                    button.setEnabled(false);
                    if (i != intents - 1) {
                        buttonCorreccio.setEnabled(false);
                    } else {
                        button.setCurrentColor(colorList.get(combinacio_intentada[j]), combinacio_intentada[j]);
                        buttonCorreccio.setEnabled(true);
                    }
                }

                buttonList.add(button);
                buttonListCorreccio.add(buttonCorreccio);
                panel.add(button);
                panelCorreccio.add(buttonCorreccio);
            }
            buttonMatrixIntentat.add(buttonList);
            buttonMatrixCorreccio.add(buttonListCorreccio);

            panelList.add(panel);
            panelListCorreccio.add(panelCorreccio);
            pCombinacions.add(panel);
            pCorreccio.add(panelCorreccio);
        }

        pCombinacions.revalidate();
        pCombinacions.repaint();

        pCorreccio.revalidate();
        pCorreccio.repaint();

        //pColors.setLayout(new BoxLayout(pColors, BoxLayout.Y_AXIS));
        pColors.setLayout(new FlowLayout());

        int color_start = 1;
        int color_end = colors + 1;
        //Codigo para mostrar la paleta de colores
        if (tipus_partida.equals("CODEMAKER")) {
            color_start = 0;
            color_end = 3;
        }

        for (int i = color_start; i < color_end; i++) {
            RoundButton button = new RoundButton("");
            if (tipus_partida.equals("CODEMAKER")) {
                if (i == 1) {
                    button.setCurrentColor(Color.BLACK, 9);
                } else if (i == 2) {
                    button.setCurrentColor(Color.WHITE, 10);
                } else if (i == 0) {
                    button.setCurrentColor(Color.GRAY, 0);
                }
            } else {
                button.setCurrentColor(colorList.get(i), i);
            }

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    current_color = button.getCurrentColor();
                    System.out.println(current_color);
                    super.mousePressed(e);
                }
            });
            pColors.add(button);
        }
    }

    private void onCorregir() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes, IOException {
        Boolean guanyat = true;
        lInvalidCombinacio.setVisible(false);
        int longitudCombinacioIntentat = buttonMatrixIntentat.get(intents - current_round - 1).size();

        if (tipus_partida.equals("CODEBREAKER")) {

            Integer[] combinacioUsuari = getCombinacioIntentada();

            if (combinacioUsuari == null) {
                lInvalidCombinacio.setText("Siusplau, no deixis cap posició sense color");
                lInvalidCombinacio.setVisible(true);
            } else { //Si la combinacio es valida
                String correccioColors = CtrlPresentacio.jugarRondaCodebreaker(combinacioUsuari);
                Integer[] correccioNumeros = traduirCorrecioANumero(correccioColors);

                System.out.println(correccioColors);

                //Deshabilitem els botons ja pintats per l'ususari (prk no els pugui tornar a pintar) i habilitem els seguents.
                for (int i = 0; i < longitudCombinacioIntentat; ++i) {
                    RoundButton button = buttonMatrixIntentat.get(intents - current_round - 1).get(i);
                    button.setEnabled(false);
                    if (current_round < intents - 1)
                        buttonMatrixIntentat.get(intents - (current_round + 1) - 1).get(i).setEnabled(true);

                    buttonMatrixCorreccio.get(intents - current_round - 1).get(i).setCurrentColor(colorList.get(correccioNumeros[i]), correccioNumeros[i]);
                    buttonMatrixCorreccio.get(intents - current_round - 1).get(i).revalidate();
                    buttonMatrixCorreccio.get(intents - current_round - 1).get(i).repaint();
                }

                //Comprovem si la correccio son tot B, es a dir, que l'usuari ha endivinat el codi.
                for (int i = 0; i < longitudCombinacioIntentat && guanyat; ++i) {
                    if (correccioColors.charAt(i) != 'B') guanyat = false;
                }

                // Si ha guanyat
                if (guanyat) {
                    CtrlPresentacio.partidaAcabada(true);
                    CtrlPresentacio.vistaEstadistiquesPartida(getLocation(), getExtendedState(), "Has Guanyat!");
                    dispose();
                }
                //Si no ha guanyat i es la ultima ronda
                else if (current_round >= intents - 1) {
                    JOptionPane.showMessageDialog(pCombinacions, "T'has quedat sense intents!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    CtrlPresentacio.partidaAcabada(false);
                    CtrlPresentacio.vistaEstadistiquesPartida(getLocation(), getExtendedState(), "Has Perdut!");
                    dispose();
                } else { //Si no ha guanyat i no es la ultima ronda
                    current_round += 1;
                }
            }

        } else { // Codemaker
            Integer[] correccioUsuariNumeros = getCorreccioUsuari();
            System.out.println("correccioUsuariNumeros" + correccioUsuariNumeros.toString());
            String correccioUsuariString = traduirCorrecioAString(correccioUsuariNumeros);
            int longitudCorreccio = buttonMatrixCorreccio.get(intents - current_round - 1).size();
            Boolean is_well_corrected = CtrlPresentacio.setCorreccioRonda(correccioUsuariString);
            System.out.println(correccioUsuariString);
            System.out.println(is_well_corrected);
            if (!is_well_corrected)
                JOptionPane.showMessageDialog(pCombinacions, "La correccio introduida no es correcte! Torna a provar", "Correccio Incorrecte", JOptionPane.INFORMATION_MESSAGE);
            else {
                Integer[] combinacioMaquina = getCombinacioIntentada();

                //Comprovem si la correccio son tot B, es a dir, que l'usuari ha endivinat el codi.
                for (int i = 0; i < longitudCombinacioIntentat && guanyat; ++i) {
                    if (correccioUsuariString.charAt(i) != 'B') guanyat = false;
                }

                if (!guanyat && current_round < intents) { //Esta ben corregit i no ha guanyat i te intents
                    combinacio_intentada = CtrlPresentacio.jugarRondaCodemaker();


                    System.out.println("on corregir codemaker bucle 502 combinacio_intentada " + combinacio_intentada);
                    for (int i = 0; i < longitudCorreccio; i++) {
                        //Deshabilitem els botons de correccio ja pintats per l'usuari
                        RoundButton buttonCorreccio = buttonMatrixCorreccio.get(intents - current_round).get(i);
                        buttonCorreccio.setEnabled(false);

                        System.out.println("on corregir codemaker bucle 502 combinacio_intentada[i] " + combinacio_intentada[i]);

                        //Pinta la combinacio triada per la maquina
                        buttonMatrixIntentat.get(intents - current_round - 1).get(i).setCurrentColor(colorList.get(combinacio_intentada[i]), combinacio_intentada[i]);
                        buttonMatrixIntentat.get(intents - current_round - 1).get(i).revalidate();
                        buttonMatrixIntentat.get(intents - current_round - 1).get(i).repaint();
                        System.out.println(combinacio_intentada[i]);

                        //Habilita els seguents botons de correccio
                        if (current_round < intents)
                            buttonMatrixCorreccio.get(intents - current_round - 1).get(i).setEnabled(true);
                    }

                    current_round += 1;
                } else if (!guanyat) {
                    JOptionPane.showMessageDialog(pCombinacions, "La maquina s'ha quedat sense intents, has guanyat!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    CtrlPresentacio.partidaAcabadaCodemaker();
                    dispose();
                } else { //Esta ben corregit i ha guanyat i te intents
                    JOptionPane jOptionPane = new JOptionPane();
                    jOptionPane.showMessageDialog(pCombinacions, "La maquina ha guanyat!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

//                    jOptionPane.addMouseListener(new MouseAdapter() {
//                        @Override
//                        public void mousePressed(MouseEvent e) {
//
//                            super.mousePressed(e);
//                        }
//                    });
                    CtrlPresentacio.partidaAcabadaCodemaker();
                    CtrlPresentacio.vistaMenuInicial(getLocation(), getExtendedState());
                    dispose();
                }
            }
        }
    }

    private Integer[] traduirCorrecioANumero(String correccioColors) {
        int longitudCombinacioIntentat = buttonMatrixIntentat.get(intents - current_round - 1).size();

        Integer[] correccioNumeros = new Integer[longitudCombinacioIntentat];

        for (int i = 0; i < longitudCombinacioIntentat; i++) {

            if (correccioColors.charAt(i) == 'B') correccioNumeros[i] = 9;
            else if (correccioColors.charAt(i) == 'W') correccioNumeros[i] = 10;
            else correccioNumeros[i] = 0;
        }

        return correccioNumeros;
    }

    private String traduirCorrecioAString(Integer[] correccioColors) {
        int longitudCombinacioIntentat = buttonMatrixIntentat.get(intents - current_round - 1).size();

        String correccioNumeros = "";

        for (int i = 0; i < longitudCombinacioIntentat; i++) {

            System.out.println("traduir correcio a string" + correccioColors[i]);
            if (correccioColors[i] == 9) correccioNumeros += "B";
            else if (correccioColors[i] == 10) correccioNumeros += "W";
            else correccioNumeros += "-";

        }

        return correccioNumeros;
    }

    //Retorna la combinacio intentada del usuari o null en cas que no sigui valida per que no ha posat tots els colors.
    private Integer[] getCombinacioIntentada() {
        int longitudCombinacioIntentat = buttonMatrixIntentat.get(intents - current_round - 1).size();
        Integer[] combinacioUsuari = new Integer[longitudCombinacioIntentat];

        for (int i = 0; i < longitudCombinacioIntentat; i++) {
            RoundButton button = buttonMatrixIntentat.get(intents - current_round - 1).get(i);
            combinacioUsuari[i] = button.getCurrentColor();

            if (combinacioUsuari[i] == 0) {
                return null;
            }
        }

        return combinacioUsuari;
    }

    private Integer[] getCorreccioUsuari() {
        int longitudCombinacioIntentat = buttonMatrixCorreccio.get(intents - current_round - 1).size();
        Integer[] combinacioUsuari = new Integer[longitudCombinacioIntentat];

        for (int i = 0; i < longitudCombinacioIntentat; i++) {
            RoundButton button = buttonMatrixCorreccio.get(intents - current_round - 1).get(i);
            combinacioUsuari[i] = button.getCurrentColor();
        }

        return combinacioUsuari;
    }

    private void onSortir() throws IOException, InstanciaNoExisteix, InstanciaJaExisteix {

        JOptionPane jOptionPane = new JOptionPane();
        int opcioEscollida = jOptionPane.showConfirmDialog(pCombinacions, "Sortint de la partida... Vols guardar-la?", "Guardar Partida", JOptionPane.YES_NO_CANCEL_OPTION);
        if (opcioEscollida == jOptionPane.YES_OPTION) { //Guardar la partida
            CtrlPresentacio.guardarPartida();
            CtrlPresentacio.vistaMenuInicial(getLocation(), getExtendedState());
            dispose();
        } else if (opcioEscollida == jOptionPane.NO_OPTION) { //Guardar la partida
            CtrlPresentacio.eliminarPartida();
            CtrlPresentacio.vistaMenuInicial(getLocation(), getExtendedState());
            dispose();
        } else if (opcioEscollida == jOptionPane.CANCEL_OPTION) {

        }
        //jOptionPane.getComponent(0);
        //dispose();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(3, 3, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setPreferredSize(new Dimension(1080, 720));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bSortir = new JButton();
        bSortir.setText("Sortir");
        panel2.add(bSortir, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bCorretgir = new JButton();
        bCorretgir.setText("Corretgir");
        panel2.add(bCorretgir, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pCombinacions = new JPanel();
        pCombinacions.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(pCombinacions, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pColors = new JPanel();
        pColors.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(pColors, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bPista = new JButton();
        bPista.setBackground(new Color(-16777216));
        bPista.setBorderPainted(true);
        bPista.setContentAreaFilled(true);
        bPista.setFocusable(false);
        Font bPistaFont = this.$$$getFont$$$(null, -1, 28, bPista.getFont());
        if (bPistaFont != null) bPista.setFont(bPistaFont);
        bPista.setForeground(new Color(-1));
        bPista.setText("Pista");
        contentPane.add(bPista, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pCorreccio = new JPanel();
        pCorreccio.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(pCorreccio, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lInvalidCombinacio = new JLabel();
        lInvalidCombinacio.setForeground(new Color(-4520936));
        lInvalidCombinacio.setText("");
        contentPane.add(lInvalidCombinacio, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
