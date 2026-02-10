package tn.aneti.evenements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.evenements", "tn.aneti.common"})
@EnableDiscoveryClient
public class EvenementsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvenementsServiceApplication.class, args);
    }
}
