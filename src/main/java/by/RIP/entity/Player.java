package by.RIP.entity;

import by.RIP.CPU.CPU;

import by.RIP.tryer.GamePanel;
import by.RIP.tryer.KeyHadler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class Player extends Entity {
    @Autowired
    KeyHadler keyH;
    private long lastTimeCalled = 0;
    private final long callInterval = 7000;
    private final long catchAnimationTime = 3000; // 3 секунды в миллисекундах
    private boolean isCatching = false;
    private long catchStartTime;
    @Autowired
    public CPU cpu;
    public final int screenX;
    public final int screenY;

    public BufferedImage fisher, fisher1, catch1, catch2, tile;
    long lastSoundTime = 0;
    long soundInterval = 3000;
    private long currentTime = System.currentTimeMillis();

    public Player(GamePanel gp, KeyHadler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        setDefaultValues();
        getPlayerImage();
        solidArea = new Rectangle();
        solidArea.x = 122;
        solidArea.y = 156;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 25;
        solidArea.height = 25;
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "stay";

//        player status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        tile = setUp("/player/fisher down333");

        left = setUp("/player/fisher left");
        left1 = setUp("/player/fisher left 1");
        stay = setUp("/player/fisher stay");
        right1 = setUp("/player/fisher right 1");
        right2 = setUp("/player/fisher right 2");
        stayLeft = setUp("/player/fisher stay left");
        up1 = setUp("/player/fisher up1");
        up2 = setUp("/player/fisher up2");
        down = setUp("/player/fisher down");
        down1 = setUp("/player/fisher down1");
        fisher = setUp("/player/fisher prosses");
        fisher1 = setUp("/player/fisher prosses 1");
        catch1 = setUp("/player/fisher catch");
        catch2 = setUp("/player/fisher catch1");


    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.fishPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.fishPressed) {
                direction = "fisher";
            } else {
                direction = "stay";
            }

            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            gp.collisionChecker.checkFishAreaTile(this);

            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);
//            check npc collision
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
//            check event
            gp.eHandler.checkEvent();
            gp.keyH.enterPressed = false;

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
    }

    public void pickUpObject(int i) {
        if (i != 999) {
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }

        }
    }

    public void draw(Graphics2D g2) {
        currentTime = System.currentTimeMillis();
        BufferedImage image = stay;
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
                    image = stayLeft;
                } else if (spriteNum == 2) {
                    image = left1;
                }
                break;
            case "fisher":
                if (currentTime - lastTimeCalled >= callInterval) {
                    if (cpu.catchingCPU()) {
                        cpu.catchFish();
                        isCatching = true;
                        catchStartTime = currentTime;
                    }
                    lastTimeCalled = currentTime;
                }
                if (isCatching) {
                    if (currentTime - lastSoundTime > soundInterval) {
                        gp.playSE(5);
                        lastSoundTime = currentTime;
                    }
                    if (currentTime - catchStartTime < catchAnimationTime) {
                        if (spriteNum == 1) {
                            image = catch1;
                        } else if (spriteNum == 2) {
                            image = catch2;
                        }
                    } else {
                        isCatching = false;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = fisher;
                    } else if (spriteNum == 2) {
                        image = fisher1;
                    }
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
