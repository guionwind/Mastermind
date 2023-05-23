package presentacio.views;

import presentacio.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class vistaTutorial extends JDialog {
    private JPanel contentPane;
    private JButton bEnrere;
    private JLabel lDefinicio3;
    private JLabel lDefinicio;
    private JLabel lDefinicio2;
    private JButton bSeguent;
    private JPanel pPg1;
    private JPanel pPg2;
    private JPanel pPg3;
    private JPanel pCardTutorial;

    private int pgActual = 0;

    public vistaTutorial() {
        setContentPane(contentPane);
        setModal(true);
        this.pack();
        setVisible(true);

        bEnrere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                CardLayout cl = (CardLayout) pCardTutorial.getLayout();
                if (pCardTutorial.getComponent(pgActual) == pPg1) {
                    CtrlPresentacio.iniPresentacio();
                    setVisible(false);
                } else {
                    cl.previous(pCardTutorial);
                }

                if (pgActual == 0) pgActual = 2;
                else pgActual--;

            }
        });
        bSeguent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (pgActual == 2) pgActual = 0;
                else pgActual++;

                CardLayout cl = (CardLayout) pCardTutorial.getLayout();

                cl.next(pCardTutorial);
            }
        });
    }

    public static void main(String[] args) {
        vistaTutorial dialog = new vistaTutorial();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
