package tn.aneti.offres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.offres", "tn.aneti.common"})
@EnableDiscoveryClient
public class OffresServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OffresServiceApplication.class, args);
    }
}
