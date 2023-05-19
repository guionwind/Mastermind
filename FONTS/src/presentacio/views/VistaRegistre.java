package presentacio.views;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;

public class VistaRegistre extends javax.swing.JDialog {
    private javax.swing.JPanel contentPane;
    private javax.swing.JButton bAcceptar;
    private javax.swing.JButton bEnrere;
    private JTextField tfNomUsuari;
    private JPasswordField pfContrasenya;
    private String usuari;
    private String contrasenya;

    public VistaRegistre() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bAcceptar);

        DocumentListener myDocumentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualitzarVariable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualitzarVariable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualitzarVariable();
            }

            private void actualitzarVariable() {
                usuari = tfNomUsuari.getText();
                contrasenya = new String(pfContrasenya.getPassword());
//                System.out.println("Texto actualizado: " + usuari);
//                System.out.println("Pass actualizado: " + contrasenya);
            }
        };

        bAcceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onAcceptar();
            }
        });

        bEnrere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onEnrere();
            }
        });

        tfNomUsuari.getDocument().addDocumentListener(myDocumentListener);

        pfContrasenya.getDocument().addDocumentListener(myDocumentListener);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onEnrere();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onEnrere();
            }
        }, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private Boolean checkIntegritat(){
        //TODO aqui mirarem que la integritat de les dades sigui correcta
        return true;
    }
    private void onNomCanviat(){
        usuari = tfNomUsuari.getText();
    }

    private void onContrasenyaCanviada(){
        contrasenya = Arrays.toString(pfContrasenya.getPassword());
    }
    private void onAcceptar() {
//        if checkIntegritat(){
//          TODO aqui afegim les funcions de capa persistencia
//        }
        System.out.println("Usuari: "+usuari+"  /  Contrasenya: "+contrasenya);
        dispose();
    }
    private void onEnrere() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VistaRegistre dialog = new VistaRegistre();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
