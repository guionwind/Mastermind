package presentacio.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domini.classes.exceptions.*;
import presentacio.controllers.CtrlPresentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VistaPartidesGuardades extends JFrame {
    private JPanel contentPane;
    private JButton bAcceptar;
    private JButton bEnrere;
    private JList lPartides;
    private JLabel lPartidesGuardades;
    private int partida = -1;
    private ArrayList<String> partides = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public VistaPartidesGuardades(Point location, int state) throws IOException, ClassNotFoundException, InstanciaNoExisteix {
        setLocation(location);
        setContentPane(contentPane);
        this.pack();
        setResizable(true);
        setTitle("MASTERMIND");
        this.setIconImage(ImageIO.read(new File("./resources/antiDaltonic2.png")));
        setVisible(true);

        ImageIcon imageIconRegister = new ImageIcon("./resources/lPartidesGuardades.png");
        lPartidesGuardades.setIcon(imageIconRegister);

        ImageIcon imageIconEnrere = new ImageIcon("./resources/bEnrere.png");
        bEnrere.setIcon(imageIconEnrere);
        ImageIcon imageIconEnrerePressed = new ImageIcon("./resources/bEnrerePressed.png");
        bEnrere.setPressedIcon(imageIconEnrerePressed);

        ImageIcon imageIconAcceptar = new ImageIcon("./resources/bAcceptar.png");
        bAcceptar.setIcon(imageIconAcceptar);
        ImageIcon imageIconAcceptarPressed = new ImageIcon("./resources/bAcceptarPressed.png");
        bAcceptar.setPressedIcon(imageIconAcceptarPressed);

        setExtendedState(state);
        getRootPane().setDefaultButton(bAcceptar);

        listModel = new DefaultListModel<>();
        lPartides.setModel(listModel);

        ArrayList<String[]> infoPartides = CtrlPresentacio.getInfoPartidesGuardades();

        for (int i = 0; i < infoPartides.size(); ++i) {
            String[] infoPartida = infoPartides.get(i);
            listModel.add(i, "Numero d'Intents: " + infoPartida[1] + "   Numero de colors: " + infoPartida[2] + "   Longitud de la combinacio: " + infoPartida[3] + "   Tipus de la partida: " + infoPartida[4]);
        }
        lPartides.setVisible(true);

        bAcceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (infoPartides.size() > 0)
                        onAcceptar(infoPartides);
                } catch (IOException | ClassNotFoundException | LongitudCombinacioIncorrecte | NumeroColorsIncorrecte |
                         LongitudRespostaIncorrecte | ValorsRespostaIncorrectes | InstanciaNoExisteix ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        lPartides.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                partida = lPartides.getSelectedIndex();
            }
        });

        llistarPartides();

        bEnrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onEnrere();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void llistarPartides() {
        for (String partide : partides) {
            listModel.addElement(partide);
        }
    }

    private void onAcceptar(ArrayList<String[]> infoPartides) throws IOException, InstanciaNoExisteix, ClassNotFoundException, LongitudCombinacioIncorrecte, NumeroColorsIncorrecte, LongitudRespostaIncorrecte, ValorsRespostaIncorrectes {
        String idPartida = infoPartides.get(lPartides.getSelectedIndex())[0];
        Integer numeroIntents = Integer.valueOf(infoPartides.get(lPartides.getSelectedIndex())[1]);
        Integer numeroColors = Integer.valueOf(infoPartides.get(lPartides.getSelectedIndex())[2]);
        Integer longitudCombinacio = Integer.valueOf(infoPartides.get(lPartides.getSelectedIndex())[3]);
        String tipusPartida = infoPartides.get(lPartides.getSelectedIndex())[4];

        Integer[] solutionCode = CtrlPresentacio.getSolutionCode(idPartida, tipusPartida);
        Integer[][] combinacionsIntentades = CtrlPresentacio.getCombinacionsIntentades(idPartida, tipusPartida);
        String[] correccions = CtrlPresentacio.getCorrecions(idPartida, tipusPartida);

        for (int i = 0; i < solutionCode.length; ++i) {
            System.out.print(solutionCode[i]);
        }
        System.out.println();
        System.out.println("Vista partides guardades on guardar ");
        for (int i = 0; i < combinacionsIntentades.length; ++i) {
            for (int j = 0; j < combinacionsIntentades[i].length; ++j) {
                System.out.print(combinacionsIntentades[i][j]);
            }
            System.out.println();
        }
        System.out.println("Vista partides guardades on guardar ");
        for (int i = 0; i < correccions.length; ++i) {
            System.out.println(correccions[i]);
        }


        CtrlPresentacio.carregarPartida(Integer.valueOf(idPartida));
        CtrlPresentacio.vistaPartida(getLocation(), getExtendedState(), numeroIntents, numeroColors, longitudCombinacio, solutionCode, tipusPartida, combinacionsIntentades, correccions);

        dispose();
    }

    private void onEnrere() throws IOException {
        CtrlPresentacio.vistaMenuInicial(getLocation(), getExtendedState());
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
        contentPane.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setPreferredSize(new Dimension(1080, 720));
        contentPane.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bAcceptar = new JButton();
        bAcceptar.setBorderPainted(false);
        bAcceptar.setContentAreaFilled(false);
        bAcceptar.setFocusPainted(false);
        bAcceptar.setFocusable(false);
        bAcceptar.setText("");
        panel2.add(bAcceptar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bEnrere = new JButton();
        bEnrere.setBorderPainted(false);
        bEnrere.setContentAreaFilled(false);
        bEnrere.setFocusPainted(false);
        bEnrere.setText("");
        panel2.add(bEnrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        lPartides = new JList();
        scrollPane1.setViewportView(lPartides);
        lPartidesGuardades = new JLabel();
        lPartidesGuardades.setText("");
        contentPane.add(lPartidesGuardades, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
