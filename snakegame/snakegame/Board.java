package snakegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    JFrame f = new JFrame();
    
    private Image target;
    private Image tail;
    private Image head;
    
    private final int ALL_DOTS = 1200;
    private final int DOT_SIZE = 10;
    private final int RANDOM_POSITION = 29;
    
    private int xaxis;
    private int yaxis;
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    private boolean inGame = true;
    
    private int dots;
    private int count; 
    private Timer timer;
    
    Board() {
        addKeyListener(new TAdapter());
        
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400, 300));
        setFocusable(true);
        
        loadImages();
        initGame();
    }
    
    public void loadImages() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/target.png"));
        target = i1.getImage();
        
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/tail.png"));
        tail = i2.getImage();
        
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head = i3.getImage();
    }
    
    public void initGame() {
        dots = 3;
        count = 0;
        
        for (int i = 0; i < dots; i++) {
            y[i] = 50;
            x[i] = 50 - i * DOT_SIZE;
        }
        
        locateDots();
        
        timer = new Timer(140, this);
        timer.start();
    }
    
    public void locateDots() {
        int r = (int)(Math.random() * RANDOM_POSITION);
        xaxis = r * DOT_SIZE;
                
        r = (int)(Math.random() * RANDOM_POSITION);
        yaxis = r * DOT_SIZE;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        draw(g);
    }
    
    public void draw(Graphics g) {
        if (inGame) {
            g.drawImage(target, xaxis, yaxis, this);

            for (int i = 0 ; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(tail, x[i], y[i], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameover();
        }
    }


    public void move() {
        for (int i = dots ; i > 0 ; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        
        if (leftDirection) {
            x[0] = x[0] - DOT_SIZE;
        }
        if (rightDirection) {
            x[0] = x[0] + DOT_SIZE;
        }
        if (upDirection) {
            y[0] = y[0] - DOT_SIZE;
        }
        if (downDirection) {
            y[0] = y[0] + DOT_SIZE;
        }
    }
    
    public void checkApple() {
        if ((x[0] == xaxis) && (y[0] == yaxis)) {
            dots++;
            locateDots();
            count++;
        }
    }
    public void gameover(){
        add(new win(count));
    }
    
    public void checkCollision() {
        for(int i = dots; i > 0; i--) {
            if (( i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }
        
        if (y[0] >= 400) {
            inGame = false;
        }
        if (x[0] >= 400) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }
    
    public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_LEFT && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            
            if (key == KeyEvent.VK_RIGHT && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            
            if (key == KeyEvent.VK_UP && (!downDirection)) {
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
            
            if (key == KeyEvent.VK_DOWN && (!upDirection)) {
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
    
}
