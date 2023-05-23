package presentacio.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaEstadistiquesPartida extends JDialog {
    private JPanel contentPane;
    private JButton bAcceptar;
    private JLabel lStatus;
    private JLabel lIntents;
    private JLabel lPuntuacio;

    public VistaEstadistiquesPartida() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bAcceptar);

        bAcceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAcceptar();
            }
        });

        initialize_parameters();
    }

    private void initialize_parameters() {
//TODO    if (persistencia.status_partida == perduda){
//            lStatus.setText("Has perdut!");
//            lStatus.setForeground(Color.RED);
//        } else{
//            lStatus.setText("Has guanyat!");
//            lStatus.setForeground(Color.GREEN);
//        }
        //TODO asignar puntuacio i intents
    }

    private void onAcceptar() {
        // TODO passem a la seguent vista (ranquing)
        dispose();
    }

    public static void main(String[] args) {
        VistaEstadistiquesPartida dialog = new VistaEstadistiquesPartida();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
