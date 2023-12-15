package by.RIP.object;

import by.RIP.tryer.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends  SuperObject{
    GamePanel gp;
    public  OBJ_Heart(GamePanel gp){
        this.gp=gp;
        name="Heart";
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/heart_full.png")));
            image2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/heart_half.png")));
            image3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/heart_empty.png")));
            image=utilityTool.scaleImage(image,gp.tileSize,gp.tileSize);
            image2 =utilityTool.scaleImage(image2,gp.tileSize,gp.tileSize);
            image3= utilityTool.scaleImage(image3,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
