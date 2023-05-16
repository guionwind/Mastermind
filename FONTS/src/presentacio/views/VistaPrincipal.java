package presentacio.views;

import presentacio.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.*;

public class VistaPrincipal extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton loginButton;
    private JButton tutorialButton;
    private JButton registerButton;

    public VistaPrincipal() {
        setContentPane(contentPane);
        setModal(true);
        //setResizable(false); Serveix per fer que la finestra no pugui ser resizable.
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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

        tutorialButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                loginButton.setVisible(false);
                CtrlPresentacio.vistaTutorial();
                setVisible(false);
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    public static void main(String[] args) {
        VistaPrincipal dialog = new VistaPrincipal();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
