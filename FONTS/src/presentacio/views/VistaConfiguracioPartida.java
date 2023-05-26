package presentacio.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import presentacio.controllers.CtrlPresentacio;
import presentacio.custom.RoundButton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Locale;

public class VistaConfiguracioPartida extends JDialog {
    private JPanel contentPane;
    private JButton bJugar;
    private JButton bEnrere;
    private JComboBox cbTipusPartida;
    private JSlider sIntents;
    private JSlider sColors;
    private JSlider sLongitud;
    private JPanel pCombinacio;
    private JComboBox cbAlgorisme;
    private JLabel lTipusAlgorisme;
    private JLabel lCombinacio;
    private JLabel lIntents;
    private JLabel lColors;
    private int longitud = 4;
    private int colors = 6;
    private int intents = 5;
    private ArrayList<Color> colorList = new ArrayList<>();
    private ArrayList<RoundButton> buttonList = new ArrayList<>();

    public VistaConfiguracioPartida(Point location) {
        setLocation(location);
        setContentPane(contentPane);
        this.pack();
        setVisible(true);
        getRootPane().setDefaultButton(bJugar);
        initButtonsPanel();
        sLongitud.setPaintLabels(true);
        sLongitud.setPaintTicks(true);


        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.ORANGE);
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.PINK);


        bJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onJugar();
            }
        });

        bEnrere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEnrere();
            }
        });

        sLongitud.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int numButtons = sLongitud.getValue();
                System.out.println(numButtons);

                pCombinacio.removeAll();
                pCombinacio.setLayout(new FlowLayout());


                for (int i = 0; i < numButtons; i++) {
                    RoundButton button = buttonList.get(i);
                    pCombinacio.add(button);
                }

                pCombinacio.revalidate();
                pCombinacio.repaint();
            }
        });

        cbTipusPartida.addActionListener((new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                handleFields();
            }
        }));

        cbAlgorisme.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbAlgorisme.getSelectedItem().toString().equals("Genetic")) {
                    sColors.setMaximum(7);
                    sLongitud.setMaximum(7);
                } else {
                    sColors.setMaximum(8);
                    sLongitud.setMaximum(8);
                }
            }
        }));

        sIntents.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                lIntents.setText(String.valueOf(sIntents.getValue()));
            }
        });
        sColors.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                lColors.setText(String.valueOf(sColors.getValue()));
            }
        });
    }

    private void initButtonsPanel() {
        pCombinacio.setLayout(new FlowLayout());

        // Código para agregar los botones al panel pCombinacio
        int numButtons = 8;
        for (int i = 0; i < numButtons; i++) {
            RoundButton button = new RoundButton("");
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (cbTipusPartida.getSelectedItem().toString().equals("Codemaker")) {
                        int current = button.getCurrentColor();
                        current = (current == sColors.getValue()) ? 0 : (current + 1);
                        button.setCurrentColor(colorList.get(current), current);
                        super.mouseClicked(e);
                    }
                }
            });
            buttonList.add(button);
            if (i < 4) pCombinacio.add(button);
        }

        pCombinacio.revalidate();
        pCombinacio.repaint();
    }

    private void handleFields() {
        if (cbTipusPartida.getSelectedItem().toString().equals("Codemaker")) {
            lTipusAlgorisme.setVisible(true);
            cbAlgorisme.setVisible(true);
            sColors.setMaximum(8);
            sLongitud.setMaximum(8);

            sIntents.setEnabled(false);
            sColors.setEnabled(false);
            sLongitud.setEnabled(false);

            intents = sIntents.getValue();
            colors = sColors.getValue();
            longitud = sLongitud.getValue();

            sIntents.setValue(5);
            sColors.setValue(6);
            sLongitud.setValue(4);

        } else {
            lTipusAlgorisme.setVisible(false);
            cbAlgorisme.setVisible(false);

            sIntents.setValue(intents);
            sColors.setValue(colors);
            sLongitud.setValue(longitud);

            sIntents.setEnabled(true);
            sColors.setEnabled(true);
            sLongitud.setEnabled(true);
        }
    }

    private void onJugar() {
        // add your code here
        CtrlPresentacio.vistaPartida(getLocation());
        dispose();
    }

    private void onEnrere() {
        // add your code here if necessary
        CtrlPresentacio.vistaMenuInicial(getLocation());
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
        contentPane.setLayout(new GridLayoutManager(8, 3, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setMinimumSize(new Dimension(700, 420));
        contentPane.setPreferredSize(new Dimension(1080, 720));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(11, 8, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cbTipusPartida = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Codebreaker");
        defaultComboBoxModel1.addElement("Codemaker");
        cbTipusPartida.setModel(defaultComboBoxModel1);
        panel2.add(cbTipusPartida, new GridConstraints(0, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Numero Intents:");
        panel2.add(label1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sIntents = new JSlider();
        sIntents.setMaximum(20);
        sIntents.setMinimum(1);
        sIntents.setPaintLabels(true);
        sIntents.setPaintTicks(true);
        sIntents.setValue(5);
        panel2.add(sIntents, new GridConstraints(4, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Numero Colors:");
        panel2.add(label2, new GridConstraints(5, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sColors = new JSlider();
        sColors.setMaximum(8);
        sColors.setMinimum(1);
        sColors.setPaintLabels(true);
        sColors.setPaintTicks(true);
        sColors.setValue(6);
        panel2.add(sColors, new GridConstraints(6, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Longitud Combinacio:");
        panel2.add(label3, new GridConstraints(7, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sLongitud = new JSlider();
        sLongitud.setMaximum(8);
        sLongitud.setMinimum(1);
        sLongitud.setPaintLabels(true);
        sLongitud.setPaintTicks(true);
        sLongitud.setValue(4);
        panel2.add(sLongitud, new GridConstraints(8, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lCombinacio = new JLabel();
        lCombinacio.setText("Combinacio:");
        lCombinacio.setVisible(true);
        panel2.add(lCombinacio, new GridConstraints(9, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pCombinacio = new JPanel();
        pCombinacio.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pCombinacio.setVisible(true);
        panel2.add(pCombinacio, new GridConstraints(10, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cbAlgorisme = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("FiveGuess");
        defaultComboBoxModel2.addElement("Genetic");
        cbAlgorisme.setModel(defaultComboBoxModel2);
        cbAlgorisme.setVisible(false);
        panel2.add(cbAlgorisme, new GridConstraints(2, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lTipusAlgorisme = new JLabel();
        lTipusAlgorisme.setText("Tipus Algorisme:");
        lTipusAlgorisme.setVisible(false);
        panel2.add(lTipusAlgorisme, new GridConstraints(1, 0, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lIntents = new JLabel();
        lIntents.setText("5");
        panel2.add(lIntents, new GridConstraints(4, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lColors = new JLabel();
        lColors.setText("6");
        panel2.add(lColors, new GridConstraints(6, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        contentPane.add(spacer1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 36, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Configuració de la partida");
        contentPane.add(label4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        contentPane.add(panel3, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bJugar = new JButton();
        bJugar.setBackground(new Color(-6556135));
        Font bJugarFont = this.$$$getFont$$$(null, -1, 28, bJugar.getFont());
        if (bJugarFont != null) bJugar.setFont(bJugarFont);
        bJugar.setText("Jugar");
        panel3.add(bJugar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bEnrere = new JButton();
        Font bEnrereFont = this.$$$getFont$$$(null, -1, 28, bEnrere.getFont());
        if (bEnrereFont != null) bEnrere.setFont(bEnrereFont);
        bEnrere.setText("Enrere");
        panel3.add(bEnrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        contentPane.add(spacer3, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        contentPane.add(spacer4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        contentPane.add(spacer5, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        contentPane.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
