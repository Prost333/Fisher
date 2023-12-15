package by.RIP.entity;

import by.RIP.sound.RecordPlayer;
import by.RIP.tryer.GamePanel;
import by.RIP.tryer.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    public int worldX, worldY;
    public int speed;
    public GamePanel gp;
    public BufferedImage left, left1, right1, right2, stay, stayLeft, up1, up2, down, down1;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean fishArea = false;
    public int actionLockCCounter = 0;
    String dialogues[] = new String[20];
    public int dialogueIndex = 0;
//    character
    public int maxLife;
    public int life;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
//        gp.playSE(0);
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkPlayer(this);
        gp.collisionChecker.checkFishAreaTile(this);
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = stay;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {

                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down;
                    } else if (spriteNum == 2) {
                        image = down1;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = stay;
                    } else if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left;
                    } else if (spriteNum == 2) {
                        image = left1;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public BufferedImage setUp(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imageName + ".png")));
            scaledImage = utilityTool.scaleImage(scaledImage, gp.tileSize * 6, gp.tileSize * 6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

}
