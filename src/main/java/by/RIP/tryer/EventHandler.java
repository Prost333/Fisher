package by.RIP.tryer;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX;
    int eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect=new Rectangle();
        eventRect.x=23;
        eventRect.y=23;
        eventRect.width=2;
        eventRect.height=2;
        eventRectDefaultX=eventRect.x;
        eventRectDefaultY=eventRect.y;
    }
    public void checkEvent(){
        if (hit(21, 23, "right")){damagePit(gp.dialogueState);}
        if (hit(18,23,"up")){healing(gp.dialogueState);}
        if (hit(25,25,"right")){teleport(gp.dialogueState);}
    }
    public boolean hit(int eventCol, int eventRow,String reqDirection){
        boolean hit=false;

        gp.player.solidArea.x=gp.player.worldX+gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.worldY+gp.player.solidArea.y;
        eventRect.x=eventCol*gp.tileSize+eventRect.x;
        eventRect.y=eventRow*gp.tileSize+eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)){
            if (gp.player.direction.equals(reqDirection)||reqDirection.equals("any")){
                hit=true;
            }
        }

        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY;
        eventRect.x=eventRectDefaultX;
        eventRect.y=eventRectDefaultY;

        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState=gameState;
        gp.ui.currentDialogue="You fall into a pit";
        gp.player.life-=1;

    }
    public void teleport(int gameState){
        gp.gameState=gameState;
        gp.ui.currentDialogue="Teleport!";
        gp.player.worldX=gp.tileSize*34;
        gp.player.worldY=gp.tileSize*10;
    }
    public  void healing (int gameState){
        if (gp.keyH.enterPressed){
            gp.gameState=gameState;
            gp.ui.currentDialogue="you drink the water.\nYour life has been recovered";
            gp.player.life=gp.player.maxLife;
        }

    }
}
