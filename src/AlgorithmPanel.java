import javax.swing.*;
import java.awt.*;

public class AlgorithmPanel extends JPanel {
    private int[] data;

    public AlgorithmPanel(int[] data) {
        this.data = data;
    }

    public void setData(int[] data) {
        this.data = data;
        repaint(); // Request the panel to be redrawn
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null) return;

        int width = getWidth() / data.length;
        for (int i = 0; i < data.length; i++) {
            g.setColor(Color.BLUE);
            g.fillRect(i * width, getHeight() - data[i], width, data[i]);
        }
    }

    public int[] getData() {
        return data;
    }
}
