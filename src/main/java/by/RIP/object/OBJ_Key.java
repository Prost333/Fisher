package by.RIP.object;

import by.RIP.tryer.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends  SuperObject{
    GamePanel gp;
    public  OBJ_Key(GamePanel gp){
        this.gp=gp;
        name="Key";
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/key.png")));
            utilityTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
