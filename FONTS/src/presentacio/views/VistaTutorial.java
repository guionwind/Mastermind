package presentacio.views;

import presentacio.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.*;

public class VistaTutorial extends JDialog {
    private JPanel contentPane;
    private JButton bEnrere;
    private JLabel lDefinicio3;
    private JLabel lDefinicio;
    private JLabel lDefinicio2;
    private JButton bSeguent;
    private JPanel pPg1;
    private JPanel pPg2;
    private JPanel pPg3;

    public VistaTutorial() {
        setContentPane(contentPane);
        setModal(true);
        this.pack();
        setVisible(true);

        bEnrere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                CtrlPresentacio.iniPresentacio();
                setVisible(false);
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VistaTutorial dialog = new VistaTutorial();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
