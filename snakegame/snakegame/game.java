package snakegame;

import javax.swing.*;

public class game extends JFrame {
    
    game() {
        super("Snake Game");
        add(new Board());
        pack();
        
        setLocationRelativeTo(null);
        setResizable(false);
        // setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new game().setVisible(true);
    }
}
