package src;
import javax.swing.*;
public class App{
    public static void main(String[] args) {
        int tilesSize=32;
        int rows=16;
        int cols=16;
        int bordWidth=tilesSize*cols;
        int bordHeight=tilesSize*rows;
        JFrame frame = new JFrame("Space Inavders");
        frame.setVisible(true);
        frame.setSize(bordWidth, bordHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpaceInvaders spaceInvaders = new SpaceInvaders();
        frame.add(spaceInvaders);
        frame.pack();
        spaceInvaders.requestFocus();
        frame.setVisible(true);
        
    }
}