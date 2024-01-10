package ar.edu.utn.frc.tup.lciii.scheduled;

import ar.edu.utn.frc.tup.lciii.clients.MicroBRestClient;
import ar.edu.utn.frc.tup.lciii.clients.MicroCRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;
import java.util.Scanner;

@Configuration
@EnableScheduling
public class ScheduleMicroB {

    @Autowired
    private MicroBRestClient microBRestClient;

    @Autowired
    private MicroCRestClient microCRestClient;

    @Scheduled(fixedDelay = 1000)
    public void scheduleActionCallMicroB() {
        try {
            ResponseEntity<String> microResponse = microBRestClient.rps(randomRps());
            System.out.println(microResponse.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleActionCallMicroC() {
        try {
            String microResponse = microCRestClient.rps(randomRps()).getBody();
            System.out.println(microResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String randomRps(){
        String[] opciones = {"piedra", "papel", "tijeras"};
        Random random = new Random();
        return opciones[random.nextInt(opciones.length)];
    }

    public String elejirRps(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elija piedra - papel - tijeras");
        return scanner.nextLine();
    }
}
