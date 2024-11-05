import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private AlgorithmPanel algorithmPanel;

    public ControlPanel() {
        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort"};
        JComboBox<String> algorithmSelector = new JComboBox<>(algorithms);
        JButton sortButton = new JButton("Sort");

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] data = generateRandomData(50); // Generate random data
                algorithmPanel.setData(data);
                String selectedAlgorithm = (String) algorithmSelector.getSelectedItem();
                if ("Bubble Sort".equals(selectedAlgorithm)) {
                    SortingAlgorithms.bubbleSort(algorithmPanel);
                } else if ("Selection Sort".equals(selectedAlgorithm)) {
                    SortingAlgorithms.selectionSort(algorithmPanel);
                } else if ("Insertion Sort".equals(selectedAlgorithm)) {
                    SortingAlgorithms.insertionSort(algorithmPanel);
                }
            }
        });

        add(algorithmSelector);
        add(sortButton);
    }

    public void setAlgorithmPanel(AlgorithmPanel algorithmPanel) {
        this.algorithmPanel = algorithmPanel;
    }

    private int[] generateRandomData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int) (Math.random() * 400) + 1;
        }
        return data;
    }
}
