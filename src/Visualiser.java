import javax.swing.*;
import java.awt.*;
public class Visualiser {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Algorithm Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            ControlPanel controlPanel = new ControlPanel();
            AlgorithmPanel algorithmPanel = new AlgorithmPanel(new int[0]);

            frame.add(controlPanel, BorderLayout.NORTH);
            frame.add(algorithmPanel, BorderLayout.CENTER);

            controlPanel.setAlgorithmPanel(algorithmPanel); 

            frame.setVisible(true);
        });
    }
}
