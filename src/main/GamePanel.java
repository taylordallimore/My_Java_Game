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
import util.Constants;
import util.Constants.posEnum;
import util.Constants.Directions;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 50, yDelta = 50;
    private int x = 0, y = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 20;
    private static posEnum currentAni = posEnum.IDLE1;
    private int playerDirection = -1;
    private boolean moving = false;

    public static int randomInt = 1;

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
        animations = new BufferedImage[7][8];
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

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= Constants.getAniNums(currentAni)) {
                animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            currentAni = posEnum.JUMPING;
        } else {
            //if(randomInt == 1){
            currentAni = posEnum.IDLE1;
            }
            // else{
            //     currentAni = posEnum.IDLE2;
            // }
        //}
    }

    private void updatePosition(){
        if(moving){
            switch(playerDirection){
                case Directions.LEFT:
                xDelta -= 5;
                    break;
                case Directions.RIGHT:
                xDelta += 5;
                    break;
                case Directions.UP:
                yDelta -= 5;
                    break;
                case Directions.DOWN:
                yDelta += 5;
                break;

            }
        }
    }

    public static void setRandomInt(int randomInt) {
        GamePanel.randomInt = randomInt;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePosition();
        try {
            g.drawImage(animations[Constants.getAniLocation(currentAni)][animationIndex], (int) xDelta, (int) yDelta,
                    170, 170, null);
        } catch (Exception e) {
            System.out.println("error on load image");
            System.exit(ABORT);
        }

    }

}
