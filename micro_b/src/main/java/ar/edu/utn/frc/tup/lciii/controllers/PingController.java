package ar.edu.utn.frc.tup.lciii.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PingController {

    private Integer playerWins = 0;
    private Integer pcWins = 0;
    private Integer counter = 0;

    @GetMapping("/ping")
    public String pong() {
        return "pong from micro b";
    }

    @GetMapping("/rps/{eleccionJugador}")
    public String rps(@PathVariable String eleccionJugador){
        counter++;
        if(playerWins > 5 && counter < 50) {
            System.out.println("Call N° " + counter + " - Micro B fuera de servicio");
            throw new RuntimeException("Micro B ya no quiere jugar, vuelva mas tarde!");
        }
        System.out.println("Call N° " + counter + " - jugando con Micro B");
        return jugarPiedraPapelTijeras(eleccionJugador);
    }
    public String jugarPiedraPapelTijeras(String eleccionJugador) {
        String[] opciones = {"piedra", "papel", "tijeras"};
        Random random = new Random();
        String eleccionComputadora = opciones[random.nextInt(opciones.length)];

        System.out.println("Jugador eligió: " + eleccionJugador);
        System.out.println("Computadora eligió: " + eleccionComputadora);

        if (eleccionJugador.equals(eleccionComputadora)) {
            System.out.println("Empate!");
            return "Jugaste al rps con Micro B y empataron";
        } else if ((eleccionJugador.equals("piedra") && eleccionComputadora.equals("tijeras")) ||
                (eleccionJugador.equals("papel") && eleccionComputadora.equals("piedra")) ||
                (eleccionJugador.equals("tijeras") && eleccionComputadora.equals("papel"))) {
            System.out.println("¡Jugador gana!");
            playerWins++;
            return "Jugaste al rps con Micro B y ganaste";
        } else {
            System.out.println("¡Computadora gana!");
            pcWins++;
            return "Jugaste al rps con Micro B y perdiste";
        }
    }
}
