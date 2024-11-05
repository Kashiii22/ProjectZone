import javax.swing.*;
public class SortingAlgorithms {
    // Bubble Sort Algorithm
    public static void bubbleSort(AlgorithmPanel panel) {
        int[] data = panel.getData();
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data.length - 1 - i; j++) {
                        if (data[j] > data[j + 1]) {
                            int temp = data[j];
                            data[j] = data[j + 1];
                            data[j + 1] = temp;
                            panel.setData(data);
                            Thread.sleep(50);
                        }
                    }
                }
                return null;
            }
        };
        worker.execute();
    }

    // Selection Sort Algorithm
    public static void selectionSort(AlgorithmPanel panel) {
        int[] data = panel.getData();
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < data.length - 1; i++) {
                    int minIndex = i;
                    for (int j = i + 1; j < data.length; j++) {
                        if (data[j] < data[minIndex]) {
                            minIndex = j;
                        }
                    }
                    // Swap the found minimum element with the first element
                    int temp = data[minIndex];
                    data[minIndex] = data[i];
                    data[i] = temp;
                    panel.setData(data);
                    Thread.sleep(50); // Delay for visualization
                }
                return null;
            }
        };
        worker.execute();
    }

    // Insertion Sort Algorithm
    public static void insertionSort(AlgorithmPanel panel) {
        int[] data = panel.getData();
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 1; i < data.length; i++) {
                    int key = data[i];
                    int j = i - 1;
                    while (j >= 0 && data[j] > key) {
                        data[j + 1] = data[j];
                        j = j - 1;
                        panel.setData(data);
                        Thread.sleep(50); // Delay for visualization
                    }
                    data[j + 1] = key;
                    panel.setData(data);
                    Thread.sleep(50); // Additional delay after insertion
                }
                return null;
            }
        };
        worker.execute();
    }
}
