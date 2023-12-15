package by.RIP.tryer;

import by.RIP.collision.CollisionChecker;
import by.RIP.entity.Entity;
import by.RIP.entity.Player;
import by.RIP.object.SuperObject;
import by.RIP.sound.RecordPlayer;
import by.RIP.tile.TileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;
    public UI ui = new UI(this);

    public EventHandler eHandler = new EventHandler(this);

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worlHeight = tileSize * maxScreenRow;

    //    game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    int FPS = 60;
    //    System
    public TileManager tileManager = new TileManager(this);
    public KeyHadler keyH = new KeyHadler(this);
    public RecordPlayer recordPlayer = new RecordPlayer();
    public Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssertSetter aSetter = new AssertSetter(this);

    //    Entity
    @Autowired
    public Player player;
    //    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity npc[] = new Entity[10];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();

//        playMusic(0);

        gameState = titleState;
    }

    public void stratGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }
//        title state
        if (gameState == titleState) {
            ui.draw(g2);

        } else {
            //        tile
            tileManager.draw(g2);
//        object
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
//        npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
//        player
            player.draw(g2);

//        ui
            ui.draw(g2);
        }

//        debug
        if (!keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time:" + passed, 10, 400);
            System.out.println("Draw Time:" + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        recordPlayer.setFile(i);
        recordPlayer.playList(i);
    }

    public void stopMusic() {
        recordPlayer.stop();
    }

    public void playSE(int i) {
        recordPlayer.setFile(i);
        recordPlayer.playList(i);
    }

}
