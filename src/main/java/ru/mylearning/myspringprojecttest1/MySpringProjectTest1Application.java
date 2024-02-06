package ru.mylearning.myspringprojecttest1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@SpringBootApplication
public class MySpringProjectTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(MySpringProjectTest1Application.class, args);

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long usedBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                System.out.println(usedBytes);
            }
        }, 0, 5000);




    }

}
