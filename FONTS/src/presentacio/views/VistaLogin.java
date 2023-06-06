package presentacio.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domini.classes.exceptions.ContrasenyaIncorrecte;
import domini.classes.exceptions.InstanciaNoExisteix;
import presentacio.controllers.CtrlPresentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class VistaLogin extends JFrame {
    private JPanel contentPane;
    private JTextField tFNomUsuari;
    private JButton bLogin;
    private JButton bCancel;
    private JPasswordField pFContrasenya;
    private JLabel lPwdError;
    private JLabel lUsernameError;
    private JLabel lUserNotExists;
    private JLabel lContrasenyaIncorrecte;
    private JLabel lLogin;
    private JLabel lUsername;

    public VistaLogin(Point point, int state) throws IOException, FontFormatException {
        setLocation(point);

        setContentPane(contentPane);

        this.pack();
        setResizable(true);

        setTitle("MASTERMIND");
        this.setIconImage(ImageIO.read(new File("./resources/antiDaltonic2.png")));

        ImageIcon imageIconLogin = new ImageIcon("./resources/lLogin.png");
        lLogin.setIcon(imageIconLogin);

        ImageIcon imageIconCancel = new ImageIcon("./resources/bCancel.png");
        bCancel.setIcon(imageIconCancel);
        ImageIcon imageIconCancelPressed = new ImageIcon("./resources/bCancelPressed.png");
        bCancel.setPressedIcon(imageIconCancelPressed);

        ImageIcon imageIconLoginBoto = new ImageIcon("./resources/bLoginPetit.png");
        bLogin.setIcon(imageIconLoginBoto);
        ImageIcon imageIconLoginPressed = new ImageIcon("./resources/bLoginPetitPressed.png");
        bLogin.setPressedIcon(imageIconLoginPressed);

        setVisible(true);
        setExtendedState(state);

        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                onLogin();
            }
        });
        bCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onCancel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        contentPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                //if (e.getKeyChar() = 'enter') {

                //}
            }
        });
    }

    private void onCancel() throws IOException {
        CtrlPresentacio.vistaPrincipal(getLocation(), getExtendedState());
        dispose();
    }

    private void onLogin() {
        lUsernameError.setVisible(false);
        lUserNotExists.setVisible(false);
        lPwdError.setVisible(false);
        lContrasenyaIncorrecte.setVisible(false);

        if (tFNomUsuari.getText().isEmpty() || String.valueOf(pFContrasenya.getPassword()).isEmpty()) {
            if (tFNomUsuari.getText().isEmpty()) {
                lUsernameError.setText("Siusplau indica un nom d'Usuari");
                lUsernameError.setVisible(true);
            }
            if (String.valueOf(pFContrasenya.getPassword()).isEmpty()) {
                lPwdError.setText("Siusplau indica una contrasenya");
                lPwdError.setVisible(true);
            }

        } else {
            try {
                CtrlPresentacio.login(tFNomUsuari.getText(), String.valueOf(pFContrasenya.getPassword()));
                CtrlPresentacio.vistaMenuInicial(getLocation(), getExtendedState());
                dispose();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (InstanciaNoExisteix ex) {
                lUserNotExists.setText("L'usuari no existeix, registra't siusplau");
                lUserNotExists.setVisible(true);
            } catch (ContrasenyaIncorrecte ex) {
                lContrasenyaIncorrecte.setText("La contrasenya és incorrecte");
                lContrasenyaIncorrecte.setVisible(true);
            }
        }
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
        contentPane.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setPreferredSize(new Dimension(1080, 720));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lLogin = new JLabel();
        Font lLoginFont = this.$$$getFont$$$(null, -1, 28, lLogin.getFont());
        if (lLoginFont != null) lLogin.setFont(lLoginFont);
        lLogin.setText("");
        panel1.add(lLogin, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(13, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bCancel = new JButton();
        bCancel.setBorderPainted(false);
        bCancel.setContentAreaFilled(false);
        bCancel.setFocusPainted(false);
        bCancel.setText("");
        panel3.add(bCancel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bLogin = new JButton();
        bLogin.setBorderPainted(false);
        bLogin.setContentAreaFilled(false);
        bLogin.setFocusPainted(false);
        bLogin.setText("");
        panel3.add(bLogin, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lPwdError = new JLabel();
        Font lPwdErrorFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 14, lPwdError.getFont());
        if (lPwdErrorFont != null) lPwdError.setFont(lPwdErrorFont);
        lPwdError.setForeground(new Color(-4520936));
        lPwdError.setText("Label");
        lPwdError.setVisible(false);
        panel2.add(lPwdError, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lUsernameError = new JLabel();
        Font lUsernameErrorFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 14, lUsernameError.getFont());
        if (lUsernameErrorFont != null) lUsernameError.setFont(lUsernameErrorFont);
        lUsernameError.setForeground(new Color(-4520936));
        lUsernameError.setText("Label");
        lUsernameError.setVisible(false);
        panel2.add(lUsernameError, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lUserNotExists = new JLabel();
        Font lUserNotExistsFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 14, lUserNotExists.getFont());
        if (lUserNotExistsFont != null) lUserNotExists.setFont(lUserNotExistsFont);
        lUserNotExists.setForeground(new Color(-4520936));
        lUserNotExists.setText("Label");
        lUserNotExists.setVisible(false);
        panel2.add(lUserNotExists, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lContrasenyaIncorrecte = new JLabel();
        Font lContrasenyaIncorrecteFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 14, lContrasenyaIncorrecte.getFont());
        if (lContrasenyaIncorrecteFont != null) lContrasenyaIncorrecte.setFont(lContrasenyaIncorrecteFont);
        lContrasenyaIncorrecte.setForeground(new Color(-4520936));
        lContrasenyaIncorrecte.setText("Label");
        lContrasenyaIncorrecte.setVisible(false);
        panel2.add(lContrasenyaIncorrecte, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tFNomUsuari = new JTextField();
        panel2.add(tFNomUsuari, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pFContrasenya = new JPasswordField();
        panel2.add(pFContrasenya, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lUsername = new JLabel();
        Font lUsernameFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 14, lUsername.getFont());
        if (lUsernameFont != null) lUsername.setFont(lUsernameFont);
        lUsername.setText("Usuari");
        panel2.add(lUsername, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 14, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Contrasenya");
        panel2.add(label1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel6, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel7, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel8, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel9, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        contentPane.add(spacer1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        contentPane.add(spacer3, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        contentPane.add(spacer4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        contentPane.add(spacer5, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        contentPane.add(spacer6, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
