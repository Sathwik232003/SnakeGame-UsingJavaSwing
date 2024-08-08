package snakegame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class win extends JFrame {
    JFrame f;
    win(int k) {
        f = new JFrame();
        JOptionPane.showMessageDialog(f, "Game Over!! Total Points: " + k, "Game Results", JOptionPane.INFORMATION_MESSAGE);
        f.dispose(); 
    }
}
