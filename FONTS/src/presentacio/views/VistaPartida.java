package presentacio.views;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domini.classes.exceptions.LongitudCombinacioIncorrecte;
import domini.classes.exceptions.LongitudRespostaIncorrecte;
import domini.classes.exceptions.NumeroColorsIncorrecte;
import domini.classes.exceptions.ValorsRespostaIncorrectes;
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
    private int longitud = 4;
    private int colors = 6;
    private int intents = 5;
    private int current_color = 8;
    private String tipus_partida = "Codebreaker";
    private int current_round = 0;
    private String combinacio_intentada = "";
    private ArrayList<Integer> combinacio = new ArrayList<>();
    private ArrayList<Color> colorList = new ArrayList<>();
    private ArrayList<ArrayList<RoundButton>> buttonMatrix = new ArrayList<>();
    private ArrayList<ArrayList<RoundButton>> buttonMatrixCorreccio = new ArrayList<>();
    private ArrayList<JPanel> panelList = new ArrayList<>();
    private ArrayList<JPanel> panelListCorreccio = new ArrayList<>();


    public VistaPartida(Point location, int init_intents, int init_colors, int init_longitud, Integer[] init_combinacio, String init_tipusPartida) throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        setLocation(location);
        setUndecorated(false);
        setContentPane(contentPane);
        this.pack();
        setVisible(true);
        getRootPane().setDefaultButton(bSortir);

        intents = init_intents;
        colors = init_colors;
        longitud = init_longitud;
        tipus_partida = init_tipusPartida;
        combinacio.addAll(Arrays.asList(init_combinacio));

        System.out.println("Starting game with:");
        System.out.println(intents + " intents");
        System.out.println(colors + " colors");
        System.out.println(longitud + " longitud");
        System.out.println(tipus_partida + " tipus partida");
        if (tipus_partida == "Codemaker") {
            bPista.setVisible(false);
            System.out.println("Solution code: ");

            for (int i = 0; i < combinacio.size(); i++) {
                System.out.print(init_combinacio[i]);
            }
            System.out.println();
        }

        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.ORANGE);
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.PINK);
        colorList.add(Color.GRAY);
        colorList.add(Color.BLACK);
        colorList.add(Color.WHITE);

        if (tipus_partida == "Codemaker") {
            combinacio_intentada = CtrlPresentacio.jugarRondaCodemaker();
            System.out.println("Primera comb: "+combinacio_intentada);
        }

        initButtonsPanel();

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
                    onCorretgir();
                } catch (LongitudCombinacioIncorrecte ex) {
                    throw new RuntimeException(ex);
                } catch (NumeroColorsIncorrecte ex) {
                    throw new RuntimeException(ex);
                } catch (LongitudRespostaIncorrecte ex) {
                    throw new RuntimeException(ex);
                } catch (ValorsRespostaIncorrectes ex) {
                    throw new RuntimeException(ex);
                }
                super.mousePressed(e);
            }
        });

        bSortir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSortir();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onSortir();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSortir();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void mostrarPista(){
        String pista = CtrlPresentacio.demanarPista();
        String final_pista = "";
        for(int i = 0; i < pista.length(); i++){
            if(pista.charAt(i) != '?'){
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

                final_pista += " "+temp_color;
            }else{
                final_pista += " ?";
            }
        }
        JOptionPane.showMessageDialog(pCombinacions, "La teva pista es: "+final_pista, "Pista", JOptionPane.PLAIN_MESSAGE);

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
                        if (button.isEnabled()) {
                            button.setCurrentColor(colorList.get(current_color), current_color);
                        }
                        super.mousePressed(e);
                    }
                });
                if (tipus_partida == "Codebreaker") {
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
                        button.setCurrentColor(colorList.get(Integer.valueOf(combinacio_intentada.charAt(j)-48) - 1), Integer.valueOf(combinacio_intentada.charAt(j)-48) - 1);
                        buttonCorreccio.setEnabled(true);
                    }
                }

                buttonList.add(button);
                buttonListCorreccio.add(buttonCorreccio);
                panel.add(button);
                panelCorreccio.add(buttonCorreccio);
            }
            buttonMatrix.add(buttonList);
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

        int color_start = 0;
        int color_end = colors;
        //Codigo para mostrar la paleta de colores
        if (tipus_partida == "Codemaker") {
            color_start = 8;
            color_end = 11;
        }

        for (int i = color_start; i < color_end; i++) {
            RoundButton button = new RoundButton("");
            button.setCurrentColor(colorList.get(i), i);
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

    private void onCorretgir() throws LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        System.out.println("RONDA " + current_round + ": ");
        Boolean guanyat = false;
        if (tipus_partida == "Codebreaker") {
            int longi = buttonMatrix.get(intents - current_round - 1).size();
            Integer[] combi = new Integer[longi];
            for (int i = 0; i < longi; i++) {
                RoundButton button = buttonMatrix.get(intents - current_round - 1).get(i);
                combi[i] = button.getCurrentColor() + 1;
                System.out.println(combi[i]);
            }
            String solucio = CtrlPresentacio.jugarRondaCodebreaker(combi);
            for (int i = 0; i < longi; i++) {
                RoundButton button = buttonMatrix.get(intents - current_round - 1).get(i);
                button.setEnabled(false);
                if (current_round < intents - 1) buttonMatrix.get(intents - (current_round + 1) - 1).get(i).setEnabled(true);

                int color = 0;
                if (solucio.charAt(i) == 'B') {
                    guanyat = true;
                    color = 9;
                } else if (solucio.charAt(i) == 'W') {
                    guanyat = false;
                    color = 10;
                } else {
                    guanyat = false;
                    color = 8;
                }
                buttonMatrixCorreccio.get(intents - current_round - 1).get(i).setCurrentColor(colorList.get(color), color);
                buttonMatrixCorreccio.get(intents - current_round - 1).get(i).revalidate();
                buttonMatrixCorreccio.get(intents - current_round - 1).get(i).repaint();
            }
        } else {
            String correccio = "";
            for (int i = 0; i < buttonMatrixCorreccio.get(intents - current_round - 1).size(); i++) {
                int button_color = buttonMatrixCorreccio.get(intents - current_round - 1).get(i).getCurrentColor();
                if (button_color == 9) {
                    guanyat = true;
                    correccio += "B";
                } else if (button_color == 10) {
                    guanyat = false;
                    correccio += "W";
                } else {
                    guanyat = false;
                    correccio += "-";
                }
            }
            Boolean is_well_corrected = CtrlPresentacio.setCorreccioRonda(correccio);
            if (is_well_corrected && !guanyat) {
                combinacio_intentada = CtrlPresentacio.jugarRondaCodemaker();
                for (int i = 0; i < buttonMatrixCorreccio.get(intents - current_round - 1).size(); i++) {
                    RoundButton button_correccio = buttonMatrixCorreccio.get(intents - current_round - 1).get(i);
                    button_correccio.setEnabled(false);
                    buttonMatrix.get(intents - current_round - 1).get(i).setCurrentColor(colorList.get(Integer.valueOf(combinacio_intentada.charAt(i)) - 1), Integer.valueOf(combinacio_intentada.charAt(i)) - 1);
                    if (current_round < intents - 1) buttonMatrixCorreccio.get(intents - current_round + 1 - 1).get(i).setEnabled(true);
                }
            } else if (!is_well_corrected) {
                JOptionPane.showMessageDialog(pCombinacions, "La correccio introduida no es correcte! Torna a provar", "Correccio Incorrecte", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (guanyat) {
            if (tipus_partida == "Codebreaker") {
                CtrlPresentacio.partidaAcabada(true);
                CtrlPresentacio.vistaEstadistiquesPartida(getLocation(), "Has Guanyat!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(pCombinacions, "La maquina ha guanyat!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                CtrlPresentacio.partidaAcabadaCodemaker();
                dispose();
            }
        }
        if (current_round >= intents-1 && !guanyat) {
            if (tipus_partida == "Codebreaker") {
                JOptionPane.showMessageDialog(pCombinacions, "T'has quedat sense intents!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                CtrlPresentacio.partidaAcabada(false);
                CtrlPresentacio.vistaEstadistiquesPartida(getLocation(), "Has Perdut!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(pCombinacions, "La maquina s'ha quedat sense intents!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                CtrlPresentacio.partidaAcabadaCodemaker();
                dispose();
            }
        }
        current_round += 1;
    }


    private void onSortir() {
        // add your code here if necessary
        dispose();
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
        bSortir.setText("Enrere");
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
