package presentacio.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domini.classes.exceptions.InstanciaNoExisteix;
import presentacio.controllers.CtrlPresentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.File;
import java.util.Locale;

public class VistaMenuInicial extends JFrame {
    private JPanel contentPane;
    private JButton bSortir;
    private JButton bRanquing;
    private JButton bTancarSessio;
    private JButton bNovaPartida;
    private JButton bCarregarPartida;
    private JLabel lTitle;

    public VistaMenuInicial(Point location, int state) throws IOException {
        setLocation(location);
        setContentPane(contentPane);
        this.pack();
        setResizable(true);
        setTitle("MASTERMIND");
        this.setIconImage(ImageIO.read(new File("./resources/antiDaltonic2.png")));

        ImageIcon imageIconTitle = new ImageIcon("./resources/LTitle.png");
        lTitle.setIcon(imageIconTitle);

        ImageIcon imageIconNovaPartida = new ImageIcon("./resources/bNovaPartida.png");
        bNovaPartida.setIcon(imageIconNovaPartida);
        ImageIcon imageIconNovaPartidaPressed = new ImageIcon("./resources/bNovaPartidaPressed.png");
        bNovaPartida.setPressedIcon(imageIconNovaPartidaPressed);

        ImageIcon imageIconCarregarPartida = new ImageIcon("./resources/bCarregarPartida.png");
        bCarregarPartida.setIcon(imageIconCarregarPartida);
        ImageIcon imageIconCarregarPartidaPressed = new ImageIcon("./resources/bCarregarPartidaPressed.png");
        bCarregarPartida.setPressedIcon(imageIconCarregarPartidaPressed);

        ImageIcon imageIconRanquing = new ImageIcon("./resources/bRanquing.png");
        bRanquing.setIcon(imageIconRanquing);
        ImageIcon imageIconRanquingPressed = new ImageIcon("./resources/bRanquingPressed.png");
        bRanquing.setPressedIcon(imageIconRanquingPressed);

        ImageIcon imageIconTancarSessio = new ImageIcon("./resources/bTancarSessio.png");
        bTancarSessio.setIcon(imageIconTancarSessio);
        ImageIcon imageIconTancarSessioPressed = new ImageIcon("./resources/bTancarSessioPressed.png");
        bTancarSessio.setPressedIcon(imageIconTancarSessioPressed);

        ImageIcon imageIconSortir = new ImageIcon("./resources/bSortir.png");
        bSortir.setIcon(imageIconSortir);
        ImageIcon imageIconSortirPressed = new ImageIcon("./resources/bSortirPressed.png");
        bSortir.setPressedIcon(imageIconSortirPressed);

        setVisible(true);
        setExtendedState(state);

        bSortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        bTancarSessio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onTancarSessio();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bNovaPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    onNovaPartida();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bCarregarPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onCarregarPartida();
                } catch (IOException | ClassNotFoundException | InstanciaNoExisteix ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bRanquing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    onRanquing();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void onTancarSessio() throws IOException {
        CtrlPresentacio.tancarSessio();
        CtrlPresentacio.vistaPrincipal(getLocation(), getExtendedState());
        dispose();
    }

    private void onNovaPartida() throws IOException {
        CtrlPresentacio.vistaConfiguracioPartida(getLocation(), getExtendedState());
        dispose();
    }

    private void onCarregarPartida() throws IOException, ClassNotFoundException, InstanciaNoExisteix {
        CtrlPresentacio.vistaCarregarPartida(getLocation(), getExtendedState());
        dispose();
    }

    private void onRanquing() throws IOException, ClassNotFoundException {
        CtrlPresentacio.vistaRanquing(getLocation(), getExtendedState());
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
        contentPane.setLayout(new GridLayoutManager(10, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setPreferredSize(new Dimension(1080, 720));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bSortir = new JButton();
        bSortir.setBackground(new Color(-6075570));
        bSortir.setBorderPainted(false);
        bSortir.setContentAreaFilled(false);
        bSortir.setFocusable(false);
        bSortir.setText("");
        panel2.add(bSortir, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        contentPane.add(spacer3, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bRanquing = new JButton();
        bRanquing.setBackground(new Color(-4212169));
        bRanquing.setBorderPainted(false);
        bRanquing.setContentAreaFilled(false);
        bRanquing.setFocusPainted(false);
        Font bRanquingFont = this.$$$getFont$$$(null, -1, 36, bRanquing.getFont());
        if (bRanquingFont != null) bRanquing.setFont(bRanquingFont);
        bRanquing.setText("");
        panel3.add(bRanquing, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bNovaPartida = new JButton();
        bNovaPartida.setBackground(new Color(-14121578));
        bNovaPartida.setBorderPainted(false);
        bNovaPartida.setContentAreaFilled(false);
        bNovaPartida.setFocusPainted(false);
        Font bNovaPartidaFont = this.$$$getFont$$$(null, -1, 31, bNovaPartida.getFont());
        if (bNovaPartidaFont != null) bNovaPartida.setFont(bNovaPartidaFont);
        bNovaPartida.setText("");
        panel4.add(bNovaPartida, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bCarregarPartida = new JButton();
        bCarregarPartida.setBackground(new Color(-11245421));
        bCarregarPartida.setBorderPainted(false);
        bCarregarPartida.setContentAreaFilled(false);
        bCarregarPartida.setFocusable(false);
        Font bCarregarPartidaFont = this.$$$getFont$$$(null, -1, 36, bCarregarPartida.getFont());
        if (bCarregarPartidaFont != null) bCarregarPartida.setFont(bCarregarPartidaFont);
        bCarregarPartida.setText("");
        panel4.add(bCarregarPartida, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel4.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel4.add(spacer5, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bTancarSessio = new JButton();
        bTancarSessio.setBackground(new Color(-9009816));
        bTancarSessio.setBorderPainted(false);
        bTancarSessio.setContentAreaFilled(false);
        bTancarSessio.setFocusPainted(false);
        Font bTancarSessioFont = this.$$$getFont$$$(null, -1, 36, bTancarSessio.getFont());
        if (bTancarSessioFont != null) bTancarSessio.setFont(bTancarSessioFont);
        bTancarSessio.setText("");
        panel5.add(bTancarSessio, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lTitle = new JLabel();
        lTitle.setFocusable(true);
        Font lTitleFont = this.$$$getFont$$$(null, -1, 72, lTitle.getFont());
        if (lTitleFont != null) lTitle.setFont(lTitleFont);
        lTitle.setText("");
        panel6.add(lTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        contentPane.add(spacer6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        contentPane.add(spacer7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        contentPane.add(spacer8, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
