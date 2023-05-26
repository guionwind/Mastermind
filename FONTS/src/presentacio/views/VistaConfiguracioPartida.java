package presentacio.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import presentacio.custom.RoundButton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VistaConfiguracioPartida extends JDialog {
    private JPanel contentPane;
    private JButton bAcceptar;
    private JButton bEnrere;
    private JComboBox cbTipusPartida;
    private JSlider sIntents;
    private JSlider sColors;
    private JSlider sLongitud;
    private JPanel pCombinacio;
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
        getRootPane().setDefaultButton(bAcceptar);
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

        bAcceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        bEnrere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

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
                    int current = button.getCurrentColor();
                    current = (current == sColors.getValue()) ? 0 : (current + 1);
                    button.setCurrentColor(colorList.get(current), current);
                    super.mouseClicked(e);
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
            if (cbTipusPartida.getSelectedItem().toString().equals("Genetic")) {
                sColors.setMaximum(7);
                sLongitud.setMaximum(7);
            } else {
                sColors.setMaximum(8);
                sLongitud.setMaximum(8);
            }
            sIntents.setValue(intents);
            sColors.setValue(colors);
            sLongitud.setValue(longitud);

            sIntents.setEnabled(true);
            sColors.setEnabled(true);
            sLongitud.setEnabled(true);
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
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
        contentPane.setLayout(new GridLayoutManager(4, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setMinimumSize(new Dimension(700, 420));
        contentPane.setPreferredSize(new Dimension(1080, 720));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bAcceptar = new JButton();
        bAcceptar.setText("Acceptar");
        panel2.add(bAcceptar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bEnrere = new JButton();
        bEnrere.setText("Enrere");
        panel2.add(bEnrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Configuracio Partida");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbTipusPartida = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Codebreaker");
        defaultComboBoxModel1.addElement("Codemaker");
        defaultComboBoxModel1.addElement("Genetic");
        cbTipusPartida.setModel(defaultComboBoxModel1);
        panel3.add(cbTipusPartida, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Numero Intents:");
        panel3.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sIntents = new JSlider();
        sIntents.setMaximum(20);
        sIntents.setMinimum(1);
        sIntents.setPaintLabels(true);
        sIntents.setPaintTicks(true);
        sIntents.setValue(5);
        panel3.add(sIntents, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Numero Colors:");
        panel3.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sColors = new JSlider();
        sColors.setMaximum(8);
        sColors.setMinimum(1);
        sColors.setPaintLabels(true);
        sColors.setPaintTicks(true);
        sColors.setValue(6);
        panel3.add(sColors, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Longitud Combinacio:");
        panel3.add(label4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sLongitud = new JSlider();
        sLongitud.setMaximum(8);
        sLongitud.setMinimum(1);
        sLongitud.setPaintLabels(true);
        sLongitud.setPaintTicks(true);
        sLongitud.setValue(4);
        panel3.add(sLongitud, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Combinacio:");
        panel3.add(label5, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pCombinacio = new JPanel();
        pCombinacio.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(pCombinacio, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        contentPane.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
