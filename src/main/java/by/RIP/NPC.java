package by.RIP;

import by.RIP.entity.Fish;
import by.RIP.service.FishService;
import by.RIP.service.imp.FishServiceImp;
import by.RIP.sound.RecordPlayer;
import by.RIP.tryer.GamePanel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.swing.*;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class NPC {
    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(NPC.class);

        JFrame window = new JFrame();
        RecordPlayer recordPlayer = new RecordPlayer();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.stratGameThread();


    }
}
