package presentacio.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import presentacio.custom.RoundButton;

public class VistaPartida extends JDialog {
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
    private ArrayList<Integer> combinacio = new ArrayList<Integer>();
    private ArrayList<Color> colorList = new ArrayList<>();
    private ArrayList<ArrayList<RoundButton>> buttonMatrix = new ArrayList<>();
    private ArrayList<ArrayList<RoundButton>> buttonMatrixCorreccio = new ArrayList<>();
    private ArrayList<JPanel> panelList = new ArrayList<>();
    private ArrayList<JPanel> panelListCorreccio = new ArrayList<>();


    public VistaPartida() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bSortir);

        combinacio.add(0); //FIXME TREURE UN COP IMPLEMENTAT AMB DOMINI
        combinacio.add(1);
        combinacio.add(2);
        combinacio.add(3);

        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.ORANGE);
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.PINK);
        colorList.add(Color.GRAY);

        initButtonsPanel();

        bPista.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //TODO DOMINI getPista
                String pista = "La pista xd";
                JOptionPane.showMessageDialog(pCombinacions, pista, "Pista", JOptionPane.INFORMATION_MESSAGE);

                super.mousePressed(e);
            }
        });

        bCorretgir.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.print("RONDA " + current_round + ": ");
                for (int i = 0; i < buttonMatrix.get(intents - current_round - 1).size(); i++) {
                    RoundButton button = buttonMatrix.get(intents - (current_round) - 1).get(i);
                    System.out.print(button.getCurrentColor() + " ");
                    button.setEnabled(false);
                    buttonMatrix.get(intents - (current_round + 1) - 1).get(i).setEnabled(true);

                    //TODO DOMINI
                    Color color = button.getCurrentColor() == combinacio.get(i) ? Color.WHITE : Color.BLACK;
                    int colorNum = button.getCurrentColor() == combinacio.get(i) ? colors + 1 : colors + 2;
                    buttonMatrixCorreccio.get(intents - current_round - 1).get(i).setCurrentColor(color, colorNum);
                    buttonMatrixCorreccio.get(intents - current_round - 1).get(i).revalidate();
                    buttonMatrixCorreccio.get(intents - current_round - 1).get(i).repaint();
                }
                System.out.println();
                current_round += 1;
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
                        } else {
                            button.setCurrentColor(colorList.get(8), 8);
                        }
                        super.mousePressed(e);
                    }
                });
                if (tipus_partida == "Codebreaker") {
                    buttonCorreccio.setEnabled(false);
                    if (i != intents - 1) {
                        button.setEnabled(false);
                    }
                } else {
                    button.setEnabled(false);
                    if (i != intents - 1) {
                        buttonCorreccio.setEnabled(false);
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

        pColors.setLayout(new BoxLayout(pColors, BoxLayout.Y_AXIS));
        for (int i = 0; i < colors; i++) {
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


    private void onSortir() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VistaPartida dialog = new VistaPartida();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
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
        bPista.setBorderPainted(true);
        bPista.setContentAreaFilled(true);
        bPista.setIcon(new ImageIcon(getClass().getResource("/presentacio/custom/icons/lightbulb.png")));
        bPista.setText("");
        contentPane.add(bPista, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pCorreccio = new JPanel();
        pCorreccio.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(pCorreccio, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
