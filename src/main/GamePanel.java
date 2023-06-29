package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private int x = 0, y = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 20;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[6][7];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/resources/sqrl_sheet.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);

    }

    public void changeXDelta(int value) {
        this.xDelta += value;
        repaint();
    }

    public void changeYDelta(int value) {
        this.yDelta += value;

    }

    public void setPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;

    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= 7) {
                animationIndex = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        g.drawImage(animations[2][animationIndex], (int) xDelta, (int) yDelta, 128, 128, null);
    }
}
