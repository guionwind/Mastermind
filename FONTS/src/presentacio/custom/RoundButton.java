package presentacio.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class RoundButton extends JButton {
    private Color COLOR = Color.GRAY;
    private int CURRENT_COLOR = 0;
    public RoundButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(50, 50));
        setBackground(COLOR);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBackground(COLOR);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(COLOR);
            }
        });
    }

    public int getCurrentColor(){
        return CURRENT_COLOR;
    }

    public void setCurrentColor(Color c, int i){
        COLOR = c;
        CURRENT_COLOR = i;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2.setColor(COLOR);

        // Dibuja el círculo del botón
        g2.fillOval(0, 0, width - 1, height - 1);

        // Dibuja el texto centrado en el círculo
        g2.setColor(getForeground());
        g2.drawString(getText(), (width - g2.getFontMetrics().stringWidth(getText())) / 2,
                (height + g2.getFontMetrics().getAscent()) / 2);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No dibuja el borde del botón
    }
}
