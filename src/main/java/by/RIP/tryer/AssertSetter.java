package by.RIP.tryer;

import by.RIP.entity.Npc_OlDMan;
import by.RIP.object.OBJ_fish_speed;


public class AssertSetter {
    GamePanel gp;

    public AssertSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject(){

    }
    public  void setNPC(){
        gp.npc[0]=new Npc_OlDMan(gp);
        gp.npc[0].worldX=gp.tileSize*25;
        gp.npc[0].worldY=gp.tileSize*25;


    }
}
