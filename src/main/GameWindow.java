package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame frame;

    public GameWindow(GamePanel gamePanel) {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
