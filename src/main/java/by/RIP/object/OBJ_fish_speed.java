package by.RIP.object;

import by.RIP.tryer.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_fish_speed extends SuperObject{
    GamePanel gp;
    public  OBJ_fish_speed(GamePanel gp){
        this.gp=gp;
        name="fish_speed";
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/fish1.png")));
            utilityTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
