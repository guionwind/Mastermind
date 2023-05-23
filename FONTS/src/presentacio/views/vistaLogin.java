package presentacio.views;

import presentacio.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.*;

public class vistaLogin extends JDialog {
    private JPanel contentPane;
    private JTextField fUsername;
    private JTextField fPassword;
    private JButton bLogin;
    private JButton bCancel;

    public vistaLogin() {
        setContentPane(contentPane);
        setModal(true);


        bCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                CtrlPresentacio.iniPresentacio();
                setVisible(false);
            }
        });
    }


    public static void main(String[] args) {
        vistaLogin dialog = new vistaLogin();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
