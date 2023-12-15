package by.RIP.CPU;

import by.RIP.entity.Fish;
import by.RIP.service.FishService;
import by.RIP.service.imp.FishServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Random;
@Service
public class CPU {
    @Autowired
    public FishService fishServiceImp;
    public Fish fish;
    Boolean status;
    int catching;
    Random random = new Random();
    Random random1 = new Random();

    public boolean catchingCPU() {
        int p = random1.nextInt(10);
        catching = random.nextInt(10);
        if (catching == p) {
            System.out.println("you catch fish " + "c=" + catching + " p=" + p);
            return true;
        } else {
            System.out.println("you didn't catch anything" + "c=" + catching + " p=" + p);
            return false;
        }
    }

    public Fish catchFish() {
        Long random = random1.nextLong(fishServiceImp.findAll().size());
        Float weightRandom = random1.nextFloat(3);
        Fish fish1 = fishServiceImp.findById(random);
        fish1.setWeight(weightRandom);
        return fish1;
    }
}
