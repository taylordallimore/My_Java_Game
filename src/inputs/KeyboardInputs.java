package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static util.Constants.Directions;
import main.GamePanel;
import util.Constants.Directions;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                gamePanel.setDirection(Directions.LEFT);
                System.out.println("A");
                break;
            case KeyEvent.VK_S:
                gamePanel.setDirection(Directions.DOWN);
                System.out.println("S");
                break;
            case KeyEvent.VK_D:
                gamePanel.setDirection(Directions.RIGHT);
                System.out.println("D");
                break;
            case KeyEvent.VK_W:
                System.out.println("W");
                gamePanel.setDirection(Directions.UP);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GamePanel.setRandomInt((int) Math.floor(Math.random() * (3 - 1) + 1));
        System.out.println(GamePanel.randomInt);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_W:
                gamePanel.setMoving(false);
                break;
        }
    }
}
