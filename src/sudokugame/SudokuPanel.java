package sudokugame;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {

    public SudokuPanel(){
        this.setPreferredSize(new Dimension(550,450));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);

        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(Color.BLACK);
        int slotWidth = this.getWidth() / 9;
        int slotHeight = this.getHeight() / 9;

        for (int i = 0; i <= this.getWidth(); i += slotWidth) {
            if ((i / slotWidth) % 3 == 0) {
                g2d.setStroke(new BasicStroke(4));
                g2d.drawLine(i, 0, i, this.getHeight());
            } else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(i, 0, i, this.getHeight());
            }
        }

        for (int i = 0; i <= this.getHeight(); i+= slotHeight) {
            if ((i / slotHeight % 3 == 0)) {
                g2d.setStroke(new BasicStroke(4));
                g2d.drawLine(0, i, this.getWidth(), i);
            } else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, i, this.getWidth(), i);
            }
        }
    }
}
