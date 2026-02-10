package tn.aneti.recherche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.recherche", "tn.aneti.common"})
@EnableDiscoveryClient
public class RechercheServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RechercheServiceApplication.class, args);
    }
}
