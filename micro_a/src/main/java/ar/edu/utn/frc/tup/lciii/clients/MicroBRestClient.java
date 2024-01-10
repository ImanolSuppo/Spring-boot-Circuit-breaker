package ar.edu.utn.frc.tup.lciii.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MicroBRestClient {

    String baseResourceUrl = "http://localhost:8081/rps/";

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<String> rps(String mano) {
        return restTemplate.getForEntity(baseResourceUrl + mano, String.class);
    }

    public ResponseEntity<String> fallback(Exception e){
        return ResponseEntity.status(200).body("API B FUERA DE SERVICIO");
    }
}
