package by.RIP.tile;

import by.RIP.tryer.GamePanel;
import by.RIP.tryer.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    public GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/map.txt");
    }

    public void getTileImage() {

        setup(20, "stone", true,false);
        setup(21, "green", false,false);
        setup(22, "water", true,true);
        setup(23, "tree1", true,false);
        setup(24, "earth", false,false);
        setup(25, "tree", true,false);
        setup(26, "reed", true,false);
        setup(27, "green1", false,false);
        setup(28, "waterLeftUg", true,true);
        setup(29, "waterUp", true,true);
        setup(10, "waterRightUg", true,true);
        setup(11, "waterRight", true,true);
        setup(12, "waterRightDownUg", true,true);
        setup(13, "waterDown", true,true);
        setup(14, "waterLeftDownUg", true,true);
        setup(15, "waterLeft", true,true);
        setup(16, "WleftUg", true,true);
        setup(17, "WRigUg", true,true);
        setup(18, "waterRihtUgUp", true,true);
        setup(19, "waterRightUglmidl", true,true);

    }

    public void setup(int index, String imageName, boolean collision, boolean fishArea) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].fishArea=fishArea;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String file) {
        try {
            InputStream is = getClass().getResourceAsStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
