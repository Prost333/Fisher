package by.RIP.entity;

import by.RIP.tryer.GamePanel;
import java.util.Random;

public class Npc_OlDMan extends Entity {
    public Npc_OlDMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogues();
    }

    public void getImage() {

        left = setUp("/npc/oldman_left_1");
        left1 = setUp("/npc/oldman_left_2");
        stay = setUp("/npc/oldman_right_1");
        right1 = setUp("/npc/oldman_right_1");
        right2 = setUp("/npc/oldman_right_2");
        up1 = setUp("/npc/oldman_up_1");
        up2 = setUp("/npc/oldman_up_1");
        down = setUp("/npc/oldman_down_1");
        down1 = setUp("/npc/oldman_down_2");
    }

    public void setDialogues() {
        dialogues[0] = "Hello, strange!";
        dialogues[1] = "So do you know Princess Alina?";
        dialogues[2] = "If you want to see her, catch three fish";
        dialogues[3] = "Bye";
    }

    public void setAction() {
        actionLockCCounter++;
        if (actionLockCCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }

            actionLockCCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }


}
